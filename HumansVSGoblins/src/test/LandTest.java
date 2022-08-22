import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class LandTest {

    @Test
    void createMap() {

        Land.navigateMap(0,0);

        assertEquals(Land.map.size(),81,"Did not create the correct number of land tiles in the grid");

    }

    @AfterEach
    void tearDown(){}
}