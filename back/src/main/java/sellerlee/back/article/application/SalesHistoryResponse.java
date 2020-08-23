package sellerlee.back.article.application;

import java.time.LocalDateTime;

import sellerlee.back.article.domain.Article;

public class SalesHistoryResponse {
    private Long id;
    private String title;
    private Long price;
    private LocalDateTime createdTime;
    private Long favoriteCount;
    private String thumbnail;
    private String tradeState;

    private SalesHistoryResponse() {
    }

    private SalesHistoryResponse(Long id, String title, Long price, LocalDateTime createdTime,
            Long favoriteCount, String thumbnail, String tradeState) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.createdTime = createdTime;
        this.favoriteCount = favoriteCount;
        this.thumbnail = thumbnail;
        this.tradeState = tradeState;
    }

    public static SalesHistoryResponse of(Article article, Long favoriteCount) {
        return new SalesHistoryResponse(
                article.getId(),
                article.getTitle(),
                article.getPrice(),
                article.getCreatedTime(),
                favoriteCount,
                article.getPhotos().pickThumbnail(),
                article.getTradeState().getTradeState()
        );
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getPrice() {
        return price;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Long getFavoriteCount() {
        return favoriteCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTradeState() {
        return tradeState;
    }
}
