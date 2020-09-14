package com.jikgorae.api.favorite.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.jikgorae.api.article.application.ArticleCardResponse;
import com.jikgorae.api.article.application.ArticleViewService;
import com.jikgorae.api.article.domain.Article;
import com.jikgorae.api.favorite.domain.Favorite;
import com.jikgorae.api.favorite.domain.FavoriteRepository;
import com.jikgorae.api.member.domain.Member;

@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final ArticleViewService articleViewService;

    public FavoriteService(FavoriteRepository favoriteRepository,
            ArticleViewService articleViewService) {
        this.favoriteRepository = favoriteRepository;
        this.articleViewService = articleViewService;
    }

    public List<ArticleCardResponse> showFavorites(Member member) {
        return articleViewService.showFavorites(member);
    }

    @Transactional
    public Long create(FavoriteRequest request, Member loginMember) {
        Favorite favorite = new Favorite(new Article(request.getArticleId()), loginMember);
        Favorite saved = favoriteRepository.save(favorite.create());

        return saved.getId();
    }

    @Transactional
    public void remove(FavoriteRequest request, Member loginMember) {
        Favorite favorite = new Favorite(new Article(request.getArticleId()), loginMember);
        favoriteRepository.save(favorite.remove());
        favoriteRepository.deleteByMemberIdAndArticleId(loginMember.getId(),
                request.getArticleId());
    }
}