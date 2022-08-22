import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TreasureTest {

    @BeforeEach
    void setUp(){

    }

    @Test
    void treasure(){
        Treasure t = new Treasure();

        assertNotEquals(t.inventory.size(),0,"Did not fill chest");
    }

}