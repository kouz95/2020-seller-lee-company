/**
 * @author joseph415
 */

package sellerlee.back.article.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static sellerlee.back.fixture.ArticleFixture.*;
import static sellerlee.back.fixture.FavoriteFixture.*;
import static sellerlee.back.fixture.MemberFixture.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import sellerlee.back.article.domain.ArticleRepository;
import sellerlee.back.favorite.domain.FavoriteRepository;
import sellerlee.back.member.domain.MemberRepository;

@ExtendWith(value = MockitoExtension.class)
class ArticleViewServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @Mock
    private FavoriteRepository favoriteRepository;

    @Mock
    private ArticleRepository articleRepository;

    private ArticleViewService articleViewService;

    @BeforeEach
    void setUp() {
        articleViewService = new ArticleViewService(articleRepository, memberRepository,
                favoriteRepository);
    }

    @DisplayName("Article 와 Member 를 가져온 후 객체들를 이용해 Favorite 객체를 가져온다")
    @Test
    void showArticle() {
        when(articleRepository.findById(51L)).thenReturn(Optional.of(ARTICLE1));
        when(memberRepository.findById(52L)).thenReturn(Optional.of(MEMBER2));
        when(favoriteRepository.findFavoriteByArticleAndMember(any(), any())).thenReturn(
                Optional.of(FAVORITE));

        ArticleResponse articleResponse = articleViewService.showArticle(51L, 52L);

        assertThat(articleResponse.getId()).isEqualTo(51L);
    }

    @DisplayName("Member 의 article 을 tradeState 에 따라 다르게 가져온다 - 판매 완료 일 경우")
    @Test
    void showSalesDetailsInCaseCompleted() {
        when(articleRepository.findAllByAuthorAndTradeState(any(), any())).thenReturn(
                Collections.singletonList(
                        ARTICLE4
                ));

        List<salesHistoryResponse> salesHistoryRespons = articleViewService.showSalesDetails(
                MEMBER1, "판매 완료");

        assertThat(salesHistoryRespons).hasSize(1);
    }

    @DisplayName("Member 의 article 을 tradeState 에 따라 다르게 가져온다 - 판매 완료 일 경우")
    @Test
    void showSalesDetailsInCaseNotCompleted() {
        when(articleRepository.findAllByAuthorAndTradeStateNot(any(), any())).thenReturn(
                Arrays.asList(
                        ARTICLE1, ARTICLE2, ARTICLE3
                ));

        List<salesHistoryResponse> salesHistoryRespons = articleViewService.showSalesDetails(
                MEMBER1, "예약중|판매중");

        assertThat(salesHistoryRespons).hasSize(3);
    }
}