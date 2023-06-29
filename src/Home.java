import javax.swing.*;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {

    JButton add, update, view, remove;

    Home() {
        setLayout(null);

        ImageIcon bgHome = new ImageIcon(ClassLoader.getSystemResource("images/bgHome.jpg"));
        Image bgHomeScaled = bgHome.getImage().getScaledInstance(1456, 819, Image.SCALE_DEFAULT);
        JLabel bgHomeLabel = new JLabel(new ImageIcon(bgHomeScaled));
        bgHomeLabel.setBounds(0, 0, 1456, 819);
        add(bgHomeLabel);

        JLabel heading = new JLabel("Employee Management System");
        heading.setForeground(Color.BLACK);
        heading.setFont(new Font("Arial", Font.BOLD, 50));
        heading.setBounds(270, 50, 1000, 100);
        bgHomeLabel.add(heading);

        Font buttonFont = new Font("New Times Roman", Font.BOLD, 25);
        add = new JButton("Add Employee");
        add.setBackground(Color.PINK);
        add.setForeground(Color.BLACK);
        add.setFont(buttonFont);
        add.setBounds(400, 170, 300, 80);
        add.addActionListener(this);
        bgHomeLabel.add(add);

        view = new JButton("View Employees");
        view.setBackground(Color.YELLOW);
        view.setForeground(Color.BLACK);
        view.setFont(buttonFont);
        view.setBounds(720, 170, 300, 80);
        view.addActionListener(this);
        bgHomeLabel.add(view);

        update = new JButton("Update Employee");
        update.setBackground(Color.blue);
        update.setForeground(Color.WHITE);
        update.setFont(buttonFont);
        update.setBounds(400, 270, 300, 80);
        update.addActionListener(this);
        bgHomeLabel.add(update);

        remove = new JButton("Remove Employee");
        remove.setBackground(Color.RED);
        remove.setForeground(Color.WHITE);
        remove.setFont(buttonFont);
        remove.setBounds(720, 270, 300, 80);
        remove.addActionListener(this);
        bgHomeLabel.add(remove);


        setTitle("Home");
        setSize(1456, 819);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            new AddEmployee();
            setVisible(false);
        } else if (e.getSource() == view) {
            new ViewEmployees();
            setVisible(false);
        } else if (e.getSource() == update) {
            new ViewEmployees();
            setVisible(false);
        } else if (e.getSource() == remove) {
            new RemoveEmployee();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}