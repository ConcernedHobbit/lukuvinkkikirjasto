package kirjasto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            db.addHint(new Hint(name, linkki, author, publisher, year, hint_type));
        }
        io.print("Lisättiin vinkki nimellä " + name +
                     ", Otsikko: " + linkki   );
    }

    private void remove() {
        io.print("Minkä vinkin haluat poistaa? (Syötä id-numero)");
//        io.nextLine();
        int delHint = (io.nextInt());
        db.removeHint(delHint);
        io.print("Vinkki " + delHint + " poistettu");
    }

    private void browse() {
        for (String hint : db.getAllHints()) {
            System.out.println(hint);
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
                "4) Sulje valikko");

            io.print("Syota komento: ");
            int cmd = io.nextInt();


            switch(cmd) {
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
                default:
                    io.print("Vaara syote");

            }

        }
    }


