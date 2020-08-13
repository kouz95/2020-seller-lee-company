/**
 * @author josehp415
 */

package sellerlee.back.article.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sellerlee.back.article.domain.Article;
import sellerlee.back.article.domain.ArticleRepository;
import sellerlee.back.article.domain.TradeState;
import sellerlee.back.member.domain.Member;

@Service
public class ArticleService {
    public static final int FIRST_PAGE = 0;

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long create(ArticleCreateRequest request) {
        Article article = articleRepository.save(request.toArticle());
        return article.getId();
    }

    public List<FeedResponse> showArticlePage(Long lastArticleId, int size) {
        PageRequest pageRequest = PageRequest.of(FIRST_PAGE, size);
        Page<Article> articlePage = articleRepository.findByIdLessThanOrderByIdDesc(lastArticleId,
                pageRequest);
        return FeedResponse.listOf(articlePage.getContent());
    }

    @Transactional
    public void updateTradeState(Member member,
            TradeSateUpdateRequest tradeSateUpdateRequest) {
        Article article = getArticleByAuthorAndTradeState(member, tradeSateUpdateRequest);
        article.updateState(TradeState.fromString(tradeSateUpdateRequest.getTradeState()));
    }

    private Article getArticleByAuthorAndTradeState(Member member,
            TradeSateUpdateRequest tradeSateUpdateRequest) {
        return articleRepository.findByAuthorAndId(member,
                tradeSateUpdateRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("article 존재하지 않습니다."));
    }
}
