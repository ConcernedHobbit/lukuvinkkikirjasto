package kirjasto;

import java.util.Scanner;


class ConsoleIO implements IO {
    private Scanner cInput;

    public ConsoleIO() {
        cInput = new Scanner(System.in);
    }

    public Scanner getcInput() {
        return cInput;
    }

    public int nextInt() {
        return cInput.nextInt();
    }

    public void print(String msg) {
        System.out.println(msg);
    }

}
