/*
 * @Author: Dawid Szczesny
 * @ID: 21300293
 * @Title: Week 1 Assigment
 */

// imports
import java.awt.*;
import javax.swing.*;

// Application MyApp extending the JFrame framework
public class MyApp extends JFrame {

    // Static final fields
    private static final Dimension WindowSize = new Dimension(600, 600);
    private static final int rectSize = 40; // rectangle size
    private static final int OFFSET = 40; // the offset from the border of the screen

    private static final int RECTS = (int)(WindowSize.getWidth()-(2*OFFSET))/rectSize - 2; // the amount of rectangles there will be in a row
    private static final int PADDING = (int) (2*rectSize) /(RECTS - 1); // padding size

    public MyApp() {
        // setting title
        this.setTitle("JFrame Week 1 Assigment");
        
        // setting up JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setResizable(false); // no resizing of display
        setVisible(true); // makes visible
    }

    // Method ran after every frame update.
    // Nested for loop draws every rectangle onto screen acquring new colour each time
    public void paint(Graphics g) {
        for (int i = 0; i < RECTS; i++) {
            for(int j = 0; j < RECTS; j++){
                g.setColor(
                    new Color(
                        (int)Math.random()*255,
                        (int)Math.random()*255,
                        (int)Math.random()*255
                    )
                );

                g.fillRect(OFFSET + (i*rectSize) + (i*PADDING), OFFSET + (j*rectSize) + (j*PADDING), rectSize, rectSize);
            }
        }
    }

    public static void main(String[] args) {
        MyApp app = new MyApp(); // runs application
    }

}