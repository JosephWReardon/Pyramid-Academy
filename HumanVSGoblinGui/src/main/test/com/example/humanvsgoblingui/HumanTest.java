package com.example.humanvsgoblingui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class HumanTest {

    Human human;

    @BeforeEach
    void setup(){
        human = new Human(new Land(0,0));
    }

    @Test
    void makeMove() {

        Land dest = new Land(1,0);
        dest.setContent(null);
        human.setHealth(1);
        human.makeMove('e');

        assertNull(Land.map.get("0-0").getContent(),"Did not empty previous land");
        assertEquals(dest.getContent(),human,"Did not move to correct land");
        assertEquals(human.getLog().get(human.getLog().size()-1),"Human moved East","Did not add correct update to the log");

        dest = new Land(-1,0);
        dest.setContent(null);
        human.setLand(new Land(0,0));
        human.getLand().setContent(human);
        human.makeMove('w');
        assertEquals(dest.getContent(),human,"Did not move to correct land");
        assertEquals(human.getLog().get(human.getLog().size()-1),"Human moved West","Did not add correct update to the log");

        dest = new Land(0,-1);
        dest.setContent(null);
        human.setLand(new Land(0,0));
        human.getLand().setContent(human);
        human.makeMove('n');
        assertEquals(dest.getContent(),human,"Did not move to correct land");
        assertEquals(human.getLog().get(human.getLog().size()-1),"Human moved North","Did not add correct update to the log");

        dest = new Land(0,1);
        dest.setContent(null);
        human.setLand(new Land(0,0));
        human.getLand().setContent(human);
        human.setCharmType(1);
        human.setCharmStrength(1);
        human.makeMove('s');
        assertEquals(dest.getContent(),human,"Did not move to correct land");
        assertEquals(human.getLog().get(human.getLog().size()-1),"Human moved South","Did not add correct update to the log");
        assertEquals(human.getHealth(),2,"Did not increase health when equipped with pendant");

        assertTrue(human.makeMove('n'),"Ended game when player still had health");

        human.setHealth(0);
        human.setCharmType(0);
        assertFalse(human.makeMove('s'),"Continued game after player death");
    }

    @Test
    void use() {

        Item i = new Item('h');
        i.setName("Spear");
        i.setStrength(5);
        i.setType(1);

        human.getInventory().add(i);
        human.setBaseStrength(0);

        human.use("Spear:");

        assertEquals(human.getStrength(),5,"Did not add items strength.");
        assertEquals(human.getBaseStrength(),0,"Item increased base strength.");
        assertEquals(human.getEquipStrength(),5,"Item did not add to equip strength.");


        i.setName("Pendant");
        i.setStrength(1);
        i.setType(2);

        human.getInventory().add(i);
        human.setHealth(0);
        Land dest = new Land(1,0);

        human.use("Pendant:");
        human.makeMove('e');

        assertEquals(1,human.getHealth(),"Pendant did not increase health after moving");


        i.setName("War Relic");
        i.setStrength(1);
        i.setType(3);

        human.getInventory().add(i);
        human.setHealth(100);
        human.getLand().setContent(new Goblin(human.getLand()));
        ((Goblin) human.getLand().getContent()).setHealth(0);

        human.use("War Relic:");
        human.check();

        assertEquals(3,i.getType());
        assertEquals(2,human.getCharmType(),"Didn't equip correct charm.");

        human.makeMove('w');

        assertEquals(1,human.getBaseStrength(),"War Relic did not increase strength after battle");


    }

    @Test
    void cleanInventory() {

        Item i = new Item('h');
        i.setType(1);
        i.setName("Spear");
        i.setStrength(3);

        human.getInventory().add(i);

        i = new Item('h');
        i.setType(1);
        i.setName("Spear");
        i.setStrength(4);

        human.getInventory().add(i);

        i = new Item('h');
        i.setType(1);
        i.setName("Spear");
        i.setStrength(5);

        human.getInventory().add(i);

        human.cleanInventory();

        assertEquals(1,human.getInventory().size(),"Did not remove correct amount of items");
        assertEquals(5,human.getInventory().get(0).getStrength(),"Removed wrong items");

    }

    @Test
    void move() {

        Land dest = new Land(1,0);
        assertEquals(dest,human.move('e'),"Moved to wrong land");

        dest = new Land(-1,0);
        assertEquals(dest,human.move('w'),"Moved to wrong land");

        dest = new Land(0,1);
        assertEquals(dest,human.move('s'),"Moved to wrong land");

        dest = new Land(0,-1);
        assertEquals(dest,human.move('n'),"Moved to wrong land");

    }

    @Test
    void check() {

        Item i = new Item('h');
        i.setType(1);
        i.setName("Spear");
        i.setStrength(3);

        human.getLand().setContent(new Treasure());
        ((Treasure) human.getLand().getContent()).getInventory().add(i);
        human.check();

        assertTrue(human.getInventory().size() > 0,"Did not loot properly");

        human.setInventory(new ArrayList<>());
        Goblin gob = new Goblin(human.getLand());
        gob.getLoot().getInventory().add(i);
        assertTrue(human.getInventory().size() > 0,"Did not loot after combat properly");

    }
}