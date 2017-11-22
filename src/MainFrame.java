import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    JButton
            logowanie = new JButton("Logowanie"),
            wyjscie = new JButton("Wyjscie");


    MainFrame() {
        setTitle("System");
        Container c = getContentPane();
        setSize(300, 200);
        setLayout(new GridLayout(1,2));
        c.add(logowanie);
        c.add(wyjscie);

        logowanie.addActionListener(new LogowanieB());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class LogowanieB implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Logowanie logowanie = new Logowanie();
            logowanie.setVisible(true);

        }
    }
}
