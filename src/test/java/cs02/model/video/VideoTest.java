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
    class get {
        @Test
        @DisplayName("비디오 클립 목록에서 원하는 위치에 있는 클립을 찾을 수 있다.")
        void success_get() {
            var clip1 = clipBundle.clips()[0];
            var clip2 = clipBundle.clips()[1];
            var clip3 = clipBundle.clips()[2];
            var clip4 = clipBundle.clips()[3];
            var clip5 = clipBundle.clips()[4];

            video.add(clip1);
            video.add(clip2);
            video.add(clip3);
            video.add(clip4);
            video.add(clip5);

            var actualClip1 = video.get(0);
            var actualClip2 = video.get(1);
            var actualClip3 = video.get(2);
            var actualClip4 = video.get(3);
            var actualClip5 = video.get(4);

            assertAll(
                () -> assertEquals(clip1.id(), actualClip1.id()),
                () -> assertEquals(clip2.id(), actualClip2.id()),
                () -> assertEquals(clip3.id(), actualClip3.id()),
                () -> assertEquals(clip4.id(), actualClip4.id()),
                () -> assertEquals(clip5.id(), actualClip5.id())
            );
        }

        @Test
        @DisplayName("비디오 클립 목록에 존재하지 않는 클립을 찾으면 null을 반환한다.")
        void fail_get() {
            var clip1 = clipBundle.clips()[0];

            video.add(clip1);

            assertAll(
                () -> assertNull(video.get(1)),
                () -> assertNull(video.get(-1)),
                () -> assertNull(video.get(100))
            );
        }
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

    @Nested
    @DisplayName("insert 기능 테스트")
    class Insert {
        @Test
        @DisplayName("비디오 클립 목록의 원하는 위치에 클립을 삽입할 수 있다.")
        void success_insert() {
            var clip1 = clipBundle.clips()[0];
            var clip2 = clipBundle.clips()[1];
            var clip3 = clipBundle.clips()[2];

            video.insert(0, clip1);
            video.insert(0, clip2);
            video.insert(1, clip3);

            var actualClip1 = video.get(0);
            var actualClip2 = video.get(1);
            var actualClip3 = video.get(2);

            assertAll(
                () -> assertEquals(clip2.id(), actualClip1.id()),
                () -> assertEquals(clip3.id(), actualClip2.id()),
                () -> assertEquals(clip1.id(), actualClip3.id())
            );
        }

        @Test
        @DisplayName("비디오 클립 목록의 크기를 넘는 위치에 클립을 삽입할 경우 클립을 목록의 맨 뒤에 추가한다.")
        void success_insert_to_over_index() {
            var clip1 = clipBundle.clips()[0];
            var clip2 = clipBundle.clips()[1];

            video.insert(0, clip1);
            video.insert(999, clip2);

            var actualClip1 = video.get(1);

            assertEquals(clip2.id(), actualClip1.id());
        }
    }

    @Nested
    @DisplayName("delete 기능 테스트")
    class Delete {

        @Test
        @DisplayName("비디오 클립 목록에서 입력한 아이디에 해당하는 클립을 제거할 수 있다.")
        void success_delete() {
            var clip1 = clipBundle.clips()[0];
            var clip2 = clipBundle.clips()[1];
            var clip3 = clipBundle.clips()[2];
            var clip4 = clipBundle.clips()[3];
            var clip5 = clipBundle.clips()[4];

            video.add(clip1);
            video.add(clip2);
            video.add(clip3);
            video.add(clip4);
            video.add(clip5);

            assertAll(
                () -> {
                    video.delete(clip3.id());
                    assertEquals(clip4.id(), video.get(2).id());
                },
                () -> {
                    video.delete(clip5.id());
                    assertNull(video.get(4));
                },
                () -> {
                    video.delete(clip1.id());
                    assertEquals(clip2.id(), video.get(0).id());
                }
            );
        }
    }
}