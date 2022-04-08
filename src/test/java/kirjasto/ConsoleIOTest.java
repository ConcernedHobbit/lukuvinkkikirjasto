package kirjasto;


import static org.junit.Assert.*;

import org.junit.Test;


import java.util.Scanner;

public class ConsoleIOTest {

    @Test
    public void testConstructorCreatesScanner() {
        ConsoleIO consoleTestIO = new ConsoleIO(new Scanner(System.in));
        assertNotNull(consoleTestIO.getcInput());
    }

    @Test
    public void testConsoleIoNextInt() {
        ConsoleIO consoleTestIO = new ConsoleIO(new Scanner("5"));
        assertEquals(5, consoleTestIO.nextInt());
    }

}
