/**
 * @author joseph415
 */

package sellerlee.back.article.presentation;

import static sellerlee.back.article.presentation.ArticleController.*;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sellerlee.back.article.application.ArticleCreateRequest;
import sellerlee.back.article.application.ArticleResponse;
import sellerlee.back.article.application.ArticleService;
import sellerlee.back.article.application.ArticleViewService;
import sellerlee.back.article.application.FeedResponse;
import sellerlee.back.article.application.TradeSateUpdateRequest;
import sellerlee.back.article.application.salesHistoryResponse;
import sellerlee.back.member.domain.Member;

@RestController
@RequestMapping(ARTICLE_URI)
public class ArticleController {
    public static final String ARTICLE_URI = "/articles";

    private final ArticleService articleService;
    private final ArticleViewService articleViewService;

    public ArticleController(ArticleService articleService,
            ArticleViewService articleViewService) {
        this.articleService = articleService;
        this.articleViewService = articleViewService;
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody ArticleCreateRequest request) {
        Long articleId = articleService.create(request);
        return ResponseEntity
                .created(URI.create(ARTICLE_URI + "/" + articleId))
                .build();
    }

    @GetMapping
    public ResponseEntity<List<FeedResponse>> showArticlePage(
            @RequestParam Long lastArticleId,
            @RequestParam int size) {
        List<FeedResponse> responses = articleService.showArticlePage(lastArticleId, size);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> showArticle(@PathVariable Long articleId,
            @RequestParam Long memberId) {
        ArticleResponse articleResponse = articleViewService.showArticle(articleId, memberId);

        return ResponseEntity.ok(articleResponse);
    }

    @GetMapping("/trade-state")
    public ResponseEntity<List<salesHistoryResponse>> showSalesDetailsArticle(
            @RequestParam String tradeState) {
        // TODO: 2020/08/12 로그인생기면 없어질 member 
        Member member = new Member(
                51L,
                "turtle@woowabro.com",
                "1234",
                "testNickname",
                "testUri",
                4.0);
        List<salesHistoryResponse> salesHistoryRespons = articleViewService.showSalesDetails(
                member,
                tradeState);

        return ResponseEntity.ok(salesHistoryRespons);
    }

    @PatchMapping("/trade-state")
    public ResponseEntity<salesHistoryResponse> updateTradeState(
            @RequestBody TradeSateUpdateRequest tradeSateUpdateRequest) {
        // TODO: 2020/08/12 로그인생기면 없어질 member
        Member member = new Member(
                51L,
                "turtle@woowabro.com",
                "1234",
                "testNickname",
                "testUri",
                4.0);

        articleService.updateTradeState(member, tradeSateUpdateRequest);

        return ResponseEntity.ok().build();
    }
}
