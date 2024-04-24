import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Track extends JPanel {
    private int length;
    private int pos;
    private Horse horse;

    private Image horseImage;
    private Image XImage;

    public Track(int length, Horse horse) {
        this.length = length;
        this.horse = horse;
        pos = 0;
        // get the images of the horse and x symbol for falling
        horseImage = new ImageIcon(getClass().getResource("horse.png")).getImage();
        XImage = new ImageIcon(getClass().getResource("fell.png")).getImage();
        // set up panel
        setPreferredSize(new Dimension(600, 100));
        setBackground(new Color(0, 130, 4));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
    }

    public Horse getHorse() {
        return horse;
    }

    public void resetpos() {
        // set the horse position to starting position at 0
        pos = 0;
    }

    public boolean moveHorse() {
        if (!horse.isFell()) {
            // get random number
            Random random = new Random();
            int move = random.nextInt(2 - 1 + 1) + 1;

            pos += move;
            // random the chance of horse falling
            if (random.nextFloat() < 0.005) {
                horse.Fallen(true);
            }
            // increase the speed
            horse.updateStats(move, 1);
            if (pos >= length) {
                pos = length;
                return true;
            }
            repaint();
        }
        return false;
    }

    public int getXPos(int width, int pos) {
        return pos + (50 - width) / 2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int horsepos = (int) (((double) pos / length) * (getWidth() - 50));
        if (horse.isFell()) {
            g.drawImage(XImage, horsepos, 20, 50, 50, null);
            g.setFont(new Font("Calibri", Font.PLAIN, 16));
            // find the width of the string to align it centre
            FontMetrics fm = g.getFontMetrics();
            int y = 90;
            int width = fm.stringWidth(horse.getName());
            // draw name
            int x = getXPos(width, horsepos);
            g.drawString(horse.getName(), x, y);
        } else {
            g.drawImage(horseImage, horsepos, 10, 100, 70, null);
            g.setColor(horse.getColor());
            g.fillRect(horsepos + 45, 32, 20, 10);
            g.setColor(Color.BLACK);
            g.drawRect(horsepos + 45, 32, 20, 10);
            g.setFont(new Font("Calibri", Font.PLAIN, 16));
            FontMetrics fm = g.getFontMetrics();
            int width = fm.stringWidth(horse.getName());
            int x = getXPos(width, horsepos);
            int y = 100;
            g.drawString(horse.getName(), x, y);
        }
    }
}