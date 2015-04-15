/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import tetris.domain.Pistelaskuri;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author totutotu
 */
public class PistelaskuriTest {
    private Pistelaskuri laskuri = new Pistelaskuri();
    
    public PistelaskuriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void yksiRiviKasvattaaPisteitaKymmenen() {
        laskuri.kasvataPisteita(1); 
        assertEquals(laskuri.getPisteet(), 10);
    }
    
    @Test
    public void kasiRiviaKasvattaaPisteitaOikein() {
        laskuri.kasvataPisteita(2); 
        assertEquals(laskuri.getPisteet(), 25);
    }
    
    @Test
    public void kolmeRiviaKasvattaaPisteitaOikein() {
        laskuri.kasvataPisteita(3); 
        assertEquals(laskuri.getPisteet(), 45);
        
    }
    
    @Test
    public void neljaRiviaKasvattaaPisteitaOikein() {
        laskuri.kasvataPisteita(4); 
        assertEquals(laskuri.getPisteet(), 80);
    }
    
    @Test
    public void useaRiviKasvattaaPisteitaOikein1() {
        laskuri.kasvataPisteita(1);
        laskuri.kasvataPisteita(2);
        laskuri.kasvataPisteita(2);
        laskuri.kasvataPisteita(1);
        
        assertEquals(laskuri.getPisteet(), 70);
        
    }
    
    @Test
    public void useaRiviKasvattaaPisteitaOikein2() {
        laskuri.kasvataPisteita(1); 
        laskuri.kasvataPisteita(3);
        laskuri.kasvataPisteita(4);
        laskuri.kasvataPisteita(2);
        
        assertEquals(laskuri.getPisteet(), 160);
        
    }
    
    @Test
    public void useaRiviKasvattaaPisteitaOikein3() {
        laskuri.kasvataPisteita(1); 
        laskuri.kasvataPisteita(4);
        laskuri.kasvataPisteita(4);
        laskuri.kasvataPisteita(4);
        laskuri.kasvataPisteita(4);
        
        assertEquals(laskuri.getPisteet(), 330);
        
    }
}
