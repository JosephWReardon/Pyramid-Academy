import java.util.Random;

public class Goblin {

    private int health;
    private int strength;
    private Land land;
    private Treasure loot;

    public Goblin(Land land){

        Random r = new Random();

        this.setLand(land);
        this.setHealth(r.nextInt(8,15));
        this.setStrength(r.nextInt(3,7));
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
        this.health = health;
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
