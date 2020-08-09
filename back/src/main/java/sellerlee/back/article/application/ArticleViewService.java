/**
 * @author joseph415
 */

package sellerlee.back.article.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sellerlee.back.article.domain.Article;
import sellerlee.back.article.domain.ArticleRepository;
import sellerlee.back.article.domain.TradeState;
import sellerlee.back.favorite.domain.Favorite;
import sellerlee.back.favorite.domain.FavoriteRepository;
import sellerlee.back.member.domain.Member;
import sellerlee.back.member.domain.MemberRepository;

@Service
public class ArticleViewService {
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final FavoriteRepository favoriteRepository;

    public ArticleViewService(ArticleRepository articleRepository,
            MemberRepository memberRepository,
            FavoriteRepository favoriteRepository) {
        this.articleRepository = articleRepository;
        this.memberRepository = memberRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Transactional(readOnly = true)
    public ArticleResponse showArticle(Long articleId, Long memberId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Optional<Favorite> favorite =
                favoriteRepository.findFavoriteByArticleAndMember(article, member);

        return ArticleResponse.of(article, favorite.isPresent());
    }

    public List<SalesDetailsResponse> showSalesDetails(Member member, String tradeState) {
        if (TradeState.isCompleted(tradeState)) {
            List<Article> articles = articleRepository.findAllByAuthorAndTradeState(member,
                    TradeState.COMPLETED);

            return getSalesDetailsResponses(articles, 3L);
        }
        List<Article> articles = articleRepository.findAllByAuthorAndTradeStateNot(member,
                TradeState.COMPLETED);

        return getSalesDetailsResponses(articles, 3L);
    }

    private List<SalesDetailsResponse> getSalesDetailsResponses(List<Article> articles,
            Long chatCount) {
        return articles.stream()
                .map(article -> SalesDetailsResponse.of(article,
                        favoriteRepository.countAllByArticle(article), chatCount))
                .collect(Collectors.toList());
    }
}
