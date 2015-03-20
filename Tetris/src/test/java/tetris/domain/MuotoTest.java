/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

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
public class MuotoTest {
    
    public MuotoTest() {
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
    public void nelionKoordinaatitTulostuvatOikein() {
        Muoto muoto = new Muoto();
       
        muoto.asetaNelioMuoto();
        
        String vastaus = muoto.toString();
        
        assertEquals("( 0 0 )\n" +
                    "( 1 0 )\n" +
                    "( 0 1 )\n" +
                    "( 1 1 )", vastaus);
    }
}
