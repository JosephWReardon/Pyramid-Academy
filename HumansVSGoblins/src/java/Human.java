import java.util.ArrayList;
import java.util.Scanner;

public class Human {


    private Land land;
    private int strength;
    private int equipStrength;
    private int baseStrength;
    private int health;

    private int charmType;
    private int charmStrength;

    private ArrayList<Item> inventory = new ArrayList<>();

    public Human(Land land){
        this.setHealth(30);
        this.setStrength(7);
        this.setBaseStrength(7);
        this.setLand(land);
        land.setContent(this);
    }

    public boolean takeTurn(){

        Scanner s = new Scanner(System.in);
        char move;

        do{
            move = s.next().toCharArray()[0];
            if(move == 'i'){ openInventory(); Land.navigateMap(getLand().getCoordinate()[0],getLand().getCoordinate()[1]);}
        }while(move != 'n' && move != 's' && move != 'e' && move != 'w' );

        Land next = this.move(move);
        this.getLand().setContent(null);
        this.setLand(next);
        if(charmType == 1){ setHealth(getHealth()+charmStrength);}
        this.check();
        this.getLand().setContent(this);

        return this.getHealth() > 0;

    }

    private void openInventory() {

        cleanInventory();

        System.out.println("Enter 'c' to close inventory.\nEnter the number of an item to select it.\nEnter 'i' to inspect selected item.\nEnter 'u' to use selected item.\n\nThe human has " + getHealth() + " health and " + getStrength() + " strength.\n");

        int x = 0;
        for(Item i: inventory){
            System.out.println((x +1) + ". " + i.getName() + "\t\t" + i.getStrength());
            x++;
        }

        if(!inventory.isEmpty()) {
            char select;
            Scanner s = new Scanner(System.in);
            Item item = inventory.get(0);

            useInventory:
            while (true) {
                select = s.next().toCharArray()[0];
                if (select == 'c') {
                    break useInventory;
                }
                if (select == 'i') {
                    System.out.println(item.getDescription());
                } else if (select == 'u') {
                    switch (item.getType()) {
                        case 0 -> {
                            setHealth(getHealth() + item.getStrength());
                            System.out.println("Human consumed the " + item.getName() + " bringing their health to " + getHealth() + ".");
                            inventory.remove(item);
                            if (inventory.isEmpty()) {
                                System.out.println("Inventory is empty.");
                                break useInventory;
                            }
                            item = inventory.get(0);
                            x--;
                        }
                        case 1 -> {
                            setEquipStrength(item.getStrength());
                            setStrength(getEquipStrength() + 7);
                            System.out.println("Human equipped the " + item.getName() + " achieving a strength of " + getStrength() + ".");
                        }
                        case 2 -> {
                            setCharmType(1);
                            setCharmStrength(item.getStrength());
                            System.out.println("Human donned the pendant.");
                        }
                        case 3 -> {
                            setCharmType(2);
                            setCharmStrength(item.getStrength());
                            System.out.println("Human equipped the war relic.");
                        }
                    }
                }else if(Integer.parseInt(select + "") < 1){
                    item = inventory.get(0);
                }else if(Integer.parseInt(select + "") > x){
                    item = inventory.get(x-1);
                }else if(Integer.parseInt(select + "") >= 1 || Integer.parseInt(select + "") <= x ){
                    item = inventory.get(Integer.parseInt(select + "") -1);
                }


            }
        }else {
            System.out.println("Inventory is empty.");
        }


    }

    private void cleanInventory() {
        if(!inventory.isEmpty()){

            int x = 0;
            while(x < inventory.size()-1) {
                Item I = inventory.get(0);
                ArrayList<Item> removal = new ArrayList<>();

                for (Item i : inventory) {
                    if (I.getName().contentEquals(i.getName())) {
                        if (i.getStrength() > I.getStrength()) {
                            removal.add(I);
                        } else {
                            removal.add(i);
                            I = i;
                        }
                    }
                }

                inventory.removeAll(removal);
                inventory.add(I);
                x++;
            }
        }
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
            if(getCharmType() == 2){
                baseStrength++;
                strength = baseStrength + equipStrength;
            }
            this.loot(goblin.getLoot());
            this.getLand().setContent(this);
        } else{
            System.out.println("The human has died.");
        }

    }

    private void loot(Treasure treasure){
        if(treasure != null) {

            ArrayList<Item> consumed = new ArrayList<>();

            for (Item i : treasure.getInventory()) {
                System.out.println("The human looted " + i.getName() + ".");
                if(i.getType() == 0){
                    health += i.getStrength();
                    System.out.println("The human ate the " + i.getName() + " bringing their health to " + health);
                    consumed.add(i);

                }
            }

            treasure.getInventory().removeAll(consumed);


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

    public int getEquipStrength() {
        return equipStrength;
    }

    public void setEquipStrength(int equipStrength) {
        this.equipStrength = equipStrength;
    }

    public int getCharmType() {
        return charmType;
    }

    public void setCharmType(int charmType) {
        this.charmType = charmType;
    }

    public int getCharmStrength() {
        return charmStrength;
    }

    public void setCharmStrength(int charmStrength) {
        this.charmStrength = charmStrength;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public void setBaseStrength(int baseStrength) {
        this.baseStrength = baseStrength;
    }
}
