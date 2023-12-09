package subway.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.domain.SubwayInfoRepository;
import subway.domain.SubwayInitialMaker;
import subway.view.InputView;
import subway.view.OutputView;

public class SubwayController {

    private final InputView inputView;
    private final OutputView outputView;
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
    }


    public void trainStart() {
//        List<Station> byLineName = SubwayInfoRepository.findByLineName("신분당선");
//        for (Station station : byLineName) {
//            System.out.println(station.getName());
//        }
    }
}
