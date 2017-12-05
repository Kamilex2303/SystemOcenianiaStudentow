import java.sql.*;
import java.awt.*;
import java.*;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;

public class Main {

    Connection c = null;



    static void printTeachers() throws SQLException{
        Connection c = DriverManager.getConnection("jdbc:sqlite:main");
        c.setAutoCommit(true);
        Statement stmt;
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM nauczyciele;");
        while (rs.next()) {
            String imie = rs.getString("imie");
            String nazwisko = rs.getString("nazwisko");
            String login = rs.getString("login");
            String haslo = rs.getString("haslo");
            System.out.println(imie + " " + nazwisko);
        }
        c.close();

    }



    public static void main(String[] args) throws SQLException {
        JFrame frame = new MainFrame();

    }
}
