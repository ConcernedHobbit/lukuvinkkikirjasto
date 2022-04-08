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
        String author = "";
        String publisher = "";
        int hint_type = 1; // basic
        int year = 0;
        io.print("Vinkin lisäys: ");
        io.print(("Anna vinkin tyyppi"));
        io.print(("1 = nettisivu"));
        io.print(("2 = kirja"));
        hint_type = io.nextInt();
        io.print(("Anna vinkin otsikko"));
//        io.nextLine();
        String name = io.nextLine();
        io.print("Anna vinkin linkki");
        String linkki = io.nextLine();
        if (hint_type == 2) { // book
            io.print(("Anna kirjoittaja"));
            author = io.nextLine();
            io.print(("Anna julkaisija"));
            publisher = io.nextLine();
            io.print(("Anna julkaisuvuosi"));
            year = io.nextInt();
        }
        if (useDB) {
            int id = db.addHint(new Hint(name, linkki, author, publisher, year, hint_type));
            io.print("Lisää tagit vinkkiin ja erottele ne pilkulla tai jätä tyhjäksi");
            String tags = io.nextLine();
            if (!tags.isEmpty()) db.addTags(id, tags);
        }
        io.print("Lisättiin vinkki nimellä " + name +
                ", Otsikko: " + linkki);
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
                "7) Hae tagilla vinkkei\n" +
                "8) Lisää tagi existing vinkille");

        io.print("Syota komento: ");
        int cmd = io.nextInt();


        switch (cmd) {
            case 1:
                io.print("Lisataan vinkki");
//                    if (useDB) {
                this.add();
//                    }
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


