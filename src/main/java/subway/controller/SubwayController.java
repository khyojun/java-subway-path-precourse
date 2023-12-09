package subway.controller;

import subway.domain.TravelResult;
import subway.domain.TravelStation;
import subway.domain.StationRelation;
import subway.domain.SubwayInitialMaker;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
    private StationRelation stationRelation;
    public SubwayController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        init();
    }

    private void init() {
        SubwayInitialMaker subwayInitialMaker = new SubwayInitialMaker();
        subwayInitialMaker.madeLine("2호선");
        subwayInitialMaker.madeLine("3호선");
        subwayInitialMaker.madeLine("신분당선");
        stationRelation = new StationRelation();
    }


    public void trainStart() {

        while(isStart()){
            String function = functionList();
            if(isNotBackWard(function)){
                TravelResult travelResult = showResult(function);
                outputView.printResult(travelResult);
            }
        }
    }

    private TravelResult showResult(String function) {
        try {
            TravelStation travelStation = inputSubwayTravel();
            if (function.equals("1"))
                return travelStation.shortestDistance(stationRelation);
            if (function.equals("2"))
                return travelStation.shortestTime(stationRelation);
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
        }catch (IllegalArgumentException error){
            outputView.printError(error.getMessage());
            return showResult(function);
        }
    }

    private TravelStation inputSubwayTravel() {
        try {
            outputView.printStartStation();
            String start = inputView.startStation();
            outputView.printDestStation();
            String dest = inputView.destStation();
            return new TravelStation(start, dest);
        }catch (IllegalArgumentException error){
            outputView.printError(error.getMessage());
            return inputSubwayTravel();
        }
    }

    private static boolean isNotBackWard(String function) {
        return !function.equals("B");
    }

    private String functionList() {
        try {
            outputView.featureList();
            String feature = inputView.feature();
            return feature;
        }catch (IllegalArgumentException error){
            outputView.printError(error.getMessage());
            return functionList();
        }
    }

    private boolean isStart() {
        return !choiceMainMenu().equals("Q");
    }

    private String choiceMainMenu() {
        try {
            outputView.mainMenu();
            String inputMainChoice = inputView.mainChoice();
            return inputMainChoice;
        }catch (IllegalArgumentException error){
            outputView.printError(error.getMessage());
            return choiceMainMenu();
        }
    }
}
