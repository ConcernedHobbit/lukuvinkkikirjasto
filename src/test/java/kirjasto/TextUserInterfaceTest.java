package kirjasto;

import database.HintDaoJdbc;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Test;

import kirjasto.Main;
import kirjasto.TextUserInterface;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Scanner;



class textIO implements IO {
    int[] inputs;
    int count;
    ArrayList<String> outputs;

    public textIO(int... inputs) {
        this.inputs = inputs;
        this.outputs = new ArrayList<String>();
    }

    public int nextInt() {return inputs[count++];}

    @Override
    public String nextLine() {
        return "haha";
    }

    public void print(String msg) {outputs.add(msg);}

    }

public class TextUserInterfaceTest {





    @Test
    public void addMenuOptionOutput() {
        textIO tIO = new textIO(1);
        new TextUserInterface(tIO).display();

        assertEquals("Lisataan vinkki", tIO.outputs.get(2));

    }

    @Test
    public void BrowseMenuOptionOutput() {
        textIO tIO = new textIO(2);
        new TextUserInterface(tIO).display();


        assertEquals("Selataan vinkkeja", tIO.outputs.get(2));

    }

    @Test
    public void RemoveMenuOptionOutput() {
        textIO tIO = new textIO(3);
        new TextUserInterface(tIO).display();

        assertEquals("Poista vinkki", tIO.outputs.get(2));

    }

    @Test
    public void ExitMenuOptionOutput() {
        textIO tIO = new textIO(4);
        new TextUserInterface(tIO).display();

        assertEquals("exit", tIO.outputs.get(2));

    }

    @Test
    public void DefaultMenuOptionOutput() {
        textIO tIO = new textIO(101010);
        new TextUserInterface(tIO).display();

        assertEquals("Vaara syote", tIO.outputs.get(2));

    }
        

}
