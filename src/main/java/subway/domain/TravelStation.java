package subway.domain;

import subway.constant.ErrorMessage;

public class TravelStation {

    private final Station start;
    private final Station dest;


    public TravelStation(String start, String dest) {
        checkStation(start, dest);
        this.start = new Station(start);
        this.dest = new Station(dest);
    }

    private void checkStation(String start, String dest) {
        if (isNoPresentStation(start, dest)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_STATION.getMessage());
        }
        if (start.equals(dest)) {
            throw new IllegalArgumentException(ErrorMessage.SAME_START_DEST.getMessage());
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
        return stationRelation.shortestTime(start, dest);
    }
}
