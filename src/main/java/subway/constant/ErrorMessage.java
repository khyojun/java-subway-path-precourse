package subway.constant;

public enum ErrorMessage {
    PREFIX("[ERROR] "),

    NO_CONNECT_STATION("해당 두 역은 연결되어있지 않습니다."),
    NO_STATION("해당 역이 없습니다."),
    INVALID_INPUT_STATION("유효하지 않은 역을 작성하셨습니다!"),

    SAME_START_DEST("출발역과 도착역이 동일합니다!"),
    INVALID_MENU_INPUT("메인 메뉴 입력이 유효하지 않습니다. 다시 입력해주세요 !"),
    INVALID_FEATURE_INPUT("기능 입력이 유효하지 않습니다. 다시 입력해주세요 !"),

    INVALID_RESULT("유효하지 않은 결과입니다.")
    ;


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
