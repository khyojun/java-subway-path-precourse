package subway.view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String mainChoice() {
        String input = scanner.nextLine();
        if(input.equals("1") || input.equals("Q"))
            return input;
        throw new IllegalArgumentException("[ERROR] 메인 메뉴 입력이 유효하지 않습니다. 다시 입력해주세요 !");
    }

    public String feature() {
        String input = scanner.nextLine();
        if(input.equals("1") || input.equals("2") || input.equals("B"))
            return input;
        throw new IllegalArgumentException("[ERROR] 기능 입력이 유효하지 않습니다. 다시 입력해주세요 !");
    }
}
