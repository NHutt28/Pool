import javax.swing.*;
import java.awt.*;

public class Ball {

    private int x, y;
    private int dx, dy;
    private double angle;
    private int speed;
    private boolean hasPocketed;
    private boolean isSolid;
    private int number;
    private Image ballDraw;
    private int size;

    private GameView window;

    public Ball(int x, int y, int radius, int number, GameView window) {
        this.x = x;
        this.y = y;
        this.dx = 20;
        this.dy = 20;
        this.size = radius;
        this.hasPocketed = false;
        this.number = number;
        ballDraw = new ImageIcon("Resources/" + number + "Ball.png").getImage();
        this.window = window;
    }
    public Ball(int x, int y, int radius, GameView window) {
        this.x = x;
        this.y = y;
        this.dx = 30;
        this.dy = 30;
        this.size = radius;
        this.hasPocketed = false;
        ballDraw = new ImageIcon("Resources/White Ball.png").getImage();
        this.window = window;
    }

    // Getters and Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return ballDraw;
    }

    public void setBallDraw(Image ballDraw) {
        this.ballDraw = ballDraw;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public void move()
    {
        this.x += dx;
        this.y += dy;
        bounceOffWall();
        this.addFriction();
    }
    // Makes sure speed slows every time
    public void addFriction()
    {
        // Pythagorean triangle
        speed = (int) Math.sqrt((dx*dx) + dy*dy);
        speed *= 0.9;
        if (Math.abs(speed) < 0.5)
        {
            speed = 0;
            dx = 0;
            dy = 0;
        }
    }

    // Ball bounces off different things
    public void bounceOffWall()
    {
        if (x - size < window.TABLE_LEFT || x + size > window.TABLE_RIGHT)
        {
            // Reflects
            if(x - size < window.TABLE_LEFT)
            {
                x = size+window.TABLE_LEFT;
            }
            else
            {
                x = window.TABLE_RIGHT;
            }
            dx *= -1;
        }
        if (y - size < window.TABLE_TOP || y + size > window.TABLE_BOTTOM)
        {
            if(y - size < window.TABLE_TOP)
            {
                y = size+window.TABLE_TOP;
            }
            else
            {
                y = window.TABLE_BOTTOM;
            }
            dy *= -1;
        }
        angle = Math.atan2( dx, dy);
    }
    public void bounceOffBall(Ball collided)
    {

    }
    // Draw
    public void draw(Graphics g)
    {
        g.drawImage(ballDraw, x - size, y - size, 2 * size, 2 * size, window);
    }
}
