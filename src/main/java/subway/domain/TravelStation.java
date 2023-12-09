package subway.domain;

public class TravelStation {

    private Station start;
    private Station dest;


    public TravelStation(String start, String dest) {
        checkStation(start, dest);
        this.start = new Station(start);
        this.dest = new Station(dest);
    }

    private void checkStation(String start, String dest) {
        if(isNoPresentStation(start, dest)){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 역을 작성하셨습니다!");
        }
        if(start.equals(dest)){
            throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 동일합니다!");
        }
    }

    private static boolean isNoPresentStation(String start, String dest) {
        return !SubwayInfoRepository.isStationPresent(start)
            || !SubwayInfoRepository.isStationPresent(dest);
    }

    public TravelResult shortestDistance(StationRelation stationRelation) {
        return stationRelation.shortestDistance(start, dest);
    }

    public TravelResult shortestTime(StationRelation stationRelation) {
        return stationRelation.shortestTime(start,dest);
    }
}
