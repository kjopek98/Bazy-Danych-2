package WarstwaBiznesowa;

import WarstwaAplikacji.Aplikacja;
import WarstwaAplikacji.MenuGlowneAdmin;
import WarstwaAplikacji.SQLUtilities;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenedzerBilety {

    public static void dodajBilet(String [] dane){
        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        try {
            SQLUtilities.Connect(URL);
            int bagazID = 0, userID = 0, pasazerID = 0, iloscSiedzen = 0;

            String zapytanie = "SELECT BagazID FROM Bagaz WHERE Pakiet = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1,dane[1]);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) bagazID = r.getInt(1);

            zapytanie = "SELECT UserID FROM Konto WHERE Login = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, Aplikacja.login);
            r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) userID = r.getInt(1);

            zapytanie = "SELECT PasazerID FROM Pasazer WHERE UserID = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setInt(1,userID);
            r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) pasazerID = r.getInt(1);

            zapytanie = "SELECT COUNT(*) FROM Bilet WHERE Numer_Lotu = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setInt(1, Integer.parseInt(dane[0]));
            r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) iloscSiedzen = r.getInt(1) + 1;

            String zapytanie1 = "INSERT INTO Bilet (Numer_siedzenia, Status, BagazID, UserID, Numer_lotu, PasazerID) VALUES (?,?,?,?,?,?)";
            s = SQLUtilities.connection.prepareStatement(zapytanie1);
            s.setInt(1, iloscSiedzen);
            s.setString(2, "ZAKUPIONY");
            s.setInt(3, bagazID);
            s.setInt(4, userID);
            s.setInt(5,Integer.parseInt(dane[0]));
            s.setInt(6,pasazerID);
            SQLUtilities.ExecuteNonQuery(s);
            JOptionPane.showMessageDialog(null, "Bilet zostal zakupiony!");
            SQLUtilities.connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static void anulujBilet(String [] dane){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";

        try {
            SQLUtilities.Connect(URL);
            int bagazID = 0, userID = 0, pasazerID = 0;

            String zapytanie = "SELECT BagazID FROM Bagaz WHERE Pakiet = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1,dane[1]);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) bagazID = r.getInt(1);

            zapytanie = "SELECT UserID FROM Konto WHERE Login = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, Aplikacja.login);
            r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) userID = r.getInt(1);

            zapytanie = "SELECT PasazerID FROM Pasazer WHERE UserID = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setInt(1,userID);
            r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) pasazerID = r.getInt(1);


            String zapytanie1 = "UPDATE Bilet SET Status = 'ANULOWANY' WHERE Numer_Lotu = ? AND UserID = ? AND BagazID = ? AND PasazerID = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie1);
            s.setInt(1, Integer.parseInt(dane[0]));
            s.setInt(2, userID);
            s.setInt(3, bagazID);
            s.setInt(4, pasazerID);
            SQLUtilities.ExecuteNonQuery(s);
            JOptionPane.showMessageDialog(null, "Bilet zostal anulowany!");
            SQLUtilities.connection.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }
}
