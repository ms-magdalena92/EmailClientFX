package emailClient.enums;

public enum ThemeColor {
    LIGHT("themeLight"),
    DEFAULT("themeDefault"),
    DARK("themeDark");

    private final String fileName;

    ThemeColor(String fileName) {
        this.fileName = fileName;
    }

    public String getThemeCssPath() {
        return "css/" + fileName + ".css";
    }
}
