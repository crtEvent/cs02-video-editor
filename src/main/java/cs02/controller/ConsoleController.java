package cs02.controller;

import cs02.model.ClipGenerator;
import cs02.model.dto.Clip;
import cs02.model.dto.ClipBundle;
import cs02.model.video.Video;
import cs02.view.ConsoleView;
import java.util.Objects;
import java.util.function.Function;

public class ConsoleController {

    private final ClipGenerator clipGenerator;
    private final ConsoleView view;
    private final Video video;
    private ClipBundle clipBundle;

    private static final Function<String, String> COMMAND_ERROR_MESSAGE = (String command)
        -> String.format("'%s'는 잘못된 명령어 입니다.", command);
    private static final String HELP_MESSAGE = "[도움말]" + System.lineSeparator()
        + " - clips: 클립 리스트 출력" + System.lineSeparator()
        + " - add <id>: 비디오 리스트 맨 뒤에 클립 추가" + System.lineSeparator()
        + " - insert <id> <index>: <index> 위치에 클립 추가" + System.lineSeparator()
        + " - delete <id> : <id>에 해당하는 클립 제거" + System.lineSeparator()
        + " - render: 비디오의 클립 개수와 총 길이를 보여준다" + System.lineSeparator()
        + " - exit: 프로그램 종료";

    public ConsoleController() {
        this.clipGenerator = new ClipGenerator(15);
        this.view = new ConsoleView();
        this.video = new Video();
    }

    public void execute() {
        clipBundle = clipGenerator.generateClips();
        view.printResult(
            String.format("---영상클립 생성---%n%s", clipBundle.toString())
        );

        while (true) {
            try {
                String command = view.inputCommand();
                String result = generateResult(command);
                if (Objects.equals(Commands.EXIT, result)) break;
                view.printResult(result);
            } catch (Exception e) {
                view.printErrorMessage(e);
            }
        }
    }

    private String generateResult(String command) {
        var commandComponents = command.split(" ");

        if (commandComponents.length < 1) {
            throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(command));
        }

        return switch (commandComponents[0]) {
            case Commands.CLIPS -> {
                if (commandComponents.length != 1) {
                    throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(command));
                }
                yield clipBundle.toString();
            }
            case Commands.ADD -> {
                if (commandComponents.length != 2) {
                    throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(command));
                }
                Clip clip = clipBundle.findById(commandComponents[1]);
                video.add(clip);

                yield video.toString();
            }
            case Commands.INSERT -> {
                if (commandComponents.length != 3) {
                    throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(command));
                }
                try {
                    Clip clip = clipBundle.findById(commandComponents[1]);
                    int index = Integer.parseInt(commandComponents[2]);
                    video.insert(index, clip);

                    yield video.toString();
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("index는 정수만 입력 가능합니다.");
                }
            }
            case Commands.DELETE -> {
                if (commandComponents.length != 2) {
                    throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(command));
                }
                video.delete(commandComponents[1]);

                yield video.toString();
            }
            case Commands.RENDER -> {
                if (commandComponents.length != 1) {
                    throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(command));
                }
                yield video.render() + System.lineSeparator() + video.toString();
            }
            case Commands.HELP -> {
                if (commandComponents.length != 1) {
                    throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(command));
                }
                yield HELP_MESSAGE;
            }
            case Commands.EXIT -> {
                if (commandComponents.length != 1) {
                    throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(command));
                }
                yield Commands.EXIT;
            }
            default -> throw new IllegalArgumentException(COMMAND_ERROR_MESSAGE.apply(command));
        };
    }
}
