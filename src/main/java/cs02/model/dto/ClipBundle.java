package cs02.model.dto;

import cs02.model.ClipGenerator;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public record ClipBundle(
    Clip[] clips
) {

    public ClipBundle(ClipGenerator clipGenerator) {
        this(clipGenerator.generateClips());
    }

    public Clip findById(String id) {
        return Arrays.stream(clips)
            .filter(clip -> Objects.equals(clip.id(), id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("'" +id + "'는 존재하지 않는 Id 입니다."));
    }

    @Override
    public String toString() {

        return Arrays.stream(clips)
            .map(Clip::toString)
            .collect(() -> new StringJoiner(System.lineSeparator()), StringJoiner::add,
                StringJoiner::merge)
            .toString();
    }
}
