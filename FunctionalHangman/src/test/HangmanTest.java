import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.List;

class HangmanTest {

    Hangman game;

    @BeforeEach
    void setUp(){ game = new Hangman("test");}

    @Test
    void calcScore() {

        game.guesses.add("t");
        game.guesses.add("b");
        game.guesses.add("c");
        game.guesses.add("d");
        game.guesses.add("e");

        game.man  = 2;

        assertEquals(10,game.calcScore(),"Did not score properly.");

    }

    @Test
    void finish() {

        assertFalse(game.finish(),"Falsely declared victory.");

        game.guesses = new HashSet(game.letters);

        assertTrue(game.finish(),"Falsely declared loss.");

    }

    @Test
    void miss() {

        assertEquals(game.miss(),"","Did not return empty string when no guesses have been incorrect.");

        game.guesses.addAll(List.of(new String[]{"a", "b", "c","t","e"}));

        assertEquals(game.miss(),"abc","Did not incorrect guesses.");

    }

    @Test
    void fill() {

        assertEquals(game.fill()," _ _ _ _","Did not return blank word with no correct guesses");

        game.guesses.addAll(List.of(new String[]{"a", "b", "c","t","e"}));

        assertEquals(game.fill()," t e _ t","Did not fill the correct letters.");

    }

    @Test
    void play() {

        assertTrue(Hangman.play("yes"),"Ended game prematurely");
        assertFalse(Hangman.play("no"),"Continued game incorrectly");

    }

    @AfterEach
    void tearDown(){}
}