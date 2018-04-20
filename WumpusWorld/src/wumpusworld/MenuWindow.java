/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wumpusworld;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static wumpusworld.WumpusWorld.moveKevinAuto;

/**
 *
 * @author Doryann
 */
public class MenuWindow extends JFrame implements ActionListener {
    
    JComboBox kevinChoice;
    JFormattedTextField holes;
    JFormattedTextField size;
    JFormattedTextField speed;
    JLabel kevinChoiceLabel;
    JLabel holeLabel;
    JLabel sizeLabel;
    JLabel speedLabel;
    JButton start;
    Container contentPane;
    int interval = 50;
    Board boardGame;
    GameWindow mWindow;
    
    public MenuWindow(){
        
        //initialisation de la fenetre et du layout
        setTitle("La légende de Kevin 12 ans");
	setSize(500,500);
        contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        
        //Initialisation des composants
         Format number = NumberFormat.getNumberInstance(Locale.FRENCH);
        //ComboBox
        kevinChoice = new JComboBox();
        kevinChoice.addItem("Kevin, le Redoublant");
        kevinChoice.addItem("Kevin, l'authentique");
        kevinChoice.addItem("Kevin, le brillant");
        kevinChoice.setPreferredSize(new Dimension(100, 20));
        kevinChoiceLabel = new JLabel("Choix du Kevin : ");
        
        //Zone de texte pour le nombre de trous
        holes = new JFormattedTextField(number);
        holes.setPreferredSize(new Dimension(40, 20));
        
        holeLabel = new JLabel("Nombre de trous :");
        
        //Zone de texte pour la taille de la map
        size = new JFormattedTextField(number);
        size.setPreferredSize(new Dimension(40, 20));
        sizeLabel = new JLabel("Taille de la map :");
        
        //Zone de texte pour la vitesse de Kévin
        speed = new JFormattedTextField(number);
        speed.setPreferredSize(new Dimension(40, 20));
        speedLabel = new JLabel("Vitesse de Kevin");
        
        //Bouton
        start = new JButton("Go !");
        start.addActionListener(this);
        
        //ajout des composants
        this.add(kevinChoiceLabel);
        this.add(kevinChoice);
        this.add(holeLabel);
        this.add(holes);
        this.add(sizeLabel);
        this.add(size);
        this.add(speedLabel);
        this.add(speed);
        this.add(start);
        
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //appel board et gamewindow en lui passant les paramètres
        int numberOfCells = 0;
        int holesNumber = 0;
        int chooseKevin = 0;
        
        if(kevinChoice.getSelectedItem().toString() == "Kevin, le Redoublant"){
            chooseKevin = 1;
        }
        if(kevinChoice.getSelectedItem().toString() == "Kevin, l'authentique"){
            chooseKevin = 2;
        }
        if(kevinChoice.getSelectedItem().toString() == "Kevin, le brillant"){
            chooseKevin = 3;
        }
        numberOfCells = Integer.valueOf(size.getText().trim());
        holesNumber = Integer.valueOf(holes.getText().trim());
        //System.out.println("valeurs : "+numberOfCells+" ,"+holesNumber);
        boardGame = new Board(numberOfCells, numberOfCells);
        boardGame.setBoard(holesNumber, chooseKevin);
        
        final Thread t1 = new Thread(){
            @Override
            public void run(){
                mWindow = new GameWindow(boardGame);
                callMyService();
                mWindow.refreshBoard();
                System.out.println(""+WumpusWorld.moveKevinAuto);
            }
        };
        t1.start();

        final Thread t2 = new Thread(){
        @Override
            public void run(){
                try {
                    t1.join();
                    sleep(3000);
                    play(boardGame, mWindow);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t2.start();
    }
                
    

    public void play(Board boardGame, GameWindow mWindow) throws InterruptedException{
        
        while (boardGame.kevin.status == 0) {
            while (WumpusWorld.moveKevinAuto && boardGame.kevin.status == 0) {
                int[] move = boardGame.kevin.go();
                boardGame.moveKevin(move[0], move[1]);
                mWindow.refreshBoard();
                sleep(interval);
            }
        sleep(1);
        }
    }
    
    private static void callMyService() {
        System.out.println("DEBUT appel bloquant");
        try {
            Thread.currentThread().sleep(7000);
            System.out.println("FIN appel bloquant");
        } catch (InterruptedException e) {
            System.out.println("appel bloquant interrompu");
        }
    }
}
