public class Main {
    public static void main(String[] args) {

        Hangman game;
        boolean play = true;

        while(play) {
            game = new Hangman();
            game.game();
            play = Hangman.play(null);
        }
    }
}