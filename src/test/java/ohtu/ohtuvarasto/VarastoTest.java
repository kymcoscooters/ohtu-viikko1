package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTilavuusOltavaPositiivinen() {
        Varasto varasto2 = new Varasto(-2);
        Varasto varasto3 = new Varasto(-2, 2);
        
        assertNotEquals(-2, varasto2.getTilavuus(), vertailuTarkkuus);
        assertNotEquals(-2, varasto3.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudelleVarastolleVoiAntaaAlkusaldon() {
        Varasto varasto2 = new Varasto(10, 2);
        
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonEiMahduEnempääKuinTilavuus() {
        varasto.lisaaVarastoon(20);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaEnempääKuinSaldonVerran() {
        varasto.lisaaVarastoon(4);
        
        assertEquals(4, varasto.otaVarastosta(6), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonEiVoiLisätäNegatiivistaMäärää() {
        // Lisätään ensin 2 varastoon
        varasto.lisaaVarastoon(2);
        
        //Yritetään poistaa negatiivisella lisäyksellä
        varasto.lisaaVarastoon(-2);
        
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastostaEiVoiOttaaNegatiivinenMäärä() {
        // Lisätään ensin 2 varastoon
        varasto.lisaaVarastoon(2);
        
        //Yritetään lisätä vielä 2 negatiivisella poistolla
        varasto.otaVarastosta(-2);
        
        assertEquals(2, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldonOltavaPositiivinen() {
        Varasto varasto2 = new Varasto(10, -2);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkusaldonOltavaPienempiKuinTilavuus() {
        Varasto varasto2 = new Varasto(10, 20);
        
        assertEquals(11, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void TekstiTulostusOnOikeanMuotoinen() {
        varasto.lisaaVarastoon(3);
        
        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }
}