package sellerlee.back.favorite.acceptance;

import static org.junit.jupiter.api.DynamicTest.*;
import static sellerlee.back.favorite.presentation.FavoriteController.*;

import java.util.stream.Stream;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import sellerlee.back.AcceptanceTest;
import sellerlee.back.favorite.application.FavoriteRequest;
import sellerlee.back.member.application.TokenResponse;

public class FavoriteAcceptanceTest extends AcceptanceTest {
    private TokenResponse token;

    /**
     * Feature: 찜 관리
     * <p>
     * Scenario: 찜을 관리한다.
     * <p>
     * Given 게시글이 생성되어 있다.
     * And 멤버가 생성되어 있다.
     * <p>
     * When 찜 등록을 한다.
     * Then 찜이 추가 된다.
     * <p>
     * When 찜 삭제를 한다.
     * Then 찜이 삭제 된다.
     */
    @DisplayName("찜 관리")
    @TestFactory
    Stream<DynamicTest> manageFavorite() throws JsonProcessingException {
        token = joinMemberAndLogin();
        Long articleId = extractId(createArticle(token));

        return Stream.of(
                dynamicTest("찜 생성", () -> createFavorite(articleId)),
                dynamicTest("찜 삭제", () -> deleteFavorite(articleId))
        );
    }

    private String createFavorite(Long articleId) throws JsonProcessingException {
        FavoriteRequest request = new FavoriteRequest(articleId);

        // @formatter:off
        return
                given()
                        .auth().oauth2(token.getAccessToken())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .body(objectMapper.writeValueAsString(request))
                .when()
                        .post(FAVORITE_URI)
                .then()
                        .log().all()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .header(HttpHeaders.LOCATION);
        // @formatter:on
    }

    private void deleteFavorite(Long articleId) throws JsonProcessingException {
        FavoriteRequest request = new FavoriteRequest(articleId);

        // @formatter:off
        given()
                .auth().oauth2(token.getAccessToken())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(objectMapper.writeValueAsString(request))
        .when()
                .delete(FAVORITE_URI)
        .then()
                .log().all()
                .statusCode(HttpStatus.NO_CONTENT.value());
        // @formatter:on
    }
}
