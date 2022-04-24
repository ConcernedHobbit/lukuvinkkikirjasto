package kirjasto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Hint {

    @NonNull
    private String header;
    @NonNull
    private HintType type;
    private String tags;

    public String toString() {
        String returnable = "Otsikko: " + this.header + ", Tyyppi: " + this.type;
        if (this.tags != null) returnable += "\nTagit: " + this.tags;
        return returnable;
    }
}
