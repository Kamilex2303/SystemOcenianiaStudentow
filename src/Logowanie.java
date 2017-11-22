import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Logowanie extends JFrame {

    JButton
            zalogujButton = new JButton("Zaloguj"),
            cofnijButton = new JButton("Cofij"),
            dalejButton = new JButton("Dalej");
    JTextField
            loginField = new JTextField(),
            hasloField = new JTextField(),
            operacjaField = new JTextField();

    Logowanie() {
        setTitle("Logowanie");
        Container c = getContentPane();
        setSize(400, 400);
        setLayout(new GridLayout(3, 2));
        c.add(zalogujButton);
        c.add(cofnijButton);
        c.add(loginField);
        c.add(hasloField);
        c.add(operacjaField);
        c.add(dalejButton);

        dalejButton.setEnabled(false);

        zalogujButton.addActionListener(new ZalogujButton());
        cofnijButton.addActionListener(new CofnijButton());
        dalejButton.addActionListener(new DalejButton());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    void check() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:sqlite:main");
        Statement stmt;
        stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM nauczyciele;");
        String log = loginField.getText();
        String has = hasloField.getText();
        int i = 0;
        while (rs.next()) {
            String login = rs.getString("login");
            String haslo = rs.getString("haslo");

            if (log.equals(login) && has.equals(haslo)) {
                operacjaField.setText("Ok");
                dalejButton.setEnabled(true);
                break;
            } else {
                operacjaField.setText("Blad");
                dalejButton.setEnabled(false);
            }


        }

    }

    public class ZalogujButton implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            try {
                check();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class CofnijButton implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }
    }

    public class DalejButton implements ActionListener {


        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            WykazStudentow wykazStudentow = new WykazStudentow();
            wykazStudentow.setVisible(true);
        }
    }


}
