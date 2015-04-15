package tetris.gui;

import tetris.domain.Pistelaskuri;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tetris.domain.Muoto;
import tetris.domain.Muoto.Tetrinikat;
import tetris.peli.Tetris;

public class Piirtoalusta extends JPanel implements ActionListener {

    final int taulunLeveys = 10;
    final int taulunKorkeus = 22;

    private Timer ajastin;
    private int nopeus = 400;
    private boolean lattiassa = false;
    private boolean kaynnissa = false;

//    private boolean pause = false;
    private Pistelaskuri pisteet = new Pistelaskuri();
    private int palanX = 0;
    private int palanY = 0;
    private JLabel tilanne;
    private Muoto putoava;
    private Tetrinikat[] koordinaatisto;
    private int nopeutusRaja = 80;

    public Piirtoalusta(Tetris parent) {

        setFocusable(true);
        putoava = new Muoto();
        ajastin = new Timer(400, this);
        ajastin.start();

        tilanne = parent.pisteTilanne();
        koordinaatisto = new Tetrinikat[taulunLeveys * taulunKorkeus];
        addKeyListener(new Nappaimistonkuuntelija(this));
        tyhjaAlusta();

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

    /**
     * Palauttaa muodon nimen halutusta sijainnista (väritystä varten)
     *
     * @param x
     * @param y
     * @return
     */
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
        nopeus = (int) (nopeus * 1.1);
        ajastin = new Timer(nopeus, this);
        ajastin.start();
    }

    /**
     * Luo uuden satunnaisen palan, laittaa sen ruudukon kattoon, testaa, osuuko
     * pala heti toiseen palaan, mikä lopettaisi pelin
     */
    public void uusiPala() {
        putoava.asetaRandomMuoto();
        palanX = taulunLeveys / 2 + 1;
        palanY = taulunKorkeus - 1 + putoava.minY();

        if (!yritaLiikkua(putoava, palanX, palanY)) {
            putoava.asetaMuoto(Tetrinikat.Muodoton);
            ajastin.stop();
            kaynnissa = false;
            tilanne.setText("Hävisit pelin! Pisteet:" + pisteet.getPisteet());

        }
    }

    /**
     * Kokeilee, oonko palan liikuttaminen toivottuun suuntaan mahdollista
     * Asettaa onnistuttua luokan putoava-muuttujan uudeksi palaksi
     *
     * @param uusiPala pala, jonka koordinaatit vaihdetaan haluttuun suuntaan
     * @param uusiX x-koordinaatti, johon halutaan uusiPala siirtää
     * @param uusiY y-koordinaatti johon haluttiin uusiPala siirtää
     * @return onnistuiko
     */
    public boolean yritaLiikkua(Muoto uusiPala, int uusiX, int uusiY) {

        for (int i = 0; i < 4; i++) {
            int x = uusiX + uusiPala.getX(i);
            int y = uusiY - uusiPala.getY(i);
            if (x < 0 || x >= taulunLeveys || y < 0 || y >= taulunKorkeus) {
                return false;
            }
            if (muotoSijainnissa(x, y) != Tetrinikat.Muodoton) {
                return false;
            }
        }

        putoava = uusiPala;
        palanX = uusiX;
        palanY = uusiY;
        this.repaint();

        return true;
    }

    /**
     * Liikuttaa jos mahdollista muotoa yhden alas, jos ei mahdollista,
     * tarkiostaa palaLattiassa() metodilla, onko pala lattiassa
     *
     */
    public void riviAlas() {
        if (!yritaLiikkua(putoava, palanX, palanY - 1)) {
            palaLattiassa();
        }
    }

    /**
     * Palan ollessa lattiassa asettaa koordinaatistoon tiedon muotojen
     * sijainnista, poistaa täydet rivit, ja jos yksittäisiä palasia ei jää
     * putoamaan rivien poistamisen jälkeen laittaa vireille uuden palan
     */
    private void palaLattiassa() {
        for (int i = 0; i < 4; i++) {
            int x = palanX + putoava.getX(i);
            int y = palanY - putoava.getY(i);
            koordinaatisto[(y * taulunLeveys) + x] = putoava.getMuoto();
        }

        poistaTaydetRivit();

        if (!lattiassa) {
            uusiPala();
        }
    }

    /**
     * Luo "tyhjän" koordinaatiston alustaksi, jossa jokaiseen ruutuun asetettu
     * arvo "Muodoton", joka ei reagoi mitenkää, jos sen kohdalle putoaa pala
     */
    private void tyhjaAlusta() {
        for (int i = 0; i < taulunKorkeus * taulunLeveys; i++) {
            koordinaatisto[i] = Tetrinikat.Muodoton;
        }
    }

    private void piirraNelio(Graphics g, int x, int y, Tetrinikat muoto) {
        Color colors[] = {new Color(0, 0, 0), new Color(204, 102, 102),
            new Color(102, 204, 102), new Color(102, 102, 204),
            new Color(204, 204, 102), new Color(204, 102, 204),
            new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[muoto.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, nelionLeveys() - 2, nelionKorkeus() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + nelionKorkeus() - 1, x, y);
        g.drawLine(x, y, x + nelionLeveys() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + nelionKorkeus() - 1,
                x + nelionLeveys() - 1, y + nelionKorkeus() - 1);
        g.drawLine(x + nelionLeveys() - 1, y + nelionKorkeus() - 1,
                x + nelionLeveys() - 1, y + 1);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - taulunKorkeus * nelionKorkeus();

        for (int i = 0; i < taulunKorkeus; ++i) {
            for (int j = 0; j < taulunLeveys; ++j) {
                Tetrinikat muoto = muotoSijainnissa(j, taulunKorkeus - i - 1);
                if (muoto != Tetrinikat.Muodoton) {
                    piirraNelio(g, 0 + j * nelionLeveys(),
                            boardTop + i * nelionKorkeus(), muoto);
                }
            }
        }

        if (putoava.getMuoto() != Tetrinikat.Muodoton) {
            for (int i = 0; i < 4; ++i) {
                int x = palanX + putoava.getX(i);
                int y = palanY - putoava.getY(i);
                piirraNelio(g, 0 + x * nelionLeveys(),
                        boardTop + (taulunKorkeus - y - 1) * nelionKorkeus(),
                        putoava.getMuoto());
            }
        }
    }

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

    /**
     * Tarkistaa, onko ruudukossa täysiä rivejä, jos on, poistaa rivin, pudottaa
     * "ylemmän kerroksen" palaset alas, ja kasvattaa tarvittavan määrän
     * pistelaskuria
     */
    private void poistaTaydetRivit() {

        int taydetRivit = 0;

        for (int i = taulunKorkeus - 1; i >= 0; i--) {
            boolean riviTaynna = true;

            for (int j = 0; j < taulunLeveys; j++) {
                if (muotoSijainnissa(j, i) == Tetrinikat.Muodoton) {
                    riviTaynna = false;
                    break;
                }
            }

            if (riviTaynna) {
                taydetRivit++;
                for (int k = i; k < taulunKorkeus - 1; k++) {
                    for (int j = 0; j < taulunLeveys; j++) {
                        koordinaatisto[(k * taulunLeveys) + j] = muotoSijainnissa(j, k + 1);
                    }
                }
            }
        }

        if (taydetRivit > 0) {
            pisteet.kasvataPisteita(taydetRivit);
            tilanne.setText(String.valueOf(pisteet.getPisteet()));

            if (pisteet.getPisteet() >= nopeutusRaja) {
                lisaaSpiidii();
                nopeutusRaja += 100;
            }

            lattiassa = true;
            putoava.asetaMuoto(Tetrinikat.Muodoton);
            this.repaint();
        }

    }
}
