package kirjasto;


import database.*;

public class TextUserInterface {

    private IO io;
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
        io.print(("Anna vinkin tyyppi"));
        io.print(("1 = video"));
        io.print(("2 = kirja"));
        HintType type = io.nextInt() == 1 ? HintType.VIDEO : HintType.BOOK;
        io.print(("Anna vinkin otsikko"));
        String name = io.nextLine();
        int id = 0;
        switch (type) {
            case BOOK:
                io.print(("Anna kirjoittaja"));
                String author = io.nextLine();
                io.print(("Anna julkaisija"));
                String publisher = io.nextLine();
                io.print(("Anna julkaisuvuosi"));
                int year = io.nextInt();
                id = db.addBookHint(new BookHint(name, type, author, publisher, year));
                break;
            case VIDEO:
                io.print("Anna url");
                String url = io.nextLine();
                io.print("Anna kommentti");
                String comment = io.nextLine();
                id = db.addVideoHint(new VideoHint(name, type, url, comment));
                break;
            case BLOGPOST:
                break;
            case PODCAST:
                break;
        }
        io.print("Lisää tagit vinkkiin ja erottele ne pilkulla tai jätä tyhjäksi");
        String tags = io.nextLine();
        if (!tags.isEmpty()) db.addTags(id, tags);
        io.print("Lisättiin vinkki nimellä " + name +
                ", Tyyppi: " + type);
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
                break;
            case BLOGPOST:
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

    public boolean exit() {
        return this.endState;
    }

    public void display() {
        io.print("Tervetuloa lukuvinkkikirjastoon! Sallitut komennot: \n" +
                "1) Lisaa vinkki \n" +
                "2) Selaa vinkkeja \n" +
                "3) Poista vinkki \n" +
                "4) Sulje valikko\n" +
                "5) Avaa vinkki (ID)\n" +
                "7) Hae tagilla vinkkei\n" +
                "8) Lisää tagi existing vinkille");

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
            default:
                io.print("Vaara syote");

        }

    }
}


