package wumpusworld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import static wumpusworld.WumpusWorld.moveKevinAuto;

public class GameWindow implements ActionListener {

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
    private final ImageIcon fogImg = new ImageIcon("images/fog.jpg");

    private final ImageIcon heatFireImg = new ImageIcon("images/heat/heatfire.jpg");
    private final ImageIcon heatGoldImg = new ImageIcon("images/heat/heatgold.jpg");
    private final ImageIcon heatGroundImg = new ImageIcon("images/heat/heatground.jpg");
    private final ImageIcon heatKevinImg = new ImageIcon("images/heat/heatkevin.jpg");
    private final ImageIcon heatKevinInFireImg = new ImageIcon("images/heat/heatkevininfire.jpg");
    private final ImageIcon heatKevinInMonsterImg = new ImageIcon("images/heat/heatkevininmonster.jpg");
    private final ImageIcon heatKevinInGoldImg = new ImageIcon("images/heat/heatkeviningold.jpg");
    private final ImageIcon heatMonsterImg = new ImageIcon("images/heatmonster.jpg");

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

    private LongestPath longestPath;
    private Board currentBoard;

    private int xPositionKevin;
    private int yPositionKevin;

    //private int djikstraTime;  // temps d'execution de Djikstra
    private int loooongTime;  // temps d'execution de l'algo pas opti

    private boolean showPath = false;

    private JButton[][] buttonGrid;
    private JButton djikstraButton;
    private JButton loooongButton;
    private JButton autoButton;
    private JButton displayModeButton;
    private JButton fogButton;
    
    private JMenuItem jmiRestart;
    private JMenuItem jmiMenu;
    private JMenuItem jmiExit;
    private JMenuItem jmiAbout;
    
    private JFrame window;

    JLabel djikstraModTimeLabel;
    JLabel loooongModTimeLabel;

    public GameWindow(Board mBoard) {
        currentBoard = mBoard;
        int windowSize = 900 / 11 * currentBoard.col;
        buttonGrid = new JButton[currentBoard.col][currentBoard.row];
        JFrame window = new JFrame();
        window.setResizable(true);
        window.setTitle("Le monde de Wumpus");
        window.setSize((900 / 11 * currentBoard.col) + 200, windowSize);
        window.setLayout(new BorderLayout());
        
        // Menu
        
        JMenuBar jmb = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        jmiMenu = new JMenuItem("Menu");
        jmiRestart = new JMenuItem("Restart");
        jmiExit = new JMenuItem("Exit");
        jmFile.add(jmiRestart);
        jmFile.add(jmiMenu);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);

        JMenu jmHelp = new JMenu("Help");
        jmiAbout = new JMenuItem("About");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);

        jmiRestart.addActionListener(this);
        jmiMenu.addActionListener(this);
        jmiExit.addActionListener(this);
        jmiAbout.addActionListener(this);
        
        window.setJMenuBar(jmb);
        window.setVisible(true);
        
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
        JPanel loooongModPanel = new JPanel();
        loooongModPanel.setLayout(new BoxLayout(loooongModPanel, BoxLayout.LINE_AXIS));
        loooongButton = new JButton("Loooong Mod");
        loooongButton.addActionListener(this);
        loooongModPanel.add(loooongButton);
        loooongModTimeLabel = new JLabel("Time");
        loooongModPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        loooongModTimeLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        loooongModPanel.add(loooongModTimeLabel);

        // Djikstra generate mode
        JPanel djikstraModPanel = new JPanel();
        djikstraModPanel.setLayout(new BoxLayout(djikstraModPanel, BoxLayout.LINE_AXIS));
        djikstraButton = new JButton("Djikstra Mod");
        djikstraButton.addActionListener(this);
        djikstraModPanel.add(djikstraButton);
        djikstraModTimeLabel = new JLabel("Time");
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
        JPanel displayModePanel = new JPanel();
        displayModePanel.setLayout(new BoxLayout(displayModePanel, BoxLayout.LINE_AXIS));
        displayModeButton = new JButton("Display Mode");
        displayModeButton.addActionListener(this);
        displayModePanel.add(displayModeButton);
        displayModePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel fogPanel = new JPanel();
        fogPanel.setLayout(new BoxLayout(fogPanel, BoxLayout.LINE_AXIS));
        fogButton = new JButton("Fog");
        fogButton.addActionListener(this);
        fogPanel.add(fogButton);
        fogPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel autoPanel = new JPanel();
        autoPanel.setLayout(new BoxLayout(autoPanel, BoxLayout.LINE_AXIS));
        autoButton = new JButton("Auto");
        autoButton.addActionListener(this);
        autoPanel.add(autoButton);
        autoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        moveKevinPanel.add(yoloPanel);
        moveKevinPanel.add(commonPanel);
        moveKevinPanel.add(mamaPanel);
        moveKevinPanel.add(displayModePanel);
        moveKevinPanel.add(fogPanel);
        moveKevinPanel.add(autoPanel);

        // add elements to configPanel
        configPanel.add(generateLabelPanel);
        configPanel.add(loooongModPanel);
        configPanel.add(djikstraModPanel);
        configPanel.add(moveKevinLabelPanel);
        configPanel.add(moveKevinPanel);

        // add both JPanel to window
        window.getContentPane().add(gridPanel, BorderLayout.CENTER);
        window.getContentPane().add(configPanel, BorderLayout.EAST);

        xPositionKevin = currentBoard.row - 2;
        yPositionKevin = 1;

        //shortestPath = new ShortestPath(currentBoard.col - 2, currentBoard.row - 2, xPositionKevin, yPositionKevin, currentBoard.colGold, currentBoard.rowGold, currentBoard.getProxiBoard());
        //shortestPath.djikstra();
        // start filling grid
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {
                String cellLabel = "";
                ImageIcon image = groundImg;

                // Affichage classique
                if (currentBoard.displayMode) {
                    if (currentBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    } else if (currentBoard.getBoard()[row][col].getHole() == true) {
                        if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinInFireImg;
                        } else {
                            image = fireImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                        if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinInMonsterImg;
                        } else {
                            image = monsterImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                        if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinInGoldImg;
                        } else {
                            image = goldImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                        image = kevinImg;
                        xPositionKevin = row;
                        yPositionKevin = col;
                    } else {
                        image = groundImg;
                    }
                    if (currentBoard.fogMode && currentBoard.getBoard()[row][col].getFog() == true) {
                        image = fogImg;
                    }
                } // Affichage du vent et des odeurs
                else if (!currentBoard.displayMode) {
                    if ((currentBoard.getBoard()[row][col].getWind() == true) && (currentBoard.getBoard()[row][col].getSmell() == true)) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellHeatKevinInFireImg;
                            } else {
                                image = smellHeatFireImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellHeatKevinInMonsterImg;
                            } else {
                                image = smellHeatMonsterImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellHeatKevinInGoldImg;
                            } else {
                                image = smellHeatGoldImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = smellHeatKevinImg;
                            xPositionKevin = row;
                            yPositionKevin = col;
                        } else {
                            image = smellHeatGroundImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getSmell() == true) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellKevinInFireImg;
                            } else {
                                image = smellFireImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellKevinInMonsterImg;
                            } else {
                                image = smellMonsterImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellKevinInGoldImg;
                            } else {
                                image = smellGoldImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = smellKevinImg;
                        } else {
                            image = smellGroundImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getWind() == true) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = heatKevinInFireImg;
                            } else {
                                image = heatFireImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = heatKevinInMonsterImg;
                            } else {
                                image = heatMonsterImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = heatKevinInGoldImg;
                            } else {
                                image = heatGoldImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = heatKevinImg;
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
                    if (currentBoard.fogMode && currentBoard.getBoard()[row][col].getFog() == true) {
                        image = fogImg;
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
                if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                    xPositionKevin = row;
                    yPositionKevin = col;
                }
                // Affichage classique
                if (currentBoard.displayMode) {
                    if (currentBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    } else if (currentBoard.getBoard()[row][col].getHole() == true) {
                        if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinInFireImg;
                        } else {
                            image = fireImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                        if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinInMonsterImg;
                        } else {
                            image = monsterImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                        if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinInGoldImg;
                        } else {
                            image = goldImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                        image = kevinImg;
                        xPositionKevin = row;
                        yPositionKevin = col;
                    } else {
                        image = groundImg;
                    }
                    if (currentBoard.fogMode && currentBoard.getBoard()[row][col].getFog() == true) {
                        image = fogImg;
                    }
                } // Affichage du vent et des odeurs
                else if (!currentBoard.displayMode) {
                    if ((currentBoard.getBoard()[row][col].getWind() == true) && (currentBoard.getBoard()[row][col].getSmell() == true)) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellHeatKevinInFireImg;
                            } else {
                                image = smellHeatFireImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellHeatKevinInMonsterImg;
                            } else {
                                image = smellHeatMonsterImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellHeatKevinInGoldImg;
                            } else {
                                image = smellHeatGoldImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = smellHeatKevinImg;
                            xPositionKevin = row;
                            yPositionKevin = col;
                        } else {
                            image = smellHeatGroundImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getSmell() == true) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellKevinInFireImg;
                            } else {
                                image = smellFireImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellKevinInMonsterImg;
                            } else {
                                image = smellMonsterImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = smellKevinInGoldImg;
                            } else {
                                image = smellGoldImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = smellKevinImg;
                        } else {
                            image = smellGroundImg;
                        }
                    } else if (currentBoard.getBoard()[row][col].getWind() == true) {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = heatKevinInFireImg;
                            } else {
                                image = heatFireImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = heatKevinInMonsterImg;
                            } else {
                                image = heatMonsterImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = heatKevinInGoldImg;
                            } else {
                                image = heatGoldImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = heatKevinImg;
                        } else {
                            image = heatGroundImg;
                        }
                    } else {
                        if (currentBoard.getBoard()[row][col].getHole() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = kevinInFireImg;
                            } else {
                                image = fireImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getMonster() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = kevinInMonsterImg;
                            } else {
                                image = monsterImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getGold() == true) {
                            if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                                image = kevinInGoldImg;
                            } else {
                                image = goldImg;
                            }
                        } else if (currentBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinImg;
                        } else {
                            image = groundImg;
                        }
                    }

                    if (currentBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    }
                    if (currentBoard.fogMode && currentBoard.getBoard()[row][col].getFog() == true) {
                        image = fogImg;
                    }
                }
                int scale = 2;
                int width = image.getIconWidth();
                int newWidth = width / scale;
                buttonGrid[row][col].setIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
                buttonGrid[row][col].setDisabledIcon(new ImageIcon(image.getImage().getScaledInstance(newWidth, -1, java.awt.Image.SCALE_SMOOTH)));
                buttonGrid[row][col].setMargin(new Insets(0, 0, 0, 0));
                buttonGrid[row][col].setBorder(BorderFactory.createEmptyBorder());
                showPath = false;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object source = e.getSource();
        
        // Onglet Exit
        if (source == jmiExit){
            System.exit(0);
        }
        
        // Onglet Restart
        else if (source == jmiRestart){
            // TODO
        }
        
        // Onglet Menu
        else if (source == jmiMenu){
            // TODO
        }
        
        // Onglet About
        else if (source == jmiAbout){
            JFrame aboutFrame = new JFrame("About");
            aboutFrame.setSize(500, 500);
            aboutFrame.setLocationRelativeTo(null);
            aboutFrame.setVisible(true);
            
            JPanel nameAbout = new JPanel();
            JLabel labelAbout = new JLabel("<html>----------------- Create by -----------------  <br><br><br> RACZKIEWICZ Pavel <br> SERMONT Thomas <br> TIBAU Tancrede <br> BOULET Guillaume <br> LAURO Doryann <br> BERGESE Sébastien </html>");
            labelAbout.setBorder(new EmptyBorder(10, 10, 10, 10));
            nameAbout.add(labelAbout);
            
            aboutFrame.add(nameAbout);
        }
        
        else if (source == djikstraButton) {
            showPath = !showPath;
            // Djikstra BUTTON
            if (showPath) {
                System.out.println("Point de depart: " + xPositionKevin + " " + yPositionKevin);
                ShortestPath shortestPath = null;
                System.gc();
                shortestPath = new ShortestPath(currentBoard.col - 2, currentBoard.row - 2, xPositionKevin, yPositionKevin, currentBoard.colGold, currentBoard.rowGold, currentBoard.getProxiBoard());
                int djikstraTime = (int) Calendar.getInstance().getTimeInMillis();
                System.out.println(djikstraTime);
                shortestPath.djikstra();
                shortestPath.path();
                int djikstraTime2 = (int) Calendar.getInstance().getTimeInMillis();
                System.out.println(djikstraTime2);
                int djikstraTime3 = djikstraTime2 - djikstraTime;
                djikstraModTimeLabel.setText(djikstraTime3 + " Milli");
                //shortestPath.showDjikstra();
                // start filling grid
                for (int row = 0; row < buttonGrid.length; row++) {
                    for (int col = 0; col < buttonGrid[row].length; col++) {
                        // DJIKSTRA
                        for (int index = 0; index < shortestPath.path.size(); index++) {
                            if (row == shortestPath.path.get(index)[0] && col == shortestPath.path.get(index)[1] && (currentBoard.getBoard()[row][col].getGold() == false)) {
                                ImageIcon image = djikstraImg;
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
            } else {
                refreshBoard();
            }
        } else if (source == loooongButton) {
            showPath = !showPath;
            if (true) {
                System.out.println("MARCHE PO LOL");
            } else if (showPath) {
                System.out.println("Point de depart: " + xPositionKevin + " " + yPositionKevin);
                longestPath = new LongestPath(xPositionKevin, yPositionKevin, 19, currentBoard);
                loooongTime = Calendar.getInstance().get(Calendar.MILLISECOND);
                longestPath.looooongInit();
                loooongTime = Calendar.getInstance().get(Calendar.MILLISECOND) - loooongTime;
                loooongModTimeLabel.setText(loooongTime + " Milli");
                longestPath.showLoooong();
                // start filling grid
                for (int row = 0; row < buttonGrid.length; row++) {
                    for (int col = 0; col < buttonGrid[row].length; col++) {
                        // LOOOOONG
                        for (int index = 0; index < longestPath.looooong.size(); index++) {
                            if (row == longestPath.looooong.get(index)[0] && col == longestPath.looooong.get(index)[1] && (currentBoard.getBoard()[row][col].getGold() == false)) {
                                ImageIcon image = djikstraImg;
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
            } else {
                refreshBoard();
            }
        } else if (source == autoButton) {
            moveKevinAuto = !moveKevinAuto;
        } else if (source == displayModeButton) {
            currentBoard.displayMode = !currentBoard.displayMode;
            refreshBoard();
        } else if (source == fogButton) {
            currentBoard.fogMode = !currentBoard.fogMode;
            refreshBoard();
        }
    }

}
