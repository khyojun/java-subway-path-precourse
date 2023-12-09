package subway.controller;

import subway.constant.ErrorMessage;
import subway.constant.FeatureInputConstant;
import subway.constant.MainMenuInputConstant;
import subway.domain.Line;
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
        subwayInitialMaker.madeLine(Line.SECOND_LINE);
        subwayInitialMaker.madeLine(Line.THIRD_LINE);
        subwayInitialMaker.madeLine(Line.NEW_BUNDANG_LINE);
        stationRelation = new StationRelation();
    }


    public void trainStart() {
        while (isStart()) {
            TravelResult travelResult = travelProcess();
            outputView.printResult(travelResult);
        }
    }



    private TravelResult travelProcess() {
        try {
            String feature = featureInput();
            if (isNotBackWard(feature)) {
                return calculateTravelResult(feature);
            }
            throw new IllegalArgumentException(ErrorMessage.INVALID_RESULT.getMessage());
        } catch (IllegalArgumentException error) {
            outputView.printError(error.getMessage());
            return travelProcess();
        }
    }

    private TravelResult calculateTravelResult(String feature) {
        TravelStation travelStation = inputSubwayTravel();
        if (feature.equals(FeatureInputConstant.SHORTEST_PATH.getIn()))
            return travelStation.shortestDistance(stationRelation);
        if (feature.equals(FeatureInputConstant.SHORTEST_TIME.getIn()))
            return travelStation.shortestTime(stationRelation);
        throw new IllegalArgumentException(ErrorMessage.INVALID_RESULT.getMessage());
    }

    private TravelStation inputSubwayTravel() {
        try {
            outputView.printStartStation();
            String start = inputView.startStation();
            outputView.printDestStation();
            String dest = inputView.destStation();
            return new TravelStation(start, dest);
        } catch (IllegalArgumentException error) {
            throw new IllegalArgumentException(error.getMessage());
        }
    }

    private static boolean isNotBackWard(String function) {
        return !function.equals(FeatureInputConstant.BACKWARD.getIn());
    }

    private String featureInput() {
        try {
            outputView.featureList();
            String feature = inputView.feature();
            return feature;
        } catch (IllegalArgumentException error) {
            outputView.printError(error.getMessage());
            return featureInput();
        }
    }

    private boolean isStart() {
        return !choiceMainMenu().equals(MainMenuInputConstant.QUIT.getIn());
    }

    private String choiceMainMenu() {
        try {
            outputView.mainMenu();
            return inputView.mainChoice();
        } catch (IllegalArgumentException error) {
            outputView.printError(error.getMessage());
            return choiceMainMenu();
        }
    }
}
