package WarstwaBiznesowa;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class MenedzerPolaczeniaTest {

    @Test(expected = IllegalArgumentException.class)
    public void dodajPolaczenieTest() {
        Date data = Date.valueOf("2020/04/21");
        int [] dane = new int[2];
        MenedzerPolaczenia.dodajPolaczenie(data, dane);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void dodajPolaczenieTest2() {
        Date data = Date.valueOf("2020-04-21");
        int [] dane = new int[1];
        MenedzerPolaczenia.dodajPolaczenie(data, dane);
    }

    @Test
    public void usunPolaczenieTest() {
    }

    @Test
    public void uzupelnijTest() {
        int numerID = 5;
        Assert.assertNotNull(MenedzerPolaczenia.uzupelnij(numerID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void modyfikujPolaczenieTest() {
        Date data = Date.valueOf("2020/04/21");
        int [] dane = new int[3];
        MenedzerPolaczenia.modyfikujPolaczenie(data, dane);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void modyfikujPolaczenieTest2() {
        Date data = Date.valueOf("2020-04-21");
        int [] dane = new int[1];
        MenedzerPolaczenia.modyfikujPolaczenie(data, dane);
    }

    @Test
    public void podajDaneTest() {
        Assert.assertNotNull(MenedzerPolaczenia.podajDane());
    }
}