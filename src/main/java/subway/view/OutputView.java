package subway.view;

public class OutputView {

    private static final String MAIN_MENU = "## 메인 화면\n1. 경로 조회\nQ. 종료";

    private static final String CHOICE_MAIN_MENU = "## 원하는 기능을 선택하세요.";

    private static OutputView instance = new OutputView();



    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public void mainMenu() {
        print(MAIN_MENU);
        printEnter();
        print(CHOICE_MAIN_MENU);
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
}
