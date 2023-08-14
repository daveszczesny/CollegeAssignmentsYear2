/*
 * Author: Dawid Szczesny
 * ID: 21300293
 * Date: 17 Jan 2023
 */

import java.awt.*;
import javax.swing.*;



public class MyApp extends JFrame implements Runnable {
    // Constants
    private static final Dimension WindowSize = new Dimension(600, 600);
    private static final int NUMGAMEOBJECTS = 30;
    
    // GameObject array
    private GameObject[] GameObjectArray = new GameObject[NUMGAMEOBJECTS];

    // constructor
    public MyApp() {

        // setting up JFrame
        this.setTitle("Assignment 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setResizable(false);
        setVisible(true);

        // Create game objects and adds them to game object array
        for (int i = 0; i < NUMGAMEOBJECTS; i++) {
            GameObject obj = new GameObject();
            GameObjectArray[i] = obj;
        }

        // Creates and starts a thread
        Thread thread = new Thread(this);
        thread.start();
        
    }

    // From Runnable
    // Runs for the duration of the program
    public void run() {
        while (true) {
            this.repaint(); // runs paint();

            // try catch to catch InterruptedException error
            try {
                Thread.sleep(500); // sleeps for 500ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Paint method
    public void paint(Graphics g) {
        // clears the screen for update
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);

        // loops through game objects, moves them and redraws them
        for (GameObject gameObject : GameObjectArray) {

            gameObject.move();
            gameObject.paint(g);
        }
    }

    public static void main(String[] args) {
        MyApp app = new MyApp();
    }

}