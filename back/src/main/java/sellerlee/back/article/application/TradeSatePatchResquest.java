package sellerlee.back.article.application;

public class TradeSatePatchResquest {
    private Long id;
    private String tradeState;

    public TradeSatePatchResquest() {
    }

    public TradeSatePatchResquest(Long id, String tradeState) {
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
