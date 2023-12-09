package subway.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SubwayInfoRepository {

    private static Map<Line, Station> subwayInfo = new HashMap<>();


    public static Map<Line,Station> subways() {
        return Collections.unmodifiableMap(subwayInfo);
    }

    public static void addSubwayInfo(Line line, Station station) {
        subwayInfo.put(line, station);
    }

    public static boolean deleteSubwayInfo(Line line, Station station) {

        return subwayInfo.entrySet().removeIf(entry ->
            Objects.equals(entry.getKey(), line) && Objects.equals(entry.getValue(), station));

    }

    public static void deleteAll() {
        subwayInfo.clear();
    }

}
