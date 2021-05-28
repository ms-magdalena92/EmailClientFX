package emailClient.enums;

public enum FontSize {
    SMALL("fontSmall"),
    MEDIUM("fontMedium"),
    BIG("fontBig");

    private final String fileName;

    FontSize(String fileName) {
        this.fileName = fileName;
    }

    public String getFontSizePath() {
        return "css/" + fileName + ".css";
    }
}
