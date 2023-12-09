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
//
//    1. 지하철역으로 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역이 등록되어 있다.
//        2. 지하철 노선으로 2호선, 3호선, 신분당선이 등록되어 있다.
// 3. 노선에 역이 아래와 같이 등록되어 있다.(왼쪽 끝이 상행 종점)
//    - 2호선: 교대역 - ( 2km / 3분 ) - 강남역 - ( 2km / 3분 ) - 역삼역
//   - 3호선: 교대역 - ( 3km / 2분 ) - 남부터미널역 - ( 6km / 5분 ) - 양재역 - ( 1km / 1분 ) - 매봉역
//   - 신분당선: 강남역 - ( 2km / 8분 ) - 양재역 - ( 10km / 3분 ) - 양재시민의숲역

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

//        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graphDistance);
//        GraphPath<String, DefaultWeightedEdge> shortestPath = dijkstraShortestPath.getPath("교대역", "역삼역");
//
//        System.out.println("shortestPath.getWeight() = " + shortestPath.getWeight());
    }



    private void madeBundangInfo() {
        graphDistance.setEdgeWeight(graphDistance.addEdge("강남역", "양재역"), 2);
        time.setEdgeWeight(time.addEdge("강남역", "양재역"), 8);

        graphDistance.setEdgeWeight(graphDistance.addEdge("양재역", "양재시민의숲역"), 10);
        time.setEdgeWeight(time.addEdge("양재역", "양재시민의숲역"), 3);
    }

    private void madeThirdInfo() {
        graphDistance.setEdgeWeight(graphDistance.addEdge("교대역", "남부터미널역"), 3);
        time.setEdgeWeight(time.addEdge("교대역", "남부터미널역"), 2);

        graphDistance.setEdgeWeight(graphDistance.addEdge("남부터미널역", "양재역"), 6);
        time.setEdgeWeight(time.addEdge("남부터미널역", "양재역"), 5);

        graphDistance.setEdgeWeight(graphDistance.addEdge("양재역", "매봉역"), 1);
        time.setEdgeWeight(time.addEdge("양재역", "매봉역"), 1);
    }

    private void madeSecondInfo() {
        graphDistance.setEdgeWeight(graphDistance.addEdge("교대역", "강남역"), 2);
        time.setEdgeWeight(time.addEdge("교대역", "강남역"), 3);

        graphDistance.setEdgeWeight(graphDistance.addEdge("강남역", "역삼역"), 2);
        time.setEdgeWeight(time.addEdge("강남역", "역삼역"), 3);
    }

    // 그냥 경로 없으면  null 터져버림 이거 try catch

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
            sum += time.getEdgeWeight(time.getEdge(route.get(i),route.get(i+1)));
        }
        return sum;
    }

    private int calculateRouteByDistance(List<String> route) {
        int sum=0;
        for(int i=0; i<route.size()-1; i++){
            sum += graphDistance.getEdgeWeight(graphDistance.getEdge(route.get(i),route.get(i+1)));
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
