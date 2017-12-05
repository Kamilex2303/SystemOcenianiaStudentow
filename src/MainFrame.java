import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    JButton
            logowanieT = new JButton("Logowanie nauczyciel"),
            logowanieStudent = new JButton("Informacje o studencie");


    MainFrame() {
        setTitle("System");
        Container c = getContentPane();
        setSize(300, 200);
        setLayout(null);
        c.add(logowanieT);
        logowanieT.setBounds( 25 , 25 , 250 , 20 );

        c.add(logowanieStudent);
        logowanieStudent.setBounds( 25 , 50 , 250 , 20 );


        logowanieT.addActionListener(new LogowanieT());
        logowanieStudent.addActionListener( new LogowanieStudent());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class LogowanieT implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Logowanie logowanie = new Logowanie();
            logowanie.setVisible(true);

        }
    }

    public class LogowanieStudent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible( false );
            LogowanieS log = new LogowanieS();
            log.setVisible( true );
        }

    }
}
