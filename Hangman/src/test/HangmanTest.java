import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class HangmanTest {

    Hangman hangman;

    @BeforeEach
    void setUp() {
        hangman = new Hangman("test");
    }

    @Test
    void fill(){

        hangman.guesses.add('t');

        assertEquals("t _ _ t", hangman.fill(), "Does not fill blanks correctly");

    }

    @Test
    void miss(){
        hangman.guesses.add('t');
        hangman.guesses.add('o');
        hangman.guesses.add('b');
        hangman.guesses.add('s');
        hangman.guesses.add('u');

        assertEquals("buo",hangman.miss(),"Did not display missed letters properly");
    }

    @Test
    void win(){
        hangman.guesses.add('t');
        hangman.guesses.add('e');
        hangman.guesses.add('s');
        hangman.guesses.add('q');
        hangman.guesses.add('w');

        assertTrue(hangman.finish(), "Declared lost when winning");
    }

    @Test
    void lose(){
        hangman.guesses.add('t');
        hangman.guesses.add('q');
        hangman.guesses.add('s');
        hangman.guesses.add('l');
        hangman.guesses.add('w');

        assertFalse(hangman.finish(), "Declared victory when losing");
    }

    @Test
    void no(){
        assertFalse(hangman.play("no"), "Continued play falsely");
    }

    @Test
    void yes(){
        assertTrue(hangman.play("yes"), "Continued play falsely");
    }

    @AfterEach
    void tearDown() {

    }
}
