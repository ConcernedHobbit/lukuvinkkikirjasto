package kirjasto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookHint extends Hint {

    private String author;
    private String publisher;
    private int year;

    public BookHint(String header, HintType type, String author, String publisher, int year) {
        super(header, type);
        this.author = author;
        this.publisher = publisher;
        this.year = year;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAuthor: " + this.author + "\nPublisher: " + this.publisher + "\nYear: " + this.year;
    }
}
