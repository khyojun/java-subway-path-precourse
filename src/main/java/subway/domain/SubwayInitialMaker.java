package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class SubwayInitialMaker {

    private static final List<String> secondStation = List.of(Station.EDUCATION_COLLEGE,
        Station.GANGNAM, Station.YEOKSAM);
    private static final List<String> thirdStation = List.of(Station.EDUCATION_COLLEGE,
        Station.SOUTH_TERMINAL, Station.YANGZAE, Station.MAEBONG);
    private static final List<String> bundangStation = List.of(Station.GANGNAM, Station.YANGZAE,
        Station.YANGZAE_FOREST);

    public void madeLine(String notConvertedLine) {
        Line line = new Line(notConvertedLine);
        LineRepository.addLine(line);
        if (line.getName().equals(Line.SECOND_LINE)) {
            madeSecondStation(line);
        }
        if (line.getName().equals(Line.THIRD_LINE)) {
            madeThirdStation(line);
        }
        if (line.getName().equals(Line.NEW_BUNDANG_LINE)) {
            madeNewBundangStation(line);
        }
    }

    private void madeNewBundangStation(Line line) {
        SubwayInfoRepository.madeInitLine(line, new ArrayList<>());
        for (String station : bundangStation) {
            SubwayInfoRepository.addSubwayInfo(line, new Station(station));
        }
    }

    private void madeThirdStation(Line line) {
        SubwayInfoRepository.madeInitLine(line, new ArrayList<>());
        for (String station : thirdStation) {
            SubwayInfoRepository.addSubwayInfo(line, new Station(station));
        }
    }

    private void madeSecondStation(Line line) {
        SubwayInfoRepository.madeInitLine(line, new ArrayList<>());
        for (String station : secondStation) {
            SubwayInfoRepository.addSubwayInfo(line, new Station(station));
        }
    }
}
