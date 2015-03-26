package tetris.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetris.domain.Muoto;
import tetris.domain.Muoto.Tetrinikat;

public class Piirtoalusta extends JPanel implements Paivitettava {
    
    final int taulunLeveys = 10;
    final int taulunKorkeus = 22;
    
//    private Timer timer;
//    private boolean lattiassa = false;
//    private boolean kaynnissa = false;
//    private boolean pause = false;
//    private Pistelaskuri pisteet;
//    private int palanX;
//    private int palanY;
//    private JLabel tilanne;
//    private Muoto putoava;
//    private Tetrinikat[] koordinaatisto;
    
    @Override
    public void paivita() {
        this.repaint();
        
        
    }

    @Override
    public void lopeta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
