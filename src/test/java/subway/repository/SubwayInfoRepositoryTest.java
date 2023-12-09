package subway.repository;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.Line;
import subway.domain.Station;
import subway.domain.SubwayInfoRepository;

public class SubwayInfoRepositoryTest {


    @AfterEach
    void clear(){
        SubwayInfoRepository.deleteAll();
    }

    @DisplayName("서브웨이 이름 가져오는지 테스트")
    @Test
    void testSave(){
        Line line = new Line("2");
        SubwayInfoRepository.addSubwayInfo(line, (Station) List.of(new Station("hi")));

        Map<Line, List<Station>> subways =
            SubwayInfoRepository.subways();
        Assertions.assertThat(subways.get(line).get(0)).isEqualTo("hi");
    }

}
