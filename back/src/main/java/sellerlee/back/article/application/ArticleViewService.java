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

@Service
public class ArticleViewService {
    private final ArticleRepository articleRepository;
    private final FavoriteRepository favoriteRepository;

    public ArticleViewService(ArticleRepository articleRepository,
            FavoriteRepository favoriteRepository) {
        this.articleRepository = articleRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @Transactional(readOnly = true)
    public ArticleResponse showArticle(Long articleId, Member member) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        Optional<Favorite> favorite =
                favoriteRepository.findFavoriteByArticleAndMember(article, member);

        long favoriteCount = favoriteRepository.countAllByMember(member);

        return ArticleResponse.of(article, favorite.isPresent(), favoriteCount);
    }

    // TODO: 2020/08/17 chatCount mockData
    public List<SalesHistoryResponse> showSalesDetails(Member member, String tradeState) {
        if (TradeState.isCompleted(tradeState)) {
            List<Article> articles = getTradeCompletedBy(member);

            return getSalesDetailsResponses(articles, 3L);
        }
        List<Article> articles = getTradeNotCompletedBy(member);

        return getSalesDetailsResponses(articles, 3L);
    }

    private List<Article> getTradeNotCompletedBy(Member member) {
        return articleRepository.findAllByAuthorAndTradeStateNot(member,
                TradeState.COMPLETED);
    }

    private List<Article> getTradeCompletedBy(Member member) {
        return articleRepository.findAllByAuthorAndTradeState(member,
                TradeState.COMPLETED);
    }

    private List<SalesHistoryResponse> getSalesDetailsResponses(List<Article> articles,
            Long chatCount) {
        return articles.stream()
                .map(article -> SalesHistoryResponse.of(article,
                        favoriteRepository.countAllByArticle(article), chatCount))
                .collect(Collectors.toList());
    }

    // @Transactional(readOnly = true)
    // public List<ArticleResponse> showByTradeState(String state) {
    //     TradeState tradeState = TradeState.valueOf(state);
    //     List<Article> articles = articleRepository.findByTradeState(tradeState);
    //     return articles.stream()
    //             .map(ArticleResponse::of)
    //             .collect(Collectors.toList());
    // }
}
