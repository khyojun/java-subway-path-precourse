package subway.domain;

public class TravelStation {

    private StationRelation stationRelation;
    private Station start;
    private Station dest;


    public TravelStation(String start, String dest) {
        checkStation(start, dest);
        this.start = new Station(start);
        this.dest = new Station(dest);
    }

    private void checkStation(String start, String dest) {
        if(!SubwayInfoRepository.isStationPresent(start) && !SubwayInfoRepository.isStationPresent(dest)){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 역을 작성하셨습니다!");
        }
        if(start.equals(dest)){
            throw new IllegalArgumentException("[ERROR] 출발역과 도착역이 동일합니다!");
        }
    }

    public Station getStart() {
        return start;
    }

    public Station getDest() {
        return dest;
    }

    public TravelResult shortestDistance(StationRelation stationRelation) {
        return stationRelation.shortestDistance(start, dest);
    }

    public TravelResult shortestTime(StationRelation stationRelation) {
        return stationRelation.shortestTime(start,dest);
    }
}
