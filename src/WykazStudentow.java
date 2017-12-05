import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class WykazStudentow extends JFrame {

        JButton
                cofnijButton = new JButton("Cofnij"),
                dodajButton = new JButton("Dodaj ocene");

        JTextField
                indeksField = new JTextField(),
                ocenaField = new JTextField();
        JLabel
                indeksLable = new JLabel(  ),

                ocenaLable = new JLabel(  );
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> wykazList = new JList<>(model);



        WykazStudentow() throws SQLException {
            setTitle("Wykaz");
            Container c = getContentPane();
            setSize(600, 1000);
            setLayout(null);

            c.add(dodajButton);
            c.add(cofnijButton);
            c.add(indeksField);
            c.add(ocenaField);
            c.add(wykazList);
            c.add(indeksLable);
            c.add(ocenaLable);
            ocenaLable.setText( "Ocena : " );
            indeksLable.setText( "Indeks : " );

            indeksLable.setBounds(100 , 100 , 100 , 40);
            indeksField.setBounds(250 , 100 , 100 , 40);
            ocenaLable.setBounds( 100 , 150 , 100 , 20 );
            ocenaField.setBounds( 250 , 150 , 100 , 20  );
            dodajButton.setBounds( 250 , 200 , 100 , 60 );
            cofnijButton.setBounds( 20 , 650 , 80 , 30 );
            wykazList.setBounds( 100 , 300 , 400 , 400 );


            printStudents();

            dodajButton.addActionListener(new DodajButton());
            cofnijButton.addActionListener(new CofnijButton2());



            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

    void printStudents() throws SQLException{
        Connection c = DriverManager.getConnection("jdbc:sqlite:main");
        Statement stmt;
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM uczniowie;");
        model.clear();
        while (rs.next()) {
            String imie = rs.getString("imie");
            String nazwisko = rs.getString("nazwisko");
            int indeks = rs.getInt("indeks");
            double srednia = rs.getDouble("srednia");
            String dane = imie + " " + nazwisko + " , " + indeks + " , "+ srednia + "\n";
            model.addElement(dane);

        }
        c.close();
    }



    void dodajOcene() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:main");
        Statement stmt;
        stmt = c.createStatement();
        int index = Integer.parseInt(indeksField.getText());
        int ocena = Integer.parseInt(ocenaField.getText());
        String addGrade;
        ResultSet rs = stmt.executeQuery("SELECT * FROM uczniowie WHERE indeks = "+index+";");
            double srednia = rs.getDouble( "srednia" );
            if(srednia==0) addGrade = "UPDATE uczniowie set srednia = (srednia +" + ocena + ") where indeks=" + index + ";";
            else addGrade = "UPDATE uczniowie set srednia = (srednia +" + ocena + ")/2 where indeks=" + index + ";";
            stmt.executeUpdate( addGrade );
        printStudents();
        c.close();

    }

    public class DodajButton implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            try {
                dodajOcene();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class CofnijButton2 implements ActionListener {


        public void actionPerformed(ActionEvent e) {
          setVisible(false);
          Logowanie logowanie = new Logowanie();
          logowanie.setVisible(true);
        }
    }



}
