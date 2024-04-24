import javax.swing.*;
import java.awt.*;
import java.util.List;

class StatsPanel extends JPanel {
    private List<HorseGUI> horses;

    public StatsPanel() {
        setLayout(new GridLayout(0, 1));
        setBackground(new Color(1, 14, 82));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private double getWinrate(HorseGUI horse) {
        if (horse.getWin() == 0) {
            return 0.0;
        }
        return ((double) horse.getWin() / NumRace()) * 100;
    }

    private int NumRace() {
        int races = 0;
        for (HorseGUI horse : horses) {
            races += horse.getWin();
        }
        return races;
    }

    public void updateStats(List<HorseGUI> horses) {
        if (horses != null) {
            this.horses = horses;
            removeAll();
            for (HorseGUI horse : this.horses) {
                double winrate = getWinrate(horse);
                double speed = horse.getAvSpeed();
                String winString = String.format("%.2f", winrate);
                String SpeedString = String.format("%.2f", speed);
                JLabel winlabel = new JLabel(horse.getName() + ": Win Rate - " + winString + "%");
                JLabel speedLabel = new JLabel("Average Speed: " + SpeedString + " m/s");
                winlabel.setFont(new Font("Calibri", Font.PLAIN, 14));
                winlabel.setForeground(new Color(252, 170, 5));
                speedLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
                speedLabel.setForeground(new Color(252, 170, 5));
                add(winlabel);
                add(speedLabel);
            }
            revalidate();
            repaint();
        }
    }
}