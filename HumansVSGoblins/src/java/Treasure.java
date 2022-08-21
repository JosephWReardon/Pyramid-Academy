public class Treasure {

    private Land land;

    public Treasure(Land land){
        this.setLand(land);
    }

    public String toString(){
        return "Ξ";
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }
}
