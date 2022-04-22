package kirjasto;


import static org.junit.Assert.*;

import database.HintDaoJdbc;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock.*;
import org.mockito.Mockito;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


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

    public textIO(String[] inputs) {
        this.inputs = new ArrayList<String>();
        for (String i : inputs) {
            this.inputs.add(i);
        }
    }

    public void populateMenu() {
        this.expectedOutputs.add(
                "Tervetuloa lukuvinkkikirjastoon! Sallitut komennot: \n" +
                        "1) Lisaa vinkki \n" +
                        "2) Selaa vinkkeja \n" +
                        "3) Poista vinkki \n" +
                        "4) Sulje valikko\n" +
                        "5) Avaa vinkki (ID)\n" +
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

    /*
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
     */

    HintDaoJdbc mockDao;
    BookHint testBook;
    VideoHint testVideo;
    ArrayList<String> hints;
    String videoTag;


    @Before
    public void setup() {
        mockDao = mock(HintDaoJdbc.class);
        testVideo = new VideoHint("testVideo", HintType.VIDEO,
                "test.com", "cool");
        testBook = new BookHint("testBook", HintType.BOOK, "testAuth",
                "testPub", 2000);
        hints = new ArrayList<>();
        hints.add("test");
        videoTag = "hieno video";

        when(mockDao.getBookHint(1)).thenReturn(testBook);
        when(mockDao.getAllHints()).thenReturn(hints);
        when(mockDao.addBookHint(testBook)).thenReturn(1);
        when(mockDao.addVideoHint(testVideo)).thenReturn(1);
        when(mockDao.getHintType(1)).thenReturn(HintType.VIDEO);
        when(mockDao.getHintType(2)).thenReturn(HintType.BOOK);
        when(mockDao.findTags("nice")).thenReturn(hints);
        //Mockito.doNothing().when(mockDao).removeHint(any());
    }

    @Test
    public void addBookHintOptionOutputWithoutTags() {
        String[] inputs = {"1", "2", "testBook", "testAuth", "testPub", "2000", "0"};
        String[] outputs = {"lisataan vinkki", "Vinkin lisäys: ",
                "Anna vinkin tyyppi", "1 = video", "2 = kirja", "3 = blogpost", "4 = podcast",
                "Anna vinkin otsikko", "Anna kirjoittaja", "Anna julkaisija",
                "Anna julkaisuvuosi", "Haluatko lisata tageja vinkille? 1 = kylla 0 = ei",
                "Lisättiin vinkki nimellä testBook, Tyyppi: KIRJA"
        };
        //"1 = video", "2 = kirja", "lol"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO, mockDao).display();
        testOutputLength(tIO);
        verify(mockDao, times(1)).addBookHint(testBook);
        //testOutputEquality(tIO);
    }

    @Test
    public void addVideoHintOptionOutputWithoutTags() {
        String[] inputs = {"1", "1", "testVideo", "test.com", "cool", "0"};
        String[] outputs = {"lisataan vinkki", "Vinkin lisäys: ",
                "Anna vinkin tyyppi", "1 = video", "2 = kirja", "3 = blogpost", "4 = podcast",
                "Anna vinkin otsikko", "Anna url", "Anna kommentti", "Haluatko lisata tageja vinkille? 1 = kylla 0 = ei",
                "Lisättiin vinkki nimellä testVideo, Tyyppi: VIDEO"};
        //"1 = video", "2 = kirja", "lol"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO, mockDao).display();
        verify(mockDao, times(1)).addVideoHint(testVideo);
        testOutputLength(tIO);
        //testOutputEquality(tIO);
    }

    @Test
    public void addVideoWithTags() {
        String[] inputs = {"1", "1", "testVideo", "test.com", "cool", "1", "hieno video"};
        String[] outputs = {"lisataan vinkki", "Vinkin lisäys: ",
                "Anna vinkin tyyppi", "1 = video", "2 = kirja", "3 = blogpost", "4 = podcast",
                "Anna vinkin otsikko", "Anna url", "Anna kommentti",
                "Haluatko lisata tageja vinkille? 1 = kylla 0 = ei",
                "Lisää tägit vinkkiin ja erottele ne pilkulla tai jätä tyhjäksi",
                "Lisättiin vinkki nimellä testVideo, Tyyppi: VIDEO"};

        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO, mockDao).display();
        testOutputLength(tIO);
        verify(mockDao, times(1)).addTags(1, "hieno video");

    }

    @Test
    public void browseMenuOptionOutput() {
        String[] inputs = {"2"};
        String[] outputs = {"Selataan vinkkeja"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO, mockDao).display();
        verify(mockDao, times(1)).getAllHints();
        //System.out.println(tIO.outputs.get(2));
        assertEquals("Selataan vinkkeja", tIO.outputs.get(2));

    }

    @Test
    public void removeMenuOptionOutput() {
        String[] inputs = {"3", "1"};
        String[] outputs = {"Poista vinkki",
                "Minkä vinkin haluat poistaa? (Syötä id-numero)",
                "Vinkki 1 poistettu"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO, mockDao).display();
        verify(mockDao, times(1)).removeHint(1);
        assertEquals("Poista vinkki", tIO.outputs.get(2));
        testOutputLength(tIO);

    }

    @Test
    public void exitMenuOptionOutput() {
        String[] inputs = {"4"};
        String[] outputs = {"exit"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO, mockDao).display();
        testOutputLength(tIO);
        testOutputEquality(tIO);
    }

    @Test
    public void defaultMenuOptionOutput() {
        String[] inputs = {"101010"};
        String[] outputs = {"Vaara syote"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO).display();
        testOutputLength(tIO);
        testOutputEquality(tIO);
    }

    @Test
    public void openHint() {
        String[] inputs = {"5"};
        String[] outputs = {"Syötä avattavan vinkin ID"};
        textIO tIO = new textIO(inputs, outputs);
        //new TextUserInterface(tIO, mockDao).display();
        //verify(mockDao, times(1)).getHintType(1);
        //testOutputLength(tIO);
        //testOutputEquality(tIO);

    }

    @Test
    public void testTagSearch() {
        String[] inputs = {"7", "nice"};
        String[] outputs = {"Syötä haettava tagi"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO, mockDao).display();

        verify(mockDao, times(1)).findTags("nice");

    }

    @Test
    public void addTagstoExisting() {
        String[] inputs = {"8", "1", "cool"};
        String[] outputs = {"Anna vinkin ID, jolle lisätään tagit",
                "Lisää tägit, useampi tägi erotellaan pilkulla"};
        textIO tIO = new textIO(inputs, outputs);
        new TextUserInterface(tIO, mockDao).display();
        verify(mockDao, times(1)).addTags(1, "cool");

    }

    @Test
    public void nonExistingTagReturnsNothing() {

    }

    public void testOutputLength(textIO tIO) {
        assertEquals(tIO.outputs.size(), tIO.expectedOutputs.size());
    }

    public void testOutputEquality(textIO tIO) {

        boolean iterEqual = false;

        for (int i = 0; i < tIO.outputs.size(); i++) {
            if (tIO.outputs.get(i).equals(tIO.expectedOutputs.get(i))) {
                iterEqual = true;
            } else {
                iterEqual = false;
            }
        }
        assertTrue(iterEqual);
    }
}




