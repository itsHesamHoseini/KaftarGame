package com.example.kaftargame;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
//import org.giffx.core.GifImageView;

import java.lang.reflect.Array;
import java.util.Random;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.*;


// its_hesamhoseini - Pooya Amini - MohammadHoseinEkrami


public class Game extends Application {
    private long lastUpdate = 0;
    private double backgroundX = -20;





//    public Enemies enemy1;


//    public Enemies[] enemies;
    public ArrayList<Enemies> Enemies=new ArrayList<Enemies>();;



    private final double startX = 70;
    private final double endX = 400;

//    private final double MovingX=(double)((int)(1800/7));
    private ImageView characterImage2;
    private ImageView backgroundImageViewBottom;
    private Image backgroundImageBottom;
    private double SpeedOfGame=200;
    public ImageView heartView,heartView2,heartView3,heartView4,heartView5;

    private double speed=0.300;
    private int Point=0;
    public int myHeart=5;

    private double originalX, originalY;


    int[] X_Lists;
    public ArrayList<Heart> X_Heart;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("hello-view.fxml"));
        VBox root = fxmlLoader.load();
        Pane pane = new Pane();

        // We Add A background from Resource folder!
        Image backgroundImage = new Image("background2.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        // We Set Layout{x,y}
        backgroundImageView.setLayoutX(-20);
        backgroundImageView.setLayoutY(-20);
        // We Set Size for our background!
        backgroundImageView.setFitWidth(1920);
        backgroundImageView.setFitHeight(1080);
        // i don't know it :))
        backgroundImageView.setPreserveRatio(false);
        backgroundImageView.setSmooth(true);


        // We Add Ground in our game
        backgroundImageBottom = new Image("down2.png");
        backgroundImageViewBottom = new ImageView(backgroundImageBottom);
//        backgroundImageViewBottom.setY(800);
//        backgroundImageViewBottom.setX(200);
        backgroundImageViewBottom.setLayoutX(0-20);
        backgroundImageViewBottom.setLayoutY(0-20);
        // We Set width = 3840 and it's 2x of Form Width
        backgroundImageViewBottom.setFitWidth(3840);
        backgroundImageViewBottom.setFitHeight(1080);
        // It's from the internet ; what is this?
        backgroundImageViewBottom.setPreserveRatio(false);
        backgroundImageViewBottom.setSmooth(true);





        Image characterImage = new Image("01.png");
        characterImage2 = new ImageView(characterImage);
        characterImage2.setFitWidth(200);
        characterImage2.setFitHeight(200);
        characterImage2.setLayoutX(startX);
        characterImage2.setLayoutY(650);
        originalX = characterImage2.getLayoutX();
        originalY = characterImage2.getLayoutY();


//        HeartAndPoint _HeartAndPoint = new HeartAndPoint();







        X_Lists=new int[]{0,1*130,2*130,3*130,4*130};
        X_Heart = new ArrayList<>();
        for (int i: X_Lists) {
            X_Heart.add(new Heart("heart.png",(double)(i),(double)(0)));
            X_Heart.get(X_Heart.size()-1).MakeHeart();
        }


        /// This Code is for Show PointBar
        Image PointField = new Image("PointField.png");
        ImageView PointPhoto = new ImageView(PointField);
        PointPhoto.setLayoutX(0);
        PointPhoto.setLayoutY(150);
        PointPhoto.setFitWidth(1871/4.2);
        PointPhoto.setFitHeight(585/4.2);

        /// This Code is for Show PointLabel
        Text text = new Text("Point: "+Point);
        text.setFont(Font.font("Arial", 32));
        text.setFill(Color.WHITE);
        text.setLayoutX(200);
        text.setLayoutY(220);

        /// This code is for Add ImagesView to pane
        for (ImageView i : new ImageView[]{backgroundImageView,backgroundImageViewBottom,characterImage2,PointPhoto}) {
                pane.getChildren().add(i);
        }
        for(int i = 0 ; i < X_Heart.size() ; i++) {
            pane.getChildren().add(
                    X_Heart.get(i).ReturnImageView()
            );
        }


        Random rand = new Random();
//        int randomNumber = rand.nextInt(2) + 1;
        int randomNumber=1;
        Enemies=new ArrayList<Enemies>();
        for(int i =0 ;i < randomNumber ; i++) {

            int randomNumber2 = new Random().nextInt(201) + 1800;

            Enemies en = new Enemies("013-enemy.png", randomNumber2, 650);
            Enemies.add(en);
            Enemies.get( Enemies.size()-1 ).MakeEnemy();

            pane.getChildren().add(Enemies.get( Enemies.size()-1 ).ReturnImageView() );
        }


        /// this code is for add text to pane
        pane.getChildren().add(text);


        /// this code is for add pane to root

        root.getChildren().add(pane);
        /// we created a Scene
        Scene scene = new Scene(root, 1920, 1080);







        /// Event for click Mouse => {RightClick and LeftClick}
        scene.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                PauseTransition pause = new PauseTransition(Duration.millis(300));
                pause.setOnFinished(pauseEvent -> {
                    if (event.getClickCount() == 1) {

                        double enemy_loc;
                        double my_loc = characterImage2.getLayoutX() + characterImage2.getTranslateX();
                        for (int i = 0; i < Enemies.size(); i++) {

                            enemy_loc = Enemies.get(i).ReturnImageView().getLayoutX() + Enemies.get(i).ReturnImageView().getTranslateX();

                            if (enemy_loc - my_loc <= 60 + 220/*Width of me*/) {
                                Enemies.get(i).ReturnImageView().setVisible(false);
                                text.setText("Point: " + (++Point));
                                speed /= 1.06;
                            }
                        }
                            /// ----------------
                            /// Shamshir Zadan
                            Image img = new Image("shamshir.png");
                            characterImage2.setImage(img);
                            TranslateTransition translate = new TranslateTransition();
                            translate.setNode(characterImage2);
                            translate.setDuration(Duration.millis(200));
                            translate.setCycleCount(2);
                            translate.setInterpolator(Interpolator.LINEAR);
                            translate.setAutoReverse(true);
                            translate.setByY(-100);
                            translate.setByX(40);
                            translate.play();
                            Timeline timeline = new Timeline(
                                    new KeyFrame(Duration.seconds(0.2), timelineEvent -> characterImage2.setImage(new Image("01.png")))
                            );
                            timeline.play();
                            /// ----------------
                    }
                });
                pause.play();
            }
            else if(event.getButton() == MouseButton.SECONDARY){
                PauseTransition pause = new PauseTransition(Duration.millis(300));
                pause.setOnFinished(pauseEvent -> {


                        if (event.getClickCount() == 1) {
                            double enemy_loc;
                            double my_loc = characterImage2.getLayoutX() + characterImage2.getTranslateX();

                for (int i = 0; i < Enemies.size(); i++) {
                    enemy_loc = Enemies.get(i).ReturnImageView().getLayoutX() + Enemies.get(i).ReturnImageView().getTranslateX();
                    if (enemy_loc - my_loc <= 60 + 220/*Width of me*/) {
                        Enemies.get(i).ReturnImageView().setVisible(false);
                        text.setText("Point: " + (++Point));
                        speed /= 1.06;
                    }
                }

                Image img = new Image("shamshir.png");
                characterImage2.setImage(img);
                TranslateTransition translate = new TranslateTransition();
                translate.setNode(characterImage2);
                translate.setDuration(Duration.millis(200));
                translate.setCycleCount(2);
                translate.setInterpolator(Interpolator.LINEAR);
                translate.setAutoReverse(true);
                translate.play();
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(0.2), e -> characterImage2.setImage( new Image("01.png") ))
                );
                timeline.play();
                            }
                });
                pause.play();
            }
        });
















        /// we set Timer and it run every 0.005ms

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                // Moving Ground and Enemies of game
                if (now - lastUpdate >= 0.005 ) {
                    TranslateTransition transition = new TranslateTransition(Duration.seconds(speed), backgroundImageViewBottom);
                    transition.setByX(-SpeedOfGame);
                    transition.play();


                    int min = (new Random()).nextInt((1000 - 700) + 1) + 700;
                    TranslateTransition transition2;

                    for (int i = 0; i < Enemies.size(); i++) {

                        if (Enemies.get(i).ReturnImageView() != null) {
                            transition2 = new TranslateTransition(Duration.seconds(speed), Enemies.get(i).ReturnImageView());
                            transition2.setByX(-SpeedOfGame);
                            transition2.play();
                            System.out.println("i:" + i);
//                            System.out.println(Enemies.get(i).ReturnImageView().getTranslateX());
                            System.out.println();

//                            if(Enemies.get(i) == null){
//                                continue;
//                            }

                            if (Enemies.get(i).ReturnImageView().getLayoutX() + Enemies.get(i).ReturnImageView().getTranslateX() - characterImage2.getLayoutX() - characterImage2.getTranslateX() < min) {
                                Enemies.get(i).ReturnImageView().setOpacity(0.2);
                            }


                            if (Enemies.get(i).ReturnImageView().getLayoutX() + Enemies.get(i).ReturnImageView().getTranslateX() < -200) {
                                if (Enemies.get(i).ReturnImageView().isVisible()) {
                                    --myHeart;
                                    if (myHeart == 0) {
                                        X_Heart.get(myHeart).ReturnImageView().setOpacity(0.05);
                                        System.out.println("Heart is Zero : " + myHeart);
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Game Over");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Game Over! Your Point Record : " + Point);
                                        alert.show();
                                        System.exit(1);
                                    } else {
                                        System.out.println("Heart : " + myHeart);
                                        X_Heart.get(myHeart).ReturnImageView().setOpacity(0.05);
                                    }
                                }
                                pane.getChildren().remove(Enemies.get(i).ReturnImageView());
                                Enemies.remove(i);
                                Enemies.add(new Enemies("013-enemy.png", new Random().nextInt(201) + 1800, 650));

                                pane.getChildren().add(Enemies.get(Enemies.size()-1).ReturnImageView());

                            }


                            }


                        }

                        if (backgroundImageViewBottom.getLayoutX() + backgroundImageViewBottom.getTranslateX() < -1920) {


                            backgroundImageViewBottom.setImage(null);

                            pane.getChildren().remove(backgroundImageViewBottom);
                            pane.getChildren().remove(characterImage2);


//                            for (int i2 = 0; i2 < Enemies.size(); i2++) {
//                                pane.getChildren().remove(Enemies.get(i2).ReturnImageView());
//                            }

                            backgroundImageBottom = new Image("down2.png");
                            backgroundImageViewBottom = new ImageView(backgroundImageBottom);
                            backgroundImageViewBottom.setLayoutX(-20);
                            backgroundImageViewBottom.setLayoutY(-20);

                            backgroundImageViewBottom.setFitWidth(3840);
                            backgroundImageViewBottom.setFitHeight(1080);
                            backgroundImageViewBottom.setPreserveRatio(false);
                            backgroundImageViewBottom.setSmooth(true);

                            pane.getChildren().add(backgroundImageViewBottom);
                            pane.getChildren().add(characterImage2);


//                            for (int i2 = 0; i2 < Enemies.size(); i2++) {
//                                pane.getChildren().add(Enemies.get(i2).ReturnImageView());
//                            }


                        }
                        lastUpdate = now;
//                    }
                }
            }
        };
        // Start the animation timer
        timer.start();
        stage.setScene(scene);
        stage.setTitle("Hello");
        stage.setFullScreen(true);
        stage.show();
    }





    public static void main(String[] args) {
        launch();
    }
}
