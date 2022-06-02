package org.example;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.awt.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class BirdGame extends Pane{
    public static String hofFile = "src/main/java/org/example/HOF.txt";
    private Sprite cross;
    private double time = 0.1;
    private int score = 0;
    private ArrayList<Sprite> birdsFromLeft;
    private ArrayList<ImageView> bullets;
    private double birdSpeed = 10;
    private double bulletTime = 1;
    private FileWriter fw;
    private FileReader fr;
    private Scanner reader;
    private ArrayList<String> data = new ArrayList<>();



    public BirdGame(String cross){
        super.setPrefSize(700,700);

        fw = null;
        fr = null;

        try {

            fr = new FileReader(hofFile);
            reader = new Scanner(fr);
            while(reader.hasNext()){
                data.add(reader.nextLine());
            }
            reader.close();
            fr.close();


            fw = new FileWriter(hofFile);

            for (int i = 0; i < data.size(); i++) {
                fw.write(data.get(i) + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        Image img = new Image("background2.jpg");
        ImageView imageView = new ImageView(img);
        imageView.relocate(0,0);
        imageView.setPreserveRatio(true);
        getChildren().add(imageView);

        Label playerScore = new Label("Your score : " + score);
        playerScore.setFont(new Font(30));
        playerScore.setLayoutX(200);
        playerScore.setLayoutY(20);
        playerScore.setPrefSize(200,50);
        playerScore.setStyle("-fx-text-fill: MAGENTA");
        getChildren().add(playerScore);

        Sprite crossSprite = new Sprite(cross,325,325,50,50);
        crossSprite.setLayoutX(MouseInfo.getPointerInfo().getLocation().getX()-355*2);
        crossSprite.setLayoutY(MouseInfo.getPointerInfo().getLocation().getY()-50*2);
        getChildren().add(crossSprite);

        birdsFromLeft = new ArrayList<>();


        for (int i = 0; i < 4; i++) {
            String str1 = "bird" + (2+(int)(Math.random()*3)) + ".gif";
            Sprite bird1 = new Sprite(str1,-70,100+(Math.random()*400),300,70);
            bird1.setOnMouseClicked(evt3->{
                if(bullets.size() > 0) {
                    score++;
                    playerScore.setText("Your score : " + score);
                    bird1.setLayoutX(-100);
                    bird1.setLayoutY(100+(Math.random()*400));
                    birdSpeed += 0.2;
                    getChildren().remove(bullets.get(bullets.size()-1));
                    bullets.remove(bullets.get(bullets.size()-1));
                    bulletTime = 1;
                }
            });
            birdsFromLeft.add(bird1);
            getChildren().add(birdsFromLeft.get(i));
        }

        int y = 600;
        int x = 640;
        bullets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Image img2 = new Image("bullet3.png");
            ImageView imageView1 = new ImageView(img2);
            imageView1.setFitWidth(90);
            imageView1.setFitHeight(120);
            imageView1.setPreserveRatio(true);
            imageView1.setLayoutY(y);
            imageView1.setLayoutX(x);
            x-=50;
            bullets.add(imageView1);
            getChildren().add(bullets.get(i));
        }

        AnimationTimer animationTimer1 = new AnimationTimer() {
            @Override
            public void handle(long l) {
                bulletTime -= 0.01;
                if(bulletTime <= 0 && bullets.size() < 5){
                    bulletTime = 1;

                    Image img2 = new Image("bullet3.png");
                    ImageView imageView1 = new ImageView(img2);
                    imageView1.setFitWidth(90);
                    imageView1.setFitHeight(120);
                    imageView1.setPreserveRatio(true);

                    if(bullets.size() == 0){
                        imageView1.setLayoutX(640);
                        imageView1.setLayoutY(600);
                    }

                    else{
                        imageView1.setLayoutY(600);
                        imageView1.setLayoutX(bullets.get(bullets.size()-1).getLayoutX()-50);
                    }

                    bullets.add(imageView1);
                    getChildren().add(bullets.get(bullets.size()-1));
                }
            }
        };
        animationTimer1.start();



        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                time -= 0.01;
                if(time <= 0){
                    time = 0.1;

                    for (Sprite bird : birdsFromLeft) {
                        bird.move(birdSpeed,0);
                        if(bird.getLayoutX() >= 680){

                            getChildren().clear();

                            Label playerScore = new Label("Your score : " + score);
                            playerScore.setFont(new Font(30));
                            playerScore.setLayoutX(250);
                            playerScore.setLayoutY(325);
                            playerScore.setPrefSize(200,50);
                            playerScore.setStyle("-fx-text-fill: MAGENTA");
                            getChildren().add(playerScore);

                            try {


                                fw.write(String.valueOf(score));
                                fw.write("\n");
                                fw.close();
                                this.stop();
                                animationTimer1.stop();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }



                            break;
                        }
                    }

                }

                crossSprite.setLayoutX(MouseInfo.getPointerInfo().getLocation().getX()-630);
                crossSprite.setLayoutY(MouseInfo.getPointerInfo().getLocation().getY()-150);
            }
        };
        animationTimer.start();
















    }





}
