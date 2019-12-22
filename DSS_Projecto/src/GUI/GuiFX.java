package GUI;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static javafx.concurrent.Worker.State.FAILED;

public class GuiFX extends JFrame {

    private final JFXPanel jfxPanel = new JFXPanel();
    private Controller ctrl;
    private String Dir = System.getProperty("user.dir");


    private final JPanel panel = new JPanel(new BorderLayout());
    private final JLabel lblStatus = new JLabel();
    private String musica;

    private final JButton btnGo = new JButton("Go");
    private final JTextField txtURL = new JTextField();
    private final JProgressBar progressBar = new JProgressBar();

    public GuiFX(Controller controller) {
        super();
        ctrl = controller;
        musica = null;
        initComponents();
    }

    public void setMusica(String musica){
        this.musica = musica;
    }

    private void initComponents() {
        panel.add(jfxPanel, BorderLayout.CENTER);

        getContentPane().add(panel);
        setPreferredSize(new Dimension(1200, 675));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    void createScene() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                if (musica == null) {
                    return;
                }

                //goes to user Directory
                File f = new File(Dir, musica);


                //Converts media to string URL
                Media media = null;
                try {
                    media = new Media(f.toURI().toURL().toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

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
                    Slider timeSlider = new Slider(0.0, player.getTotalDuration().toSeconds(), 0.0);

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
                    InvalidationListener sliderChangeListener = o -> {
                        Duration seekTo = Duration.seconds(timeSlider.getValue());
                        player.seek(seekTo);
                    };
                    timeSlider.valueProperty().addListener(sliderChangeListener);

                    // Link the player's time to the slider
                    player.currentTimeProperty().addListener(l -> {
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
                    javafx.scene.control.Label labelVolume = new javafx.scene.control.Label("Volume");
                    sliderPaneVol.addRow(0, labelVolume, volumeSlider);
                    javafx.scene.control.Label labelTime = new javafx.scene.control.Label("Time");
                    sliderPaneTime.addRow(0, labelTime, timeSlider);

                    // Create the Buttons
                    javafx.scene.control.Button playButton = new javafx.scene.control.Button("Play");
                    javafx.scene.control.Button pauseButton = new javafx.scene.control.Button("Pause");
                    javafx.scene.control.Button replayButton = new javafx.scene.control.Button("Replay");
                    javafx.scene.control.Button closeButton = new Button("Close");

                    // Create the Event Handlers for the Button
                    playButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                        public void handle(javafx.event.ActionEvent event) {
                            if (player.getStatus() == MediaPlayer.Status.PLAYING) {
                                //
                            } else {
                                player.play();
                            }
                        }
                    });

                    replayButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                        public void handle(javafx.event.ActionEvent event) {
                            player.seek(player.getStartTime());
                        }
                    });

                    pauseButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                        public void handle(javafx.event.ActionEvent event) {
                            player.pause();
                        }
                    });

                    closeButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                        public void handle(javafx.event.ActionEvent event) {
                            player.pause();
                            player.seek(player.getStopTime());
                            ctrl.showReproduzirConteudo();
                        }
                    });

                    // Create the HBox
                    HBox controlBox = new HBox(5, sliderPaneVol, playButton, pauseButton, replayButton, closeButton, sliderPaneTime);
                    controlBox.setAlignment(Pos.BASELINE_CENTER);

                    // Create the VBox
                    VBox root = new VBox(-40, mediaView, controlBox/*,messageArea*/);
                    root.setAlignment(Pos.BASELINE_CENTER);

                    // Create the Scene
                    Scene scene = new Scene(root, 1200, 675);
                    // Add the scene to the Stage
                    jfxPanel.setScene(scene);
                    // Set the title of the Stage
                });


                // If we want to do stuff no fim
                player.setOnEndOfMedia(() -> {
                    System.out.println("end of media");
                    if(!ctrl.reproduzirConteudo())
                       ctrl.showReproduzirConteudo();
                });
            }
        });
    }

    public void exit(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                com.sun.javafx.application.PlatformImpl.tkExit();
                Platform.exit();
            }
        });
    }
}