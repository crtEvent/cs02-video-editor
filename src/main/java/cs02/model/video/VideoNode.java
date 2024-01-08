package cs02.model.video;

import cs02.model.dto.Clip;

public class VideoNode {

    Clip clip;
    VideoNode next;

    public VideoNode(Clip clip) {
        this.clip = clip;
        this.next = null;
    }
}
