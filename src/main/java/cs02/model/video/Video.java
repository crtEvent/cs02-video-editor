package cs02.model.video;

import cs02.model.dto.Clip;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Video {

    VideoNode head;
    private int size;

    public Video() {
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(Clip clip) {
        size++;
        if (head == null) {
            head = new VideoNode(clip);
            return;
        }

        var current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new VideoNode(clip);
    }

    @Override
    public String toString() {

        return Stream
            .iterate(head, Objects::nonNull, node -> node.next)
            .map(node -> String.format("[%s, %ds]", node.clip.id(), node.clip.length()))
            .collect(Collectors.joining("---", "|---", "---[end]"));
    }
}
