import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {
    JLabel selectEmployeeLabel, nameLabel, phoneLabel, aadhaarLabel, name, phone, aadhaar;
    Choice selectEmployee;
    JButton back, remove;

    RemoveEmployee() {
        setLayout(null);

        Font accent = new Font("Verdana", Font.BOLD, 25);

        selectEmployeeLabel = new JLabel("Select Employee by ID ");
        selectEmployeeLabel.setForeground(Color.BLACK);
        selectEmployeeLabel.setFont(accent);
        selectEmployeeLabel.setBounds(40, 40, 340, 50);
        add(selectEmployeeLabel);

        selectEmployee = new Choice();
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from employees");
            while (rs.next()) {
                selectEmployee.add(rs.getString("id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        selectEmployee.setBounds(390, 40, 220, 50);
        selectEmployee.setFont(accent);
        add(selectEmployee);

        nameLabel = new JLabel("Employee Name : ");
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(accent);
        nameLabel.setBounds(40, 110, 300, 50);
        add(nameLabel);

        name = new JLabel();
        name.setForeground(Color.BLACK);
        name.setFont(accent);
        name.setBounds(360, 110, 300, 50);
        add(name);

        phoneLabel = new JLabel("Employee Phone : ");
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setFont(accent);
        phoneLabel.setBounds(40, 170, 300, 50);
        add(phoneLabel);

        phone = new JLabel();
        phone.setForeground(Color.BLACK);
        phone.setFont(accent);
        phone.setBounds(360, 170, 300, 50);
        add(phone);


        aadhaarLabel = new JLabel("Aadhaar Number : ");
        aadhaarLabel.setForeground(Color.BLACK);
        aadhaarLabel.setFont(accent);
        aadhaarLabel.setBounds(40, 230, 300, 50);
        add(aadhaarLabel);

        aadhaar = new JLabel();
        aadhaar.setForeground(Color.BLACK);
        aadhaar.setFont(accent);
        aadhaar.setBounds(360, 230, 260, 50);
        add(aadhaar);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from employees where id='" + selectEmployee.getSelectedItem() + "'");
            if (rs.next()) {
                name.setText(rs.getString("name"));
                phone.setText(rs.getString("phone"));
                aadhaar.setText(rs.getString("aadhaar"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        selectEmployee.addItemListener(e -> {
            try {
                Conn conn = new Conn();
                ResultSet rs = conn.s.executeQuery("select * from employees where id='" + selectEmployee.getSelectedItem() + "'");
                while (rs.next()) {
                    name.setText(rs.getString("name"));
                    phone.setText(rs.getString("phone"));
                    aadhaar.setText(rs.getString("aadhaar"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        ImageIcon bgDelete = new ImageIcon(ClassLoader.getSystemResource("images/bgDelete.png"));
        Image bgDeleteScaled = bgDelete.getImage().getScaledInstance(550, 750, Image.SCALE_DEFAULT);
        JLabel bgDeleteLabel = new JLabel(new ImageIcon(bgDeleteScaled));
        bgDeleteLabel.setBounds(600, 20, 550, 750);
        add(bgDeleteLabel);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(accent);
        back.setBounds(40, 500, 250, 80);
        back.addActionListener(this);
        add(back);

        remove = new JButton("Remove");
        remove.setBackground(Color.RED);
        remove.setForeground(Color.WHITE);
        remove.setFont(accent);
        remove.setBounds(300, 500, 250, 80);
        remove.addActionListener(this);
        add(remove);

        getContentPane().setBackground(Color.WHITE);
        setTitle("Remove Employee");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == remove) {
            try {
                Conn conn = new Conn();
                conn.s.executeUpdate("delete from employees where id='" + selectEmployee.getSelectedItem() + "'");
                JOptionPane.showMessageDialog(null, "Employee with Id: " + selectEmployee.getSelectedItem() + " has been removed");
                new Home();
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            new Home();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
