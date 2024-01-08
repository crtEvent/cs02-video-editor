package cs02.model.dto;

public record Clip(
    String id,
    String title,
    int length
) {

    @Override
    public String toString() {
        return String.format("%s(%s): %2ds", title, id, length);
    }
}
