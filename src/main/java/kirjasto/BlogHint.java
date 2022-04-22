package kirjasto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogHint extends Hint {

    private String author;
    private String url;

    public BlogHint(String header, HintType type, String author, String url) {
        super(header, type);
        this.author = author;
        this.url = url;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAuthor: " + this.author + ", URL: " + this.url;
    }
}
