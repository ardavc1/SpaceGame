import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OyunEkrani extends JFrame {

    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {
        OyunEkrani ekran = new OyunEkrani("Uzay Oyunu");

        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setSize(800, 600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Oyun oyun = new Oyun();
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        oyun.setFocusTraversalKeysEnabled(false);

        ekran.add(oyun);
        ekran.setVisible(true);

        ekran.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String message = "Skor: " + oyun.getSkor() +
                                "\nHarcanan Ateş: " + oyun.getHarcananAtes();
                JOptionPane.showMessageDialog(ekran, message);
            }
        });
    }
}
