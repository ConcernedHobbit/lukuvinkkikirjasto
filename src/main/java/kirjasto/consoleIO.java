package kirjasto;

import java.util.Scanner;


class ConsoleIO implements IO {
    private Scanner cInput;

    public ConsoleIO(Scanner instream) {
        cInput = instream;
    }

    public Scanner getcInput() {
        return cInput;
    }

    public int nextInt() {
        return Integer.parseInt(cInput.nextLine());
//        return cInput.nextInt();
    }

    public String nextLine() {
        return cInput.nextLine();
    }

    public void print(String msg) {
        System.out.println(msg);
    }

}
