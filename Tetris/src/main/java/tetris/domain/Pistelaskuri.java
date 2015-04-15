
package tetris.gui;

public class Pistelaskuri {
    public int pisteet;

    public Pistelaskuri() {
        this.pisteet = 0;
    }
    
    //pisteitä kasvatetaan enemmän, 
    //jos kerrallaan saadaan useampi rivi poistettua
    public void kasvataPisteita(int rivit) {
        if(rivit == 1) {
            pisteet += 10;
        } else if (rivit == 2) {
            pisteet += 25;
        } else if (rivit == 3) {
            pisteet += 45;
        } else if (rivit == 4) {
            pisteet += 80;
        }
        
    }
    public int getPisteet() {
        return this.pisteet;
    }
}
