package kirjasto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PodcastHint extends Hint {

    private String author;
    private String name;
    private String description;

    public PodcastHint(String header, HintType type, String author, String name, String description) {
        super(header, type);
        this.author = author;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAuthor: " + this.author + "\nPodcast name: " +
                this.name + "\nDescription: " + this.description;
    }
}
