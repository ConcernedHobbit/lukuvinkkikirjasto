package kirjasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;

import kirjasto.Main;
import kirjasto.TextUserInterface;
import org.w3c.dom.Text;

import java.util.ArrayList;
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