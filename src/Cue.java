import javax.swing.*;

public class Cue {

    private int strength;
    private double angle;
    private ImageIcon image;
    private boolean shown;

    private GameView window;

    public Cue() {
        this.angle = 0.0;
        this.strength = 0;
        this.shown = true;
    }

    // getters and setters
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    // Hits ball with certain strength
    public void hitBall(int strength, double angle)
    {

    }

}
