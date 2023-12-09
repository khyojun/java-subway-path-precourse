package subway.domain;

import java.util.List;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultWeightedEdge;

public class TravelResult {

    private List<String> route;
    private int shortestPath;
    private int shortestTime;

    public List<String> getRoute() {
        return route;
    }

    public TravelResult(int shortestPath, int shortestTime, List<String> route) {
        this.shortestPath = shortestPath;
        this.shortestTime = shortestTime;
        this.route = route;
    }



    public int getShortestPath() {
        return shortestPath;
    }

    public int getShortestTime() {
        return shortestTime;
    }
}
