package subway.view;

import java.util.Scanner;
import subway.constant.ErrorMessage;
import subway.constant.FeatureInputConstant;
import subway.constant.MainMenuInputConstant;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String mainChoice() {
        String input = scanner.nextLine();
        if (input.equals(MainMenuInputConstant.START.getIn()) || input.equals(
            MainMenuInputConstant.QUIT.getIn())) {
            return input;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_INPUT.getMessage());
    }

    public String feature() {
        String input = scanner.nextLine();
        if (input.equals(FeatureInputConstant.SHORTEST_PATH.getIn()) || input.equals(
            FeatureInputConstant.SHORTEST_TIME.getIn()) || input.equals(
            FeatureInputConstant.BACKWARD.getIn())) {
            return input;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_FEATURE_INPUT.getMessage());
    }

    public String startStation() {
        return scanner.nextLine();
    }

    public String destStation() {
        return scanner.nextLine();
    }
}
