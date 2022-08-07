import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class DragonCaveTest {

    DragonCave cave;

    @BeforeEach
    void setUp() {
        cave = new DragonCave();
    }

    @Test
    void getEnding() {

        assertEquals("\nYou approach the cave...\nIt is dark and spooky...\nA large dragon jumps out in front of you! He opens his jaws and...\nGives you a pile of gold!",cave.getEnding(cave.cave),"Wrong Ending");

    }

    @AfterEach
    void tearDown() {

    }
}