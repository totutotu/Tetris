package tetris.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetris.domain.Muoto;
import tetris.domain.Muoto.Tetrinikat;
import tetris.peli.Tetris;

public class Piirtoalusta extends JPanel implements ActionListener, Paivitettava {

    final int taulunLeveys = 10;
    final int taulunKorkeus = 22;

    private Timer ajastin;
    private int nopeus = 400;
    private boolean lattiassa = false;
    private boolean kaynnissa = false;

//    private boolean pause = false;
    private Pistelaskuri pisteet = new Pistelaskuri();
    private int palanX;
    private int palanY;
    private JLabel tilanne;
    private Muoto putoava;
    private Tetrinikat[] koordinaatisto;

    public Piirtoalusta(Tetris parent) {

        setFocusable(true);
        putoava = new Muoto();
        ajastin = new Timer(400, this);
        ajastin.start();

        tilanne = parent.pisteTilanne();
        koordinaatisto = new Tetrinikat[taulunLeveys * taulunKorkeus];
        addKeyListener(new Nappaimistonkuuntelija(this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (lattiassa) {
            lattiassa = false;
            uusiPala();
        } else {
            riviAlas();

        }
    }

    int nelionLeveys() {
        return (int) getSize().getWidth() / taulunLeveys;
    }

    int nelionKorkeus() {
        return (int) getSize().getHeight() / taulunKorkeus;
    }

    Tetrinikat muotoSijainnissa(int x, int y) {
        return koordinaatisto[(y * taulunLeveys) + x];
    }

    /**
     * Tyhjentää kentän, luo uuden palan, käynnistää ajan
     */
    public void kaynnista() {

        kaynnissa = true;
        lattiassa = false;
        tyhjaAlusta();

        uusiPala();
        ajastin.start();
    }

    /**
     * Lisää spiidii
     */
    public void lisaaSpiidii() {
        nopeus = (int) (nopeus * 1.5);
        ajastin = new Timer(nopeus, this);
        ajastin.start();
    }
/**
 * Luo uuden satunnaisen palan, laittaa sen ruudukon kattoon,
 * testaa, osuuko pala heti toiseen palaan, mikä lopettaisi pelin
 */
    public void uusiPala() {
        putoava.asetaRandomMuoto();
        palanX = taulunLeveys / 2 + 1;
        palanY = taulunKorkeus - 1 + putoava.minY();

        if (!yritaLiikkua(putoava, palanX, palanY)) {
            putoava.asetaMuoto(Tetrinikat.Muodoton);
            ajastin.stop();
            kaynnissa = false;

        }
    }
/**
 * Kokeilee, oonko palan liikuttaminen toivottuun suuntaan mahdollista
 * Asettaa onnistuttua luokan putoava-muuttujan uudeksi palaksi
 * @param uusiPala pala, jonka koordinaatit vaihdetaan haluttuun suuntaan
 * @param uusiX x-koordinaatti, johon halutaan uusiPala siirtää
 * @param uusiY y-koordinaatti johon haluttiin uusiPala siirtää
 * @return 
 */
    public boolean yritaLiikkua(Muoto uusiPala, int uusiX, int uusiY) {

        for (int i = 0; i < 4; i++) {
            int x = uusiX + uusiPala.getX(i);
            int y = uusiY + uusiPala.getY(i);
            if (x < 0 || x >= taulunLeveys || y < 0 || y >= taulunKorkeus) {
                return false;
            }
        }

        putoava = uusiPala;
        palanX = uusiX;
        palanY = uusiY;
        paivita();
        System.out.println("khyl");
        
        return true;
    }

    public void riviAlas() {
        if (!yritaLiikkua(putoava, palanX, palanY - 1)) {
            palaLattiassa();
        }
    }

    private void palaLattiassa() {
        for (int i = 0; i < 4; i++) {
            int x = palanX + putoava.getX(i);
            int y = palanY + putoava.getY(i);
            koordinaatisto[(y  * taulunKorkeus) + x] = putoava.getMuoto();
        }
        
        poistaTaydetRivit();
        
        if (lattiassa) {
            uusiPala();
        }
        
        System.out.println("cool");
    }

    private void tyhjaAlusta() {
        for (int i = 0; i < taulunKorkeus * taulunLeveys; i++) {
            koordinaatisto[i] = Tetrinikat.Muodoton;
        }
    }

    @Override
    public void lopeta() {

    }

    @Override
    public void paivita() {
        this.repaint();
    }

    //Eteenpäin getterit
    public boolean getKaynnissa() {
        return kaynnissa;
    }

    public Muoto getPutoava() {
        return this.putoava;
    }

    public int getPalanX() {
        return palanX;
    }

    public int getPalanY() {
        return palanY;
    }

    private void poistaTaydetRivit() {
        lattiassa = true;
    }

}
