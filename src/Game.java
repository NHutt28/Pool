import java.util.ArrayList;

public class Game {

    private final int NUM_BALLS = 16;

    private Ball black;
    private Ball white;
    private ArrayList<Ball> balls;
    private ArrayList<pocket> holes;
    private int state;

    private final double friction = 0.98;

    private GameView window;

    public Game(ArrayList<pocket> holes, ArrayList<Ball> balls, Ball white) {
        this.holes = holes;
        this.white = white;
        this.balls = balls;
        this.state = 0;
    }

    // Getters and Setters
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    public ArrayList<pocket> getHoles() {
        return holes;
    }

    public void setHoles(ArrayList<pocket> holes) {
        this.holes = holes;
    }

    // Plays the actual game - allows people to hit balls
    public void playGame()
    {

    }

    public boolean checkHitPocket(Ball ball)
    {
        return false;
    }
}

