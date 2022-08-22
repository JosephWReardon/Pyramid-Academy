import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class HumanTest {

    Human human;

    @BeforeEach
    void setUp(){
        human = new Human(new Land(0,0));
    }

    @Test
    void move() {

        Land land = new Land(1,0);

        assertEquals(land,human.move('e'),"Did not move in the correct direction");
    }

    @Test
    void check() {

        Goblin goblin = new Goblin(human.getLand());
        goblin.setHealth(0);

        human.check();

        assertEquals(human.getLand().getContent(),human,"Combat did not result in the human taking the land");

    }

    @Test
    void loot(){
        Treasure treasure = new Treasure();
        ArrayList<Item> inventory = new ArrayList<>();

        inventory.add(new Item('g'));
        inventory.add(new Item('t'));

        treasure.setInventory(inventory);

        human.getLand().setContent(treasure);

        human.check();

        assertEquals(human.getInventory(),inventory,"Did not properly loot the chest");
    }

    @AfterEach
    void tearDown(){

    }
}