package com.example.humanvsgoblingui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
        }else if(roll < 5){
            this.setContent(new Treasure());
        }

    }

    public static GridPane navigate(int X, int Y){

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(8);

        int row = 0;
        int column = 0;
        String content =" ";
        Label temp;

        for(int y = Y - 4; y <= Y + 4; y++){



            for(int x = X - 4; x <= X + 4; x++){
                if(!Land.map.containsKey(x + "-" + y)) {
                    Land.map.putIfAbsent(x + "-" + y, new Land(x,y));
                }

                if(Land.map.get(x + "-" + y).getContent() == null) {
                    content = " ";
                }else if(Land.map.get(x + "-" + y).getContent().getClass() == Human.class){
                    content = " H ";
                }else if(Land.map.get(x + "-" + y).getContent().getClass() == Goblin.class){
                    content = " G ";
                }else if(Land.map.get(x + "-" + y).getContent().getClass() == Treasure.class){
                    content = " T ";
                }else{
                    content = " ";
                }

                temp = new Label(content);
                temp.setStyle("-fx-border-color: black;");
                temp.setMinSize(15,15);
                GridPane.setConstraints(temp,column,row);
                grid.getChildren().add(temp);

                column++;
            }

            row++;
            column = 0;
        }

        return grid;

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
