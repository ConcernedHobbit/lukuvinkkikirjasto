
package kirjasto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hint {

    private String header;
    private String link;

    public String toString() {
        return ("Otsikko: " + getHeader() + ", Linkki: " + getLink());
    }
}
