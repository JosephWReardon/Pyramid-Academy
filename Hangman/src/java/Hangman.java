import java.util.HashSet;
import java.util.Scanner;
import java.util.Random;

public class Hangman {

    public String word;
    public HashSet<Character> letters;
    public HashSet<Character> guesses;
    public int man;
    public Scanner s;

    public Hangman(String word){
        this.word = word;
        this.letters = new HashSet<>();
        this.guesses = new HashSet<>();
        this.man = 0;
        this.s = new Scanner(System.in);

        for(char x: word.toCharArray()){
            letters.add(x);
        }
    }

    public Hangman(){
        this.word = drawFromWordBank();
        this.letters = new HashSet<>();
        this.guesses = new HashSet<>();
        this.man = 0;
        this.s = new Scanner(System.in);

        for(char x: word.toCharArray()){
            letters.add(x);
        }
    }

    private String drawFromWordBank() {

        Random ran = new Random();

        return switch (ran.nextInt(0, 10)) {
            case 0 -> "cheese";
            case 1 -> "cat";
            case 2 -> "dog";
            case 3 -> "sunshine";
            case 4 -> "agony";
            case 5 -> "potato";
            case 6 -> "sycophant";
            case 7 -> "gargoyle";
            case 8 -> "mississippi";
            case 9 -> "loser";
            default -> "default";
        };


    }

    public void game(){

        System.out.println("H A N G M A N\n");

        while(this.man < 7 && !guesses.containsAll(letters)){
            hang();
            System.out.println("\nMissed letters: " + this.miss());
            System.out.println(this.fill());
            takeAGuess();
        }

        hang();
        finish();

    }

    private void hang(){

        switch (this.man) {
            case 0 -> System.out.println("+---+\n\n     |\n\n     |\n\n     |\n\n    ===");
            case 1 -> System.out.println("+---+\n\n O   |\n\n     |\n\n     |\n\n    ===");
            case 2 -> System.out.println("+---+\n\n O   |\n\n |   |\n\n     |\n\n    ===");
            case 3 -> System.out.println("+---+\n\n O   |\n\n |   |\n\n |   |\n\n    ===");
            case 4 -> System.out.println("+---+\n\n O   |\n\n/|   |\n\n |   |\n\n    ===");
            case 5 -> System.out.println("+---+\n\n O   |\n\n/|\\  |\n\n |   |\n\n    ===");
            case 6 -> System.out.println("+---+\n\n O   |\n\n/|\\  |\n\n |   |\n\n/   ===");
            case 7 -> System.out.println("+---+\n\n O   |\n\n/|\\  |\n\n |   |\n\n/ \\ ===");
            default -> System.out.println("+---+\n\n O   |\n\n |   |\n\n |   |\n\n/   ===");
        }

    }

    private void takeAGuess(){

        char guess;

        System.out.println("Guess a letter.\n\n");

        try {

            guess = s.next().charAt(0);

            if(guesses.contains(guess)){
                System.out.println("\n\nYou have already guessed that letter. Choose again.");
                this.takeAGuess();
            } else if(letters.contains(guess)){
                guesses.add(guess);
            } else{
                guesses.add(guess);
                this.man++;
            }

        }catch(Exception e){
            System.out.println("\n\nThat is not a valid guess");
            this.takeAGuess();
        }

    }

    boolean finish(){
        if(guesses.containsAll(letters)){

            System.out.println("\n\nYes! The secret word is " +'"' + word + '"' + "! You have won!\nDo you want to play again? (yes or no)");

            return true;

        } else{

            System.out.println("The secret word was " +'"' + word + '"' + ". You have lost.\nDo you want to play again? (yes or no)");

            return false;

        }
    }

    String miss(){

        String misses = "";

        HashSet<Character> tempGuesses = new HashSet<>(this.guesses);

        tempGuesses.removeAll(this.letters);

        for(char x: tempGuesses){
            misses = misses + x;
        }

        return misses;
    }

    String fill(){

        String fill = "";

        HashSet<Character> tempLetters = new HashSet<>(this.letters);

        tempLetters.retainAll(this.guesses);

        for(char x: word.toCharArray()){
            if(tempLetters.contains(x)){
                if(!fill.equals("")){fill += " ";}
                fill += x;
            }else{
                fill += " _";
            }
        }

        return fill;
    }

    public static boolean play(String input){

        boolean play = false;
        boolean choice = true;

        while(choice) {
            try {
                Scanner s = new Scanner(System.in);
                if(input == null){input = s.next();}
                if (input.contentEquals("yes")) {
                    play = true;
                    choice = false;
                } else if (input.contentEquals("no")) {
                    choice = false;
                } else {
                    System.out.println("That is not an option.");
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        return play;
    }

}
