package MediaCenter;

import java.io.File;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.beans.property.DoubleProperty;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.GridPane;
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
import javafx.util.Duration;

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

        // Create a MediaCenter.Media Player
        final MediaPlayer player = new MediaPlayer(media);
        // Automatically begin the playback
        player.setAutoPlay(true);

        //change width and height to fit video
        final MediaView mediaView = new MediaView(player);
        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);

        // MediaCenter.Media Ready
        player.setOnReady(() -> {
            System.out.println("media ready");
            // Create Time Slider
            Slider timeSlider = new Slider(0.0,player.getTotalDuration().toSeconds(),0.0);

            // Create Volume Slider
            final Slider volumeSlider = new Slider(0.0, 1.0, 0.5);
            volumeSlider.setMajorTickUnit(0.01);

            // Create the DropShadow effect, not sure if necessary
            DropShadow dropshadow = new DropShadow();
            dropshadow.setOffsetY(5.0);
            dropshadow.setOffsetX(5.0);
            dropshadow.setColor(Color.WHITE);
            mediaView.setEffect(dropshadow);

            // Bind Properties
            player.volumeProperty().bind(volumeSlider.valueProperty());

            // Listen to the slider. When it changes, seek with the player.
            // MediaPlayer.seek does nothing when the player is stopped, so the play/pause button's handler
            // always sets the start time to the slider's current value, and then resets it to 0 after starting the player.
            InvalidationListener sliderChangeListener = o-> {
                Duration seekTo = Duration.seconds(timeSlider.getValue());
                player.seek(seekTo);
            };
            timeSlider.valueProperty().addListener(sliderChangeListener);

            // Link the player's time to the slider
            player.currentTimeProperty().addListener(l-> {
                // Temporarily remove the listener on the slider, so it doesn't respond to the change in playback time
                // I thought timeSlider.isValueChanging() would be useful for this, but it seems to get stuck at true
                // if the user slides the slider instead of just clicking a position on it.
                timeSlider.valueProperty().removeListener(sliderChangeListener);

                // Keep timeText's text up to date with the slider position.
                Duration currentTime = player.getCurrentTime();
                int value = (int) currentTime.toSeconds();
                timeSlider.setValue(value);

                // Re-add the slider listener
                timeSlider.valueProperty().addListener(sliderChangeListener);
            });

            // Create the GridPane Volume
            GridPane sliderPaneVol = new GridPane();
            sliderPaneVol.setStyle("-fx-background-color:WHITE");

            // Create the GridPane Time
            GridPane sliderPaneTime = new GridPane();
            sliderPaneTime.setStyle("-fx-background-color:WHITE");

            // Add the details to the GridPane
            Label labelVolume  = new Label("Volume");
            sliderPaneVol.addRow(0, labelVolume, volumeSlider);
            Label labelTime  = new Label("Time");
            sliderPaneTime.addRow(0, labelTime, timeSlider);

            // Create the Buttons
            Button playButton = new Button("Play");
            Button pauseButton = new Button("Pause");
            Button replayButton = new Button("Replay");
            Button closeButton = new Button("Close");

            // Create the Event Handlers for the Button
            playButton.setOnAction(new EventHandler <ActionEvent>()
            {
                public void handle(ActionEvent event)
                {
                    if (player.getStatus() == Status.PLAYING)
                    {
                        //
                    }
                    else
                    {
                        player.play();
                    }
                }
            });

            replayButton.setOnAction(new EventHandler <ActionEvent>()
            {
                public void handle(ActionEvent event)
                {
                    player.seek(player.getStartTime());
                }
            });

            pauseButton.setOnAction(new EventHandler <ActionEvent>()
            {
                public void handle(ActionEvent event)
                {
                    player.pause();
                }
            });

            closeButton.setOnAction(new EventHandler <ActionEvent>()
            {
                public void handle(ActionEvent event)
                {
                    stage.close();
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
                    // Handle asynchronous error in MediaCenter.Media object.
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
            HBox controlBox = new HBox(5, sliderPaneVol, playButton, pauseButton, replayButton,closeButton, sliderPaneTime);
            controlBox.setAlignment(Pos.BASELINE_CENTER);

            // Create the VBox
            VBox root = new VBox(-40,mediaView,controlBox/*,messageArea*/);
            root.setAlignment(Pos.BASELINE_CENTER);

            // Create the Scene
            Scene scene = new Scene(root, 1200, 675);
            // Add the scene to the Stage
            stage.setScene(scene);
            // Set the title of the Stage
            stage.setTitle("MediaCenter.Media Content");
            // Display the Stage
            stage.show();
        });


        // If we want to do stuff no fim
        player.setOnEndOfMedia(() -> {
            System.out.println("end of media");
        });
    }

    private void printMessage(MediaException error)
    {
        MediaException.Type errorType = error.getType();
        String errorMessage = error.getMessage();
        messageArea.appendText("\n" + "Type:" + errorType + ", error mesage:" + errorMessage);
    }
}