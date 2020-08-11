/**
 * @author begaonnuri
 */

package sellerlee.back.article.domain;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sellerlee.back.member.domain.Member;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByIdLessThanOrderByIdDesc(Long lastArticleId, Pageable pageRequest);

    List<Article> findAllByAuthorAndTradeStateNot(Member author, TradeState tradeState);

    List<Article> findAllByAuthorAndTradeState(Member author, TradeState tradeState);
}
