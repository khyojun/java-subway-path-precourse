package subway.view;

public class OutputView {

    private static final String MAIN_MENU = "## 메인 화면\n1. 경로 조회\nQ. 종료";

    private static final String FUNCTION_LIST = "## 경로 기준\n1. 최단 거리\n2. 최소 시간\nB. 돌아가기";

    private static final String CHOICE_FUNCTION = "## 원하는 기능을 선택하세요.";

    private static OutputView instance = new OutputView();



    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void mainMenu() {
        print(MAIN_MENU);
        printEnter();
        print(CHOICE_FUNCTION);
    }

    private void printEnter() {
        System.out.println();
    }

    private void print(String message) {
        System.out.println(message);
    }

    public void printError(String message) {
        print(message);
        printEnter();
    }

    public void featureList() {
        print(FUNCTION_LIST);
        printEnter();
        print(CHOICE_FUNCTION);
    }
}
