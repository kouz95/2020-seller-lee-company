/**
 * @author kouz95
 */

package sellerlee.back.article.domain;

import static java.util.Collections.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

@Embeddable
public class Photos {
    @ElementCollection
    @CollectionTable(name = "photo", joinColumns = @JoinColumn(name = "article_id"))
    private List<String> photos;

    protected Photos() {
    }

    public Photos(List<String> photos) {
        this.photos = new ArrayList<>(photos);
    }

    public static Photos of(String... photos) {
        return new Photos(Arrays.asList(photos));
    }

    public List<String> toList() {
        return unmodifiableList(photos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Photos photos1 = (Photos)o;
        return Objects.equals(photos, photos1.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photos);
    }
}
