import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogowanieS extends JFrame {
    JLabel
            info = new JLabel(  ),
            tekstSrednia = new JLabel(),
            tekstIndeks = new JLabel();

    JTextField indeks = new JTextField(  );

    JButton
            sprawdz= new JButton( "Sprawdz" ),
            cofnijButton = new JButton("Cofnij");

    LogowanieS(){
        setTitle("LogowanieS");
        Container c = getContentPane();
        setSize(400, 400);
        setLayout(null);
        c.add( indeks );
        c.add( info );
        c.add( sprawdz );
        c.add(tekstIndeks);
        c.add( tekstSrednia );
        c.add(cofnijButton);
        tekstIndeks.setText( " Indeks : " );
        tekstIndeks.setVisible( true );
        tekstIndeks.setBounds( 50 , 50 , 100 , 20 );
        indeks.setBounds( 125 , 50 , 150 , 20 );
        sprawdz.setBounds( 150 , 125 , 100 , 40  );
        tekstSrednia.setBounds( 150 , 175 , 150 , 20 );
        tekstSrednia.setText( "Informacje : " );
        info.setBounds(  50 , 225 , 300 , 30  );
        cofnijButton.setBounds( 150 , 275 , 100 , 40 );

        sprawdz.addActionListener( new SprawdzButton() );
        cofnijButton.addActionListener( new CofnijButton() );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

    }

    void  printInfo() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:main");
        Statement stmt;
        stmt = c.createStatement();
        int index = Integer.parseInt( indeks.getText() );
        ResultSet rs = stmt.executeQuery("SELECT * FROM uczniowie WHERE indeks = "+index+";");
        while (rs.next()) {
            String imie = rs.getString("imie");
            String nazwisko = rs.getString("nazwisko");
            int indeks = rs.getInt("indeks");
            double srednia = rs.getDouble("srednia");
            String dane = imie+" "+nazwisko+" , "+indeks+" , "+srednia;
            info.setText( dane );
        }

    }

    public class SprawdzButton implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            try {
                printInfo();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class CofnijButton implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setVisible( false );
            MainFrame frame = new MainFrame();
            frame.setVisible( true );
        }
    }


}
