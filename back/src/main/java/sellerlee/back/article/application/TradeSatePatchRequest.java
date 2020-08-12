package sellerlee.back.article.application;

public class TradeSatePatchRequest {
    private Long id;
    private String tradeState;

    private TradeSatePatchRequest() {
    }

    public TradeSatePatchRequest(Long id, String tradeState) {
        this.id = id;
        this.tradeState = tradeState;
    }

    public Long getId() {
        return id;
    }

    public String getTradeState() {
        return tradeState;
    }
}
