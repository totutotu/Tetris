/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tetris.domain.Muoto.Tetrinikat;

/**
 *
 * @author totutotu
 */
public class MuotoTest {

    private Muoto muoto = new Muoto();

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
        muoto.asetaNelioMuoto();

        String vastaus = muoto.toString();

        assertEquals("( 0 0 ) \n"
                + "( 1 0 ) \n"
                + "( 0 1 ) \n"
                + "( 1 1 ) \n", vastaus);
    }

    @Test
    public void uusiMuotoOnTyhjaMuoto() {
        String vastaus = muoto.toString();

        assertEquals("( 0 0 ) \n"
                + "( 0 0 ) \n"
                + "( 0 0 ) \n"
                + "( 0 0 ) \n", vastaus);
    }

    @Test
    public void zetanKoordinaatitTulostuvatOikein() {
        muoto.asetaZetaMuoto();

        String vastaus = muoto.toString();

        assertEquals("( 0 -1 ) \n"
                + "( 0 0 ) \n"
                + "( -1 0 ) \n"
                + "( -1 1 ) \n", vastaus);
    }

    @Test
    public void assanKoordinaatitTulostuvatOikein() {
        muoto.asetaAssaMuoto();

        String vastaus = muoto.toString();

        assertEquals("( 0 -1 ) \n"
                + "( 0 0 ) \n"
                + "( 1 0 ) \n"
                + "( 1 1 ) \n", vastaus);
    }

    @Test
    public void allanKoordinaatitTulostuvatOikein() {
        muoto.asetaLMuoto();

        String vastaus = muoto.toString();

        assertEquals("( -1 -1 ) \n"
                + "( 0 -1 ) \n"
                + "( 0 0 ) \n"
                + "( 0 1 ) \n", vastaus);
    }

    @Test
    public void L2nKoordinaatitTulostuvatOikein() {
        muoto.asetaL2Muoto();

        String vastaus = muoto.toString();

        assertEquals("( 1 -1 ) \n"
                + "( 0 -1 ) \n"
                + "( 0 0 ) \n"
                + "( 0 1 ) \n", vastaus);
    }

    @Test
    public void viivanKoordinaatitTulostuvatOikein() {
        muoto.asetaViivaMuoto();

        String vastaus = muoto.toString();

        assertEquals("( 0 -1 ) \n"
                + "( 0 0 ) \n"
                + "( 0 1 ) \n"
                + "( 0 2 ) \n", vastaus);
    }

    @Test
    public void teenKoordinaatitTulostuvatOikein() {
        muoto.asetaTeeMuoto();

        String vastaus = muoto.toString();

        assertEquals("( -1 0 ) \n"
                + "( 0 0 ) \n"
                + "( 1 0 ) \n"
                + "( 0 1 ) \n", vastaus);
    }

    @Test
    public void nelionKaantoEiTeeMitaan() {
        muoto.asetaNelioMuoto();
        String originaali = muoto.toString();
        muoto.kaanna();
        assertEquals(originaali, muoto.toString());
    }

    @Test
    public void zetanKaantoToimiiOikein() {
        muoto.asetaZetaMuoto();
        muoto = muoto.kaanna();
        assertEquals("( -1 0 ) \n"
                + "( 0 0 ) \n"
                + "( 0 1 ) \n"
                + "( 1 1 ) \n", muoto.toString());
    }

    @Test
    public void assanKaantoToimiiOikein() {
        muoto.asetaAssaMuoto();
        muoto = muoto.kaanna();
        assertEquals("( -1 0 ) \n"
                + "( 0 0 ) \n"
                + "( 0 -1 ) \n"
                + "( 1 -1 ) \n"
                + "", muoto.toString());
    }

    @Test
    public void allanKaantoToimiiOikein() {
        muoto.asetaLMuoto();
        muoto = muoto.kaanna();
        assertEquals("( -1 1 ) \n"
                + "( -1 0 ) \n"
                + "( 0 0 ) \n"
                + "( 1 0 ) \n"
                + "", muoto.toString());
    }

    @Test
    public void teenKaantoToimiiOikein() {
        muoto.asetaTeeMuoto();
        muoto = muoto.kaanna();
        assertEquals("( 0 1 ) \n"
                + "( 0 0 ) \n"
                + "( 0 -1 ) \n"
                + "( 1 0 ) \n"
                + "", muoto.toString());
    }

}
