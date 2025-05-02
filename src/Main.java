import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javax.swing.SwingUtilities;
import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Video abspielen
        Media media = new Media(new File("resources/choclate.mp4").toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setMute(true);
        player.play();

        MediaView mediaView = new MediaView(player);
        mediaView.setPreserveRatio(false);
        mediaView.setFitWidth(1280);
        mediaView.setFitHeight(720);

        Scene scene = new Scene(new javafx.scene.Group(mediaView), 1280, 720);
        stage.setTitle("Mind4Math Hintergrund");
        stage.setScene(scene);
        stage.setAlwaysOnTop(false);
        stage.setX(0);
        stage.setY(0);
        stage.show();

        // Swing-GUI starten
        SwingUtilities.invokeLater(() -> new Mind4MathUI().setVisible(true));
    }

    public static void main(String[] args) {
        launch(args);
    }
}





/*
public class Main {
    public static void main(String[] args) {
        new Mind4MathUI();
        
    }
}
*/