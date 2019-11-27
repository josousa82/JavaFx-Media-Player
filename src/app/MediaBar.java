package app;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;

// split events in single private static methods that are called by the constructor
public class MediaBar extends HBox {

    Slider time = new Slider();
    Slider vol = new Slider();
    Button playButton = new Button("||");
    Label volume = new Label("Volume: ");
    MediaPlayer player;


    public MediaBar(MediaPlayer play) {

        player = play;

        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));

        vol.setPrefWidth(70);
        vol.setMinWidth(30);
        vol.setValue(100);

        HBox.setHgrow(time, Priority.ALWAYS);

        playButton.setPrefWidth(30);

        getChildren().add(playButton);
        getChildren().add(time);
        getChildren().add(volume);
        getChildren().add(vol);

        playButton();

        // make time slider run
        updateTimeSliderValue();

        //Jump in time in the video
        jumpSliderValue();

        //vol slider
        volSlider();

    }


    protected void updateTimeSliderValue() {
        player.currentTimeProperty().addListener(observable -> Platform.runLater(() -> time.setValue(
                player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100)));
    }


    protected void jumpSliderValue() {
        time.valueProperty().addListener(observable -> {
            if (time.isPressed()) {
                player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));
            }
        });
    }

    protected void volSlider(){
        vol.valueProperty().addListener(observable -> {
            if (vol.isPressed()) {
                player.setVolume(vol.getValue() / 100);
            }
        });
    }



    protected void playButton(){

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                MediaPlayer.Status status = player.getStatus();

                if (status == MediaPlayer.Status.PLAYING) {

                    if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) {

                        player.seek(player.getStartTime());
                        player.play();

                    } else {

                        player.pause();
                        playButton.setText(">");
                    }

                }
                if (status == MediaPlayer.Status.PAUSED ||
                        status == MediaPlayer.Status.HALTED ||
                        status == MediaPlayer.Status.STOPPED) {

                    player.play();
                    playButton.setText("||");
                }
            }
        });
    }

    /*public void playPauseOnClick(EventHandler<MouseEvent> mouseEventEventHandler) {
        //pause video if click on viewing area

        mouseEventEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MediaPlayer.Status status = player.getStatus();
                if(status == MediaPlayer.Status.PLAYING) {
                    player.pause();
                    playButton.setText(">");
                }else if(status == MediaPlayer.Status.PAUSED ||
                        status == MediaPlayer.Status.HALTED ||
                        status == MediaPlayer.Status.STOPPED){
                    player.play();
                    playButton.setText("||");
                }
            }
        };

    }*/

    public void fullScreenButton(){
        //add button to make video full screen
    }

    protected void resizeVideoToWindowSize(){
        //Resize video to window size

    }

}
