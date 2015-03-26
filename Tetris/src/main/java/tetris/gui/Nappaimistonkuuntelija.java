package tetris.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import tetris.domain.Muoto;

public class Nappaimistonkuuntelija implements KeyListener {
    public Muoto muoto;
    
    
    public Nappaimistonkuuntelija(Muoto muoto) {
        this.muoto = muoto;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == 'p' || ke.getKeyCode() == 'P') {
            //pause
        }
        
//        if(isPaused) {
//            return;
//        }
        
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
              //kokeile siirtää vasen
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            //oikealle
        } else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            //lattialle
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            //rivi alas
        } else if (ke.getKeyCode() == KeyEvent.VK_UP) {
            //käännä
        } 
    
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
}
