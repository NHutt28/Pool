import java.util.ArrayList;

public class pocket {

    private ArrayList<Ball> pocketedBalls;
    private GameView window;
    private int size;

    public pocket(ArrayList<Ball> pocketedBalls, int size, GameView window) {
        this.pocketedBalls = pocketedBalls;
        this.size = size;
        this.window = window;
    }

    // Pockets a ball
    public void addBall(Ball pocketed)
    {

    }
    // Getters and Setters
    public ArrayList<Ball> getPocketedBalls() {
        return pocketedBalls;
    }

    public void setPocketedBalls(ArrayList<Ball> pocketedBalls) {
        this.pocketedBalls = pocketedBalls;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
