package tetris.domain;

import java.util.Random;

public class Muoto {

    
    //luokan pitäisi olla valmis
    
    public enum Tetrinikat {
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

   public int getX(int index) {
        return koord[index][0];
    }

    public int getY(int index) {
        return koord[index][1];
    }
    
    public Tetrinikat getMuoto() {
        return this.pala;
    }

    public void asetaRandomMuoto() {
        Random random = new Random(6);
        int x = random.nextInt();
        Tetrinikat[] values = Tetrinikat.values();
        asetaMuoto(values[x]);
    }
    
    @Override
    public String toString() {
        String koordinaatit = "";
        for (int[] koord1 : koord) {
            koordinaatit += "( ";
            for (int l : koord1) {
                koordinaatit += l + " ";
            }
            koordinaatit +=") \n";
        }
        return koordinaatit;
    }
    
    ///Tästä eteenpäin yksittäiset metodit muotojen asettamiselle
    public void asetaZetaMuoto() {
        this.asetaMuoto(Tetrinikat.Z);
    }
    
    public void asetaNelioMuoto() {
        this.asetaMuoto(Tetrinikat.Nelio);
    }
    
    public void asetaAssaMuoto() {
        this.asetaMuoto(Tetrinikat.S);
    }
    
    public void asetaTeeMuoto() {
        this.asetaMuoto(Tetrinikat.T);
    }
    
    public void asetaL2Muoto() {
        this.asetaMuoto(Tetrinikat.L2);
    }
    
    public void asetaLMuoto() {
        this.asetaMuoto(Tetrinikat.L);
    }
    
    public void asetaViivaMuoto() {
        this.asetaMuoto(Tetrinikat.Viiva);
    }
}