import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Mind4MathUI extends JFrame {

    private JTextField feldA;
    private JTextField feldB;
    private JLabel ergebnisLabel;

    public Mind4MathUI() {
        setTitle("Mind4Math – Dein Mathe-Assistent");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 600);
        setLocationRelativeTo(null);

        // Farben
        Color orange = new Color(255, 102, 0);
        Color rot = new Color(255, 51, 51);
        Color hintergrundWeiß = Color.WHITE;

        // Hauptpanel
        JPanel hauptPanel = new JPanel();
        hauptPanel.setBackground(hintergrundWeiß);
        hauptPanel.setLayout(new BoxLayout(hauptPanel, BoxLayout.Y_AXIS));
        hauptPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Logo
        try {
            BufferedImage logo = ImageIO.read(new File("logo.png"));
            JLabel logoLabel = new JLabel(new ImageIcon(logo.getScaledInstance(160, 160, Image.SCALE_SMOOTH)));
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            hauptPanel.add(logoLabel);
        } catch (IOException e) {
            System.out.println("Logo konnte nicht geladen werden.");
        }

        // Titel
        JLabel titel = new JLabel("");
        titel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titel.setAlignmentX(Component.CENTER_ALIGNMENT);
        hauptPanel.add(titel);

        // Beschreibung
        JLabel beschreibung = new JLabel("Grundlagen der Mathematik und Informatik visuell anwenden");
        beschreibung.setFont(new Font("SansSerif", Font.PLAIN, 16));
        beschreibung.setAlignmentX(Component.CENTER_ALIGNMENT);
        hauptPanel.add(Box.createVerticalStrut(10));
        hauptPanel.add(beschreibung);

        // Abstand
        hauptPanel.add(Box.createVerticalStrut(20));

        // Eingabefelder initialisieren
        feldA = new JTextField();
        feldB = new JTextField();
        styleTextField(feldA);
        styleTextField(feldB);

        // Eingaben
        hauptPanel.add(erzeugeEingabeBlock("Zahl A:", feldA));
        hauptPanel.add(Box.createVerticalStrut(10));
        hauptPanel.add(erzeugeEingabeBlock("Zahl B:", feldB));
        hauptPanel.add(Box.createVerticalStrut(20));

        // Buttons-Bereich
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(hintergrundWeiß);

        buttonPanel.add(erstelleButton("ggT berechnen", orange, this::berechneGGT));
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(erstelleButton("EEA berechnen", rot, this::berechneEEA));
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(erstelleButton("Primzahl testen", orange, this::testePrimzahl));
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(erstelleButton("kgV berechnen", rot, this::berechneKGV));

        hauptPanel.add(buttonPanel);
        hauptPanel.add(Box.createVerticalStrut(20));

        // Ergebnis-Anzeige
     // Ergebnisfeld als Panel mit Label innen
        JPanel ergebnisPanel = new JPanel();
        ergebnisPanel.setBackground(new Color(255, 248, 240)); // helles Orange
        ergebnisPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 153, 102), 1, true),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        ergebnisPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        ergebnisPanel.setMaximumSize(new Dimension(350, 60));

        ergebnisLabel = new JLabel(" ", SwingConstants.CENTER);
        ergebnisLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        ergebnisLabel.setForeground(new Color(51, 51, 51));

        ergebnisPanel.add(ergebnisLabel);
        hauptPanel.add(ergebnisPanel);

    

        add(hauptPanel);
        
        setVisible(true);
        
     // Abstand zum Footer
        hauptPanel.add(Box.createVerticalStrut(30));

        // Footer / Copyright
        JLabel footer = new JLabel("© Kasem Rashrash 2025", SwingConstants.CENTER);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footer.setForeground(Color.GRAY);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        hauptPanel.add(footer);

    }

    private JPanel erzeugeEingabeBlock(String labelText, JTextField feld) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setBackground(Color.WHITE);
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setMaximumSize(new Dimension(300, 50));

        feld.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldPanel.add(label);
        fieldPanel.add(Box.createVerticalStrut(5));
        fieldPanel.add(feld);

        return fieldPanel;
    }


    private JButton erstelleButton(String text, Color farbe, java.awt.event.ActionListener aktion) {
        JButton button = new JButton(text);
        button.setBackground(farbe);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(300, 40));
        button.addActionListener(aktion);
        return button;
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(300, 30));
    }

    private void berechneGGT(ActionEvent e) {
        try {
            int a = Integer.parseInt(feldA.getText());
            int b = Integer.parseInt(feldB.getText());
            int ggt = GGT.berechneGGT(a, b);
            ergebnisLabel.setText("Ergebnis: ggT = " + ggt);
        } catch (NumberFormatException ex) {
            ergebnisLabel.setText("Bitte gültige Zahlen eingeben!");
        }
    }

    private void berechneEEA(ActionEvent e) {
        try {
            int a = Integer.parseInt(feldA.getText());
            int b = Integer.parseInt(feldB.getText());
            EEAResult result = EEA.berechneEEA(a, b);
            ergebnisLabel.setText("<html>ggT: " + result.ggT + "<br>x: " + result.x + ", y: " + result.y + "</html>");
        } catch (NumberFormatException ex) {
            ergebnisLabel.setText("Bitte gültige Zahlen eingeben!");
        }
    }

    private void testePrimzahl(ActionEvent e) {
        try {
            int zahl = Integer.parseInt(feldA.getText());
            boolean istPrim = PrimzahlTester.istPrimzahl(zahl);
            if (istPrim) {
                ergebnisLabel.setText("Ja, " + zahl + " ist eine Primzahl.");
            } else {
                ergebnisLabel.setText("Nein, " + zahl + " ist keine Primzahl.");
            }
        } catch (NumberFormatException ex) {
            ergebnisLabel.setText("Bitte eine gültige Zahl in Feld A eingeben!");
        }
    }

    private void berechneKGV(ActionEvent e) {
        try {
            int a = Integer.parseInt(feldA.getText());
            int b = Integer.parseInt(feldB.getText());
            int ggt = GGT.berechneGGT(a, b);
            int kgv = KGV.berechneKGV(a, b);
            int produkt = a * b;

            String text = "<html>"
                + "<b>kgV: " + kgv + "</b><br>"
                + "→ " + a + " × " + b + " / ggT(" + a + ", " + b + ") = "
                + produkt + " / " + ggt
                + "</html>";

            ergebnisLabel.setText(text);
        } catch (NumberFormatException ex) {
            ergebnisLabel.setText("<html><span style='color:red'>Bitte gültige Zahlen eingeben!</span></html>");
        }
    }

}
