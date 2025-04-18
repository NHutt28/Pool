import java.awt.*;

public class Ball {

    private int x, y;
    private Color colour;
    private double dx, dy;
    private boolean hasPocketed;
    private boolean isSolid;
    private int number;
    private int size;

    private GameView window;

    public Ball(int x, int y, int radius, Color colour) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.size = radius;
        this.colour = colour;
        this.hasPocketed = false;
    }

    // Getters and Setters
    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColour() {
        return colour;
    }

    public void setColour(Color colour) {
        this.colour = colour;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public boolean isHasPocketed() {
        return hasPocketed;
    }

    public void setHasPocketed(boolean hasPocketed) {
        this.hasPocketed = hasPocketed;
    }

    public double getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void move()
    {
        if (!hasPocketed) {
            x += dx;
            y += dy;
        }
    }
    // Makes sure speed slows every time
    public void addFriction()
    {
        dx *= 0.98;
        dy *= 0.98;
        if (Math.abs(dx) < 0.1) dx = 0;
        if (Math.abs(dy) < 0.1) dy = 0;
    }

    // Ball bounces off different things
    public void bounceOffWall()
    {
        if (x - size < 0 || x + size > window.WINDOW_WIDTH)
        {
            dx *= -1;
        }
        if (y - size < 0 || y + size > window.WINDOW_HEIGHT)
        {
            dy *= -1;
        }
    }
    public void bounceOffBall(Ball collided)
    {

    }
    // Draw
    public void draw(Graphics g)
    {
        g.setColor(colour);
        g.fillOval(x - size, y - size, 2 * size, 2 * size);
    }
}
