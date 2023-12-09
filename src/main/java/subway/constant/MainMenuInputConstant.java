package subway.constant;

public enum MainMenuInputConstant {

    START("1"),

    QUIT("Q")
    ;


    private final String in;

    MainMenuInputConstant(String in) {
        this.in = in;
    }

    public String getIn() {
        return in;
    }
}
