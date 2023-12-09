package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            .findAny().map(entry -> entry.getValue()).orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 역이 없습니다."));
    }

    public static boolean isStationPresent(String name){
        return subwayInfo.values().stream()
            .flatMap(List::stream) // 각 라인의 역 목록을 평면화
            .anyMatch(station -> station.getName().equals(name));
    }


    // 이후 요구사항 생기면 구현!
//    public static boolean deleteSubwayInfo(Line line, Station station) {
//        return subwayInfo.entrySet().removeIf(entry ->
//            Objects.equals(entry.getKey(), line) && Objects.equals(entry.getValue(), station));
//    }

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
