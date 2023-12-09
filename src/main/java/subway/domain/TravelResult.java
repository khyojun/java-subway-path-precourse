package subway.domain;

import java.util.List;

public record TravelResult(int shortestPath, int shortestTime, List<String> route) {

}
