import java.util.Random;
import java.util.Scanner;

public class DragonCave {
    Scanner sc = new Scanner(System.in);
    Random rg = new Random();

    public int cave = rg.nextInt(2) + 1;

    public void caveGame(){

        int choice = getPath();

        String ending =  getEnding(choice);

    }

    public int getPath(){
        System.out.println("You are in a land full of dragons. In front of you,\nyou see two caves. In one cave, the dragon is friendly\nand will share his treasure with you. The other dragon\nis greedy and hungry and will eat you on sight.\nWhich cave will you go into? (1 or 2)\n");

        int choice = 0;

        while(choice != 1 && choice != 2) {
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return choice;
    }

    public String getEnding(int choice){

        String ending;

        if(choice == cave){
            ending = "\nYou approach the cave...\nIt is dark and spooky...\nA large dragon jumps out in front of you! He opens his jaws and...\nGives you a pile of gold!";
        }else{
            ending = "\nYou approach the cave...\nIt is dark and spooky...\nA large dragon jumps out in front of you! He opens his jaws and...\nGobbles you down in one bite!";
        }

        return ending;
    }

}
