import java.awt.*;

class HorseGUI {
    private String name;
    private int id;
    private int win;
    private boolean fell;
    private Color horseColor;
    private double tolDistance;
    private long tolTime;

    public HorseGUI(String name, int id, Color horseColor) {
        this.name = name;
        this.win = 0;
        this.fell = false;
        this.id = id;
        this.horseColor = horseColor;
        this.tolDistance = 0;
        this.tolTime = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWin() {
        return win;
    }

    public void increaseWin() {
        win++;
    }

    public boolean isFell() {
        return fell;
    }

    public void Fallen(boolean fell) {
        this.fell = fell;
    }

    public Color getColor() {
        return horseColor;
    }

    public double getAvSpeed() {
        if (tolTime == 0) {
            return 0.0;
        } else {
            return tolDistance / tolTime;
        }
    }

    public void updateStats(double distanceCovered, long timeTaken) {
        tolDistance += distanceCovered;
        tolTime += timeTaken;
    }
}