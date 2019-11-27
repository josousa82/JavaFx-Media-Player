package app;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;



public class Main extends Application {


    Player player;
    FileChooser fileChooser;

    private static MenuItem open;
    private static MenuItem exit;
    private static Menu file;
    private static MenuBar menu;

    Stage primaryStage;
    MediaPlayer mediaPlayer;
    Scene scene;

    // just to make it work
    URL mediaUrl = getClass().getResource("bunny.mp4");
    String MEDIA_URL = mediaUrl.toExternalForm();

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        topMenuBuilder();

        player = new Player(MEDIA_URL);

        player.setTop(menu);

        scene = new Scene(player, 720, 510, Color.BLACK);

        clickButtom();

        this.primaryStage.setScene(scene);

        this.primaryStage.show();



    }

    private void topMenuBuilder(){

        open = new MenuItem("Open");
        exit = new MenuItem("Exit");
        file = new Menu("File");
        menu = new MenuBar();
        file.getItems().add(open);

        //added to menu bar
        file.getItems().add(exit);
        menu.getMenus().add(file);

        openMenu();
        exitMenu();

    }

    private void openMenu(){

        fileChooser = new FileChooser();
        open.setOnAction(e -> {
            player.player.pause();
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file != null){
                try {
                    player = new Player(file.toURI().toURL().toExternalForm());
                    Scene scene = new Scene(player, 720, 535, Color.BLACK);
                    primaryStage.setScene(scene);
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
            }

        });

    }

    private void exitMenu(){
        exit.setOnAction(event -> {
            primaryStage.close();
        });
    }

    protected void clickButtom(){

        scene.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player.bar.playButton();
            }
        });
    }

    public static void main(String[] args) {

        launch(args);
    }
}
