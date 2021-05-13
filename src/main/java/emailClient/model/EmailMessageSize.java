package emailClient.model;

public class EmailMessageSize implements Comparable<EmailMessageSize> {

    private final int size;

    public EmailMessageSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        if (size <= 0) {
            return "0";
        } else if (size < 1024) {
            return size + " B";
        } else if (size < 1048576) {
            return size / 1024 + " kB";
        } else {
            return size / 1048576 + " MB";
        }
    }

    @Override
    public int compareTo(EmailMessageSize o) {
        return Integer.compare(size, o.size);
    }
}
