import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class MindAPP extends Application {

    private TextField feldA = new TextField();
    private TextField feldB = new TextField();
    private Label ergebnisLabel = new Label("");

    @Override
    public void start(Stage stage) {
        // 1. Video-Hintergrund
        String pfad = "resources/neurons.mp4";
        Media media = new Media(new File(pfad).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setMute(true);

        player.setOnReady(() -> player.play()); // Warten, bis Video bereit ist

  
        MediaView mediaView = new MediaView(player);
        mediaView.setPreserveRatio(false);

        // 2. Overlay
        Region overlay = new Region();
        overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        overlay.setMouseTransparent(true);

        // 3. GUI-Inhalt
        ImageView logo = new ImageView(new Image(new File("resources/mind4mathlogo2.png").toURI().toString()));
        logo.setFitWidth(100);
        logo.setPreserveRatio(true);
        logo.setOnMouseClicked(e -> getHostServices().showDocument("https://kasem-rashrash.com"));

        Label titel = new Label("Mind4Math");
        titel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #F5F5F5;"
                + "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.7), 3, 0, 0, 1);");

        Label beschreibung = new Label("Grundlagen der Mathematik und Informatik visuell anwenden");
        beschreibung.setStyle("-fx-font-size: 14px; -fx-text-fill: #DDDDDD;"
                + "-fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.5), 2, 0, 0, 1);");

        feldA.setPromptText("Zahl A");
        feldB.setPromptText("Zahl B");
        feldA.setMaxWidth(200);
        feldB.setMaxWidth(200);
        feldA.setStyle("-fx-background-color: white; -fx-border-color: #CCCCCC; -fx-font-size: 14px;");
        feldB.setStyle("-fx-background-color: white; -fx-border-color: #CCCCCC; -fx-font-size: 14px;");

        Button ggtBtn = createButton("ggT berechnen", "#7C4DFF", e -> berechneGGT());
        Button eeaBtn = createButton("EEA berechnen", "#7C4DFF", e -> berechneEEA());
        Button primBtn = createButton("Primzahl testen", "#7C4DFF", e -> testePrimzahl());
        Button kgvBtn = createButton("kgV berechnen", "#7C4DFF", e -> berechneKGV());
        Button fibBtn = createButton("Fibonacci berechnen", "#7C4DFF", e -> berechneFibonacci());
        Button fakBtn = createButton("Fakultät berechnen", "#7C4DFF", e -> berechneFakultaet());

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setAlignment(Pos.CENTER);

        buttonGrid.add(ggtBtn, 0, 0);
        buttonGrid.add(eeaBtn, 1, 0);

        buttonGrid.add(primBtn, 0, 1);
        buttonGrid.add(kgvBtn, 1, 1);

        buttonGrid.add(fibBtn, 0, 2);
        buttonGrid.add(fakBtn, 1, 2);
        ggtBtn.setPrefWidth(180);
        eeaBtn.setPrefWidth(180);
        primBtn.setPrefWidth(180);
        kgvBtn.setPrefWidth(180);
        fibBtn.setPrefWidth(180);
        fakBtn.setPrefWidth(180);



        ergebnisLabel.setStyle("-fx-background-color: rgba(255,255,255,0.1);"
                + "-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"
                + "-fx-padding: 12px 24px; -fx-border-color: #9C27B0;"
                + "-fx-border-width: 1; -fx-border-radius: 8; -fx-background-radius: 8;");
        ergebnisLabel.setMaxWidth(400);
        ergebnisLabel.setWrapText(true);
        ergebnisLabel.setAlignment(Pos.CENTER);

        Label footer = new Label("\u00A9 Kasem Rashrash 2025");
        footer.setStyle("-fx-font-size: 12px; -fx-text-fill: #CCCCCC;");

        VBox inhalt = new VBox(15, logo, titel, beschreibung, feldA, feldB,
                buttonGrid, ergebnisLabel, footer);


        inhalt.setAlignment(Pos.CENTER);
        inhalt.setMaxWidth(400);

        // 4. Root mit Video + Overlay + Inhalt
        StackPane root = new StackPane(mediaView, overlay, inhalt);
        Scene scene = new Scene(root, 800, 600);

        overlay.prefWidthProperty().bind(scene.widthProperty());
        overlay.prefHeightProperty().bind(scene.heightProperty());
        mediaView.fitWidthProperty().bind(scene.widthProperty());
        mediaView.fitHeightProperty().bind(scene.heightProperty());

        // 5. Fenster anzeigen
        stage.setTitle("Mind4Math – Dein Mathe-Assistent");
        stage.setScene(scene);
        stage.show();
    }

    private Button createButton(String text, String color, javafx.event.EventHandler<javafx.event.ActionEvent> handler) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white;"
                + "-fx-font-size: 14px; -fx-background-radius: 8; -fx-cursor: hand;");
        btn.setOnAction(handler);
        btn.setMaxWidth(200);
        return btn;
    }

    private void berechneGGT() {
        try {
            int a = Integer.parseInt(feldA.getText());
            int b = Integer.parseInt(feldB.getText());
            int ggt = GGT.berechneGGT(a, b);
            ergebnisLabel.setText("ggT von " + a + " und " + b + " ist: " + ggt);
        } catch (NumberFormatException ex) {
            ergebnisLabel.setText("Bitte gültige Zahlen eingeben.");
        }
    }

    private void berechneEEA() {
        try {
            int a = Integer.parseInt(feldA.getText());
            int b = Integer.parseInt(feldB.getText());
            EEAResult res = EEA.berechneEEA(a, b);
            ergebnisLabel.setText("ggT: " + res.ggT + ", x: " + res.x + ", y: " + res.y);
        } catch (NumberFormatException ex) {
            ergebnisLabel.setText("Bitte gültige Zahlen eingeben.");
        }
    }

    private void testePrimzahl() {
        try {
            int z = Integer.parseInt(feldA.getText());
            boolean istPrim = PrimzahlTester.istPrimzahl(z);
            ergebnisLabel.setText(istPrim ? z + " ist eine Primzahl ✅" : z + " ist keine Primzahl ❌");
        } catch (NumberFormatException ex) {
            ergebnisLabel.setText("Bitte eine gültige Zahl in Feld A eingeben.");
        }
    }

    private void berechneKGV() {
        try {
            int a = Integer.parseInt(feldA.getText());
            int b = Integer.parseInt(feldB.getText());
            int ggt = GGT.berechneGGT(a, b);
            int kgv = KGV.berechneKGV(a, b);
            ergebnisLabel.setText("kgV(" + a + ", " + b + ") = " + kgv + "   →   " + a + " * " + b + " / " + ggt);
        } catch (NumberFormatException ex) {
            ergebnisLabel.setText("Bitte gültige Zahlen eingeben.");
        }
    }
    
    private void berechneFibonacci() {
        try {
            int n = Integer.parseInt(feldA.getText());
            int fib = MatheFunktionen.fibonacciIterativ(n);
            ergebnisLabel.setText("Fibonacci(" + n + ") = " + fib);
        } catch (NumberFormatException ex) {
            ergebnisLabel.setText("Bitte eine gültige Zahl in Feld A eingeben.");
        }
    }

    private void berechneFakultaet() {
        try {
            int n = Integer.parseInt(feldA.getText());
            long fak = MatheFunktionen.fakultaetIterativ(n);
            ergebnisLabel.setText(n + "! = " + fak);
        } catch (NumberFormatException ex) {
            ergebnisLabel.setText("Bitte eine gültige Zahl in Feld A eingeben.");
        }
    }


    
}
