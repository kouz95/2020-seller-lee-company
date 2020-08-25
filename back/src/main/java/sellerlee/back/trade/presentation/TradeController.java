package sellerlee.back.trade.presentation;

import static sellerlee.back.trade.presentation.TradeController.*;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sellerlee.back.article.application.ArticleCardResponse;
import sellerlee.back.member.domain.Member;
import sellerlee.back.security.core.LoginMember;
import sellerlee.back.trade.application.TradeService;

@RequestMapping(ORDER_URI)
@RestController
public class TradeController {
    public static final String ORDER_URI = "/trades";

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    // TODO: 2020/08/25 현재 Trade 사용 하지 않음
    // @GetMapping
    // public ResponseEntity<List<ArticleCardResponse>> showAll(@LoginMember Member loginMember) {
    //     List<ArticleCardResponse> orders = tradeService.showAll(loginMember);
    //
    //     return ResponseEntity
    //             .ok()
    //             .body(orders);
    // }
}
