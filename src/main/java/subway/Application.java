package subway;

import java.util.Scanner;
import subway.controller.SubwayController;
import subway.view.InputView;
import subway.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        SubwayController subwayController = new SubwayController(new InputView(scanner),
            OutputView.getInstance());
        subwayController.trainStart();
    }
}
