import java.util.ArrayList;
import java.util.Scanner;

public class Human {


    private Land land;
    private int strength;
    private int health;

    private ArrayList<Item> inventory = new ArrayList<>();

    public Human(Land land){
        this.setHealth(30);
        this.setStrength(7);
        this.setLand(land);
        land.setContent(this);
    }

    public boolean takeTurn(){

        Scanner s = new Scanner(System.in);
        char move;

        do{
            move = s.next().toCharArray()[0];
        }while(move != 'n' && move != 's' && move != 'e' && move != 'w');

        Land next = this.move(move);
        this.getLand().setContent(null);
        this.setLand(next);
        this.check();
        this.getLand().setContent(this);

        return this.getHealth() > 0;

    }

    public Land move(char c){

        switch(c){
            case 'n' -> {return Land.map.get(this.land.getCoordinate()[0] + "-" + (this.land.getCoordinate()[1] - 1));}
            case 's' -> {return Land.map.get(this.land.getCoordinate()[0] + "-" + (this.land.getCoordinate()[1] + 1));}
            case 'e' -> {return Land.map.get((this.land.getCoordinate()[0] + 1) + "-" + this.land.getCoordinate()[1]);}
            case 'w' -> {return Land.map.get((this.land.getCoordinate()[0] - 1) + "-" + this.land.getCoordinate()[1]);}
            default -> {return this.getLand();}
        }

    }

    public void check(){
        if(this.getLand().getContent() == null){

        } else if (Goblin.class.equals(this.getLand().getContent().getClass())) {
            this.combat((Goblin) this.getLand().getContent());
        } else if (Treasure.class.equals(this.getLand().getContent().getClass())) {
            this.loot((Treasure) this.getLand().getContent());
        }
    }

    private void combat(Goblin goblin){

        int damage;
        while(this.getHealth() > 0 && goblin.getHealth() > 0){
            damage = (int) Math.floor(this.getStrength() * Math.random());
            goblin.setHealth(goblin.getHealth() - damage);

            if(damage == 0){
                System.out.println("The human misses.");
            }else if(damage > .7 * this.getStrength()){
                System.out.println("The human hits hard for " + damage + ".");
            }else {
                System.out.println("The human hits for " + damage + ".");
            }

            if(goblin.getHealth() > 0) {
                damage = (int) Math.floor(goblin.getStrength() * Math.random());
                this.setHealth(this.getHealth() - damage);

                if(damage == 0){
                    System.out.println("The goblin misses.");
                }else if(damage > .7 * goblin.getStrength()){
                    System.out.println("The goblin hits hard for " + damage + ".");
                }else {
                    System.out.println("The goblin hits for " + damage + ".");
                }

            } else{
                System.out.println("The goblin has died.");
                this.land.setContent(this);
                goblin.die();
            }
        }

        if(this.getHealth() > 0){
            System.out.println("The human won with " + this.getHealth() + " health remaining.");
            this.loot(goblin.getLoot());
            this.getLand().setContent(this);
        } else{
            System.out.println("The human has died.");
        }

    }

    private void loot(Treasure treasure){
        if(treasure != null) {

            for (Item i : treasure.getInventory()) {
                System.out.println("The human looted " + i.getName() + ".");
            }


            inventory.addAll(treasure.getInventory());
        }
    }

    public String toString(){
        return "H";
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(health, 0);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }
}
