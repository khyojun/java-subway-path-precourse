package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class SubwayInitialMaker {

    private static final List<String> secondStation = List.of("교대역", "강남역", "역삼역");
    private static final List<String> thirdStation = List.of("교대역", "남부터미널역", "양재역", "매봉역");
    private static final List<String> bundangStation = List.of("강남역", "양재역", "양재시민의숲역");

    public void madeLine(String notConvertedLine) {
        Line line = new Line(notConvertedLine);
        LineRepository.addLine(line);
        if(line.getName().equals("2호선"))
            madeSecondStation(line);
        if(line.getName().equals("3호선"))
            madeThirdStation(line);
        if(line.getName().equals("신분당선"))
            madeBundangStation(line);
    }

    private void madeBundangStation(Line line) {
        SubwayInfoRepository.madeInitLine(line, new ArrayList<Station>());
        for(String station : bundangStation)
            SubwayInfoRepository.addSubwayInfo(line, new Station(station));
    }

    private void madeThirdStation(Line line) {
        SubwayInfoRepository.madeInitLine(line, new ArrayList<Station>());
        for(String station : thirdStation)
            SubwayInfoRepository.addSubwayInfo(line, new Station(station));
    }

    private void madeSecondStation(Line line) {
        SubwayInfoRepository.madeInitLine(line, new ArrayList<Station>());
        for(String station : secondStation)
            SubwayInfoRepository.addSubwayInfo(line, new Station(station));
    }
}
