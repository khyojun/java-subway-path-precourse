package subway.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class StationRelation {

    private final WeightedMultigraph<String, DefaultWeightedEdge> graphDistance = new WeightedMultigraph(DefaultWeightedEdge.class);
    private final WeightedMultigraph<String, DefaultWeightedEdge> time = new WeightedMultigraph(DefaultWeightedEdge.class);

    private GraphPath<String, DefaultWeightedEdge> shortestPath;
    private GraphPath<String, DefaultWeightedEdge> shortestTime;

    public StationRelation() {
        madeBetweenStationRelation();
    }

    private void madeBetweenStationRelation() {
        Set<String> noDuplicateName = new HashSet<>(SubwayInfoRepository.findAllStationName());
        List<String> allStationName = new ArrayList<>(noDuplicateName);
        for (String station : allStationName) {
            graphDistance.addVertex(station);
            time.addVertex(station);
        }
        madeSecondInfo();
        madeThirdInfo();
        madeBundangInfo();
    }



    private void madeBundangInfo() {
        graphDistance.setEdgeWeight(graphDistance.addEdge(Station.GANGNAM, Station.YANGZAE), 2);
        time.setEdgeWeight(time.addEdge(Station.GANGNAM, Station.YANGZAE), 8);

        graphDistance.setEdgeWeight(graphDistance.addEdge(Station.YANGZAE, Station.YANGZAE_FOREST), 10);
        time.setEdgeWeight(time.addEdge(Station.YANGZAE, Station.YANGZAE_FOREST), 3);
    }

    private void madeThirdInfo() {
        graphDistance.setEdgeWeight(graphDistance.addEdge(Station.EDUCATION_COLLEGE, Station.SOUTH_TERMINAL), 3);
        time.setEdgeWeight(time.addEdge(Station.EDUCATION_COLLEGE, Station.SOUTH_TERMINAL), 2);

        graphDistance.setEdgeWeight(graphDistance.addEdge(Station.SOUTH_TERMINAL, Station.YANGZAE), 6);
        time.setEdgeWeight(time.addEdge(Station.SOUTH_TERMINAL, Station.YANGZAE), 5);

        graphDistance.setEdgeWeight(graphDistance.addEdge(Station.YANGZAE, Station.MAEBONG), 1);
        time.setEdgeWeight(time.addEdge(Station.YANGZAE, Station.MAEBONG), 1);
    }

    private void madeSecondInfo() {
        graphDistance.setEdgeWeight(graphDistance.addEdge(Station.EDUCATION_COLLEGE, Station.GANGNAM), 2);
        time.setEdgeWeight(time.addEdge(Station.EDUCATION_COLLEGE, Station.GANGNAM), 3);

        graphDistance.setEdgeWeight(graphDistance.addEdge(Station.GANGNAM, Station.YEOKSAM), 2);
        time.setEdgeWeight(time.addEdge(Station.GANGNAM, Station.YEOKSAM), 3);
    }


    public TravelResult shortestDistance(Station start, Station dest) {
        try {
            DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(
                graphDistance);
            shortestPath = dijkstraShortestPath.getPath(
                start.getName(), dest.getName());

            List<String> route = shortestPath.getVertexList();
            int byDistanceTime = calculateRouteByTime(route);
           return new TravelResult((int) shortestPath.getWeight(), byDistanceTime, route);
        }catch (NullPointerException error){
            throw new IllegalArgumentException("[ERROR] 해당 두 역은 연결되어있지 않습니다.");
        }
    }

    private int calculateRouteByTime(List<String> route) {
        int sum=0;
        for(int i=0; i<route.size()-1; i++){
            sum += (int) time.getEdgeWeight(time.getEdge(route.get(i),route.get(i+1)));
        }
        return sum;
    }

    private int calculateRouteByDistance(List<String> route) {
        int sum=0;
        for(int i=0; i<route.size()-1; i++){
            sum += (int) graphDistance.getEdgeWeight(graphDistance.getEdge(route.get(i),route.get(i+1)));
        }
        return sum;
    }

    public TravelResult shortestTime(Station start, Station dest) {
        try {
            DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestTime = new DijkstraShortestPath<>(
                time);
            shortestTime = dijkstraShortestTime.getPath(
                start.getName(), dest.getName());
            List<String> route = shortestTime.getVertexList();
            int byTimeDistance = calculateRouteByDistance(route);
            return new TravelResult(byTimeDistance, (int) shortestTime.getWeight(),
                route);
        }catch (NullPointerException error){
            throw new IllegalArgumentException("[ERROR] 해당 두 역은 연결되어있지 않습니다!");
        }
    }
}
