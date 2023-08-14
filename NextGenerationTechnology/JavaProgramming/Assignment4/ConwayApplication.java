import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

import java.io.*;

public class ConwayApplication extends JFrame implements Runnable, MouseInputListener, MouseMotionListener {

    private static final Dimension WindowSize = new Dimension(800, 800);
    private BufferStrategy strategy;
    private Graphics offscreenGraphics;

    private final int CELL = 20;
    private final int gridSize = (int) (WindowSize.width / CELL);

    private boolean[][][] grid = new boolean[gridSize][gridSize][2];

    private int FRAME = 200;

    private Rectangle start_bounds = new Rectangle(20, 50, 80, 30);
    private Rectangle random_bounds = new Rectangle(120, 50, 80, 30);
    private Rectangle save_bounds = new Rectangle(320, 50, 80, 30);
    private Rectangle load_bounds = new Rectangle(420, 50, 80, 30);
    private Random random = new Random();

    private enum GAMESTATE {
        PLAYING, NOT_PLAYING
    }

    private GAMESTATE state = GAMESTATE.NOT_PLAYING;

    public ConwayApplication() {

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                this.grid[i][j][0] = false;
                this.grid[i][j][1] = false;

            }
        }

        setupWindow();

        addMouseListener(this);
        addMouseMotionListener(this);

        Thread thread = new Thread(this);
        thread.start();

        createBufferStrategy(2);
        strategy = getBufferStrategy();

        offscreenGraphics = strategy.getDrawGraphics();

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

        if (state == GAMESTATE.NOT_PLAYING) {
            grid[(int) (e.getX() / CELL)][(int) (e.getY()
                    / CELL)][0] = !grid[(int) (e.getX() / CELL)][(int) (e.getY() / CELL)][0];
            if (start_bounds.contains(e.getX(), e.getY())) {
                // start button pressed
                // change game state
                FRAME = 500;
                state = GAMESTATE.PLAYING;
            } else if (random_bounds.contains(e.getX(), e.getY())) {
                // random button pressed
                randomiseState();
            } else if (save_bounds.contains(e.getX(), e.getY())) {
                saveGame(grid);
            } else if (load_bounds.contains(e.getX(), e.getY())) {
                this.grid = loadSave();
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        try {

            if (state == GAMESTATE.PLAYING)
                return;
            grid[(int) (e.getX() / CELL)][(int) (e.getY()
                    / CELL)][0] = !grid[(int) (e.getX() / CELL)][(int) (e.getY() / CELL)][0];
            if (start_bounds.contains(e.getX(), e.getY())) {
                // start button pressed
                // change game state
                state = GAMESTATE.PLAYING;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void randomiseState() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j][0] = random.nextBoolean();
                grid[i][j][1] = grid[i][j][0];
            }
        }
    }

    public void saveGame(boolean[][][] grid) {
        String filename = "lifegame.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    writer.write(grid[j][i][0] ? '1' : '0');
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean[][][] loadSave() {
        String filename = "lifegame.txt";
        boolean[][][] grid = new boolean[gridSize][gridSize][2];
        int count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    grid[i][count][0] = line.charAt(i) == '1' ? true : false;
                }
                count++;

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grid;
    }

    // checking neighbours
    public void checkNeighbours() {
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                //
                int liveNeighbours = 0;
                for (int xx = -1; xx <= 1; xx++) {
                    for (int yy = -1; yy <= 1; yy++) {
                        if (xx == 0 && yy == 0)
                            continue;
                        int neighbourX = x + xx;
                        int neighbourY = y + yy;

                        if (neighbourX >= 0 && neighbourX < WindowSize.width &&
                                neighbourY >= 0 && neighbourY < WindowSize.height &&
                                grid[neighbourX % gridSize][neighbourY % gridSize][0]) {
                            liveNeighbours++;
                        }

                    }
                }
                // rules for Conway's way of life
                if (grid[x][y][0] && (liveNeighbours == 2 || liveNeighbours == 3)) {
                    grid[x][y][1] = true;
                } else if (!grid[x][y][0] && liveNeighbours == 3) {
                    grid[x][y][1] = true;
                } else {
                    grid[x][y][1] = false;
                }
            }
        }

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j][0] = grid[i][j][1];
            }
        }

    }

    public void run() {

        while (true) {
            // game code
            try {
                Thread.sleep(FRAME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (state == GAMESTATE.PLAYING)
                checkNeighbours();

            this.repaint();
        }

    }

    public void paint(Graphics g) {
        try {
            g = offscreenGraphics;

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, (int) WindowSize.width, (int) WindowSize.height);

            // fills the screen with rectangles based on the value of the grids
            for (int i = 0; i < gridSize; i++) {
                for (int j = 0; j < gridSize; j++) {
                    if (this.grid[i][j][0] == true) {
                        g.setColor(Color.white);
                        g.fillRect((i * CELL), (j * CELL), CELL, CELL);
                    }

                }
            }

            // if game state is not playing, render buttons
            if (state == GAMESTATE.NOT_PLAYING) {
                g.setColor(Color.GREEN);
                g.fillRect(20, 50, 80, 30);
                g.setColor(Color.BLACK);
                g.setFont(
                        new Font(g.getFont().getName(),
                                Font.BOLD, 15));
                g.drawString("Start", 40, 70);

                g.setColor(Color.GREEN);

                g.fillRect(120, 50, 80, 30);
                g.setColor(Color.BLACK);
                g.drawString("Random", 125, 70);

                g.setColor(Color.GREEN);

                g.fillRect(320, 50, 80, 30);
                g.setColor(Color.BLACK);
                g.drawString("Save", 340, 70);

                g.setColor(Color.GREEN);

                g.fillRect(420, 50, 80, 30);
                g.setColor(Color.BLACK);
                g.drawString("Load", 440, 70);

            }

            strategy.show();

        } catch (NullPointerException e) {
            this.repaint();
        }

    }

    public void setupWindow() {
        this.setTitle("Space Invaders Alpha: 0.01");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;

        this.setBounds(x, y, WindowSize.width, WindowSize.height);
        this.setResizable(true);

        this.setVisible(true);

    }

    public static void main(String[] args) {
        new ConwayApplication();
    }

}