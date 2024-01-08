package cs02;

import cs02.controller.ConsoleController;

public class ConsoleApplication {

    public static void run() {
        var controller = new ConsoleController();
        controller.execute();
    }

    public static void main(String[] args) {
        run();
    }

}
