import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Random;

public class Land {

    public static HashMap<String,Land> map = new HashMap();

    private int[] coordinate;
    private Object content;
    private static final Random r = new Random();

    public Land(int x, int y){

        this.setCoordinate(new int[]{x, y});
        Land.map.putIfAbsent(this.getCoordinate()[0] + "-" + this.getCoordinate()[1],this);

        int roll = r.nextInt(0,100);
        if(roll < 3){
            this.setContent(new Goblin(this));
        }else if(roll < 4){
            this.setContent(new Treasure());
        }

    }

    public static void navigateMap(int X, int Y){

        System.out.println("_________________________________________________________________________");

        for(int y = Y - 4; y <= Y + 4; y++){
            System.out.print("|\t");
            for(int x = X - 4; x <= X + 4; x++){
                if(!Land.map.containsKey(x + "-" + y)) {
                    Land.map.putIfAbsent(x + "-" + y, new Land(x,y));
                }
                System.out.print(Land.map.get(x + "-" + y).toString() + "\t|\t");
            }
            System.out.println("\n_________________________________________________________________________");

        }

    }


    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String toString(){

        String land;

        if(this.getContent() == null){
            land = " ";
        } else{
            land = this.getContent().toString();
        }

        return land;

    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }
}
