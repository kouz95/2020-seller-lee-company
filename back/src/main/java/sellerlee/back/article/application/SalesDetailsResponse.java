package sellerlee.back.article.application;

import java.time.LocalDateTime;

import sellerlee.back.article.domain.Article;

public class SalesDetailsResponse {
    private static final int THUMB_NAIL = 0;
    private Long id;
    private String title;
    private Long price;
    private String tradeType;
    private String location;
    private LocalDateTime createdTime;
    private Long favoriteCount;
    private Long chatCount;
    private String thumbnail;
    private String tradeState;

    public SalesDetailsResponse() {
    }

    public SalesDetailsResponse(Long id, String title, Long price, String tradeType,
            String location, LocalDateTime createdTime, Long favoriteCount, Long chatCount,
            String thumbnail, String tradeState) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.tradeType = tradeType;
        this.location = location;
        this.createdTime = createdTime;
        this.favoriteCount = favoriteCount;
        this.chatCount = chatCount;
        this.thumbnail = thumbnail;
        this.tradeState = tradeState;
    }

    public static SalesDetailsResponse of(Article article, Long favoriteCount, Long chatCount) {
        return new SalesDetailsResponse(
                article.getId(),
                article.getTitle(),
                article.getPrice(),
                article.getTradeType().getTradeType(),
                article.getTradeLocation(),
                article.getCreatedTime(),
                favoriteCount,
                chatCount,
                article.getPhotos().getPhotos().get(THUMB_NAIL),
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

    public String getTradeType() {
        return tradeType;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public Long getFavoriteCount() {
        return favoriteCount;
    }

    public Long getChatCount() {
        return chatCount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTradeState() {
        return tradeState;
    }
}
