package part2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class HorseRaceUI extends JFrame {
    private JPanel contentSection;
    private JPanel trackSection;
    private JPanel bettingSection;

    private StatsPanel statsPanel;
    private GamblePanel gamblePanel;

    private JButton startbtn;
    private Timer timer;
    private int numhorse;
    private int trackLength;
    private boolean finished = false;

    private List<HorseGUI> horses;
    private List<Integer> horseNames;

    public HorseRaceUI() {
        // generate the frame for the GUI
        setTitle("Horse Race");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(1, 14, 82));
        contentSection = new JPanel(new BorderLayout());
        contentSection.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentSection.setBackground(new Color(1, 14, 82));
        setContentPane(contentSection);
    }

    public void startRaceGUI() {
        // create section for input - horses and length of track
        JPanel inputPanel = new JPanel();
        JLabel numhorseLabel = new JLabel("Number of Horses:");
        JTextField numhorseField = new JTextField(10);
        JLabel trackLengthLabel = new JLabel("Track Length:");
        JTextField trackLengthField = new JTextField(10);
        JButton genTrackBtn = new JButton("Generate Track");

        genTrackBtn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                new EmptyBorder(5, 10, 5, 10)));
        genTrackBtn.setBackground(new Color(1, 14, 82));
        genTrackBtn.setForeground(new Color(252, 170, 5));

        genTrackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    numhorse = Integer.parseInt(numhorseField.getText());
                    trackLength = Integer.parseInt(trackLengthField.getText());
                    gamblePanel.resetAccount();
                    showPopUp();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please provide valid input");
                }
            }
        });

        inputPanel.add(numhorseLabel);
        inputPanel.add(numhorseField);
        inputPanel.add(trackLengthLabel);
        inputPanel.add(trackLengthField);
        inputPanel.add(genTrackBtn);

        contentSection.add(inputPanel, BorderLayout.NORTH);

        trackSection = new JPanel();
        trackSection.setLayout(new GridLayout(0, 1));
        trackSection.setBackground(new Color(1, 14, 82));

        contentSection.add(new JScrollPane(trackSection), BorderLayout.CENTER);
        statsPanel = new StatsPanel();
        contentSection.add(new JScrollPane(statsPanel), BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        startbtn = new JButton("Start");
        startbtn.setEnabled(false);
        startbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean validbet = gamblePanel.SetBets(horses);
                    if (!validbet) {
                        throw new Exception("Invalid bets");
                    }
                    startRace();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        buttonPanel.add(startbtn, BorderLayout.NORTH);

        // Add GamblePanel to the bottom
        bettingSection = new JPanel(new BorderLayout());
        gamblePanel = new GamblePanel();
        bettingSection.add(new JScrollPane(gamblePanel), BorderLayout.NORTH);
        bettingSection.setLayout(new BoxLayout(bettingSection, BoxLayout.Y_AXIS));

        buttonPanel.add(bettingSection, BorderLayout.CENTER);
        contentSection.add(buttonPanel, BorderLayout.SOUTH);

        horses = new ArrayList<>();

        setVisible(true);
    }

    private void showPopUp() {
        // clear all the stuff in the track
        trackSection.removeAll();
        trackSection.revalidate();
        trackSection.repaint();
        horses.clear();

        for (int i = 0; i < numhorse; i++) {
            String name = JOptionPane.showInputDialog(null, "Enter name for Horse number " + (i + 1));
            String temp = "Choose a color for Horse " + (i + 1);
            Color horseColor = JColorChooser.showDialog(null, temp, Color.RED);
            // set the default value for horse names and color
            if (name != null && !name.isEmpty()) {
                HorseGUI horse = getHorseObj(name, i + 1, horseColor);
                Track track = new Track(trackLength, horse);
                trackSection.add(track);
            } else if (name == null || name.isEmpty()) {
                String defaultName = "Horse_" + (i + 1);
                HorseGUI horse = getHorseObj(defaultName, i + 1, horseColor);
                Track track = new Track(trackLength, horse);
                trackSection.add(track);
            }
        }
        statsPanel.updateStats(horses);
        revalidate();
        repaint();
        // make start button visible
        startbtn.setEnabled(true);
        gamblePanel.updateOdds(horses);
    }

    private HorseGUI getHorseObj(String name, int id, Color horseColor) {
        for (HorseGUI horse : horses) {
            if (horse.getId() == id) {
                return horse;
            }
        }
        HorseGUI newHorse = new HorseGUI(name, id, horseColor);
        horses.add(newHorse);
        return newHorse;
    }

    private void startRace() {
        finished = false;
        horseNames = new ArrayList<>();
        resetRace();
        startbtn.setEnabled(false);
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveAllHorse();
            }
        });
        timer.start();
    }

    private void resetRace() {
        // Reset horses fell attribute
        startbtn.setEnabled(true);
        for (HorseGUI horse : horses) {
            horse.Fallen(false);
        }
        for (Component component : trackSection.getComponents()) {
            if (component instanceof Track) {
                Track track = (Track) component;
                track.resetpos();
            }
        }
        statsPanel.updateStats(horses);
    }

    private void moveAllHorse() {
        if (finished) {
            return;
        }
        for (Component component : trackSection.getComponents()) {
            if (horseNames.size() == numhorse) {
                JOptionPane.showMessageDialog(null, "All horses fell over - No winnner!");
                timer.stop();
                statsPanel.updateStats(horses);
                finished = true;
                gamblePanel.updateBalance(null);
                gamblePanel.updateOdds(horses);
                startbtn.setEnabled(true);
                return;
            }
            if (component instanceof Track) {
                Track track = (Track) component;
                if (track.moveHorse()) {
                    HorseGUI horse = track.getHorse();
                    if (horse != null && !horse.isFell()) {
                        horse.increaseWin();
                        JOptionPane.showMessageDialog(null, horse.getName() + " won the race!");
                        timer.stop();
                        statsPanel.updateStats(horses);
                        finished = true;
                        gamblePanel.updateBalance(horse);
                        gamblePanel.updateOdds(horses);
                        startbtn.setEnabled(true);
                        return;
                    }
                } else if (track.moveHorse() == false) {
                    HorseGUI horse = track.getHorse();
                    if (horse != null && horse.isFell()) {
                        if (!horseNames.contains(horse.getId())) {
                            horseNames.add(horse.getId());
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HorseRaceUI horseRaceUI = new HorseRaceUI();
                horseRaceUI.setVisible(true);
                horseRaceUI.startRaceGUI();
            }
        });
    }
}