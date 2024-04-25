package part1;
/**
 * Write a description of class Horse here.
 * 
 * @author Thanathorn Satayamana
 * @version 1
 */
public class Horse {
    // Fields of class Horse
    private char horseSymbol;
    private String horseName;
    private double horseConfidence;
    private boolean fallen = false;
    private int distanceTravelled = 0;

    // Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        this.horseConfidence = horseConfidence;
    }

    // Other methods of class Horse
    public void fall() {
        this.fallen = true;
    }

    public double getConfidence() {
        return this.horseConfidence;
    }

    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public String getName() {
        return this.horseName;
    }

    public char getSymbol() {
        return this.horseSymbol;
    }

    public void goBackToStart() {
        this.distanceTravelled = 0;
    }

    public boolean hasFallen() {
        return this.fallen;
    }

    public void moveForward() {
        this.distanceTravelled += 1;
    }

    public void setConfidence(double newConfidence) {
        this.horseConfidence = newConfidence;
    }

    public void setSymbol(char newSymbol) {
        this.horseSymbol = newSymbol;
    }

}
