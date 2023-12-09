package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import subway.constant.ErrorMessage;

public class SubwayInfoRepository {

    private static Map<Line, List<Station>> subwayInfo = new HashMap<>();


    public static Map<Line,List<Station>> subways() {
        return Collections.unmodifiableMap(subwayInfo);
    }

    public static void addSubwayInfo(Line line, Station station) {
        List<Station> byLine = findByLineName(line.getName());
        byLine.add(station);
    }

    public static List<Station> findByLineName(String name){
        return subwayInfo.entrySet().stream()
            .filter(subwayInfo -> subwayInfo.getKey().getName().equals(name))
            .findAny().map(Entry::getValue).orElseThrow(() -> new IllegalArgumentException(
                ErrorMessage.NO_STATION.getMessage()));
    }

    public static boolean isStationPresent(String name){
        return StationRepository.stations().stream()
            .anyMatch(station -> station.getName().equals(name));
    }



    public static void deleteAll() {
        subwayInfo.clear();
    }

    public static void madeInitLine(Line line, ArrayList<Station> stations) {
        subwayInfo.put(line, stations);
    }

    public static List<String> findAllStationName(){
        List<String> allStationName = new ArrayList<>();
        for (Line line : subwayInfo.keySet()) {
            SubwayInfoRepository.findByLineName(line.getName())
                .stream()
                .map(Station::getName)
                .forEach(allStationName::add);
        }
        return allStationName;
    }
}
