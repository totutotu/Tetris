package tetris.domain;

import java.util.Random;

public class Muoto {

    public enum Tetrinikat {

        Muodoton, Z, S, Viiva, T, Nelio, L, L2
    };

    private Tetrinikat pala;
    public int koord[][];

    public int[][][] kaikkiKoordinaatit;

    public Muoto() {
        koord = new int[4][2];
        asetaMuoto(Tetrinikat.Muodoton);
    }

    /**
     * Asettaa muodolle sopivat koordinaatit taulukosta kaikkiKoordinaatit, joka
     * sisältää kaikki mahdolliset koordinaattiyhdistelmät
     *
     * @param muoto
     *
     */
    public void asetaMuoto(Tetrinikat muoto) {

        kaikkiKoordinaatit = new int[][][]{
            {{0, 0}, {0, 0}, {0, 0}, {0, 0}},
            {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},
            {{0, -1}, {0, 0}, {1, 0}, {1, 1}},
            {{0, -1}, {0, 0}, {0, 1}, {0, 2}},
            {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}},
            {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},
            {{1, -1}, {0, -1}, {0, 0}, {0, 1}}
        };

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; ++j) {
                koord[i][j] = kaikkiKoordinaatit[muoto.ordinal()][i][j];
            }
        }
        pala = muoto;
    }

    /**
     * Metodi kääntää muodon, palauttaa muodon koordinaatit käännettynä 90
     * astetta
     *
     * @return käännetty muoto
     */
    public Muoto kaanna() {
        if (pala == Tetrinikat.Nelio) {
            return this;
        }

        Muoto uusi = new Muoto();
        uusi.pala = pala;

        for (int i = 0; i < 4; i++) {
            uusi.asetaX(i, this.getY(i));
            uusi.asetaY(i, -this.getX(i));
        }

        return uusi;

    }

    private void asetaX(int index, int x) {
        koord[index][0] = x;
    }

    private void asetaY(int index, int y) {
        koord[index][1] = y;
    }

    /**
     * palauttaa halutun x-arvon pyydetystä indeksistä.. Käytetään muualta
     * for-loopissa palauttamaan vuorotellen kaikki sijainnit.
     *
     * @param i
     * @return muodon palasen sijainnin x-koordinaatistossa
     */
    public int getX(int i) {
        return koord[i][0];
    }

    /**
     * palauttaa halutun y-arvon pyydetystä indeksistä.. Käytetään muualta
     * for-loopissa palauttamaan vuorotellen kaikki sijainnit.
     *
     * @param i
     * @return muodon palasen sijainnin y-koordinaatistossa
     */
    public int getY(int i) {
        return koord[i][1];
    }

    /**
     *
     * @return kyseisen muodon nimen
     */
    public Tetrinikat getMuoto() {
        return this.pala;
    }

    /**
     * Asettaa palaan satunnaisen muodon koordinaatit
     */
    public void asetaRandomMuoto() {
        Random random = new Random();
        int x = random.nextInt(7) + 1;
        Tetrinikat[] values = Tetrinikat.values();

        asetaMuoto(values[x]);

    }

    /**
     * Palauttaa muodon koordinaatistoesityksen muodossa (x,y) koordinaattien
     * määrän verran
     *
     * @return tekstiesitys
     */
    @Override
    public String toString() {
        String koordinaatit = "";
        for (int[] koord1 : koord) {
            koordinaatit += "( ";
            for (int l : koord1) {
                koordinaatit += l + " ";
            }
            koordinaatit += ") \n";
        }
        return koordinaatit;
    }

    /**
     * Palauttaa pienimmän y-Arvon muodosta (alimman palasen sijainnin)
     *
     * @return Y
     */
    public int minY() {
        int i = koord[0][1];
        for (int j = 0; j < 4; j++) {
            i = Math.min(i, koord[j][1]);

        }
        return i;
    }

    /**
     * Asettaa muodon zeta muodolle
     */
    public void asetaZetaMuoto() {
        this.asetaMuoto(Tetrinikat.Z);
    }

    /**
     * Asettaa muodon nelio muodolle
     */
    public void asetaNelioMuoto() {
        this.asetaMuoto(Tetrinikat.Nelio);
    }

    /**
     * Asettaa muodon assa muodolle
     */
    public void asetaAssaMuoto() {
        this.asetaMuoto(Tetrinikat.S);
    }

    /**
     * Asettaa muodon tee muodolle
     */
    public void asetaTeeMuoto() {
        this.asetaMuoto(Tetrinikat.T);
    }

    /**
     * Asettaa muodon l2 muodolle
     */
    public void asetaL2Muoto() {
        this.asetaMuoto(Tetrinikat.L2);
    }

    /**
     * Asettaa muodon l muodolle
     */
    public void asetaLMuoto() {
        this.asetaMuoto(Tetrinikat.L);
    }

    /**
     * Asettaa muodon viiva muodolle
     */
    public void asetaViivaMuoto() {
        this.asetaMuoto(Tetrinikat.Viiva);
    }
}
