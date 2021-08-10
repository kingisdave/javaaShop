import javax.swing.*;
import java.awt.*;

public class SplashClass extends JWindow {
    
    public SplashClass(){
        JWindow jw = new JWindow();

        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();

        Icon img = new ImageIcon(this.getClass().getResource("bb b.gif"));
        JLabel label = new JLabel(img);
        label.setSize(200, 300);
        jw.getContentPane().add(label);
        jw.setBounds(((int)dm.getWidth() - 722)/2,((int)dm.getHeight() - 401)/2, 722, 401);
        jw.setVisible(true);
        try {
            Thread.sleep(6000);
            // System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jw.setVisible(false);
    }

    public static void main(String[] args) {
        SplashClass sc = new SplashClass();
    }
}
