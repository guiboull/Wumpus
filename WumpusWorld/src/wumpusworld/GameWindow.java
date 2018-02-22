package wumpusworld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameWindow {

    private JButton[][] buttonGrid;
    private ImageIcon kevinImg;
    private ImageIcon fireImg;
    private ImageIcon goldImg;
    private ImageIcon groundImg;
    private ImageIcon kevinInFireImg;
    private ImageIcon kevinInGoldImg;
    private ImageIcon kevinInMonsterImg;
    private ImageIcon monsterImg;
    private ImageIcon rockImg;


    public GameWindow(Board mBoard) {
        int windowSize = 900/11*mBoard.col;
        buttonGrid = new JButton[mBoard.col][mBoard.row];
        JFrame window = new JFrame();
        window.setResizable(true);
        window.setTitle("Le monde de Wumpus");
        window.setSize((900/11*mBoard.col)+200, windowSize);
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
        generateLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        generateLabelPanel.add(generateLabel);

        // Simple generate mode
        JPanel simpleModPanel = new JPanel();
        simpleModPanel.setLayout(new BoxLayout(simpleModPanel, BoxLayout.LINE_AXIS));
        simpleModPanel.add(new JButton("Simple Mod"));
        JLabel simpleModTimeLabel = new JLabel("Time");
        simpleModPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        simpleModTimeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        simpleModPanel.add(simpleModTimeLabel);

        // Djikstra generate mode
        JPanel djikstraModPanel = new JPanel();
        djikstraModPanel.setLayout(new BoxLayout(djikstraModPanel, BoxLayout.LINE_AXIS));
        djikstraModPanel.add(new JButton("Djikstra Mod"));
        JLabel djikstraModTimeLabel = new JLabel("Time");
        djikstraModTimeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        djikstraModPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        djikstraModPanel.add(djikstraModTimeLabel);

        // Label Move Kevin 
        JPanel moveKevinLabelPanel = new JPanel();
        moveKevinLabelPanel.setLayout(new BoxLayout(moveKevinLabelPanel, BoxLayout.LINE_AXIS));
        JLabel moveKevinLabel = new JLabel("Move Kevin: ");
        moveKevinLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        moveKevinLabelPanel.add(moveKevinLabel);

        // Checkbox level mode
        JPanel moveKevinPanel = new JPanel();
        moveKevinPanel.setLayout(new BoxLayout(moveKevinPanel, BoxLayout.PAGE_AXIS));

        JPanel yoloPanel = new JPanel();
        yoloPanel.setLayout(new BoxLayout(yoloPanel, BoxLayout.LINE_AXIS));
        yoloPanel.add(new JCheckBox("YOLO"));
        yoloPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel commonPanel = new JPanel();
        commonPanel.setLayout(new BoxLayout(commonPanel, BoxLayout.LINE_AXIS));
        commonPanel.add(new JCheckBox("Commom decision"));
        commonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel mamaPanel = new JPanel();
        mamaPanel.setLayout(new BoxLayout(mamaPanel, BoxLayout.LINE_AXIS));
        mamaPanel.add(new JCheckBox("Mama's son"));
        mamaPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel manualPanel = new JPanel();
        manualPanel.setLayout(new BoxLayout(manualPanel, BoxLayout.LINE_AXIS));
        manualPanel.add(new JButton("Manual"));
        manualPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel autoPanel = new JPanel();
        autoPanel.setLayout(new BoxLayout(autoPanel, BoxLayout.LINE_AXIS));
        JButton autoButton = new JButton("Auto");
        autoPanel.add(autoButton);
        autoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

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

        // images
        fireImg = new ImageIcon("images/fire.jpg");
        goldImg = new ImageIcon("images/gold.jpg");
        groundImg = new ImageIcon("images/ground.jpg");
        kevinImg = new ImageIcon("images/kevin.jpg");
        kevinInFireImg = new ImageIcon("images/kevininfire.jpg");
        kevinInGoldImg = new ImageIcon("images/keviningold.jpg");
        kevinInMonsterImg = new ImageIcon("images/kevininmonster.jpg");
        monsterImg = new ImageIcon("images/monster.jpg");
        rockImg = new ImageIcon("images/rock.jpg");

        // start filling grid
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {
                String cellLabel = "";
                ImageIcon image = groundImg;
                if (mBoard.getBoard()[row][col].getWall() == true) {
                    image = rockImg;
                }
                if (mBoard.getBoard()[row][col].getHole() == true) {
                    image = fireImg;
                }
                if (mBoard.getBoard()[row][col].getMonster() == true) {
                    image = monsterImg;
                }
                if (mBoard.getBoard()[row][col].getPlayer() == true) {
                    image = kevinImg;
                }
                if (mBoard.getBoard()[row][col].getGold() == true) {
                    image = goldImg;
                }
                if (mBoard.getBoard()[row][col].getSmell() == true) {

                }
                if (mBoard.getBoard()[row][col].getWind() == true) {

                }
                buttonGrid[row][col] = new JButton();
                buttonGrid[row][col].setEnabled(false);
                int scale = 2 ;
                int width = image.getIconWidth();
                int newWidth = width / scale;
                buttonGrid[row][col].setIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));
                buttonGrid[row][col].setDisabledIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));
                buttonGrid[row][col].setMargin(new Insets(0, 0, 0, 0));
                buttonGrid[row][col].setBorder(BorderFactory.createEmptyBorder());
                gridPanel.add(buttonGrid[row][col]);
            }
        }
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void refreshBoard(Board board) {
        // start filling grid
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {

                ImageIcon image = groundImg;

                if (board.getBoard()[row][col].getPlayer() == true) {
                    image = kevinImg;
                } else if (board.getBoard()[row][col].getWall() == true) {
                    image = rockImg;
                } else if (board.getBoard()[row][col].getHole() == true) {
                    image = fireImg;
                } else if (board.getBoard()[row][col].getMonster() == true) {
                    image = monsterImg;
                } else if (board.getBoard()[row][col].getGold() == true) {
                    image = goldImg;
                } else if (board.getBoard()[row][col].getSmell() == true) {

                } else if (board.getBoard()[row][col].getWind() == true) {

                }
                int scale = 2;
                int width = image.getIconWidth();
                int newWidth = width / scale;
                buttonGrid[row][col].setIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));
                buttonGrid[row][col].setDisabledIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1,java.awt.Image.SCALE_SMOOTH)));
                buttonGrid[row][col].setMargin(new Insets(0, 0, 0, 0));
                buttonGrid[row][col].setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

}
