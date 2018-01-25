package wumpusworld;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GameWindow {

    public GameWindow(Board mBoard) {
        int windowSize = 800;
        JButton[][] buttonGrid = new JButton[mBoard.col][mBoard.row];
        JFrame window = new JFrame();
        window.setTitle("Le monde de Wumpus");
        window.setSize(windowSize, windowSize);
        window.setLayout(new BorderLayout());
        //Generate Grid Panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(mBoard.col, mBoard.row));
        //Generate window info
        JPanel configPanel = new JPanel();
        configPanel.setLayout(new BoxLayout(configPanel, BoxLayout.PAGE_AXIS));
        
        // Label Generate Paths
        JPanel generateLabelPanel = new JPanel();
        generateLabelPanel.setLayout(new BoxLayout(generateLabelPanel, BoxLayout.LINE_AXIS));
        JLabel generateLabel = new JLabel("Generate Paths: ");
        generateLabel.setBorder(new EmptyBorder(10,10,10,10));
        generateLabelPanel.add(generateLabel);
        
        // Simple generate mode
        JPanel simpleModPanel = new JPanel();
        simpleModPanel.setLayout(new BoxLayout(simpleModPanel, BoxLayout.LINE_AXIS));
        simpleModPanel.add(new JButton("Simple Mod"));
        JLabel simpleModTimeLabel = new JLabel("Time");
        simpleModPanel.setBorder(new EmptyBorder(10,10,10,10));
        simpleModTimeLabel.setBorder(new EmptyBorder(10,10,10,10));
        simpleModPanel.add(simpleModTimeLabel);
        
        // Djikstra generate mode
        JPanel djikstraModPanel = new JPanel();
        djikstraModPanel.setLayout(new BoxLayout(djikstraModPanel, BoxLayout.LINE_AXIS));
        djikstraModPanel.add(new JButton("Djikstra Mod"));
        JLabel djikstraModTimeLabel = new JLabel("Time");
        djikstraModTimeLabel.setBorder(new EmptyBorder(10,10,10,10));
        djikstraModPanel.setBorder(new EmptyBorder(10,10,10,10));
        djikstraModPanel.add(djikstraModTimeLabel);
        
        // Label Move Kevin 
        JPanel moveKevinLabelPanel = new JPanel();
        moveKevinLabelPanel.setLayout(new BoxLayout(moveKevinLabelPanel, BoxLayout.LINE_AXIS));
        JLabel moveKevinLabel = new JLabel("Move Kevin: ");
        moveKevinLabel.setBorder(new EmptyBorder(10,10,10,10));
        moveKevinLabelPanel.add(moveKevinLabel);
        
        // Checkbox level mode
        JPanel moveKevinPanel = new JPanel();
        moveKevinPanel.setLayout(new BoxLayout(moveKevinPanel, BoxLayout.PAGE_AXIS));
        
        JPanel yoloPanel = new JPanel();
        yoloPanel.setLayout(new BoxLayout(yoloPanel, BoxLayout.LINE_AXIS));
        yoloPanel.add(new JCheckBox("YOLO"));
        yoloPanel.setBorder(new EmptyBorder(10,10,10,10));
        JPanel commonPanel = new JPanel();
        commonPanel.setLayout(new BoxLayout(commonPanel, BoxLayout.LINE_AXIS));
        commonPanel.add(new JCheckBox("Commom decision"));
        commonPanel.setBorder(new EmptyBorder(10,10,10,10));
        JPanel mamaPanel = new JPanel();
        mamaPanel.setLayout(new BoxLayout(mamaPanel, BoxLayout.LINE_AXIS));
        mamaPanel.add(new JCheckBox("Mama's son"));
        mamaPanel.setBorder(new EmptyBorder(10,10,10,10));
        JPanel manualPanel = new JPanel();
        manualPanel.setLayout(new BoxLayout(manualPanel, BoxLayout.LINE_AXIS));
        manualPanel.add(new JButton("Manual"));
        manualPanel.setBorder(new EmptyBorder(10,10,10,10));
        JPanel autoPanel = new JPanel();
        autoPanel.setLayout(new BoxLayout(autoPanel, BoxLayout.LINE_AXIS));
        autoPanel.add(new JButton("Auto"));
        autoPanel.setBorder(new EmptyBorder(10,10,10,10));
        
        moveKevinPanel.add(yoloPanel);
        moveKevinPanel.add(commonPanel);
        moveKevinPanel.add(mamaPanel);
        moveKevinPanel.add(manualPanel);
        moveKevinPanel.add(autoPanel);
        
        // add elements to configPanel
        configPanel.add(generateLabelPanel);
        configPanel.add(simpleModPanel);
        configPanel.add(djikstraModPanel);
        configPanel.add(moveKevinLabelPanel);
        configPanel.add(moveKevinPanel);
        
        // add both JPanel to window
        window.getContentPane().add(gridPanel, BorderLayout.CENTER);
        window.getContentPane().add(configPanel, BorderLayout.EAST);
        
        // start filling grid
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {
                String cellLabel = "";
                if (mBoard.getBoard()[row][col].getWall() == true) {
                    cellLabel += "WALL \n";
                }
                if (mBoard.getBoard()[row][col].getHole() == true) {
                    cellLabel += "HOLE \n";
                }
                if (mBoard.getBoard()[row][col].getMonster() == true) {
                    cellLabel += "MONSTER \n";
                }
                if (mBoard.getBoard()[row][col].getPlayer() == true) {
                    cellLabel += "KEVIN \n";
                }
                if (mBoard.getBoard()[row][col].getGold() == true) {
                    cellLabel += "GOLD \n";
                }
                if (mBoard.getBoard()[row][col].getSmell() == true) {
                    cellLabel += "SMELL \n";
                }
                if (mBoard.getBoard()[row][col].getWind() == true) {
                    cellLabel += "WIND \n";
                }
                buttonGrid[row][col] = new JButton(cellLabel);
                buttonGrid[row][col].setEnabled(false);
                gridPanel.add(buttonGrid[row][col]);
            }
        }
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

}
