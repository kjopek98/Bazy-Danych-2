package WarstwaBiznesowa;

import WarstwaAplikacji.MenuGlowneAdmin;
import WarstwaAplikacji.SQLUtilities;
import mockit.Injectable;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.swing.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class MenedzerPolaczeniaTestMockit {

    @Injectable
    int NUMERID = 5;

    @Injectable
    Date DATA = Date.valueOf("2020-04-21");

    @Injectable
    int [] DANE = {2, 3, 5};

    @Test
    public void uzupelnijTest() {
        Assert.assertNotNull(MenedzerPolaczenia.uzupelnij(NUMERID));
    }

    @Test
    public void modyfikujPolaczenieTest() {
        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        try {
            SQLUtilities.Connect(URL);
            String zapytanie = "UPDATE Lot SET Data_wylotu = ? , Miejsce_wylotu = ?, Miejsce_przylotu = ? WHERE LotID = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setDate(1,DATA);
            s.setInt(2, DANE[1]);
            s.setInt(3, DANE[2]);
            s.setInt(4, DANE[0]);
            SQLUtilities.ExecuteNonQuery(s);

            JOptionPane.showMessageDialog(null, "Dane zostaly pomyslnie wprowadzone do bazy danych");
            SQLUtilities.connection.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}