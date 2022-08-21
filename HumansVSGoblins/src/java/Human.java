import java.util.Scanner;

public class Human {


    private Land land;
    private int strength;
    private int health;

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
        while(this.getHealth() > 0 && goblin.getHealth() > 0){
            this.setHealth(this.getHealth() - (int) Math.floor(goblin.getStrength() * Math.random()));
            goblin.setHealth(goblin.getHealth() - (int) Math.floor(this.getStrength() * Math.random()));
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
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
