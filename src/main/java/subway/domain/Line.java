package subway.domain;

public class Line {

    public static final String SECOND_LINE = "2호선";
    public static final String THIRD_LINE = "3호선";
    public static final String NEW_BUNDANG_LINE = "신분당선";

    private String name;

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
