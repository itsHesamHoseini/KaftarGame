package com.example.kaftargame;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player {
    public Player(){


    }
    public void createAttackTransition(ImageView imageView) {



        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), imageView);
        transition.setByY(-50);
        transition.setByX(20);
        transition.play();

        TranslateTransition transition2 = new TranslateTransition(Duration.seconds(1), imageView);
        transition2.setByY(50);
        transition2.setByX(-20);
        transition2.play();


//        transition.setByY(50);
//        transition.setByX(-20);
//        transition.play();

//        imageView.setX(imageView.getX()+(-20));
//        imageView.setY(imageView.getY()+(50));


//        return this;
    }

//    public Player createAttackBackTransition(ImageView imageView) {
//        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), imageView);
//        transition.setByY(50);
//        transition.setByX(-20);
//        return this;
//    }
}
