
package kirjasto;

import org.junit.*;

import static org.junit.Assert.*;

public class HintTest {
    Hint hint;
    Hint hint2;

    @Before
    public void setUp() throws Exception {
        hint = new Hint("Merge sort algorithm", "https://www.youtube.com/watch?v=TzeBrDU-JaY", "", "", 0, 1);
        hint2 = new Hint("Modern VLSI design", "https://www.amazon.com/Modern-VLSI-Design-IP-Based-4th/dp/0137145004", "Wayne Wolf", "Prentice Hall", 2008, 2);
    }

    @Test
    public void getterReturnsHeaderName() {
        assertEquals("Merge sort algorithm", hint.getHeader());
    }

    @Test
    public void getterReturnsLinkName() {
        assertEquals("https://www.youtube.com/watch?v=TzeBrDU-JaY", hint.getLink());
    }

    @Test
    public void setterSetsNewHeader() {
        hint.setHeader("testheader");
        assertEquals("testheader", hint.getHeader());
    }

    @Test
    public void setterSetsNewLink() {
        hint.setLink("testlink");
        assertEquals("testlink", hint.getLink());
    }

    @Test
    public void getterReturnsAuthor() {
        assertEquals("Wayne Wolf", hint2.getAuthor());
    }

    @Test
    public void getterReturnsPublisher() {
        assertEquals("Prentice Hall", hint2.getPublisher());
    }

    @Test
    public void getterReturnsYear() {
        assertEquals(2008, hint2.getYear());
    }

    @Test
    public void getterReturnsHintType() {
        assertEquals(2, hint2.getHint_type());
    }

    @Test
    public void stringPresentationCorrect() {
        assertEquals("Otsikko: Modern VLSI design, Linkki: https://www.amazon.com/Modern-VLSI-Design-IP-Based-4th/dp/0137145004, Kirjoittaja: Wayne Wolf, Julkaisuvuosi: 2008, Julkaisija: Prentice Hall", hint2.toString());
    }
}
