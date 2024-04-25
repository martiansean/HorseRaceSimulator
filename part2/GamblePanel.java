package part2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GamblePanel extends JPanel {

    private JTextField betSizeField;
    private Account MyAccount = new Account(1000);
    private List<HorseGUI> horses;
    private List<BetPosition> betAmountList = new ArrayList<>();

    public GamblePanel() {
        setPreferredSize(new Dimension(500, 150));
        JLabel Balance = new JLabel(MyAccount.getBalance() + " credits, any error will result in bet being 0.");
        Balance.setFont(new Font("Calibri", Font.PLAIN, 14));
        add(Balance);
    }

    public void resetAccount() {
        MyAccount = new Account(1000);
    }

    private int RaceNumber() {
        int races = 0;
        for (HorseGUI horse : horses) {
            races += horse.getWin();
        }
        return races;
    }

    public double calcOdds(HorseGUI horse) {
        if (horse.getWin() == 0) {
            return 1;
        }
        return ((double) RaceNumber() / horse.getWin());
    }

    public void updateBalance(HorseGUI horse) {
        if (!(horse == null)) {
            for (BetPosition bet : betAmountList) {
                if (bet.getHorseId() == horse.getId()) {
                    MyAccount.Adjust(bet.getReturn(true));
                } else {
                    MyAccount.Adjust(bet.getReturn(false));
                }
            }
        }
    }

    public void updateOdds(List<HorseGUI> horses) {
        betAmountList.clear();
        SetBets(horses);
        if (horses != null) {
            this.horses = horses;
            removeAll();
            JLabel Balance = new JLabel(MyAccount.getBalance() + " credits, any error will result in bet being 0.");
            Balance.setFont(new Font("Arial", Font.PLAIN, 14));
            add(Balance);
            for (HorseGUI horse : this.horses) {
                double odds = calcOdds(horse);
                String oddString = String.format("%.3f", odds);
                JLabel horseOddsLabel = new JLabel(horse.getName() + " Odd is: x" + oddString);
                betSizeField = new JTextField(10);
                add(new JSeparator());
                add(horseOddsLabel);
                add(betSizeField);
            }
            revalidate();
            repaint();
        }
    }

    public boolean SetBets(List<HorseGUI> horses) {
        betAmountList.clear();
        int Total = 0;
        int index = 0;
        for (Component component : getComponents()) {
            if (component instanceof JTextField) {
                JTextField betSizeField = (JTextField) component;
                int betSize;
                try {
                    String betSizeText = betSizeField.getText();
                    betSize = Integer.parseInt(betSizeText);
                } catch (Exception e) {
                    betSize = 0;
                }
                Total += betSize;
                if (Total > MyAccount.getBalance()) {
                    JOptionPane.showMessageDialog(null, "You do not have enough credits to place this bet");
                    return false;
                }
                betAmountList.add(new BetPosition((double) calcOdds(horses.get(index)), betSize,
                        horses.get(index).getId()));
                betSizeField.setEnabled(false);
                index++;
            }
        }
        return true;
    }

}