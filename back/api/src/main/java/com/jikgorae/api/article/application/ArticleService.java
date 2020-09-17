package com.jikgorae.api.article.application;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jikgorae.api.article.domain.Article;
import com.jikgorae.api.article.domain.ArticleRepository;
import com.jikgorae.api.article.domain.TradeState;
import com.jikgorae.api.member.domain.Member;
import com.jikgorae.api.security.web.AuthorizationException;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Long create(ArticleRequest request, Member loginMember) {
        Article article = articleRepository.save(request.toArticleWithLoginMember(loginMember));

        return article.getId();
    }

    @Transactional
    public void update(Long id, ArticleRequest request, Member loginMember) {
        Article article = findById(id);
        article.update(request.toArticleWithLoginMember(loginMember));
    }

    public void deleteById(Long id, Member loginMember) {
        Article article = findById(id);
        if (loginMember.isNotSameId(article.getAuthor())) {
            throw new AuthorizationException("삭제할 수 있는 권한이 없습니다.");
        }
        articleRepository.deleteById(id);
    }

    private Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
    }

    @Transactional
    public void updateTradeState(Long id, TradeStateRequest request, Member member) {
        Article article = articleRepository.findByAuthorAndId(member, id)
                .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 article이 존재하지 않습니다."));
        article.updateTradeState(TradeState.valueOf(request.getTradeState()));
    }
}
