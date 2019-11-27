package app;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class Player extends BorderPane {

    Media media;
    MediaPlayer player;
    MediaView view;
    Pane mpane;
    MediaBar bar;


    public Player(String url) {
        mpane = new Pane();
        media = new Media(url);

        player = new MediaPlayer(media);
        view = new MediaView(player);
        view.setPreserveRatio(false);
        view.fitWidthProperty().bind(mpane.widthProperty());
        view.fitHeightProperty().bind(mpane.heightProperty());

        playPauseOnClick();



        bar = new MediaBar(player);
        setBottom(bar);
        setStyle("-fx-background-color: #bfc7c3 ");

        mpane.getChildren().add(view);
        setCenter(mpane);
        player.play();
    }

    private void playPauseOnClick(){

        view.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MediaPlayer.Status status = player.getStatus();
                if(status == MediaPlayer.Status.PLAYING) {
                    player.pause();
                    bar.playButton.setText(">");
                }else if(status == MediaPlayer.Status.PAUSED ||
                        status == MediaPlayer.Status.HALTED ||
                        status == MediaPlayer.Status.STOPPED){
                    player.play();
                    bar.playButton.setText("||");
                }
            }
        });

    }



}
