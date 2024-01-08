package cs02.model.video;

import static org.junit.jupiter.api.Assertions.*;

import cs02.model.ClipGenerator;
import cs02.model.dto.Clip;
import cs02.model.dto.ClipBundle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class VideoTest {

    Video video;
    ClipGenerator clipGenerator;
    ClipBundle clipBundle;

    @BeforeEach
    void init() {
        video = new Video();
        clipGenerator = new ClipGenerator(15);
        clipBundle = clipGenerator.generateClips();
    }

    @Nested
    @DisplayName("add 기능 테스트")
    class add {
        @Test
        @DisplayName("빈 비디오 클립 목록에 새 클립을 추가할 수 있다.")
        void add_success_add_to_empty() {
            var clip1 = clipBundle.clips()[0];

            video.add(clip1);

            assertEquals(1, video.getSize());
        }

        @Test
        @DisplayName("비디오 클립 목록의 맨 뒤에 새 클립을 추가할 수 있다.")
        void add_success() {
            var clip1 = clipBundle.clips()[0];
            var clip2 = clipBundle.clips()[1];

            video.add(clip1);
            video.add(clip2);

            assertEquals(2, video.getSize());
        }
    }

}