package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class App extends Application {
    Pane pane;
    String cross = "cross_blue.png";

    @Override
    public void start(Stage stage){
        pane = new Pane();
        setMenu();

        Scene scene = new Scene(pane, 700, 700);
        stage.setScene(scene);
        stage.show();
    }

    private void setMenu(){
        pane.getChildren().clear();
        pane.setPrefSize(700,700);
        pane.setStyle("-fx-background-color: gray");


        Button play = new Button("New Game");
        play.setLayoutX(300);
        play.setLayoutY(130);
        play.setPrefSize(100,50);
        play.setStyle("-fx-background-color: MediumSeaGreen");
        pane.getChildren().add(play);

        Button settings = new Button("Settings");
        settings.setLayoutX(300);
        settings.setLayoutY(260);
        settings.setPrefSize(100,50);
        settings.setStyle("-fx-background-color: ANTIQUEWHITE");
        pane.getChildren().add(settings);

        Button hof = new Button("Best plays");
        hof.setLayoutX(300);
        hof.setLayoutY(390);
        hof.setPrefSize(100,50);
        hof.setStyle("-fx-background-color: yellow");
        pane.getChildren().add(hof);

        hof.setOnMouseClicked(evt3->{
            pane.getChildren().clear();
            ArrayList<Integer> scores = new ArrayList<>();
            try {
                FileReader fr = new FileReader(BirdGame.hofFile);
                Scanner reader = new Scanner(fr);

                while(reader.hasNext()){
                    scores.add(reader.nextInt());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            for (int i = 0; i < scores.size(); i++) {
                for (int j = i; j < scores.size(); j++) {
                    if(scores.get(j) > scores.get(i)){
                        int buff = scores.get(i);
                        scores.set(i,scores.get(j));
                        scores.set(j,buff);
                    }
                }
            }

            Label labels[] = new Label[scores.size()];
            int y = (700-scores.size()*20)/(scores.size()+1);
            for (int i = 0; i < scores.size(); i++) {
                labels[i] = new Label(String.valueOf(scores.get(i)));
                labels[i].setLayoutY(y*(i+1) + 20*i);
                labels[i].setLayoutX(325);
                labels[i].setPrefSize(50,20);
                labels[i].setFont(new Font(20));
                labels[i].setStyle("-fx-background-color: FLORALWHITE");
                pane.getChildren().add(labels[i]);
            }

            if(labels.length > 0){
                labels[0].setStyle("-fx-background-color: GOLD");
            }

            if(labels.length > 1){
                labels[1].setStyle("-fx-background-color: SILVER");
            }

            if(labels.length > 2){
                labels[2].setStyle("-fx-background-color: BURLYWOOD");
            }

            Button exitGame = new Button("Exit");
            exitGame.setPrefSize(100,50);
            exitGame.setLayoutY(20);
            exitGame.setLayoutX(20);
            exitGame.setStyle("-fx-background-color: coral");
            pane.getChildren().add(exitGame);
            exitGame.setOnMouseClicked(evt5->{
                setMenu();
            });


        });


        Button exit = new Button("Exit");
        exit.setLayoutX(300);
        exit.setLayoutY(520);
        exit.setPrefSize(100,50);
        exit.setStyle("-fx-background-color: PINK");
        pane.getChildren().add(exit);

        pane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.EMPTY)));

        exit.setOnMouseClicked(evt->{
            System.exit(0);
        });

        settings.setOnMouseClicked(evt2->{
            pane.getChildren().clear();

            Label yourCross = new Label("Your cross : ");
            yourCross.setFont(new Font(25));
            yourCross.setPrefSize(250,50);
            yourCross.setLayoutY(75);
            yourCross.setLayoutX(200);
            pane.getChildren().add(yourCross);

            ImageView crossView = new ImageView(cross);
            crossView.setFitWidth(50);
            crossView.setFitHeight(50);
            Button crossButton = new Button();
            crossButton.setPrefSize(100,100);
            crossButton.setLayoutX(450);
            crossButton.setLayoutY(60);
            crossButton.setGraphic(crossView);
            pane.getChildren().add(crossButton);




            Image img1 = new Image("cross_black.png");
            ImageView view1 = new ImageView(img1);
            view1.setFitHeight(100);
            view1.setPreserveRatio(true);

            Image img2 = new Image("cross_white.png");
            ImageView view2 = new ImageView(img2);
            view2.setFitHeight(100);
            view2.setPreserveRatio(true);

            Image img3 = new Image("cross_blue.png");
            ImageView view3 = new ImageView(img3);
            view3.setFitHeight(100);
            view3.setPreserveRatio(true);

            Button button1 = new Button();
            Button button2 = new Button();
            Button button3 = new Button();

            button1.setLayoutX(100);
            button1.setLayoutY(300);
            button1.setPrefSize(100,100);

            button1.setOnMouseClicked(evt4->{
                cross = "cross_black.png";
                ImageView imageView = new ImageView(cross);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                crossButton.setGraphic(imageView);
            });

            button2.setLayoutX(300);
            button2.setLayoutY(300);
            button2.setPrefSize(100,100);
            button2.setOnMouseClicked(evt4->{
                cross = "cross_white.png";
                ImageView imageView = new ImageView(cross);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                crossButton.setGraphic(imageView);
            });

            button3.setLayoutX(500);
            button3.setLayoutY(300);
            button3.setPrefSize(100,100);
            button3.setOnMouseClicked(evt4->{
                cross = "cross_blue.png";
                ImageView imageView = new ImageView(cross);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                crossButton.setGraphic(imageView);
            });

            button1.setGraphic(view1);
            button2.setGraphic(view2);
            button3.setGraphic(view3);

            pane.getChildren().add(button1);
            pane.getChildren().add(button2);
            pane.getChildren().add(button3);

            Button back = new Button("Back to menu ");
            Font font = new Font(25);
            back.setFont(font);
            back.setPrefSize(250,50);
            back.setLayoutY(575);
            back.setLayoutX(225);
            pane.getChildren().add(back);
            back.setOnMouseClicked(evt3->{
                setMenu();
            });
        });

        play.setOnMouseClicked(evt4->{
            pane.getChildren().clear();
            BirdGame birdGame = null;

            birdGame = new BirdGame(cross);

            pane.getChildren().add(birdGame);
            Button exitGame = new Button("Exit");
            exitGame.setPrefSize(100,50);
            exitGame.setLayoutY(20);
            exitGame.setLayoutX(20);
            exitGame.setStyle("-fx-background-color: coral");
            pane.getChildren().add(exitGame);
            exitGame.setOnMouseClicked(evt5->{
                setMenu();
            });
        });

    }

    public static void main(String[] args) {
        launch();
    }

}