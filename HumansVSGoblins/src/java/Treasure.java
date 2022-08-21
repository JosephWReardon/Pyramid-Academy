import java.util.ArrayList;

public class Treasure {


    public ArrayList<Item> inventory = new ArrayList<>();

    public Treasure(){
        inventory.add(new Item('t'));
        for(int x = 0; x < 2; x++){
            if(Math.random() > .8) {
                inventory.add(new Item('t'));
            }
        }
    }

    public Treasure(char type){

        if(Math.random() < .8){

            this.inventory.add(new Item('g'));
        }
    }

    public String toString(){
        return "Ξ";
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }
}
