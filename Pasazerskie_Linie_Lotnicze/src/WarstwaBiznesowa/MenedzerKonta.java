package WarstwaBiznesowa;

import WarstwaAplikacji.Aplikacja;
import WarstwaAplikacji.SQLUtilities;
import WarstwaAplikacji.Start;

import javax.swing.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MenedzerKonta {

    public String LOGIN;
    public String HASLO;

    public static void zalozKonto(String [] dane){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Klient;password=klient;";
        int kontoID = 0, adresID = 0;

        try {
            SQLUtilities.Connect(URL);

            String zapytanie = "INSERT INTO Konto (Login, Password, Admin) VALUES (?,?,?)";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, dane[11]);
            s.setString(2, dane[12]);
            s.setBoolean(3, false);
            SQLUtilities.ExecuteNonQuery(s);

            zapytanie = "INSERT INTO Adres (Kraj, Wojewodztwo, Powiat, Miejscowosc, Ulica, Numer_domu, Numer_lokalu) VALUES (?,?,?,?,?,?,?)";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, dane[4]);
            s.setString(2, dane[5]);
            s.setString(3, dane[6]);
            s.setString(4, dane[7]);
            s.setString(5, dane[8]);
            s.setString(6, dane[9]);
            s.setString(7, dane[10]);
            SQLUtilities.ExecuteNonQuery(s);

            zapytanie = "SELECT MAX(UserID) FROM Konto";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if (r.next()) {
                kontoID = Integer.parseInt(r.getString(1));
                //System.out.println(kontoID);
            }
            zapytanie = "SELECT MAX(AdresID) FROM Adres";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            r = SQLUtilities.ExecuteQuery(s);
            if (r.next()) {
                adresID = Integer.parseInt(r.getString(1));
               // System.out.println(adresID);
            }

            zapytanie = "INSERT INTO Pasazer (Imie, Nazwisko, Email, Telefon_osobisty, AdresID, UserID) VALUES (?,?,?,?,?,?)";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, dane[0]);
            s.setString(2, dane[1]);
            s.setString(3, dane[2]);
            s.setString(4, dane[3]);
            s.setInt(5, adresID);
            s.setInt(6, kontoID);
            SQLUtilities.ExecuteNonQuery(s);

            JOptionPane.showMessageDialog(null, "Dane zostaly pomyslnie wprowadzone do bazy danych");
            Start.framezalozKonto.setVisible(false);
            SQLUtilities.connection.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static boolean zaloguj(String login, String haslo){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Klient;password=klient;";
        try {
            SQLUtilities.Connect(URL);
            String zapytanie = "SELECT login, password FROM Konto WHERE login = ? AND password = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, login);
            s.setString(2, haslo);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if(r.next()){
                String odczyt1 = r.getString(1);
                String odczyt2 = r.getString(2);
                if(odczyt1.equals(login) && odczyt2.equals(haslo)) return true;
            }
            SQLUtilities.connection.close();
            return false;
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }

    public static boolean sprawdzCzyAdmin(String login, String haslo){

        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Klient;password=klient;";
        try {
            SQLUtilities.Connect(URL);
            String zapytanie = "SELECT admin FROM Konto WHERE login = ? AND password = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, login);
            s.setString(2, haslo);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) {
                String odczyt = r.getString(1);
                if (odczyt.equals("1")) return true;
            }
            SQLUtilities.connection.close();
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return false;
    }

    public static void usunKonto(int ID){
        String URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        try {
            SQLUtilities.Connect(URL);
            String zapytanie = "SELECT UserID, login FROM Konto WHERE UserID = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setInt(1,ID);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if(r.next()) {
                int odczyt1 = r.getInt(1);
                String odczyt2 = r.getString(2);
                if (odczyt1 == ID) {
                    zapytanie = "DELETE FROM Konto WHERE UserID = ?";
                    s = SQLUtilities.connection.prepareStatement(zapytanie);
                    s.setInt(1, ID);
                    int potwierdzenie = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć rekord?", "Potwierdzenie", JOptionPane.YES_NO_OPTION);
                    if(potwierdzenie == 1){
                        SQLUtilities.connection.close();
                        return;
                    }
                    SQLUtilities.ExecuteNonQuery(s);
                    JOptionPane.showMessageDialog(null, "Usunieto konto o loginie:" + odczyt2);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Wprowadzony numer ID nie istnieje!");
                }
            }
            else {
                    JOptionPane.showMessageDialog(null, "Wprowadzony numer ID nie istnieje!");
                }
            SQLUtilities.connection.close();
            }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static  String[] uzupelnij(String login){

        String URL = "";
        int kontoID = 0, adresID = 0;
        if(Aplikacja.admin == true){
            URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        }
        else {
            URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Klient;password=klient;";
        }
        String [] dane = new String[12];
        for(int  i= 0; i < 12; i++){
            dane[i] = null;
        }
        try {
            SQLUtilities.Connect(URL);
            String zapytanie = "SELECT Password, UserID FROM Konto WHERE login = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, login);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if (r.next()) {
                dane[11] = r.getString(1);
                kontoID = r.getInt(2);
            }
            else{
                JOptionPane.showMessageDialog(null, "Blad przy odczycie danych z bazy - login może nie istnieć w bazie!");
                SQLUtilities.connection.close();
                return dane;
            }
            
            zapytanie = "SELECT Imie, Nazwisko, Email, Telefon_Osobisty, AdresID FROM Pasazer WHERE UserID = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setInt(1,kontoID);
            r = SQLUtilities.ExecuteQuery(s);
            if(r.next()){
                dane[0] = r.getString(1);
                dane[1] = r.getString(2);
                dane[2] = r.getString(3);
                dane[3] = r.getString(4);
                adresID = r.getInt(5);
            }

            zapytanie = "SELECT Kraj, Wojewodztwo, Powiat, Miejscowosc, Ulica, Numer_domu, Numer_lokalu FROM Adres WHERE AdresID = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setInt(1,adresID);
            r = SQLUtilities.ExecuteQuery(s);
            if(r.next()){
                dane[4] = r.getString(1);
                dane[5] = r.getString(2);
                dane[6] = r.getString(3);
                dane[7] = r.getString(4);
                dane[8] = r.getString(5);
                dane[9] = String.valueOf(r.getInt(6));
                dane[10] = String.valueOf(r.getInt(7));
            }
            return dane;

        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        return dane;

    }

    public static void modyfikujKonto(String [] dane){

        String URL = "";
        if(Aplikacja.admin == true) {
             URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Admin;password=admin;";
        }
        else{
            URL = "jdbc:sqlserver://localhost;databaseName=FlightStore;user=Klient;password=klient;";
        }
        try {
            int kontoID = 0, adresID = 0;
            SQLUtilities.Connect(URL);

            String zapytanie = "UPDATE  Konto Set Password = ? WHERE login = ?";
            PreparedStatement s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, dane[12]);
            s.setString(2, dane[11]);
            SQLUtilities.ExecuteNonQuery(s);


            zapytanie = "SELECT UserID FROM Konto WHERE login = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, dane[11]);
            ResultSet r = SQLUtilities.ExecuteQuery(s);
            if (r.next()) {
                kontoID = Integer.parseInt(r.getString(1));
                //System.out.println(kontoID);
            }

            zapytanie = "UPDATE Pasazer SET Imie = ? , Nazwisko = ?, Email = ?, Telefon_osobisty = ? WHERE UserID = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, dane[0]);
            s.setString(2, dane[1]);
            s.setString(3, dane[2]);
            s.setString(4, dane[3]);
            s.setInt(5, kontoID);
            SQLUtilities.ExecuteNonQuery(s);

            zapytanie = "SELECT AdresID FROM Pasazer WHERE UserID = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setInt(1, kontoID);
            r = SQLUtilities.ExecuteQuery(s);
            if (r.next()) {
                adresID = Integer.parseInt(r.getString(1));
                //System.out.println(kontoID);
            }

            zapytanie = "UPDATE Adres SET Kraj = ?, Wojewodztwo = ?, Powiat =?, Miejscowosc = ?, Ulica = ?, Numer_domu = ?, Numer_lokalu = ? WHERE AdresID = ?";
            s = SQLUtilities.connection.prepareStatement(zapytanie);
            s.setString(1, dane[4]);
            s.setString(2, dane[5]);
            s.setString(3, dane[6]);
            s.setString(4, dane[7]);
            s.setString(5, dane[8]);
            s.setString(6, dane[9]);
            s.setString(7, dane[10]);
            s.setInt(8, adresID);
            SQLUtilities.ExecuteNonQuery(s);
            JOptionPane.showMessageDialog(null, "Dane zostaly zmodyfikowane!");
            SQLUtilities.connection.close();

        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }

    }

}
