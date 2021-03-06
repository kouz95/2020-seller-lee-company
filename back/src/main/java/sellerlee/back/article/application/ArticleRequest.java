package sellerlee.back.article.application;

import java.util.List;

import sellerlee.back.article.domain.Article;
import sellerlee.back.article.domain.Category;
import sellerlee.back.article.domain.Photos;
import sellerlee.back.article.domain.Tags;
import sellerlee.back.article.domain.TradeState;
import sellerlee.back.member.domain.Member;

public class ArticleRequest {
    private String title;
    private Long price;
    private String category;
    private String contents;
    private List<String> tags;
    private List<String> photos;

    private ArticleRequest() {
    }

    public ArticleRequest(String title, Long price, String category, String contents,
            List<String> tags, List<String> photos) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.contents = contents;
        this.tags = tags;
        this.photos = photos;
    }

    public Article toArticleWithLoginMember(Member loginMember) {
        return new Article(
                title,
                Tags.of(tags),
                Category.fromString(category),
                contents,
                price,
                TradeState.ON_SALE,
                new Photos(photos),
                loginMember);
    }

    public String getTitle() {
        return title;
    }

    public Long getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getContents() {
        return contents;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<String> getPhotos() {
        return photos;
    }
}
