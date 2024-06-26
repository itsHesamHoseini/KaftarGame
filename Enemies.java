package com.example.kaftargame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemies {

    public ImageView NewEnemyView;
    public String URL;
    public Double LayoutX;
    public Double LayoutY;

    public Enemies() {
        this("013-enemy.png", 2000, 650);
    }

    public Enemies(String URL,double LayoutX,double LayoutY){
        this.URL=URL;
        this.LayoutX=LayoutX;
        this.LayoutY=LayoutY;
    }

    public void MakeEnemy(){
        Image EnemyImage = new Image(URL);
        NewEnemyView = new ImageView(EnemyImage);
        NewEnemyView.setLayoutX(LayoutX);
        NewEnemyView.setLayoutY(LayoutY);
    }
    public ImageView ReturnImageView(){
        return NewEnemyView;
    }


}
