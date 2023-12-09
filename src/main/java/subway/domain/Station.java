package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Station {
    public static final String GANGNAM = "강남역";
    public static final String EDUCATION_COLLEGE="교대역";
    public static final String SOUTH_TERMINAL = "남부터미널역";
    public static final String YANGZAE = "양재역";
    public static final String YANGZAE_FOREST = "양재시민의숲역";
    public static final String YEOKSAM = "역삼역";
    public static final String MAEBONG = "매봉역";

    private String name;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
