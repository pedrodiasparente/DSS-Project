import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaErrorEvent;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JavaFX extends Application
{
    // Create the Area for Logging
    private TextArea messageArea = new TextArea();
    private String Dir = System.getProperty("user.dir");
    public static void main(String[] args) throws Exception{
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //goes to user Directory
        File f = new File(Dir, "heydarren.mp4");


        //Converts media to string URL
        Media media = new Media(f.toURI().toURL().toString());

        // Create a Media Player
        final MediaPlayer player = new MediaPlayer(media);
        // Automatically begin the playback
        player.setAutoPlay(true);

        // Create a 400X300 MediaView
        final MediaView mediaView = new MediaView(player);
        mediaView.setFitWidth(400);
        mediaView.setFitHeight(300);
        mediaView.setSmooth(true);

        // Create the DropShadow effect
        DropShadow dropshadow = new DropShadow();
        dropshadow.setOffsetY(5.0);
        dropshadow.setOffsetX(5.0);
        dropshadow.setColor(Color.WHITE);

        mediaView.setEffect(dropshadow);

        // Create the Buttons
        Button playButton = new Button("Play");
        Button stopButton = new Button("Stop");

        // Create the Event Handlers for the Button
        playButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                if (player.getStatus() == Status.PLAYING)
                {
                    player.stop();
                    player.play();
                }
                else
                {
                    player.play();
                }
            }
        });

        stopButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                player.stop();
            }
        });

        // Create Handlers for handling Errors
        player.setOnError(new Runnable()
        {
            public void run()
            {
                // Handle asynchronous error in Player object.
                printMessage(player.getError());
            }
        });

        media.setOnError(new Runnable()
        {
            public void run()
            {
                // Handle asynchronous error in Media object.
                printMessage(media.getError());
            }
        });

        mediaView.setOnError(new EventHandler <MediaErrorEvent>()
        {
            public void handle(MediaErrorEvent event)
            {
                // Handle asynchronous error in MediaView.
                printMessage(event.getMediaError());
            }
        });

        // Create the HBox
        HBox controlBox = new HBox(5, playButton, stopButton);

        // Create the VBox
        VBox root = new VBox(5,mediaView,controlBox,messageArea);

        // Set the Style-properties of the HBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Create the Scene
        Scene scene = new Scene(root);
        // Add the scene to the Stage
        stage.setScene(scene);
        // Set the title of the Stage
        stage.setTitle("Handling Media Errors");
        // Display the Stage
        stage.show();
    }

    private void printMessage(MediaException error)
    {
        MediaException.Type errorType = error.getType();
        String errorMessage = error.getMessage();
        messageArea.appendText("\n" + "Type:" + errorType + ", error mesage:" + errorMessage);
    }
}