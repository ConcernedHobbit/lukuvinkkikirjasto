
package kirjasto;

import org.junit.*;

import static org.junit.Assert.*;

public class HintTest {
    VideoHint videoHint;
    BookHint bookHint;

    @Before
    public void setUp() throws Exception {
        videoHint = new VideoHint("Merge sort algorithm", HintType.VIDEO, "https://www.youtube.com/watch?v=TzeBrDU-JaY", "kannattaa katsoa ennen tentii");
        bookHint = new BookHint("Modern VLSI design", HintType.BOOK, "Wayne Bruce", "Otava", 1554);
    }

    @Test
    public void videoHintToString() {
        assertEquals(videoHint.toString(), "Otsikko: Merge sort algorithm, Tyyppi: VIDEO\n" +
                "URL: https://www.youtube.com/watch?v=TzeBrDU-JaY\n" +
                "Comment: kannattaa katsoa ennen tentii");
    }

    @Test
    public void bookHintToString() {
        assertEquals(bookHint.toString(), "Otsikko: Modern VLSI design, Tyyppi: BOOK\n" +
                "Author: Wayne Bruce\n" +
                "Publisher: Otava\n" +
                "Year: 1554");
    }
}
