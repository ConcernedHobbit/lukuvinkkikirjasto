
package kirjasto;

import org.junit.*;
import static org.junit.Assert.*;

public class HintTest {
    Hint hint;
    
    @Before
    public void setUp() throws Exception {
        hint = new Hint("Merge sort algorithm", "https://www.youtube.com/watch?v=TzeBrDU-JaY");
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
    
    
}
