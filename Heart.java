package com.example.kaftargame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Heart
{
    public String URL;
    public Double LayoutX;
    public Double LayoutY;
    public ImageView heartView;

    public Heart(String URL,Double LayoutX , Double LayoutY){
        this.URL=URL;
        this.LayoutX=LayoutX;
        this.LayoutY=LayoutY;
    }

    public ImageView MakeHeart(){
        Image heart = new Image(URL);
        heartView = new ImageView(heart);
        heartView.setLayoutX(LayoutX);
        heartView.setLayoutY(LayoutY);
        return heartView;
    }
    public ImageView ReturnImageView(){
        return heartView;
    }
}
