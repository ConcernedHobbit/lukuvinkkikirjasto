
package kirjasto;

import org.junit.*;

import static org.junit.Assert.*;

public class HintTest {
    VideoHint videoHint;
    BookHint bookHint;

    @Before
    public void setUp() throws Exception {
        videoHint = new VideoHint("Merge sort algorithm", HintType.VIDEO, "https://www.youtube.com/watch?v=TzeBrDU-JaY", "kannattaa katsoa tämä ennen tentii");
        bookHint = new BookHint("Modern VLSI design", HintType.BOOK, "Otava", "Wayne Wolf", 1554);
    }


}
