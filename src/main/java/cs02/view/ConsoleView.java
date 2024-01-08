package cs02.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class ConsoleView {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static final String INPUT_COMMAND_MESSAGE = "명령어를 입력해 주세요. (help: 도움말)";
    private static final Function<Exception, String> ERROR_MESSAGE = (Exception e)
        -> String.format("[ERROR] %s", e.getMessage());

    public String inputCommand() throws IOException {
        System.out.println(INPUT_COMMAND_MESSAGE);

        return reader.readLine();
    }

    public void printResult(String result) {
        System.out.println(result + System.lineSeparator());
    }

    public void printErrorMessage(Exception e) {
        System.out.println(ERROR_MESSAGE.apply(e) + System.lineSeparator());
    }
}
