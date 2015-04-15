package tetris.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import tetris.domain.Muoto.Tetrinikat;

/**
 * Toiminta: nuolet vasen ja oikea liikuttavat palaa koordinaatistossa
 * toivottuun suuntaan Nuoli alas pudottaa palaa rivin alas Välilyönti kääntää
 * palan
 *
 * @author totutotu
 */
public class Nappaimistonkuuntelija extends KeyAdapter {

    Piirtoalusta ruudukko;

    public Nappaimistonkuuntelija(Piirtoalusta ruudukko) {
        this.ruudukko = ruudukko;
    }

    @Override
    public void keyPressed(KeyEvent ke) {

        if (!ruudukko.getKaynnissa() || ruudukko.getPutoava().getMuoto() == Tetrinikat.Muodoton) {
            return;
        }

        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            ruudukko.yritaLiikkua(ruudukko.getPutoava(), ruudukko.getPalanX() - 1, ruudukko.getPalanY());
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            ruudukko.yritaLiikkua(ruudukko.getPutoava(), ruudukko.getPalanX() + 1, ruudukko.getPalanY());
        } else if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            ruudukko.yritaLiikkua(ruudukko.getPutoava().kaanna(), ruudukko.getPalanX(), ruudukko.getPalanY());
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            ruudukko.riviAlas();
        }
    }
}
