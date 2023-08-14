/*
 * Name: Dawid Szczesny
 * ID: 21300293
 * Date: 22.March.2023
 */


import javax.swing.*;
import java.awt.*;
import java.util.Random;

class CellularAutomata extends JFrame implements Runnable{

    private static final Dimension WindowSize = new Dimension(600, 600);
    private static final int CellSize = 3;
    private static final int CELLS = 200;
    public boolean[][][] grid = new boolean[CellSize*CELLS][CellSize*CELLS][2];

    // probability of a wall spawning on start
    private static final float wallProbability = 0.6f;

    // Constructor
    public CellularAutomata() {

        initGridBoolean();

        creatJFrameDisplay();

        // create thread
        Thread thread = new Thread(this);
        thread.start();
    }

    // run method
    public void run(){
        int runTimes = 0;
        while(true){
            try{
                Thread.sleep(400);
            }catch(InterruptedException e) {e.printStackTrace();}

            if(runTimes < 4){    
                isWallOrFloor();
                runTimes++;
                this.repaint();
            }

        }
    }

    // Paint method
    public void paint(Graphics g){
        // setting background colour
        g.setColor(Color.black);
        g.fillRect(0, 0, WindowSize.width, WindowSize.height);


        // Drawing grid
        for(int i = 0; i < grid.length; ++i){
            for(int j = 0; j<grid[0].length; ++j){
                g.setColor(grid[i][j][0] ? Color.white : Color.black);
                g.fillRect(i*CellSize, j*CellSize, CellSize, CellSize);
            }
        }

    }


    /*
     * Method to loop through entire grid and decide
     * whether it should be a wall or a floor, (1 or 0)
     * It then sets the result back to the grid rendered
     */
    private void isWallOrFloor(){
        int neighbouring_wallCells = 0;
        // loop through entire grid
        for(int gridX = 0; gridX < grid.length; gridX++){
            for(int gridY = 0; gridY < grid[0].length; gridY++){
                // value determines if current cell is wall or floor
                neighbouring_wallCells = 0;

                // loop through all the neighbours of the cell
                for(int xx = -1; xx <= 1; xx++){
                    for(int yy = -1; yy <= 1; yy++){
                        // skip current cell
                        if(xx == 0 && yy == 0) continue;

                        // check if cell is inside the grid
                        int neighbourX = xx + gridX;
                        int neighbourY = yy + gridY;
                        
                        if(neighbourX < 0 || neighbourX >= grid.length
                            || neighbourY < 0 || neighbourY >= grid[0].length) continue;

                        // if neighbour is a wall
                        if(grid[neighbourX][neighbourY][0]){ neighbouring_wallCells++;}
                    }
                }
                // if neighbouring_wallCells >= 5, then cell is wall else it is floor
                grid[gridX][gridY][1] = neighbouring_wallCells >=5;
                
                
            }
        }

        // set behind the scenes grid to rendered grid
        for(int i = 0; i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                grid[i][j][0] = grid[i][j][1];
            }
        }

    }

    // initialling all booleans in grid array
    private void initGridBoolean(){
        Random random = new Random();
        for(int i = 0; i<grid.length; ++i){
            for(int j = 0; j<grid[0].length; j++){
                grid[i][j][0] = random.nextFloat() < wallProbability ? true : false;
                grid[i][j][1] = grid[i][j][0];
            }
        }
    }

    private void creatJFrameDisplay() {
        setTitle("Cellular Automata Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width / 2 - WindowSize.width / 2;
        int y = screensize.height / 2 - WindowSize.height / 2;

        setBounds(x,y,WindowSize.width,WindowSize.height);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CellularAutomata();
    }

}
