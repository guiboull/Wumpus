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
    private ImageIcon heatFireImg;
    private ImageIcon heatGoldImg;
    private ImageIcon heatGroundImg;
    private ImageIcon heatKevinImg;
    private ImageIcon heatKevinInFireImg;
    private ImageIcon heatKevinInGoldImg;
    private ImageIcon heatKevinInMonsterImg;
    private ImageIcon smellFireImg;
    private ImageIcon smellGoldImg;
    private ImageIcon smellGroundImg;
    private ImageIcon smellKevinImg;
    private ImageIcon smellKevinInFireImg;
    private ImageIcon smellKevinInGoldImg;
    private ImageIcon smellKevinInMonsterImg;
    private ImageIcon smellMonsterImg;
    private ImageIcon smellHeatFireImg;
    private ImageIcon smellHeatGoldImg;
    private ImageIcon smellHeatGroundImg;
    private ImageIcon smellHeatKevinImg;
    private ImageIcon smellHeatKevinInFireImg;
    private ImageIcon smellHeatKevinInGoldImg;
    private ImageIcon smellHeatKevinInMonsterImg;   
    private ImageIcon smellHeatMonsterImg;
    private ImageIcon djikstraImg;

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

        ShortestPath d = new ShortestPath(mBoard.col-2, mBoard.row-2, mBoard.colGold, mBoard.rowGold, mBoard.proxiBoard);
        d.djikstra();
        d.showDjikstra();
        
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
        
        heatFireImg = new ImageIcon("images/heat/heatfire.jpg");
        heatGoldImg = new ImageIcon("images/heat/heatgold.jpg");
        heatGroundImg = new ImageIcon("images/heat/heatground.jpg");
        heatKevinImg = new ImageIcon("images/heat/heatkevin.jpg");
        heatKevinInFireImg = new ImageIcon("images/heat/heatkevininfire.jpg");
        heatKevinInMonsterImg = new ImageIcon("images/heat/heatkevininmonster.jpg");     
        heatKevinInGoldImg = new ImageIcon("images/heat/heatkevininmonster.jpg");
        
        smellFireImg = new ImageIcon("images/smell/smellfire.jpg");
        smellGoldImg = new ImageIcon("images/smell/smellgold.jpg");
        smellGroundImg = new ImageIcon("images/smell/smellground.jpg");
        smellKevinImg = new ImageIcon("images/smell/smellkevin.jpg");
        smellKevinInFireImg = new ImageIcon("images/smell/smellkevininfire.jpg");
        smellKevinInGoldImg = new ImageIcon("images/smell/smellkeviningold.jpg");
        smellKevinInMonsterImg = new ImageIcon("images/smell/smellkevininmonster.jpg");
        smellMonsterImg = new ImageIcon("images/smell/smellmonster.jpg");
        
        smellHeatFireImg = new ImageIcon("images/smellheat/smellheatfire.jpg");
        smellHeatGoldImg = new ImageIcon("images/smellheat/smellheatgold.jpg");
        smellHeatGroundImg = new ImageIcon("images/smellheat/smellheatground.jpg");
        smellHeatKevinImg = new ImageIcon("images/smellheat/smellheatkevin.jpg");
        smellHeatKevinInFireImg = new ImageIcon("images/smellheat/smellheatkevininfire.jpg");
        smellHeatKevinInGoldImg = new ImageIcon("images/smellheat/smellheatkeviningold.jpg");
        smellHeatKevinInMonsterImg = new ImageIcon("images/smellheat/smellheatkevininmonster.jpg"); 
        smellHeatMonsterImg = new ImageIcon("images/smellheat/smellheatmonster.jpg");     
        
        djikstraImg = new ImageIcon("images/djikstra.jpg");
        
        // start filling grid
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {
                String cellLabel = "";
                ImageIcon image = groundImg;
                
                // Affichage classique
                if (mBoard.typeAffichage == 1) {
                    if (mBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    }
                    else if (mBoard.getBoard()[row][col].getHole() == true) {
                        image = fireImg;
                    }
                    else if (mBoard.getBoard()[row][col].getMonster() == true) {
                        image = monsterImg;
                    }
                    else if (mBoard.getBoard()[row][col].getPlayer() == true) {
                        image = kevinImg;
                    }
                    else if (mBoard.getBoard()[row][col].getGold() == true) {
                        image = goldImg;
                    }
                    else image = groundImg;
                }
                
                // Affichage du vent et des odeurs
                else if (mBoard.typeAffichage == 2) {
                    if ((mBoard.getBoard()[row][col].getWind() == true) && (mBoard.getBoard()[row][col].getSmell() == true)) {
                        if (mBoard.getBoard()[row][col].getHole() == true) {
                           image = smellHeatFireImg;
                        }
                        else if (mBoard.getBoard()[row][col].getMonster() == true) {
                           image = smellHeatMonsterImg;
                        }
                        else if (mBoard.getBoard()[row][col].getPlayer() == true) {
                           image = smellHeatKevinImg;
                        }
                        else if (mBoard.getBoard()[row][col].getGold() == true) {
                           image = smellHeatGoldImg;
                        }
                        else image = smellHeatGroundImg;
                    }
                    else if (mBoard.getBoard()[row][col].getSmell() == true) {
                        if (mBoard.getBoard()[row][col].getHole() == true) {
                           image = smellFireImg;
                        }
                        else if (mBoard.getBoard()[row][col].getMonster() == true) {
                           image = smellMonsterImg;
                        }
                        else if (mBoard.getBoard()[row][col].getPlayer() == true) {
                           image = smellKevinImg;
                        }
                        else if (mBoard.getBoard()[row][col].getGold() == true) {
                           image = smellGoldImg;
                        }
                        else image = smellGroundImg;
                    }
                    else if (mBoard.getBoard()[row][col].getWind() == true) {
                        if (mBoard.getBoard()[row][col].getHole() == true) {
                           image = heatFireImg;
                        }
                        else if (mBoard.getBoard()[row][col].getMonster() == true) {
                           image = smellHeatMonsterImg;
                        }
                        else if (mBoard.getBoard()[row][col].getPlayer() == true) {
                           image = heatKevinImg;
                        }
                        else if (mBoard.getBoard()[row][col].getGold() == true) {
                           image = heatGoldImg;
                        }  
                        else image = heatGroundImg;
                    }
                    else {
                        if (mBoard.getBoard()[row][col].getHole() == true) {
                            image = fireImg;
                        }
                        else if (mBoard.getBoard()[row][col].getMonster() == true) {
                            image = monsterImg;
                        }
                        else if (mBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinImg;
                        }
                        else if (mBoard.getBoard()[row][col].getGold() == true) {
                            image = goldImg;
                        }
                        else image = groundImg;
                    }
                    
                    if (mBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    }
                }
                
                // DJIKSTRA
                for (int djikstra = 0; djikstra < d.path.size(); djikstra++) {    
                    if (row == d.path.get(djikstra)[0] && col == d.path.get(djikstra)[1]) {
                           image = djikstraImg;
                           System.out.println("OK");
                    }
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

    public void refreshBoard(Board mBoard) {
        // start filling grid
        for (int row = 0; row < buttonGrid.length; row++) {
            for (int col = 0; col < buttonGrid[row].length; col++) {

                ImageIcon image = groundImg;

                // Affichage classique
                if (mBoard.typeAffichage == 1) {
                    if (mBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    }
                    else if (mBoard.getBoard()[row][col].getHole() == true) {
                        image = fireImg;
                    }
                    else if (mBoard.getBoard()[row][col].getMonster() == true) {
                        image = monsterImg;
                    }
                    else if (mBoard.getBoard()[row][col].getPlayer() == true) {
                        image = kevinImg;
                    }
                    else if (mBoard.getBoard()[row][col].getGold() == true) {
                        image = goldImg;
                    }
                    else image = groundImg;
                }
                
                // Affichage du vent et des odeurs
                else if (mBoard.typeAffichage == 2) {
                    if ((mBoard.getBoard()[row][col].getWind() == true) && (mBoard.getBoard()[row][col].getSmell() == true)) {
                        if (mBoard.getBoard()[row][col].getHole() == true) {
                           image = smellHeatFireImg;
                        }
                        else if (mBoard.getBoard()[row][col].getMonster() == true) {
                           image = smellHeatMonsterImg;
                        }
                        else if (mBoard.getBoard()[row][col].getPlayer() == true) {
                           image = smellHeatKevinImg;
                        }
                        else if (mBoard.getBoard()[row][col].getGold() == true) {
                           image = smellHeatGoldImg;
                        }
                        else image = smellHeatGroundImg;
                    }
                    else if (mBoard.getBoard()[row][col].getSmell() == true) {
                        if (mBoard.getBoard()[row][col].getHole() == true) {
                           image = smellFireImg;
                        }
                        else if (mBoard.getBoard()[row][col].getMonster() == true) {
                           image = smellMonsterImg;
                        }
                        else if (mBoard.getBoard()[row][col].getPlayer() == true) {
                           image = smellKevinImg;
                        }
                        else if (mBoard.getBoard()[row][col].getGold() == true) {
                           image = smellGoldImg;
                        }
                        else image = smellGroundImg;
                    }
                    else if (mBoard.getBoard()[row][col].getWind() == true) {
                        if (mBoard.getBoard()[row][col].getHole() == true) {
                           image = heatFireImg;
                        }
                        else if (mBoard.getBoard()[row][col].getMonster() == true) {
                           image = smellHeatMonsterImg;
                        }
                        else if (mBoard.getBoard()[row][col].getPlayer() == true) {
                           image = heatKevinImg;
                        }
                        else if (mBoard.getBoard()[row][col].getGold() == true) {
                           image = heatGoldImg;
                        }  
                        else image = heatGroundImg;
                    }
                    else {
                        if (mBoard.getBoard()[row][col].getHole() == true) {
                            image = fireImg;
                        }
                        else if (mBoard.getBoard()[row][col].getMonster() == true) {
                            image = monsterImg;
                        }
                        else if (mBoard.getBoard()[row][col].getPlayer() == true) {
                            image = kevinImg;
                        }
                        else if (mBoard.getBoard()[row][col].getGold() == true) {
                            image = goldImg;
                        }
                        else image = groundImg;
                    }
                    
                    if (mBoard.getBoard()[row][col].getWall() == true) {
                        image = rockImg;
                    }
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
