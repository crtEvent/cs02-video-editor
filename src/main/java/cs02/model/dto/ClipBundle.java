package cs02.model.dto;

import java.util.Arrays;
import java.util.StringJoiner;

public record ClipBundle(
    Clip[] clips,
    int size
) {

    @Override
    public String toString() {

        return Arrays.stream(clips)
            .map(Clip::toString)
            .collect(() -> new StringJoiner(System.lineSeparator()), StringJoiner::add,
                StringJoiner::merge)
            .toString();
    }
}
