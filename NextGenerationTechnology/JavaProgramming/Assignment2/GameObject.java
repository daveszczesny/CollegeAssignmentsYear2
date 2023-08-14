import java.awt.*;

public class GameObject {

    // Member data
    private int x, y;
    private int size = 30;

    // constructor
    public GameObject() {
        // sets default x, y position
        this.x = randomPos();
        this.y = randomPos();
    }

    // On every frame update a new random position is set
    public void move() {
        this.x = randomPos();
        this.y = randomPos();
    }

    // paints a new rect
    public void paint(Graphics g) {
        g.setColor(new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)));
        g.fillRect(this.x, this.y, size, size);
    }

    // randomly picks a new position on the screen
    private int randomPos() {
        return (int) (Math.random() * 600) - size;
    }
}
