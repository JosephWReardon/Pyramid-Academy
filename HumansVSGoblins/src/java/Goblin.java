import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Goblin {

    private int health;
    private int strength;
    private Land land;
    private Treasure loot;
    public static ArrayList<Goblin> registry= new ArrayList();
    public static ArrayList<Goblin> deaths = new ArrayList();

    public Goblin(Land land){

        Random r = new Random();

        this.setLand(land);
        this.setHealth(r.nextInt(8,15));
        this.setStrength(r.nextInt(3,7));
        this.setLoot(new Treasure('t'));


        registry.add(this);
    }

    public void takeTurn(Human target){
        Land choice = pathfind(target);
        this.getLand().setContent(null);
        this.setLand(choice);
        check();
        if(getHealth() > 0) {
            this.getLand().setContent(this);
        }
    }

    Land pathfind(Human target){

        ArrayList<Character> moves = new ArrayList<>();

        if(target.getLand().getCoordinate()[0] > this.getLand().getCoordinate()[0]){
            moves.add('e');
        }else if(target.getLand().getCoordinate()[0] < this.getLand().getCoordinate()[0]){
            moves.add('w');
        }

        if(Math.random() > .5) {
            if (target.getLand().getCoordinate()[1] > this.getLand().getCoordinate()[1]) {
                moves.add('s');
            } else if (target.getLand().getCoordinate()[1] < this.getLand().getCoordinate()[1]) {
                moves.add('n');
            }
        }else{
            if (target.getLand().getCoordinate()[1] > this.getLand().getCoordinate()[1]) {
                moves.add(0,'s');
            } else if (target.getLand().getCoordinate()[1] < this.getLand().getCoordinate()[1]) {
                moves.add(0,'n');
            }
        }

        Land choice = this.getLand();

        for(char c: moves){
            choice = this.move(c);

            if(choice == null){
                return land;
            }

            if(choice.getContent() == null){
                break;
            }
            if(!choice.getContent().getClass().equals(Goblin.class)){
                break;
            }else{
                choice = this.getLand();
            }
        }

        return choice;
    }

    private Land move(char c){

        switch(c) {
            case 'n' -> {
                if(Land.map.get(this.land.getCoordinate()[0] + "-" + (this.land.getCoordinate()[1] - 1)) == null){ Land land = new Land(this.land.getCoordinate()[0],(this.land.getCoordinate()[1] - 1));}
                return Land.map.get(this.land.getCoordinate()[0] + "-" + (this.land.getCoordinate()[1] - 1));
            }
            case 's' -> {
                return Land.map.get(this.land.getCoordinate()[0] + "-" + (this.land.getCoordinate()[1] + 1));
            }
            case 'e' -> {
                return Land.map.get((this.land.getCoordinate()[0] + 1) + "-" + this.land.getCoordinate()[1]);
            }
            case 'w' -> {
                return Land.map.get((this.land.getCoordinate()[0] - 1) + "-" + this.land.getCoordinate()[1]);
            }
            default -> {
                return this.getLand();
            }
        }

    }

    private void check(){
        if(this.getLand().getContent() == null){

        } else if (Human.class.equals(this.getLand().getContent().getClass())) {
            this.combat((Human) this.getLand().getContent());
        } else if (Treasure.class.equals(this.getLand().getContent().getClass())) {
            this.loot((Treasure) this.getLand().getContent());
        }
    }

    private void loot(Treasure content) {
        loot.inventory.addAll(content.inventory);
    }

    private void combat(Human human) {

        int damage;
        while (this.getHealth() > 0 && human.getHealth() > 0) {
            damage = (int) Math.floor(this.getStrength() * Math.random());
            human.setHealth(human.getHealth() - damage);

            if (damage == 0) {
                System.out.println("The goblin misses.");
            } else if (damage > .7 * this.getStrength()) {
                System.out.println("The goblin hits hard for " + damage + ".");
            } else {
                System.out.println("The goblin hits for " + damage + ".");
            }

            if (human.getHealth() > 0) {
                damage = (int) Math.floor(human.getStrength() * Math.random());
                this.setHealth(this.getHealth() - damage);

                if (damage == 0) {
                    System.out.println("The human misses.");
                } else if (damage > .7 * human.getStrength()) {
                    System.out.println("The human hits hard for " + damage + ".");
                } else {
                    System.out.println("The human hits for " + damage + ".");
                }

            } else {
                System.out.println("The human has died.");
            }

        }
        if(this.getHealth() <= 0){

            if(this.loot != null) {

                ArrayList<Item> consumed = new ArrayList<>();

                for (Item i : getLoot().getInventory()) {
                    System.out.println("The human looted " + i.getName() + ".");
                    if(i.getType() == 0){
                        human.setHealth(human.getHealth() + i.getStrength());
                        System.out.println("The human ate the " + i.getName() + " bringing their health to " + human.getHealth());
                        consumed.add(i);

                    }
                }

                this.loot.getInventory().removeAll(consumed);

                human.getInventory().addAll(this.loot.getInventory());
            }
            this.getLand().setContent(human);
            System.out.println("The human has won with " + human.getHealth() + " remaining.");
            if(human.getCharmType() == 2){
                human.setBaseStrength(human.getBaseStrength() + human.getCharmStrength());
                human.setStrength(human.getEquipStrength() + human.getBaseStrength());
            }
            die();
        }
    }

    public void die(){
        deaths.add(this);
        if(this.getLand().getContent().equals(this)){
            this.getLand().setContent(null);
        }
    }

    public static void updateRegistry(){
        registry.removeAll(deaths);
        deaths = new ArrayList<Goblin>();
    }

    public String toString(){
        return "G";
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

    public Treasure getLoot() {
        return loot;
    }

    public void setLoot(Treasure loot) {
        this.loot = loot;
    }
}
