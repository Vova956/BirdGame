package org.example;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite extends ImageView {
    private double width;
    private double height;

    public Sprite(String filename , double locationX, double locationY, double width, double height){
        super();
        this.width = width;
        this.height = height;

        setImage(new Image(filename));
        setPreserveRatio(true);

        setFitHeight(height);
        setFitWidth(width);

        setLayoutX(locationX);
        setLayoutY(locationY);
    }

    public void move(double speedX,double speedY){
        setLayoutX(speedX + getLayoutX());
        setLayoutY(speedY + getLayoutY());
    }

    public boolean isIntersects(Sprite sprite){
        double x = Math.abs(getLayoutX() - sprite.getLayoutX());
        double y = Math.abs(getLayoutY() - sprite.getLayoutY());

        return x < width && y < height;
    }




}