package WarstwaBiznesowa;

import org.junit.Assert;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class MenedzerKontaTest {

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void zalozKontoTest() {
        String [] dane = new String[10];
        MenedzerKonta.zalozKonto(dane);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void zalozKontoTest2() {
        String [] dane = new String[13];
        MenedzerKonta.zalozKonto(dane);
    }

    @org.junit.Test
    public void zalogujTest() {
        String login = "Antoni", haslo = "antoni";
        Assert.assertTrue(MenedzerKonta.zaloguj(login, haslo));
    }

    @org.junit.Test
    public void sprawdzCzyAdminTest() {
        String login = "Krzysztof", haslo = "krzysztof";
        Assert.assertTrue(MenedzerKonta.sprawdzCzyAdmin(login, haslo));
    }

    @org.junit.Test
    public void usunKonto() {

    }

    @org.junit.Test
    public void uzupelnijTest() {
        String login = "Hubert";
        Assert.assertNotNull(MenedzerKonta.uzupelnij(login));
    }

    @org.junit.Test(expected = IndexOutOfBoundsException.class)
    public void modyfikujKontoTest() {
        String [] dane = new String[10];
        MenedzerKonta.modyfikujKonto(dane);
    }
}