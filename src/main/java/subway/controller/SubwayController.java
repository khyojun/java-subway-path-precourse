package subway.controller;

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
            while(!functionList().equals("B")){

            }
        }


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
