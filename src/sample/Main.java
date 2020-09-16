package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Main extends Application {

    private int foisClique = 0;

    MediaPlayer mediaPlayer;

    public void music() {
        String s = "cookieClickerTheme.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.73);
        mediaPlayer.play();
    }

    public void musicRainbow() {
        String s = "cookieClickerRainbowTheme.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.8);
        mediaPlayer.play();
    }

    public void stopMusique(){
        mediaPlayer.stop();
    }

    int valeurClique = 1;
    int prix1 = 10;
    int prix2 = 100;
    int prix5 = 5000;
    int clics1 = 1;
    int clics2 = 1;

    @Override
    public void start(Stage window) {

        window.setHeight(600);
        window.setWidth(800);
        window.setTitle("Cookie Clicker");
        Label cookieNumber = new Label("");

        Image cookieImage = new Image("cookie3image.png");
        ImageView viewCookie = new ImageView(cookieImage);
        Image cookieImageRainbow = new Image("cookieimagerainbow.png");
        ImageView viewCookieRainbow = new ImageView(cookieImageRainbow);
        Image cookieImageFire = new Image("cookieimagefire.png");
        ImageView viewCookieFire = new ImageView(cookieImageFire);

        Button boutonCookie = new Button();
        boutonCookie.setStyle("-fx-background-color: #6495ED; ");

        Button shop1 = new Button();
        Button shop2 = new Button();
        Button shop3 = new Button();
        Button shop4 = new Button();
        Button shop5 = new Button();

        shop1.setPrefSize(250, 65);
        shop2.setPrefSize(250, 65);
        shop3.setPrefSize(250, 65);
        shop4.setPrefSize(250, 65);
        shop5.setPrefSize(250, 65);
        shop1.setTranslateX(450);
        shop1.setTranslateY(190);
        shop2.setTranslateX(450);
        shop2.setTranslateY(255);
        shop3.setTranslateX(450);
        shop3.setTranslateY(320);
        shop4.setTranslateX(450);
        shop4.setTranslateY(385);
        shop5.setTranslateX(450);
        shop5.setTranslateY(450);

        shop1.setText("Valeur du clique +1 (" + prix1 + ")");
        shop2.setText("Double tes cookies, mais à quel prix? (" + prix2 + ")");
        shop3.setText("Débloquer Musique (50)");
        shop4.setText("Cookies Arc-en-ciel (400)");
        shop5.setText("Surprise !?!? ("+ prix5 +")");
        shop4.setDisable(true);

        cookieNumber.setScaleX(5);
        cookieNumber.setScaleY(5);
        cookieNumber.setTranslateX(206);
        cookieNumber.setTranslateY(120);

        boutonCookie.setTranslateX(130);
        boutonCookie.setTranslateY(280);
        boutonCookie.setPrefSize(cookieImage.getWidth(), cookieImage.getHeight());
        boutonCookie.setGraphic(viewCookie);
        boutonCookie.getBoundsInLocal();

        boutonCookie.setOnAction(event -> {
            foisClique += valeurClique;
            cookieNumber.setText(foisClique + " Cookies");
        });

        shop1.setOnAction(event -> {
            if (foisClique >= prix1) {
                valeurClique++;
                foisClique -= prix1;
                clics1 += 1;
                cookieNumber.setText(foisClique + " Cookies");
                prix1 = (int) (prix1 * (Math.pow(1.1, clics1)));
                shop1.setText("Valeur du clique +1 (" + prix1 + ")");
            }
        });

        shop2.setOnAction(event -> {
            if (foisClique >= prix2) {
                foisClique = foisClique * 2;
                clics2 += 1;
                cookieNumber.setText(foisClique + " Cookies");
                prix2 = (int) (prix2 * (Math.pow(2.8, clics2)));
                shop2.setText("Cookies!!! (" + prix2 + ")");
            }
        });

        shop3.setOnAction(event -> {
            if (foisClique >= 50) {
                music();
                shop3.setText("Quelle belle musique!");
                foisClique -= 50;
                cookieNumber.setText(foisClique + " Cookies");
                shop3.setDisable(true);
                shop4.setDisable(false);
            }
        });

        shop4.setOnAction(event -> {
            if (foisClique >= 400){
                boutonCookie.setGraphic(viewCookieRainbow);
                foisClique += 1600;
                musicRainbow();
                shop4.setText("WOAAAHHHhHHHH!!!!!!");
                cookieNumber.setText(foisClique + " Cookies");
                shop4.setDisable(true);
            }
        });

        shop5.setOnAction(event -> {
            if (foisClique >= prix5) {
                boutonCookie.setGraphic(viewCookieFire);
                foisClique -= prix5;
                foisClique = 0;
                boutonCookie.setDisable(true);
                cookieNumber.setText(foisClique + " Cookies");
                shop5.setText("Game Over...");
                stopMusique();
            }
        });

        Group root = new Group(boutonCookie, cookieNumber, shop1, shop2, shop3, shop4, shop5);
        Scene scene = new Scene(root, 595, 170, Color.CORNFLOWERBLUE);

        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}