

import java.util.Scanner;
import java.util.Random;

public class TakeAGuess {

    public int number;
    public boolean start = true;
    public int guess;
    public int guesses;
    public String yes;

    public String win;

    public String name;

    public void GuessGame(){
        Scanner sc = new Scanner(System.in);
        Random rc = new Random();

        while(start) {

            number = rc.nextInt(20) + 1;

            System.out.println("Hello! What is your name?\n");

            try {
                name = sc.next();
            }catch(Exception e){
                System.out.println(e.getMessage());
                break;
            }


            System.out.println("\nWell, " + name + ",I am thinking of a number between 1 and 20.\nTake a guess.\n");

            try{
                guess = sc.nextInt();
            }catch(Exception e){
                System.out.println(e.getMessage());
                break;
            }

            guesses = 1;

            while (number != guess) {
                if (guess > number) {
                    System.out.println("\nYour guess is too high.\nTake a guess.\n");
                } else {
                    System.out.println("\nYour guess is too low.\nTake a guess.\n");
                }

                try{
                    guess = sc.nextInt();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    break;
                }
                guesses++;
            }

            determineWin();

            System.out.println(win);

            while(true) {

                try{
                    yes = sc.next();
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    break;
                }

                if (yes.equals("y")) {
                    break;
                } else if (yes.equals("n")) {
                    start = false;
                    break;
                }
            }
        }
    }

    public void determineWin(){

        if(guesses <= 6) {
            win = "\nGood job, " + name + "! You guessed my number in " + guesses + " guesses!\nWould you like to play again? (y or n)\n";
        }else{
            win = "\n" + name + " ,you guessed my number in " + guesses + " guesses. Try to guess it in 6 guesses.\nWould you like to play again? (y or n)\n";
        }

    }

}
