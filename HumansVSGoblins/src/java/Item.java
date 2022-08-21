import java.util.Random;

public class Item {

    private int type;
    private String name;
    private String description;
    private int strength;

    public Item(char type){

        if(type == 'g'){
            makeLoot();
        }else if(type == 't'){
            makeTreasure();
        }

    }

    private void makeTreasure() {
        Random r = new Random();
        switch(r.nextInt(0,5)){
            case 0 : {
                setType(0);
                switch(r.nextInt(0,2)){
                    case 0 -> setName("Health Potion");
                    case 1 -> setName("Elixir");
                }
                setStrength(r.nextInt(4,7));
                setDescription(getName() + " that restores " + getStrength() + " health when consumed.");
                break;
            }
            case 1: {
                setType(1);
                switch(r.nextInt(0,3)){
                    case 0 -> setName("Ornate club");
                    case 1 -> setName("Deft Spear");
                    case 2 -> setName("Sharpened Sword");
                }
                setStrength(r.nextInt(5,8));
                setDescription(getName() + " that grants " + getStrength() + " strength when equipped.");
                break;
            }
            case 2:{
                setType(2);
                setName("Pendant");
                setStrength((r.nextInt(1,12)/10) + 1);
                setDescription(getName() + " that heals " + getStrength() + " health after moving.");
                break;
            }
            case 3: {
                setType(3);
                setName("War Relic");
                setStrength((r.nextInt(1,12)/10) + 1);
                setDescription(getName() + " that grants " + getStrength() + " strength after combat.");
                break;
            }
            default:{}
        }
    }

    public void makeLoot(){

        Random r = new Random();
        switch(r.nextInt(0,2)){
            case 0 : {
                setType(0);
                switch(r.nextInt(0,3)){
                    case 0 -> setName("Pork Chop");
                    case 1 -> setName("Steak");
                    case 2 -> setName("Unidentifiable Meat");
                }
                setStrength(r.nextInt(1,5));
                setDescription(getName() + " that restores " + getStrength() + " health when consumed.");
                break;
            }
            case 1: {
                setType(1);
                switch(r.nextInt(0,3)){
                    case 0 -> setName("Club");
                    case 1 -> setName("Spear");
                    case 2 -> setName("Sword");
                }
                setStrength(r.nextInt(3,6));
                setDescription(getName() + " that grants " + getStrength() + " strength when equipped.");
                break;
            }
            default:{}
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        if(type < 0){
            this.type = 0;
        }else if( type > 2){
            this.type = 2;
        }else{
            this.type = type;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String toString(){
        return getName();
    }
}
