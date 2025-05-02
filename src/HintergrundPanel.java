import javax.swing.*;
import java.awt.*;

public class HintergrundPanel extends JPanel {

    private final ImageIcon gif;

    public HintergrundPanel(String pfad) {
        this.gif = new ImageIcon(pfad);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = gif.getImage();
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
