package subway.constant;

public enum FeatureInputConstant {
    SHORTEST_PATH("1"),
    SHORTEST_TIME("2"),
    BACKWARD("B")
    ;
    private final String in;

    FeatureInputConstant(String in) {
        this.in = in;
    }

    public String getIn() {
        return in;
    }
}
