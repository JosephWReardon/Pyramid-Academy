import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TakeAGuessTest {

    TakeAGuess game;

    @BeforeEach
    void setUp() {
        game = new TakeAGuess();
    }

    @Test
    void start(){
        game.start = false;
        game.GuessGame();
        assertEquals(0,game.number,"False Start");
    }

    @Test
    void success(){
        game.guesses = 3;
        game.name = "Test";
        game.determineWin();
        assertEquals("\nGood job, " + game.name + "! You guessed my number in " + game.guesses + " guesses!\nWould you like to play again? (y or n)\n",game.win,"Wrong Ending");
    }

    @AfterEach
    void tearDown() {
    }
}