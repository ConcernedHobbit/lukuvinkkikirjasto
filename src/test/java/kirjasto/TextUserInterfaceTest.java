package kirjasto;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;


class textIO implements IO {
    ArrayList<String> inputs;
    int count;
    ArrayList<String> outputs;
    ArrayList<String> expectedOutputs;

    //    public textIO(int... inputs) {
    public textIO(String[] inputs, String[] outputs) {
        this.inputs = new ArrayList<String>();
        for (String i : inputs) {
            this.inputs.add(i);
        }
        this.outputs = new ArrayList<String>();
        this.expectedOutputs = new ArrayList<String>();
        this.populateMenu();
        for (String o : outputs) {
            expectedOutputs.add(o);
        }
    }

    public void populateMenu() {
        this.expectedOutputs.add("Tervetuloa lukuvinkkikirjastoon! Sallitut komennot: \n" +
                "1) Lisaa vinkki \n" +
                "2) Selaa vinkkeja \n" +
                "3) Poista vinkki \n" +
                "4) Sulje valikko\n" +
                "7) Hae tagilla vinkkei\n" +
                "8) Lisää tagi existing vinkille");
        this.expectedOutputs.add("Syota komento: ");
    }

    public void addCommands(String[] inputs, String[] outputs) {
        for (String i : inputs) {
            this.inputs.add(i);
        }
        for (String o : outputs) {
            this.expectedOutputs.add(o);
        }
    }

    public int nextInt() {
        return Integer.parseInt(inputs.get(count++));
    }

    public String nextLine() {
        return inputs.get(count++);
    }

    public void print(String msg) {
        outputs.add(msg);
    }

}

public class TextUserInterfaceTest {
    void populateAddHint(textIO tIO, String header, String link, String author, String publisher, int year, int hint_type) {
        if (hint_type == 2) {
            String[] inputs = {Integer.toString(hint_type), header, link, author, publisher, Integer.toString(year)};
            String[] outputs = {"Vinkin lisäys: ", "Anna vinkin tyyppi", "1 = nettisivu", "2 = kirja", "Anna vinkin otsikko", "Anna vinkin linkki",
                    "Anna kirjoittaja", "Anna julkaisija", "Anna julkaisuvuosi",
                    ("Lisättiin vinkki nimellä " + header + ", Otsikko: " + link)};
            tIO.addCommands(inputs, outputs);
        } else {
            String[] inputs = {Integer.toString(hint_type), header, link};
            String[] outputs = {"Vinkin lisäys: ", "Anna vinkin tyyppi", "1 = nettisivu", "2 = kirja", "Anna vinkin otsikko", "Anna vinkin linkki",
                    ("Lisättiin vinkki nimellä " + header + ", Otsikko: " + link)};
            tIO.addCommands(inputs, outputs);
        }
    }


    @Test
    public void addMenuOptionOutput() {
        String[] inputs = {"1"};
        String[] outputs = {"Lisataan vinkki"};
        textIO tIO = new textIO(inputs, outputs);
        populateAddHint(tIO, "Merge sort algorithm", "https://www.youtube.com/watch?v=TzeBrDU-JaY", "", "", 0, 1);
        new TextUserInterface(tIO).display();
        testOutputs(tIO);
    }

    @Test
    public void addMenuOptionBook() {
        String[] inputs = {"1"};
        String[] outputs = {"Lisataan vinkki"};
        textIO tIO = new textIO(inputs, outputs);
        populateAddHint(tIO, "Modern VLSI design", "https://www.amazon.com/Modern-VLSI-Design-IP-Based-4th/dp/0137145004", "Wayne Wolf", "Prentice Hall", 2008, 2);
        new TextUserInterface(tIO).display();
        testOutputs(tIO);
    }

    @Test
    public void BrowseMenuOptionOutput() {
        String[] inputs = {"2"};
        String[] outputs = {"Selataan vinkkeja"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO).display();

        //System.out.println(tIO.outputs.get(2));
        assertEquals("Selataan vinkkeja", tIO.outputs.get(2));

    }

    @Test
    public void RemoveMenuOptionOutput() {
        String[] inputs = {"3"};
        String[] outputs = {"Poista vinkki"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO).display();

        assertEquals("Poista vinkki", tIO.outputs.get(2));

    }

    @Test
    public void ExitMenuOptionOutput() {
        String[] inputs = {"4"};
        String[] outputs = {"exit"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO).display();
        testOutputs(tIO);
    }

    @Test
    public void DefaultMenuOptionOutput() {
        String[] inputs = {"101010"};
        String[] outputs = {"Vaara syote"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO).display();
        testOutputs(tIO);
    }

    void testOutputs(textIO tIO) {
        assertEquals(tIO.expectedOutputs.size(), tIO.outputs.size());
        for (int i = 0; i < tIO.outputs.size(); i++) {
            assertEquals(tIO.expectedOutputs.get(i), tIO.outputs.get(i));
        }
    }

}
