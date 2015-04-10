package tetris.peli;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tetris.gui.Piirtoalusta;

public class Tetris extends JFrame {
    JLabel pistetilanne;

    public Tetris() {
        
        pistetilanne = new JLabel("0");
        add(pistetilanne, BorderLayout.SOUTH);
        Piirtoalusta alusta = new Piirtoalusta(this);
        add(alusta);
        alusta.kaynnista();
        
        setSize(200, 400);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    }
    
    public JLabel pisteTilanne() {
        return pistetilanne;
    }
    
    public static void main(String[] args) {
        
        Tetris peli = new Tetris();
        peli.setLocationRelativeTo(null);
        peli.setVisible(true);

        
    }
    
    
    
    
}
