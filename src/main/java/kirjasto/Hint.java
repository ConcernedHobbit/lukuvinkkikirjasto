
package kirjasto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hint {

    private String header;
    private HintType type;

    public String toString() {
        return "Otsikko: " + this.header + ", Tyyppi: " + this.type;
    }
}
