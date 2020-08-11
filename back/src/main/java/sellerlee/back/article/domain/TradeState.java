/**
 * @author joseph415
 */

package sellerlee.back.article.domain;

public enum TradeState {
    ON_SALE("판매중"),
    RESERVED("예약중"),
    COMPLETED("판매 완료");

    private final String tradeState;

    TradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public static boolean isCompleted(String tradeState) {
        return TradeState.COMPLETED.tradeState.equals(tradeState);
    }

    public String getTradeState() {
        return tradeState;
    }
}
