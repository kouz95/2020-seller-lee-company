package sellerlee.back.article.acceptance;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;
import static sellerlee.back.article.presentation.ArticleController.*;
import static sellerlee.back.fixture.MemberFixture.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import sellerlee.back.AcceptanceTest;
import sellerlee.back.article.application.ArticleResponse;
import sellerlee.back.article.application.FeedResponse;
import sellerlee.back.article.application.SalesHistoryResponse;
import sellerlee.back.article.application.TradeSateUpdateRequest;

public class ArticleAcceptanceTest extends AcceptanceTest {
    public static final Long LAST_ARTICLE_ID = 4L;
    public static final int ARTICLE_SIZE = 2;

    /**
     * Feature: 게시글 관리
     * <p>
     * Scenario: 게시글을 관리한다.
     * <p>
     * When 게시글을 등록한다.
     * Then 게시글이 추가되었다.
     * <p>
     * When 전체 게시글을 조회한다.
     * Then 게시글이 조회된다.
     * <p>
     * When 게시글을 클릭한다.
     * Then 게시글 정보와 좋아요 를 응답받는다.
     */
    @DisplayName("게시글을 관리한다")
    @TestFactory
    Stream<DynamicTest> manageArticle() throws JsonProcessingException {
        Long articleId = extractId(createArticle());
        Long articleId2 = extractId(createArticle());
        Long articleId3 = extractId(createArticle());
        Long articleId4 = extractId(createArticle());

        return Stream.of(
                dynamicTest("게시글 페이지 조회", () -> {
                    List<FeedResponse> feedArticleResponses = findArticleInFeed(articleId4);
                    assertThat(feedArticleResponses.size()).isEqualTo(ARTICLE_SIZE);
                }),
                dynamicTest("게시글 상세 조회", () -> {
                    ArticleResponse articleResponse = findArticleDetailOf(articleId);
                    assertThat(articleResponse.getId()).isEqualTo(articleId);
                    assertThat(articleResponse.getFavoriteState()).isFalse();
                }),
                dynamicTest("예약중|거래중 게시글 조회", () -> {
                    List<SalesHistoryResponse> salesHistoryResponses = showSalesHistory();
                    assertThat(salesHistoryResponses).hasSize(4);
                }),
                dynamicTest("예약중 으로 tradeState 변경후 조회", () -> {
                    updateTradeState(articleId);
                    ArticleResponse articleResponse = getArticleResponse(articleId);

                    assertThat(articleResponse.getTradeState()).isEqualTo("예약중");
                }),
                dynamicTest("게시글 삭제", () -> {
                    deleteArticle(articleId);
                }));
    }

    private ArticleResponse getArticleResponse(Long id) {
        String url = ARTICLE_URI + "/" + id;

        return given()
                .when()
                .param("memberId", 51L)
                .get(url)
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().jsonPath().getObject(".", ArticleResponse.class);
    }

    private List<FeedResponse> findArticleInFeed(Long articleId) {
        return given()
                .when()
                .param("lastArticleId", articleId)
                .param("size", ARTICLE_SIZE)
                .get(ARTICLE_URI)
                .then()
                .log().all()
                .extract().jsonPath().getList(".", FeedResponse.class);
    }

    private ArticleResponse findArticleDetailOf(Long articleId) {
        String url = ARTICLE_URI + "/" + articleId;

        return given()
                .when()
                .param("memberId", MEMBER1.getId())
                .get(url)
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().jsonPath().getObject(".", ArticleResponse.class);
    }

    private void deleteArticle(Long articleId) {
        String url = ARTICLE_URI + "/" + articleId;

        given()
                .when()
                .delete(url)
                .then()
                .log().all()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    private List<SalesHistoryResponse> showSalesHistory() {
        String url = ARTICLE_URI + "/trade-state";

        return given().when()
                .param("tradeState", "예약중|거래중")
                .get(url)
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().jsonPath().getList(".", SalesHistoryResponse.class);
    }

    private void updateTradeState(Long articleId) {
        String url = ARTICLE_URI + "/trade-state";

        TradeSateUpdateRequest tradeSateUpdateRequest = new TradeSateUpdateRequest(articleId,
                "예약중");

        given().when()
                .body(tradeSateUpdateRequest)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .patch(url)
                .then()
                .log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
