package com.example.humanvsgoblingui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application{

    Button startButton,continueButton,homeButton,closeButton,leftMove,rightMove,upMove,downMove;
    Scene startScreen,gameScreen;
    Label startLabel;
    VBox startLayout;
    Human character;

    BorderPane gameBoard;

    int turn;

    boolean game = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception{

        window.setTitle("Human Vs Goblin");
        window.setOnCloseRequest(e -> window.close());

        character = new Human(new Land(0,0));
        turn = 1;

        startButton = new Button("Start the Game");
        startButton.setOnAction(e -> {
            Land.map = new HashMap<String,Land>();
            character = new Human(new Land(0,0));
            updateBoard();
            window.setScene(gameScreen);
            if(!startLayout.getChildren().contains(continueButton)) {
                startLayout.getChildren().add(1, continueButton);
            }
        });
        homeButton = new Button("Home");
        homeButton.setOnAction(e -> window.setScene(startScreen));
        continueButton = new Button("Continue Game");
        continueButton.setOnAction(e -> window.setScene(gameScreen));
        closeButton = new Button("Exit");
        closeButton.setOnAction((e -> window.close()));

        startLabel = new Label("Human vs Goblins");

        startLayout = new VBox();
        startLayout.getChildren().addAll(startLabel,startButton,closeButton);

        startScreen = new Scene(startLayout, 300, 300);

        rightMove = new Button("->");
        rightMove.setOnAction(e -> {
            if(character.makeMove('e')) {

                Goblin.updateRegistry();
                for (Goblin g : Goblin.registry) {
                    g.takeTurn(character);
                }
                Goblin.updateRegistry();

                updateBoard();
            } else{
                window.setScene(startScreen);
            }
        });
        leftMove = new Button("<-");
        leftMove.setOnAction(e -> {
            if(character.makeMove('w')) {

                Goblin.updateRegistry();
                for (Goblin g : Goblin.registry) {
                    g.takeTurn(character);
                }
                Goblin.updateRegistry();

                updateBoard();
            } else{
                window.setScene(startScreen);
            }
        });
        upMove = new Button("\\/");
        upMove.setOnAction(e -> {
            if(character.makeMove('s')) {

                Goblin.updateRegistry();
                for (Goblin g : Goblin.registry) {
                    g.takeTurn(character);
                }
                Goblin.updateRegistry();

                updateBoard();
            } else{
                window.setScene(startScreen);
            }
        });
        downMove = new Button("/\\");
        downMove.setOnAction(e -> {
            if(character.makeMove('n')) {

                Goblin.updateRegistry();
                for (Goblin g : Goblin.registry) {
                    g.takeTurn(character);
                }
                Goblin.updateRegistry();

                updateBoard();
            } else{
                window.setScene(startScreen);
            }
        });

        HBox controlLayout = new HBox(20);
        controlLayout.getChildren().addAll(leftMove,upMove,downMove,rightMove,homeButton);

        HBox topLayout = new HBox(20);
        topLayout.getChildren().add(startLabel);

        VBox logLayout = new VBox(20);
        Label temp = new Label("Turn");
        temp.setMinWidth(300);
        logLayout.getChildren().add(temp);

        temp = new Label("Health:" + character.getHealth() + "\n Strength: " + character.getStrength() + "\n");
        temp.setMinWidth(150);
        VBox inventory = new VBox(temp);

        gameBoard = new BorderPane();
        gameBoard.setBottom(controlLayout);
        gameBoard.setTop(topLayout);
        gameBoard.setCenter(Land.navigate(character.getLand().getCoordinate()[0],character.getLand().getCoordinate()[1]));
        gameBoard.setRight(logLayout);
        gameBoard.setLeft(inventory);


        gameScreen = new Scene (gameBoard,700,600);
        window.setScene(startScreen);
        window.show();
    }

    void updateBoard(){
        gameBoard.setCenter(Land.navigate(character.getLand().getCoordinate()[0],character.getLand().getCoordinate()[1]));
        gameBoard.setRight(log());
        gameBoard.setLeft(inventory());
    }

    VBox log(){

        VBox updatedLog = new VBox();

        updatedLog.getChildren().addAll(composeLog());

        return updatedLog;
    }

    ArrayList<Label> composeLog(){

        ArrayList<Label> log = new ArrayList<>();

        log.add(new Label("Turn " + turn + ":"));
        turn++;

        Label temp;

        for(String s: character.getLog()){
            temp = new Label(s);
            temp.setMinWidth(300);
            log.add(temp);
        }

        character.setLog(new ArrayList<>());

        return log;

    }

    VBox inventory(){

        VBox updatedInventory = new VBox();

        ArrayList<Button> invent = new ArrayList<>();
        Label temp = new Label("Health:" + character.getHealth() + "\n Strength: " + character.getStrength() + "\n");
        temp.setMinWidth(150);

        updatedInventory.getChildren().add(temp);

        Button tempButton;

        for(Item s: character.getInventory()){
            tempButton = new Button(s.getName() + ":\t" + s.getStrength());
            tempButton.setMinWidth(150);

            Button finalTempButton = tempButton;
            tempButton.setOnAction(e -> {
                String i = finalTempButton.getText();

                Human player = character;
                player.use(i);
                gameBoard.setLeft(inventory());
            });

            invent.add(tempButton);
        }

        updatedInventory.getChildren().addAll(invent);

        return updatedInventory;

    }



}
