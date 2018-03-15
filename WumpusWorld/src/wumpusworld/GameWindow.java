package wumpusworld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import static wumpusworld.WumpusWorld.moveKevinAuto;

public class GameWindow implements ActionListener {

    private JButton[][] buttonGrid;

    // images
    private final ImageIcon fireImg = new ImageIcon("images/fire.jpg");
    private final ImageIcon goldImg = new ImageIcon("images/gold.jpg");
    private final ImageIcon groundImg = new ImageIcon("images/ground.jpg");
    private final ImageIcon kevinImg = new ImageIcon("images/kevin.jpg");
    private final ImageIcon kevinInFireImg = new ImageIcon("images/kevininfire.jpg");
    private final ImageIcon kevinInGoldImg = new ImageIcon("images/keviningold.jpg");
    private final ImageIcon kevinInMonsterImg = new ImageIcon("images/kevininmonster.jpg");
    private final ImageIcon monsterImg = new ImageIcon("images/monster.jpg");
    private final ImageIcon rockImg = new ImageIcon("images/rock.jpg");

    private final ImageIcon heatFireImg = new ImageIcon("images/heat/heatfire.jpg");
    private final ImageIcon heatGoldImg = new ImageIcon("images/heat/heatgold.jpg");
    private final ImageIcon heatGroundImg = new ImageIcon("images/heat/heatground.jpg");
    private final ImageIcon heatKevinImg = new ImageIcon("images/heat/heatkevin.jpg");
    private final ImageIcon heatKevinInFireImg = new ImageIcon("images/heat/heatkevininfire.jpg");
    private final ImageIcon heatKevinInMonsterImg = new ImageIcon("images/heat/heatkevininmonster.jpg");
    private final ImageIcon heatKevinInGoldImg = new ImageIcon("images/heat/heatkevininmonster.jpg");

    private final ImageIcon smellFireImg = new ImageIcon("images/smell/smellfire.jpg");
    private final ImageIcon smellGoldImg = new ImageIcon("images/smell/smellgold.jpg");
    private final ImageIcon smellGroundImg = new ImageIcon("images/smell/smellground.jpg");
    private final ImageIcon smellKevinImg = new ImageIcon("images/smell/smellkevin.jpg");
    private final ImageIcon smellKevinInFireImg = new ImageIcon("images/smell/smellkevininfire.jpg");
    private final ImageIcon smellKevinInGoldImg = new ImageIcon("images/smell/smellkeviningold.jpg");
    private final ImageIcon smellKevinInMonsterImg = new ImageIcon("images/smell/smellkevininmonster.jpg");
    private final ImageIcon smellMonsterImg = new ImageIcon("images/smell/smellmonster.jpg");

    private final ImageIcon smellHeatFireImg = new ImageIcon("images/smellheat/smellheatfire.jpg");
    private final ImageIcon smellHeatGoldImg = new ImageIcon("images/smellheat/smellheatgold.jpg");
    private final ImageIcon smellHeatGroundImg = new ImageIcon("images/smellheat/smellheatground.jpg");
    private final ImageIcon smellHeatKevinImg = new ImageIcon("images/smellheat/smellheatkevin.jpg");
    private final ImageIcon smellHeatKevinInFireImg = new ImageIcon("images/smellheat/smellheatkevininfire.jpg");
    private final ImageIcon smellHeatKevinInGoldImg = new ImageIcon("images/smellheat/smellheatkeviningold.jpg");
    private final ImageIcon smellHeatKevinInMonsterImg = new ImageIcon("images/smellheat/smellheatkevininmonster.jpg");
    private final ImageIcon smellHeatMonsterImg = new ImageIcon("images/smellheat/smellheatmonster.jpg");

    private final ImageIcon djikstraImg = new ImageIcon("images/djikstra.jpg");

    private ShortestPath shortestPath;
   
    private Board currentBoard;
    
    private int xPositionKevin;
    private int yPositionKevin;
    
    private boolean showDjisktra = false;
    
    private JButton autoButton;
    private JButton djikstraButton;

    public GameWindow(Board mBoard) {
        currentBoard = mBoard;
        int windowSize = 900 / 11 * currentBoard.col;
        buttonGrid = new JButton[currentBoard.col][currentBoard.row];
        JFrame window = new JFrame();
        window.setResizable(true);
        window.setTitle("Le monde de Wumpus");
        window.setSize((900 / 11 * currentBoard.col) + 200, windowSize);
        window.setLayout(new BorderLayout());
        //Generate Grid Panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(currentBoard.col, currentBoard.row));
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
        djikstraButton = new JButton("Djikstra Mod");
        djikstraButton.addActionListener(this);
        djikstraModPanel.add(djikstraButton);
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
        autoButton = new JButton("Auto");
        autoButton.addActionListener(this);
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

        xPositionKevin = currentBoard.col - 2;
        yPositionKevin = currentBoard.row - 2;

        shortestPath = new ShortestPath(yPositionKevin, xPositionKevin, currentBoard.colGold, currentBoard.rowGold, currentBoard.proxiBoard);

        // start filling grid
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {
                String cellLabel = "";
                ImageIcon image = groundImg;

                // Affichage classique
                if (currentBoard.typeAffichage == 1) {
                    if (currentBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    } else if (currentBoard.getBoard()[row][col].getHole() == true) {
                        image = fireImg;
                    } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                        image = monsterImg;
                    } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                        image = kevinImg;
                        xPositionKevin = row;
                        yPositionKevin = col;
                    } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                        image = goldImg;
                    } else {
                        image = groundImg;
                    }
                } // Affichage du vent et des odeurs
                else if (currentBoard.typeAffichage == 2) {
                    if ((currentBoard.getBoard()[row][col].getWind() == true) && (currentBoard.getBoard()[row][col].getSmell() == true)) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            image = smellHeatFireImg;
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            image = smellHeatMonsterImg;
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = smellHeatKevinImg;
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            image = smellHeatGoldImg;
                        } else {
                            image = smellHeatGroundImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getSmell() == true) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            image = smellFireImg;
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            image = smellMonsterImg;
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = smellKevinImg;
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            image = smellGoldImg;
                        } else {
                            image = smellGroundImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getWind() == true) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            image = heatFireImg;
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            image = smellHeatMonsterImg;
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = heatKevinImg;
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            image = heatGoldImg;
                        } else {
                            image = heatGroundImg;
                        }
                    } else {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            image = fireImg;
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            image = monsterImg;
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinImg;
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            image = goldImg;
                        } else {
                            image = groundImg;
                        }
                    }

                    if (currentBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    }
                }

                buttonGrid[row][col] = new JButton();
                buttonGrid[row][col].setEnabled(false);
                int scale = 2;
                int width = image.getIconWidth();
                int newWidth = width / scale;
                buttonGrid[row][col].setIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
                buttonGrid[row][col].setDisabledIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
                buttonGrid[row][col].setMargin(new Insets(0, 0, 0, 0));
                buttonGrid[row][col].setBorder(BorderFactory.createEmptyBorder());
                gridPanel.add(buttonGrid[row][col]);
            }
        }

        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void refreshBoard() {
        // start filling grid
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {

                ImageIcon image = groundImg;

                // Affichage classique
                if (currentBoard.typeAffichage == 1) {
                    if (currentBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    } else if (currentBoard.getBoard()[row][col].getHole() == true) {
                        image = fireImg;
                    } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                        image = monsterImg;
                    } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                        image = kevinImg;
                        xPositionKevin = row;
                        yPositionKevin = col;
                    } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                        image = goldImg;
                    } else {
                        image = groundImg;
                    }
                } // Affichage du vent et des odeurs
                else if (currentBoard.typeAffichage == 2) {
                    if ((currentBoard.getBoard()[row][col].getWind() == true) && (currentBoard.getBoard()[row][col].getSmell() == true)) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            image = smellHeatFireImg;
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            image = smellHeatMonsterImg;
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = smellHeatKevinImg;
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            image = smellHeatGoldImg;
                        } else {
                            image = smellHeatGroundImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getSmell() == true) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            image = smellFireImg;
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            image = smellMonsterImg;
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = smellKevinImg;
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            image = smellGoldImg;
                        } else {
                            image = smellGroundImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getWind() == true) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            image = heatFireImg;
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            image = smellHeatMonsterImg;
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = heatKevinImg;
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            image = heatGoldImg;
                        } else {
                            image = heatGroundImg;
                        }
                    } else {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            image = fireImg;
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            image = monsterImg;
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinImg;
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            image = goldImg;
                        } else {
                            image = groundImg;
                        }
                    }

                    if (currentBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    }
                }
                int scale = 2;
                int width = image.getIconWidth();
                int newWidth = width / scale;
                buttonGrid[row][col].setIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
                buttonGrid[row][col].setDisabledIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
                buttonGrid[row][col].setMargin(new Insets(0, 0, 0, 0));
                buttonGrid[row][col].setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == djikstraButton) {
            showDjisktra = !showDjisktra;
            // Djisktra BUTTON
            if (showDjisktra) {
                shortestPath = new ShortestPath(currentBoard.col - 2, currentBoard.row - 2, currentBoard.colGold, currentBoard.rowGold, currentBoard.proxiBoard);
                shortestPath.djikstra();
                shortestPath.showDjikstra();
                // start filling grid
                for (int row = 0; row < buttonGrid.length; row++) {
                    for (int col = 0; col < buttonGrid[row].length; col++) {
                        // DJIKSTRA
                        for (int index = 0; index < shortestPath.path.size(); index++) {
                            if (row == shortestPath.path.get(index)[0] && col == shortestPath.path.get(index)[1] && (currentBoard.getBoard()[row][col].getGold() == false)) {
                                ImageIcon image = djikstraImg;
                                System.out.println("OK");
                                int scale = 2;
                                int width = image.getIconWidth();
                                int newWidth = width / scale;
                                buttonGrid[row][col].setIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
                                buttonGrid[row][col].setDisabledIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
                                buttonGrid[row][col].setMargin(new Insets(0, 0, 0, 0));
                                buttonGrid[row][col].setBorder(BorderFactory.createEmptyBorder());
                            }
                        }
                    }
                }
            } else if (!showDjisktra) {
                refreshBoard();
            }
        } else if (source == autoButton) {
            moveKevinAuto = !moveKevinAuto;
        }
    }

}
