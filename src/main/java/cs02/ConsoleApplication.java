package cs02;

import cs02.controller.ConsoleController;
import cs02.model.ClipGenerator;
import cs02.model.dto.ClipBundle;
import cs02.model.video.Video;
import cs02.view.ConsoleView;

public class ConsoleApplication {

    public static void run() {
        var controller = new ConsoleController(
            new ConsoleView(),
            new Video(),
            new ClipBundle(new ClipGenerator(15))
        );
        controller.execute();
    }

    public static void main(String[] args) {
        run();
    }

}
