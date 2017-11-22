import javax.swing.*;
import java.awt.*;

public class WykazStudentow extends JFrame {

        JButton
                cofnijButton = new JButton("Cofnij"),
                dodajButton = new JButton("Dodaj ocene");
        JTextField
                indeksField = new JTextField(),
                ocenaField = new JTextField();
        JLabel
                wykazLable = new JLabel();



        WykazStudentow() {
            setTitle("Wykaz");
            Container c = getContentPane();
            setSize(400, 400);
            setLayout(new GridLayout(3,3));

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
