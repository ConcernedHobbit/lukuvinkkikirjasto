package kirjasto;


import database.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextUserInterface {

    private final IO io;
    private HintDaoJdbc db;
    private boolean endState = false;
    private boolean useDB = false;

    public TextUserInterface(IO io, HintDaoJdbc db) {
        this.io = io;
        this.db = db;
        this.useDB = true;
    }

    public TextUserInterface(IO io) {
        this.io = io;
    }

    private void add() {
        if (!useDB) return;
        io.print("Vinkin lisäys: ");
        HintType type = selectType();
        io.print("Anna vinkin otsikko");
        String name = io.nextLine();
        switch (type) {
            case BOOK:
                book(type, name);
                break;
            case VIDEO:
                video(type, name);
                break;
            case BLOGPOST:
                blog(type, name);
                break;
            case PODCAST:
                podcast(type, name);
        }
    }

    private HintType selectType() {
        io.print("Anna vinkin tyyppi");
        io.print("1 = video");
        io.print("2 = kirja");
        io.print("3 = blogpost");
        io.print("4 = podcast");
        int i = io.nextInt();
        return (i == 1 || i == 2) ? i == 1 ? HintType.VIDEO :
                HintType.BOOK : i == 3 ? HintType.BLOGPOST : HintType.PODCAST;
    }

    private void blog(HintType type, String name) {
        int id;
        io.print("Anna kirjoittaja");
        String author = io.nextLine();
        io.print("Anna url");
        String url = io.nextLine();
        id = db.addBlogHint(new BlogHint(name, type, author, url));
        tagOption(id, name, HintType.BLOGPOST);
    }

    private void podcast(HintType type, String name) {
        int id;
        io.print("Anna kirjoittaja");
        String author = io.nextLine();
        io.print("Anna podcastin nimi");
        String publisher = io.nextLine();
        io.print("Anna kuvaus");
        String description = io.nextLine();
        id = db.addPodcastHint(new PodcastHint(name, type, author, publisher, description));
        tagOption(id, name, HintType.PODCAST);
    }

    private void video(HintType type, String name) {
        int id;
        io.print("Anna url");
        String url = io.nextLine();
        io.print("Anna kommentti");
        String comment = io.nextLine();
        id = db.addVideoHint(new VideoHint(name, type, url, comment));
        tagOption(id, name, HintType.VIDEO);
    }

    private void book(HintType type, String name) {
        int id;
        io.print("Anna kirjoittaja");
        String author = io.nextLine();
        io.print("Anna julkaisija");
        String publisher = io.nextLine();
        io.print("Anna julkaisuvuosi");

        int year = io.nextInt();
        id = db.addBookHint(
                new BookHint(name, type, author, publisher, year));
        tagOption(id, name, HintType.BOOK);
    }

    private void tagOption(int id, String name, HintType type) {
        io.print("Haluatko lisata tageja vinkille? 1 = kylla 0 = ei");
        int ans = io.nextInt();
        if (ans == 1) {
            io.print(
                    "Lisää tagit vinkkiin ja erottele ne pilkulla tai jätä tyhjäksi");
            String tags = io.nextLine();
            if (!tags.isEmpty()) db.addTags(id, tags);
        }
        io.print("Lisättiin vinkki nimellä " + name + ", Tyyppi: " + type);
    }


    private void remove() {
        io.print("Minkä vinkin haluat poistaa? (Syötä id-numero)");
        int delHint = io.nextInt();
        db.removeHint(delHint);
        io.print("Vinkki " + delHint + " poistettu");
    }

    private void browse() {
        for (String hint : db.getAllHints()) {
            io.print(hint);
        }
    }

    private void openHint() {
        io.print("Syötä avattavan vinkin ID");
        int id = io.nextInt();
        switch (db.getHintType(id)) {
            case PODCAST:
                System.out.println(db.getPodcastHint(id));
                break;
            case BLOGPOST:
                System.out.println(db.getBlogHint(id));
                break;
            case VIDEO:
                System.out.println(db.getVideoHint(id));
                break;
            case BOOK:
                System.out.println(db.getBookHint(id));
                break;
        }
    }

    private void searchWithTag() {
        io.print("Syötä haettava tagi");
        for (String x : db.findTags(io.nextLine())) {
            io.print(x);
        }
    }

    private void addTagToExisting() {
        io.print("Anna vinkin ID, jolle lisätään tagit");
        int id = io.nextInt();
        io.print("Lisää tägit, useampi tägi erotellaan pilkulla");
        String tags = io.nextLine();
        db.addTags(id, tags);
    }

    private void searchWithHeader() {
        io.print("Anna hakusana");
        for (String x : db.findWithHeader(io.nextLine())) {
            io.print(x);
        }
    }

    private void searchWithType() {
        io.print("Anna hakusana");
        for (String x : db.findWithType(this.selectType())) {
            io.print(x);
        }
    }

    private void exportDatabase() {
        io.print("Anna tiedoston nimi");
        String fileName = io.nextLine();
        try {
            File outFile = new File(fileName);
            if (!outFile.createNewFile()) {
                outFile.delete();
                outFile.createNewFile();
            }
            List<String> allHints = db.getAllHints();
            FileWriter outWriter = new FileWriter(fileName);
            outWriter.write("<HINTS>\n");
            for (int i = 0; i < allHints.size(); i++) {
                String hint = allHints.get(i);
                Integer id = Integer.parseInt(hint.split(" ", 2)[0].substring(3));
                String hintType = hint.split("Tyyppi: ")[1];
                outWriter.write("<HINT><ID>" + id + "</ID>");
                if ("VIDEO".equals(hintType)) {
                    VideoHint videoHint = db.getVideoHint(id);
                    outWriter.write("<VIDEO>");
                    outWriter.write("<HEADER>" + videoHint.getHeader() + "</HEADER>");
                    outWriter.write("<URL>" + videoHint.getUrl() + "</URL>");
                    outWriter.write("<COMMENT>" + videoHint.getComment() + "</COMMENT>");
                    outWriter.write("</VIDEO>");
                } else if ("BOOK".equals(hintType)) {
                    BookHint bookHint = db.getBookHint(id);
                    outWriter.write("<BOOK>");
                    outWriter.write("<HEADER>" + bookHint.getHeader() + "</HEADER>");
                    outWriter.write("<AUTHOR>" + bookHint.getAuthor() + "</AUTHOR>");
                    outWriter.write("<PUBLISHER>" + bookHint.getPublisher() + "</PUBLISHER>");
                    outWriter.write("<YEAR>" + bookHint.getYear() + "</YEAR>");
                    outWriter.write("</BOOK>");
                } else if ("PODCAST".equals(hintType)) {
                    PodcastHint podcastHint = db.getPodcastHint(id);
                    outWriter.write("<PODCAST>");
                    outWriter.write("<HEADER>" + podcastHint.getHeader() + "</HEADER>");
                    outWriter.write("<AUTHOR>" + podcastHint.getAuthor() + "</AUTHOR>");
                    outWriter.write("<NAME>" + podcastHint.getName() + "</NAME>");
                    outWriter.write("<DESCRIPTION>" + podcastHint.getDescription() + "</DESCRIPTION>");
                    outWriter.write("</PODCAST>");
                } else if ("BLOGPOST".equals(hintType)) {
                    BlogHint blogHint = db.getBlogHint(id);
                    outWriter.write("<BLOGPOST>");
                    outWriter.write("<HEADER>" + blogHint.getHeader() + "</HEADER>");
                    outWriter.write("<AUTHOR>" + blogHint.getAuthor() + "</AUTHOR>");
                    outWriter.write("<URL>" + blogHint.getUrl() + "</URL>");
                    outWriter.write("</BLOGPOST>");
                }
                outWriter.write("</HINT>\n");
            }
            outWriter.write("</HINTS>\n");
            outWriter.close();
        } catch (IOException e) {
            io.print("Tiedostoon vienti epäonnistui " + fileName);
        }
    }
    public boolean exit() {
        return this.endState;
    }

    public void display() {
        io.print("Tervetuloa lukuvinkkikirjastoon! Sallitut komennot: \n" +
                "1) Lisaa vinkki \n" +
                "2) Selaa vinkkeja \n" +
                "3) Poista vinkki \n" +
                "4) Lopeta\n" +
                "5) Avaa vinkki (ID)\n" +
                "7) Hae tagilla vinkkei\n" +
                "8) Lisää tagi existing vinkille\n" +
                "9) Otsikkohaku (HEADER)\n" +
                "10) Tyyppihaku (TYPE)\n" +
                "11) Vie tiedostoon (TIEDOSTO)");

        io.print("Syota komento: ");
        int cmd = io.nextInt();

        switch (cmd) {
            case 1:
                io.print("Lisataan vinkki");
                this.add();
                break;
            case 2:
                io.print("Selataan vinkkeja");
                if (useDB) {
                    this.browse();
                }
                break;
            case 3:
                io.print("Poista vinkki");
                if (useDB) {
                    this.remove();
                }
                break;
            case 4:
                io.print("exit");
                this.endState = true;
                this.exit();
                break;
            case 5:
                this.openHint();
                break;
            case 7:
                this.searchWithTag();
                break;
            case 8:
                this.addTagToExisting();
                break;
            case 9:
                this.searchWithHeader();
                break;
            case 10:
                this.searchWithType();
                break;
            case 11:
                this.exportDatabase();
                break;
            default:
                io.print("Vaara syote");
        }
    }
}


