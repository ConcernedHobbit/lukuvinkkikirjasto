
package kirjasto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hint {

    private String header;
    private String link;
    private String author;
    private String publisher;
    private int year;
    private int hint_type; // 1 = basic web page, 2 = book

    public String toString() {
        String retVal = ("Otsikko: " + getHeader() + ", Linkki: " + getLink());
        if (this.hint_type == 2) { // Book
            retVal += (", Kirjoittaja: " + getAuthor() + ", Julkaisuvuosi: " + getYear() + ", Julkaisija: " + getPublisher());
        }
        return retVal;
    }
}
