package com.example.humanvsgoblingui;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.*;

class LandTest {

    @BeforeEach
    void setup(){

    }

    @Test
    void mapUpdate() {

        Land start = new Land(0,0);
        Human human = new Human(start);

        assertNotNull(Land.map.get("0-0"),"Did not generate land when navigating");

    }

    @AfterEach
    void teardown(){

    }
}