package tetris.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Muoto {

    enum Tetrinikat {

        Muodoton, Z, S, Viiva, T, Nelio, L, L2
    };
    private Tetrinikat pala;
    ///koord-taulukossa ensimmäinen int-muuttuja x-koordinaatti, toinen y
    public int koord[][];

    public int[][][] kaikkiKoordinaatit;

    public Muoto() {
        koord = new int[4][2];
        asetaMuoto(Tetrinikat.Muodoton);
    }

    public void asetaMuoto(Tetrinikat muoto) {

        //koordinaatistotaulukko, jossa kaikkien eri muotojen mahdolliset
        //koordinaattiyhdistelmät enumien järjestyksessä
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
    
    public void asetaNelioMuoto() {
        this.asetaMuoto(Tetrinikat.Nelio);
    }
    

//    public Muoto kaanna() {
//        if (pala == Tetrinikat.Nelio) {
//            return this;
//        }
//
//        Muoto uusi = new Muoto();
//        uusi.pala = pala;
//
//        for (int i = 0; i < 4; i++) {
//
//        }
//        
//        return uusi;
//
//    }
//
//    private void asetaX(int index, int x) {
//        koord[index][0] = x;
//    }
//
//    private void asetaY(int index, int y) {
//        koord[index][1] = y;
//    }
// To be continued
//    public int getX(int index) {
//        return koord[index][0];
//    }
//
//    public int getY(int index) {
//        return koord[index][1];
//    }
//


    public void asetaRandomMuoto() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetrinikat[] values = Tetrinikat.values();
        asetaMuoto(values[x]);
    }
    
    @Override
    public String toString() {
        for (int[] koord1 : koord) {
            System.out.print("( ");
            for (int l : koord1) {
                System.out.print(l + " ");
            }
            System.out.print(")");
            System.out.println("");
        }
        return "";
    }
}