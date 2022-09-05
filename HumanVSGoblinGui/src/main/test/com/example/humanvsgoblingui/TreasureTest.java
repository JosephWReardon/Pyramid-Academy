package com.example.humanvsgoblingui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TreasureTest {

    Treasure t;

    @BeforeEach
    void setup(){
        t = new Treasure();
    }

    @Test
    void testCreation(){
        assertTrue(t.getInventory().size()>0,"Did not properly fill treasure upon creation");
    }

    @AfterEach
    void teardown(){}

}