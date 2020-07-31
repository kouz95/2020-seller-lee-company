/**
 * @author joseph415
 */

package sellerlee.back.article.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import static sellerlee.back.article.fixture.ArticleFixture.*;
import static sellerlee.back.article.fixture.FavoriteFixture.*;
import static sellerlee.back.article.fixture.MemberFixture.*;

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
        when(articleRepository.findById(1L)).thenReturn(Optional.of(ARTICLE1));
        when(memberRepository.findById(2L)).thenReturn(Optional.of(MEMBER2));
        when(favoriteRepository.findFavoriteByArticleAndMember(any(), any())).thenReturn(
                Optional.of(FAVORITE));

        ArticleResponse articleResponse = articleViewService.showArticle(1L, 2L);

        assertThat(articleResponse.getId()).isEqualTo(1L);
        assertThat(articleResponse.getAuthor().getEmail()).isEqualTo("turtle@woowabro.com");
        assertThat(articleResponse.getFavorite().getId()).isEqualTo(1L);
        assertThat(articleResponse.getFavorite().getMember().getId()).isEqualTo(2L);
        assertThat(articleResponse.getFavorite().getMember().getEmail()).isEqualTo(
                "lxxjn0@gmail.com");
    }
}