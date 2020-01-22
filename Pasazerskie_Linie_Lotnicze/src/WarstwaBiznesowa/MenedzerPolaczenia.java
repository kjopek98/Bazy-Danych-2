package WarstwaBiznesowa;

import WarstwaAplikacji.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MenedzerPolaczenia {

    public int NUMERID;
    public Date DATA;
    public int [] DANE;

    public static void dodajPolaczenie(Date data, int [] dane)
    {
        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";

        try {
            SQLUtilities.Connect(URL);

            String zapytanie = "INSERT INTO Lot (Data_wylotu, Miejsce_wylotu, Miejsce_przylotu) VALUES (?,?,?)";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setDate(1, data);
            s.setInt(2, dane[0]);
            s.setInt(3, dane[1]);
            SQLUtilities.ExecuteNonQuery(s);

            JOptionPane.showMessageDialog(null, "Dane zostaly pomyslnie wprowadzone do bazy danych");
            MenuGlowneAdmin.frameDodajPolaczenie.setVisible(false);
            SQLUtilities.connection.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static void usunPolaczenie(int dane){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";

        try {
            SQLUtilities.Connect(URL);

            String zapytanie = "SELECT LotID FROM Lot WHERE LotID = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setInt(1, dane);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) {
                int odczyt = r.getInt(1);
                if (odczyt == dane) {
                    zapytanie = "DELETE FROM Lot WHERE LotID = ?";
                    s = SQLUtilities.connection.prepareStatement(zapytanie);
                    s.setInt(1, dane);
                    int potwierdzenie = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć rekord?", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
                    if(potwierdzenie == 1){
                        SQLUtilities.connection.close();
                        return;
                    }
                    SQLUtilities.ExecuteNonQuery(s);
                    JOptionPane.showMessageDialog(null, "Usunieto lot o numerze: " + odczyt);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Wprowadzony numer ID nie istnieje!");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Wprowadzony numer ID nie istnieje!");
            }
            SQLUtilities.connection.close();
            MenuGlowneAdmin.frameUsunPolaczenie.setVisible(false);

        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public static String[] uzupelnij(int numerID){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        String [] dane1 = null;
        try {
            SQLUtilities.Connect(URL);
            String zapytanie = "SELECT Data_wylotu, Miejsce_wylotu, Miejsce_przylotu FROM Lot WHERE LotID = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setInt(1, numerID);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if (r.next()) {
                Date data = r.getDate(1);
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dataString = format.format(data);
                String [] dane = {dataString, r.getString(2), r.getString(3)};
                SQLUtilities.connection.close();
                return dane;
            }
            else{
                JOptionPane.showMessageDialog(null, "Blad przy odczycie danych z bazy");
                SQLUtilities.connection.close();
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return dane1;
    }

    public static void modyfikujPolaczenie(Date data, int [] dane){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        try {
            SQLUtilities.Connect(URL);
            String zapytanie = "UPDATE Lot SET Data_wylotu = ? , Miejsce_wylotu = ?, Miejsce_przylotu = ? WHERE LotID = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setDate(1,data);
            s.setInt(2, dane[1]);
            s.setInt(3, dane[2]);
            s.setInt(4, dane[0]);
            SQLUtilities.ExecuteNonQuery(s);

            JOptionPane.showMessageDialog(null, "Dane zostaly pomyslnie wprowadzone do bazy danych");
            SQLUtilities.connection.close();
            MenuGlowneAdmin.frameModyfikujPolaczenie.setVisible(false);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public static String [] podajDane(){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        String [] dane1 = new String[1];
        try {
            int rozmiar = 0;
            SQLUtilities.Connect(URL);
           // String zapytanie = "SELECT LotID, Data_wylotu, Miejsce_wylotu, Miejsce_przylotu FROM Lot";
            String liczba = "SELECT COUNT (*) FROM Lot";
            PreparedStatement l = SQLUtilities.connection.prepareStatement(liczba);
            ResultSet wynik = SQLUtilities.ExecuteQuery(l);
            if(wynik.next()) rozmiar = wynik.getInt(1);
            rozmiar = rozmiar * 4;
            String [] dane = new String[rozmiar];

            String zapytanie = "SELECT * FROM Lot";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            int i = 1;
            Date data;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet temp;
            while(r.next()) {
                dane[i-1] = String.valueOf(r.getInt("LotID"));
                i++;
                data = r.getDate("Data_wylotu");
                dane[i-1] = format.format(data);
                i++;
                String zapytanie1 = "SELECT Nazwa FROM Lotnisko WHERE LotniskoID = ?";
                s = SQLUtilities.connection.prepareStatement(zapytanie1);
                s.setInt(1, r.getInt("Miejsce_wylotu"));
                temp = SQLUtilities.ExecuteQuery(s);
                if(temp.next()) dane[i-1] = temp.getString(1);
               // dane[i-1] = String.valueOf(r.getInt("Miejsce_wylotu"));
                i++;
                zapytanie1 = "SELECT Nazwa FROM Lotnisko WHERE LotniskoID = ?";
                s = SQLUtilities.connection.prepareStatement(zapytanie1);
                s.setInt(1, r.getInt("Miejsce_przylotu"));
                temp = SQLUtilities.ExecuteQuery(s);
                if(temp.next()) dane[i-1] = temp.getString(1);
                //dane[i-1] = String.valueOf(r.getInt("Miejsce_przylotu"));
                i++;
            }
            SQLUtilities.connection.close();
            return dane;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return  dane1;

    }

    public static String [] wyznaczKlucze(String [] lotniska){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        String [] dane1 = new String[1];
        try {
            String [] dane = new String[2];
            SQLUtilities.Connect(URL);
            String zapytanie = "SELECT LotniskoID FROM Lotnisko WHERE Nazwa = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, lotniska[0]);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) dane[0] = r.getString(1);

            zapytanie = "SELECT LotniskoID FROM Lotnisko WHERE Nazwa = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, lotniska[1]);
            r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) dane[1] = r.getString(1);

            SQLUtilities.connection.close();
            return dane;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return dane1;
    }

    public static String [] podajDane(String [] dane){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        String [] dane1 = new String[1];
        try {
            int rozmiar = 0;
            Date data = Date.valueOf(dane[0]);
            SQLUtilities.Connect(URL);
            String liczba = "SELECT COUNT (*) FROM Lot WHERE Data_wylotu = ? AND Miejsce_wylotu = ? AND Miejsce_przylotu = ?";
            PreparedStatement l = SQLUtilities.connection.prepareStatement(liczba);
            l.setDate(1, data);
            l.setString(2,dane[1]);
            l.setString(3, dane[2]);
            ResultSet wynik = SQLUtilities.ExecuteQuery(l);
            if(wynik.next()) rozmiar = wynik.getInt(1);
            rozmiar = rozmiar * 4;
            String [] wyszukane = new String[rozmiar];

            String zapytanie = "SELECT * FROM Lot WHERE Data_wylotu = ? AND Miejsce_wylotu = ? AND Miejsce_przylotu = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setDate(1, data);
            s.setString(2, dane[1]);
            s.setString(3, dane[2]);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            int i = 1;
            Date dataString;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet temp;
            while(r.next()) {
                wyszukane[i-1] = String.valueOf(r.getInt("LotID"));
                i++;
                dataString = r.getDate("Data_wylotu");
                wyszukane[i-1] = format.format(dataString);
                i++;
                String zapytanie1 = "SELECT Nazwa FROM Lotnisko WHERE LotniskoID = ?";
                s = SQLUtilities.connection.prepareStatement(zapytanie1);
                s.setInt(1, r.getInt("Miejsce_wylotu"));
                temp = SQLUtilities.ExecuteQuery(s);
                if(temp.next()) wyszukane[i-1] = temp.getString(1);
                // dane[i-1] = String.valueOf(r.getInt("Miejsce_wylotu"));
                i++;
                zapytanie1 = "SELECT Nazwa FROM Lotnisko WHERE LotniskoID = ?";
                s = SQLUtilities.connection.prepareStatement(zapytanie1);
                s.setInt(1, r.getInt("Miejsce_przylotu"));
                temp = SQLUtilities.ExecuteQuery(s);
                if(temp.next()) wyszukane[i-1] = temp.getString(1);
                //dane[i-1] = String.valueOf(r.getInt("Miejsce_przylotu"));
                i++;
            }
            SQLUtilities.connection.close();
            return wyszukane;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return  dane1;

    }

    public static String [] podajDaneZakupione(){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        String [] dane1 = new String[1];
        try {
            int rozmiar = 0, userID = 0;
            SQLUtilities.Connect(URL);

            String zapytanie1 = "SELECT UserID FROM Konto WHERE Login = ?";
            PreparedStatement zap1 = SQLUtilities.connection.prepareStatement(zapytanie1);
            zap1.setString(1, Aplikacja.login);
            ResultSet r = SQLUtilities.ExecuteQuery(zap1);
            if(r.next()) userID = r.getInt(1);

            String liczba = "SELECT COUNT (*) FROM Bilet WHERE UserID = ? AND Status = ?";
            PreparedStatement l = SQLUtilities.connection.prepareStatement(liczba);
            l.setInt(1, userID);
            l.setString(2, "ZAKUPIONY");
            ResultSet wynik = SQLUtilities.ExecuteQuery(l);
            if(wynik.next()) rozmiar = wynik.getInt(1);
            rozmiar = rozmiar * 6;
            String [] dane = new String[rozmiar];


            String zapytanie = "SELECT Bilet.Numer_lotu, Lot.Data_wylotu, Lot.Miejsce_wylotu, Lot.Miejsce_przylotu, Bilet.Numer_siedzenia, Bilet.BagazID  FROM Lot " +
                    "INNER JOIN Bilet ON Bilet.UserID = ? AND Lot.LotID = Bilet.Numer_lotu AND Bilet.Status = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setInt(1, userID);
            s.setString(2, "ZAKUPIONY");
            r = SQLUtilities.ExecuteQuery(s);
            int i = 1;
            Date data;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ResultSet temp;
            while(r.next()) {
                dane[i-1] = String.valueOf(r.getInt("Numer_lotu"));
                i++;
                data = r.getDate("Data_wylotu");
                dane[i-1] = format.format(data);
                i++;
                String zapytanie3 = "SELECT Nazwa FROM Lotnisko WHERE LotniskoID = ?";
                s = SQLUtilities.connection.prepareStatement(zapytanie3);
                s.setInt(1, r.getInt("Miejsce_wylotu"));
                temp = SQLUtilities.ExecuteQuery(s);
                if(temp.next()) dane[i-1] = temp.getString(1);
                // dane[i-1] = String.valueOf(r.getInt("Miejsce_wylotu"));
                i++;
                zapytanie3 = "SELECT Nazwa FROM Lotnisko WHERE LotniskoID = ?";
                s = SQLUtilities.connection.prepareStatement(zapytanie3);
                s.setInt(1, r.getInt("Miejsce_przylotu"));
                temp = SQLUtilities.ExecuteQuery(s);
                if(temp.next()) dane[i-1] = temp.getString(1);
                //dane[i-1] = String.valueOf(r.getInt("Miejsce_przylotu"));
                i++;
                dane[i-1] = String.valueOf(r.getInt("Numer_siedzenia"));
                i++;
                zapytanie3 = "SELECT Pakiet FROM Bagaz WHERE BagazID = ?";
                s = SQLUtilities.connection.prepareStatement(zapytanie3);
                s.setInt(1, r.getInt("BagazID"));
                temp = SQLUtilities.ExecuteQuery(s);
                if (temp.next()) dane[i-1] = temp.getString(1);
                i++;
            }
            SQLUtilities.connection.close();
            return dane;
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return  dane1;

    }

}
