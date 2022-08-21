import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        Human me = new Human(new Land(1,1));
        Land.navigateMap(1,1);
        boolean play = true;
        while(play){
            play = me.takeTurn();
            Goblin.updateRegistry();
            for(Goblin g: Goblin.registry){
                g.takeTurn(me);
            }
            Goblin.updateRegistry();
            Land.navigateMap(me.getLand().getCoordinate()[0],me.getLand().getCoordinate()[1]);
        }

    }
}