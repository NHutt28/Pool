import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ball {

    public static ArrayList<Ball> pocketedBalls = new ArrayList<Ball>();
    private int x, y;
    private int dx, dy;
    private double angle;
    private long speed;
    private boolean hasPocketed;
    private boolean isSolid;
    private int number;
    private Image ballDraw;
    private int size;

    private GameView window;

    public Ball(int x, int y, int radius, int number, GameView window) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.size = radius;
        this.hasPocketed = false;
        this.number = number;
        ballDraw = new ImageIcon("Resources/" + number + "Ball.png").getImage();
        this.window = window;
    }
    public Ball(int x, int y, int radius, GameView window) {
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.size = radius;
        this.hasPocketed = false;
        ballDraw = new ImageIcon("Resources/White Ball.png").getImage();
        this.window = window;
        number = 20; // Random Number that doesn't overlap with any other
    }



    // Getters and Setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Image getBallDraw() {
        return ballDraw;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public void setSolid(boolean solid) {
        isSolid = solid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public GameView getWindow() {
        return window;
    }

    public void setWindow(GameView window) {
        this.window = window;
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
        isPocketed();
        this.addFriction();
        bounceOffWall();
    }
    // Makes sure speed slows every time
    public void addFriction()
    {
        // Pythagorean triangle
        //speed = (int) Math.sqrt((dx*dx) + dy*dy);
        //speed *= 0.9;
        speed = (long) Math.sqrt((dx*dx) + dy*dy) ;
        speed *= 0.97;
        if (Math.abs(speed) < 0.5)
        {
            dx = 0;
            dy = 0;
        }
        else
        {
            angle = Math.atan2(dy, dx);
            dx = (int)(Math.cos(angle) * speed);
            dy = (int)(Math.sin(angle) * speed);
        }
    }

    // Ball bounces off different things
    public void bounceOffWall()
    {
        if(hasPocketed)
        {
            return;
        }
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
        angle = Math.atan2(dy, dx);
    }

    public boolean isPocketed()
    {
        if(this.hasPocketed)
        {
            return true;
        }
        if(y - size < window.TABLE_TOP && x + size > window.TABLE_RIGHT)
        {
            this.hasPocketed = true;
        }
        else if(y - size < window.TABLE_TOP + 5 && x - size < window.TABLE_LEFT +5)
        {
            this.hasPocketed = true;
        }
        else if(y + size > window.TABLE_BOTTOM && x + size > window.TABLE_RIGHT)
        {
            this.hasPocketed = true;
        }
        else if(y + size > window.TABLE_BOTTOM - 5&& x - size < window.TABLE_LEFT + 5)
        {
            this.hasPocketed = true;
        }
        else if ((Math.abs(x - 420) <= size + 10) && y - size < window.TABLE_TOP + 5)
        {
            this.hasPocketed = true;
        }
        else if ((Math.abs(x - 420) <= size + 10) && y + size > window.TABLE_BOTTOM -5)
        {
            this.hasPocketed = true;
        }

        if(this.hasPocketed && this.number != 20)
        {
            pocketedBalls.add(this);
        }
        else if (this.hasPocketed && this.number == 20)
        {
            Game.foulState = true;
            this.setX(250);
            this.setY(280);
            this.setDx(0);
            this.setDy(0);
            this.hasPocketed = false;
        }
        return hasPocketed;
    }

    /**
     * ChatGPT coded this, my attempt is commented out
     * @param collided
     */
    public void bounceOffBall(Ball collided)
    {
//
//        if (this.dx == 0 && this.dy == 0 && collided.dx == 0 && collided.dy == 0) {
//            return;
//        }
//        boolean firstMoving = (this.dx != 0 || this.dy != 0);
//        boolean secondMoving = (collided.dx != 0 || collided.dy != 0);
//
//        int minDistanceBetween = collided.getSize() + this.getSize();
//        int xCollisionNum = collided.getX() - this.getX();
//        int yCollisionNum = collided.getY() - this.getY();
//
//
//        int distance = (int) Math.sqrt((xCollisionNum * xCollisionNum) + (yCollisionNum * yCollisionNum));
//            if (distance < minDistanceBetween && distance !=0) {
//                double overlap = minDistanceBetween - distance;
//                if (!firstMoving && secondMoving ) {
//                    this.setX((int) (this.getX() - dx * overlap));
//                    this.setY((int) (this.getY() - dy * overlap));
//                    this.bounceOffWall();
//                    this.addFriction();
//                }
//                else if (firstMoving && !secondMoving)
//                {
//                    collided.setX((int) (collided.getX() + dx * overlap));
//                    collided.setY((int) (collided.getY() + dy * overlap));
//                    collided.bounceOffWall();
//                    collided.addFriction();
//                }
//                else
//                {
//                    this.setX((int) (this.getX() - dx * overlap / 2));
//                    this.setY((int) (this.getY() - dy * overlap / 2));
//                    collided.setX((int) (collided.getX() + dx * overlap / 2));
//                    collided.setY((int) (collided.getY() + dy * overlap / 2));
//                    this.bounceOffWall();
//                    this.addFriction();
//                    collided.addFriction();
//                }
//                int tempDx = this.getDx();
//                int tempDy = this.getDy();
//                this.setDy(collided.getDy());
//                this.setDx(collided.getDx());
//                collided.setDy(tempDy);
//                collided.setDx(tempDx);
//
//            }
        if (this.hasPocketed || collided.hasPocketed) {
            return;
        }

        double minDist = this.getSize() + collided.getSize();
        double dx = collided.getX() - this.getX();
        double dy = collided.getY() - this.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if ((dist < minDist && dist != 0)) {
            // Move balls apart so they don't overlap
            double overlap = minDist - dist;
            double nx = dx / dist;
            double ny = dy / dist;

            // Move each ball away from collision point by half the overlap
            this.x -= (int)(nx * overlap / 2);
            this.y -= (int)(ny * overlap / 2);
            collided.x += (int)(nx * overlap / 2);
            collided.y += (int)(ny * overlap / 2);

            // --- Physics-based velocity calculation ---
            // Current velocities
            double v1x = this.getDx();
            double v1y = this.getDy();
            double v2x = collided.getDx();
            double v2y = collided.getDy();

            // Project velocities onto the normal and tangent directions
            double v1n = v1x * nx + v1y * ny;
            double v1t = -v1x * ny + v1y * nx;
            double v2n = v2x * nx + v2y * ny;
            double v2t = -v2x * ny + v2y * nx;

            // Coefficient of restitution (energy loss)
            double restitution = 0.97; // 1.0 = elastic, <1.0 = inelastic

            // New normal velocities (1D collision equations, equal mass)
            double v1nAfter = v2n * restitution;
            double v2nAfter = v1n * restitution;

            // Convert scalar normal/tangent velocities back to x/y
            double newV1x = v1nAfter * nx - v1t * ny;
            double newV1y = v1nAfter * ny + v1t * nx;
            double newV2x = v2nAfter * nx - v2t * ny;
            double newV2y = v2nAfter * ny + v2t * nx;

            // Assign new velocities (rounded to int for your code)
            this.setDx((int)newV1x);
            this.setDy((int)newV1y);
            collided.setDx((int)newV2x);
            collided.setDy((int)newV2y);
        }
    }
    // Draw
    public void draw(Graphics g)
    {
        if(!hasPocketed)
        {
            g.drawImage(ballDraw, x - size, y - size, 2 * size, 2 * size, window);
        }
    }
}
