package cs02.model;

import cs02.model.dto.Clip;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClipGenerator {

    private final List<String> uniqueIds;
    private final int size;
    private final Random random = new Random();

    public ClipGenerator(int size) {
        if (size > 256) {
            throw new IllegalStateException("클립은 최대 256개 까지만 생성 가능합니다.");
        }
        this.size = size;
        this.uniqueIds = new ArrayList<>(size);
    }

    public Clip[] generateClips() {
        return IntStream.range(0, size)
            .mapToObj(i -> new Clip(
                generateUniqueId(),
                String.format("제목%03d", i + 1),
                random.nextInt(1, 16)
            )).toArray(Clip[]::new);
    }

    private String generateUniqueId() {
        if (uniqueIds.size() == size) {
            throw new IllegalStateException("더이상 id를 만들 수 없습니다.");
        }

        String uniqueId;

        do {
            // 숫자 97 ~ 122은 문자 a ~ z의 10진 ASCII 코드
            uniqueId = random.ints(4, 97, 123)
                .mapToObj(i -> (char) i)
                .map(Object::toString)
                .collect(Collectors.joining());
        } while (uniqueIds.contains(uniqueId));

        uniqueIds.add(uniqueId);

        return uniqueId;
    }
}
