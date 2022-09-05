package com.example.humanvsgoblingui;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class ItemTest {

    Item i;

    @BeforeEach
    void setup(){
        i = new Item('g');
    }

    @Test
    void makeLoot() {
        i.makeLoot();
        assertTrue(i.getType() > -1,"Created illegal item type");
        assertTrue(i.getType() < 2,"Created illegal item type");
    }

    @AfterEach
    void teardown(){
    }
}