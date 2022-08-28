import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Hangman game;
        boolean play = true;

        while(play) {
            game = new Hangman();
            System.out.println("H A N G M A N\n");
            while(game.man < 7 && !game.guesses.containsAll(game.letters)){
                game.hang();
                System.out.println("\nMissed letters: " + game.miss());
                System.out.println(game.fill());
                game.takeAGuess();
            }
            game.hang();
            game.record();
            game.finish();

            play = Hangman.play(null);
        }
    }
}