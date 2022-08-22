import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class GoblinTest {

    Goblin goblin;

    @BeforeEach
    void setUp(){
        goblin = new Goblin(new Land(0,0));
        goblin.getLand().setContent(goblin);
    }

    @Test
    void pathfind() {

        Human human = new Human(new Land(1,0));
        Land land = human.getLand();
        land.setContent(human);

        assertEquals(human.getLand(), goblin.pathfind(human),"Did not move toward human");
    }

    @Test
    void updateRegistry() {

        Goblin gob = new Goblin(new Land(2,2));
        Goblin gib = new Goblin(new Land(1,1));

        Goblin.deaths.add(goblin);
        Goblin.deaths.add(gib);

        Goblin.updateRegistry();

        assertEquals(Goblin.registry.size(),1,"Did not remove dead goblins from registry");
        assertEquals(Goblin.registry.get(0),gob,"Did not kill correct goblins");
        assertEquals(Goblin.deaths.size(),0,"Did not clear deaths");

    }
}