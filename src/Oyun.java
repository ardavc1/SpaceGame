import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Ates {
    private int x
    private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Oyun extends JPanel implements KeyListener, ActionListener {
    Timer timer = new Timer(1, this);
    private int skor = 0;
    private int gecenSure = 0;
    private int harcananAtes = 0;
    private BufferedImage image;
    private ArrayList<Ates> atesler = new ArrayList<>();
    private int atesdirY = 6;
    private int topX = 360;
    private int topdirX = 5;
    private int uzayGemisiX = 360;
    private int dirUzayX = 20;

    public int getSkor() {
        return skor;
    }

    public int getHarcananAtes() {
        return harcananAtes;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        gecenSure = gecenSure+5;
        g.setColor(Color.RED);
        g.fillOval(topX, 0, 20, 20);

        g.drawImage(image, uzayGemisiX, 490, image.getWidth()/10, image.getHeight()/10, this);

        atesler.removeIf(ates -> ates.getY() <= 0);
        g.setColor(Color.blue);

        for (Ates ates : atesler) {
            g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        kontrolEt();

        String s = Integer.toString(skor);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.GREEN);
        g.drawString(s, 750, 300);

    }

    @Override
    public void repaint() {
        super.repaint();
    }

    public void kontrolEt() {
        for (Ates ates : atesler) {
            if (new Rectangle(ates.getX(), ates.getY(), 10, 20).intersects(new Rectangle(topX, 0, 20, 20))) {
                skor++;
                return;
            }
        }
    }

    public Oyun() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setBackground(Color.BLACK);

        timer.start();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_LEFT) {
            if (uzayGemisiX <= 0) {
                uzayGemisiX = 0;
            }
            else {
                uzayGemisiX -= dirUzayX;
            }
        }
        else if (c == KeyEvent.VK_RIGHT) {
            if (uzayGemisiX >= 750) {
                uzayGemisiX = 750;
            }
            else {
                uzayGemisiX += dirUzayX;
            }
        }
        else if (c == KeyEvent.VK_SPACE) {
            atesler.add(new Ates(uzayGemisiX+15, 480));
            harcananAtes++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Ates ates : atesler) {
            ates.setY(ates.getY() - atesdirY);
        }

        topX += topdirX;

        if (topX >= 750) {
            topdirX = -topdirX;
        }
        if (topX <= 0) {
            topdirX = -topdirX;
        }
        repaint();
    }
}
