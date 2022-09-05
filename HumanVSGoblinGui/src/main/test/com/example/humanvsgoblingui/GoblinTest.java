package com.example.humanvsgoblingui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class GoblinTest {

    Human human;
    Goblin goblin;

    @BeforeEach
    void setup(){
        human = new Human(new Land(2,0));
        goblin = new Goblin(new Land(0,0));
        goblin.getLand().setContent(goblin);
    }

    @Test
    void takeTurn() {

        Land dest = new Land(1,0);
        dest.setContent(new Treasure('G'));
        goblin.getLoot().setInventory(new ArrayList<Item>());

        goblin.takeTurn(human);

        assertEquals(dest,goblin.getLand(),"Did not move to correct land");
        assertTrue(goblin.getLoot().getInventory().size() > 0,"Did not loot chest");
        assertNull(Land.map.get("0-0").getContent(),"Did not empty previous land");

    }

    @Test
    void pathfind() {
        Land dest = new Land(1,0);
        assertEquals(dest,goblin.pathfind(human),"Did not move in correct direction.");
    }

    @Test
    void die() {
        goblin.die();
        assertNull(goblin.getLand().getContent(),"Did not dead goblin from lan.d");
    }

    @Test
    void updateRegistry() {
        goblin.die();
        Goblin.updateRegistry();
        assertEquals(0,Goblin.deaths.size(),"Did not clear deaths");
        assertFalse(Goblin.registry.contains(goblin),"Did not update registry.");

    }
}