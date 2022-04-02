package kirjasto;

import java.util.ArrayList;

public class TextUserInterface {

    private IO io;
    private ArrayList<Hint> hints;

    public TextUserInterface(IO io) {
        this.io = io;
    }

    private void add() {}
    private void remove() {}
    private void browse() {}
    private void exit() {}

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
                    this.add();
                    break;
                case 2:
                    io.print("Selataan vinkkeja");
                    this.browse();
                    break;
                case 3:
                    io.print("Poista vinkki");
                    this.remove();
                    break;
                case 4:
                    io.print("exit");
                    this.exit();
                default:
                    io.print("Vaara syote");

            }

        }
    }


