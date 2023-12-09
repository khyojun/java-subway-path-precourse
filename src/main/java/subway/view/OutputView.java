package subway.view;

import java.util.List;
import subway.domain.TravelResult;

public class OutputView {

    private static final String MAIN_MENU = "## 메인 화면\n1. 경로 조회\nQ. 종료";

    private static final String FUNCTION_LIST = "## 경로 기준\n1. 최단 거리\n2. 최소 시간\nB. 돌아가기";

    private static final String START_STATION = "## 출발역을 입력하세요.";

    private static final String DEST_STATION = "## 도착역을 입력하세요.";

    private static final String CHOICE_FUNCTION = "## 원하는 기능을 선택하세요.";

    private static final String FIND_RESULT_NOTI = "## 조회 결과";
    private static final String INFO_FORM = "[INFO] ---";

    private static final String INFO_DISTANCE = "[INFO] 총 거리: %dkm";
    private static final String INFO_TIME = "[INFO] 총 소요 시간: %d분";

    private static final String INFO_ROUTE = "[INFO] %s";


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

    public void printStartStation() {
        print(START_STATION);
    }

    public void printDestStation() {
        print(DEST_STATION);
    }

    public void printResult(TravelResult travelResult) {
        print(FIND_RESULT_NOTI);
        print(INFO_FORM);
        print(String.format(INFO_DISTANCE, travelResult.shortestPath()));
        print(String.format(INFO_TIME, travelResult.shortestTime()));
        print(INFO_FORM);
        printRoute(travelResult.route());
        printEnter();
    }

    private void printRoute(List<String> route) {
        for (String station : route) {
            print(String.format(INFO_ROUTE, station));
        }
    }
}
