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

    public Clip get(int index) {
        if (head == null || index < 0 || index >= size) return null;
        if (index == 0) return head.clip;

        var pointer = 1;
        var currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            if (index == pointer) break;
        }

        return currentNode.clip;
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

    public void insert(int index, Clip clip) {
        if (head == null) {
            size++;
            head = new VideoNode(clip);
            return;
        }

        if (index >= size) {
            add(clip);
            return;
        }

        if (index == 0) {
            size++;
            var tempNode = head;
            head = new VideoNode(clip);
            head.next = tempNode;
            return;
        }

        int pointer = 1;
        var currentNode = head;
        while (head.next != null) {
            if (pointer == index) {
                break;
            }
            pointer++;
            currentNode = currentNode.next;
        }

        if (currentNode.next != null) {
            size++;
            var temp = currentNode.next;
            currentNode.next = new VideoNode(clip);
            currentNode.next.next = temp;
        } else {
            size++;
            currentNode.next = new VideoNode(clip);
        }
    }

    @Override
    public String toString() {

        return Stream
            .iterate(head, Objects::nonNull, node -> node.next)
            .map(node -> String.format("[%s, %ds]", node.clip.id(), node.clip.length()))
            .collect(Collectors.joining("---", "|---", "---[end]"));
    }
}
