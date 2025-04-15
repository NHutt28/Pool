import java.awt.*;

public class Ball {

    private double x, y, dx, dy;
    private Color colour;
    private boolean hasPocketed;
    private boolean isSolid;
    private int number;
    private int size;

    private GameView window;

    public Ball(double x, double y, int radius, Color colour) {
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

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
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

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public boolean isHasPocketed() {
        return hasPocketed;
    }

    public void setHasPocketed(boolean hasPocketed) {
        this.hasPocketed = hasPocketed;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // Makes sure speed slows every time
    public void addFriction(double a)
    {

    }

    // Ball bounces off different things
    public void bounceOffWall()
    {

    }
    public void bounceOffBall(Ball collided)
    {

    }
    // Draw
    public void draw(Graphics g)
    {

    }
}
