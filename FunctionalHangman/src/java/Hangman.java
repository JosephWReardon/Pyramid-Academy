import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class Hangman {

    public String word;
    public HashSet<String> letters;
    public HashSet<String> guesses;
    public int man;
    public Scanner s;

    public Hangman(String word){
        this.word = word;
        this.guesses = new HashSet<>();
        this.man = 0;
        this.s = new Scanner(System.in);

        this.letters = new HashSet(Arrays.stream(word.split("")).collect(Collectors.toList()));
    }

    public Hangman(){
        this.word = drawFromWordBank();
        this.guesses = new HashSet<>();
        this.man = 0;
        this.s = new Scanner(System.in);

        this.letters = new HashSet(Arrays.stream(word.split("")).collect(Collectors.toList()));

    }

    String drawFromWordBank() {

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

    void hang() throws FileNotFoundException {

        String localDir = System.getProperty("user.dir");

        File f = new File(localDir + "\\src\\resources\\asciiArt" + man);
        Scanner art = new Scanner(f);

        String[] man = {"","","","","","","","",""};



        System.out.println(Arrays.stream(man).map(n -> art.nextLine()).reduce("",(x,y) -> x + "\n" + y));

    }

    int calcScore(){
        int score = 0;

        HashSet<String> temp = new HashSet<String>(letters);
        temp.retainAll(guesses);

        score += 10 * temp.size();
        score -= 5 * man;

        return score;
    }

    void record() throws IOException {

        int score = calcScore();
        Scanner s = new Scanner(System.in);
        String write = "";

        try{
            System.out.println("What is your name?");
            write = s.nextLine() + ": " + score;
        }catch(Exception e){
            System.out.println(e);
        }

        String localDir = System.getProperty("user.dir");
        File f = new File(localDir + "\\src\\resources\\ScoreBoard.txt");


        try {
            if (f.createNewFile()){
                try {
                    FileWriter wr = new FileWriter(f,true);
                    wr.write(write);
                    wr.close();
                }catch(Exception e){
                    System.out.println(e);
                }
            }else{
                try {
                    FileWriter wr = new FileWriter(f,true);
                    if(Files.readAllLines(Path.of(f.getAbsolutePath())).size() < 1){
                        wr.write(write);
                    }else {
                        wr.write("\n" + write);
                    }
                    wr.close();
                }catch(IOException e){
                    System.out.println(e.toString());
                }
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }

        try{
            String[] temp = Files.readAllLines(Path.of(f.getAbsolutePath())).stream().map(n -> n.split(" ")).reduce(new String[]{"-1000"},(x, y) -> {
                if(Integer.parseInt(x[x.length-1]) < Integer.parseInt(y[y.length-1])){
                    return y;
                }else{
                    return x;
                }
            });

            if(Integer.parseInt(temp[temp.length-1]) <= score){
                System.out.println("You got the high score of " + score + "!");
            }


        }catch(Exception e){
            System.out.println(e.toString());
        }

    }

    void takeAGuess(){

        String guess;

        System.out.println("Guess a letter.\n\n");

        try {

            guess = s.next().charAt(0) + "";

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

            System.out.println("\n\nYes! The secret word is " +'"' + word + '"' + "! You have won with a score of " + calcScore() + "!\nDo you want to play again? (yes or no)");

            return true;

        } else{

            System.out.println("The secret word was " +'"' + word + '"' + ". You have lost with a score of " + calcScore() + " .\nDo you want to play again? (yes or no)");

            return false;

        }
    }

    String miss(){

        String misses = "";

        HashSet<String> tempGuesses = new HashSet<>(this.guesses);

        tempGuesses.removeAll(this.letters);

        return tempGuesses.stream().reduce("",(x,y) -> x + y);
    }

    String fill(){

        String fill = "";

        HashSet<String> tempLetters = new HashSet<>(this.letters);

        tempLetters.retainAll(this.guesses);

        return Arrays.stream(word.split("")).map(n -> {
            if(tempLetters.contains(n)){
                return " " + n;
            }else{
                return " _";
            }
        }).reduce("",(x,y) -> x + y);

    }

    static boolean play(String input){

        Scanner s = new Scanner(System.in);

            try {
                if(input == null){input = s.next();}
                if (input.contentEquals("yes")) {
                    return true;
                } else if (input.contentEquals("no")) {
                    return false;
                } else {
                    System.out.println("That is not an option.");
                    return play(null);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                return false;
            }

    }

}
