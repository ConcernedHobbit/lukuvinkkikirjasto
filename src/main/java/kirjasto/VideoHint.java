package kirjasto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoHint extends Hint {

    private String url;
    private String comment;

    public VideoHint(String header, HintType type, String url, String comment) {
        super(header, type);
        this.url = url;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return super.toString() + "\nURL: " + this.url + "\nComment: " + this.comment;
    }
}
