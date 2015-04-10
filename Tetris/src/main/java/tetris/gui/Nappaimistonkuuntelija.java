package tetris.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import tetris.domain.Muoto.Tetrinikat;

public class Nappaimistonkuuntelija extends KeyAdapter {
    Piirtoalusta ruudukko;

    public Nappaimistonkuuntelija(Piirtoalusta ruudukko) {
        this.ruudukko = ruudukko;
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
        if(!ruudukko.getKaynnissa() || ruudukko.getPutoava().getMuoto() == Tetrinikat.Muodoton) {
            return;
        }
        
        //taukomahdollisuus?
        
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            ruudukko.yritaLiikkua(ruudukko.getPutoava(), ruudukko.getPalanX() - 1, ruudukko.getPalanY());
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            ruudukko.yritaLiikkua(ruudukko.getPutoava(), ruudukko.getPalanX() + 1, ruudukko.getPalanY());
        } else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            ruudukko.yritaLiikkua(ruudukko.getPutoava().kaanna(), ruudukko.getPalanX() , ruudukko.getPalanY());
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            ruudukko.riviAlas();
        } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
            ruudukko.yritaLiikkua(ruudukko.getPutoava(), ruudukko.getPalanX(), ruudukko.getPalanY());
        } 
    
    }
}
