import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UpdateEmployee extends JFrame implements ActionListener {
    JButton UpdateEmployee, back;
    JTextField name, fatherName, email, phone, address, aadhaar, salary, designation;
    JDateChooser dob;
    JComboBox<String> qualification;
    JLabel idNumber, nameLabel, fatherLabel, emailLabel, phoneLabel, aadhaarLabel, salaryLabel, designationLabel, dobLabel, qualificationLabel, addressLabel, idLabel;
    String id;

    UpdateEmployee(String id) {
        this.id = id;
        setLayout(null);
        Font general = new Font("Verdana", Font.PLAIN, 20);
        Font accent = new Font("Verdana", Font.BOLD, 30);
        idLabel = new JLabel("Employee ID : ");
        idLabel.setForeground(Color.BLACK);
        idLabel.setFont(accent);
        idLabel.setBounds(400, 40, 260, 50);
        add(idLabel);

        idNumber = new JLabel(id);
        idNumber.setForeground(Color.BLACK);
        idNumber.setFont(accent);
        idNumber.setBounds(680, 40, 200, 50);
        add(idNumber);

        nameLabel = new JLabel("Employee Name");
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(general);
        nameLabel.setBounds(80, 120, 200, 50);
        add(nameLabel);

        name = new JTextField();
        name.setBounds(300, 120, 290, 50);
        name.setFont(general);
        add(name);

        fatherLabel = new JLabel("Father's Name");
        fatherLabel.setForeground(Color.BLACK);
        fatherLabel.setFont(general);
        fatherLabel.setBounds(610, 120, 200, 50);
        add(fatherLabel);

        fatherName = new JTextField();
        fatherName.setBounds(830, 120, 290, 50);
        fatherName.setFont(general);
        add(fatherName);

        dobLabel = new JLabel("Date of Birth");
        dobLabel.setForeground(Color.BLACK);
        dobLabel.setFont(general);
        dobLabel.setBounds(80, 190, 200, 50);
        add(dobLabel);

        dob = new JDateChooser();
        dob.setBounds(300, 190, 290, 50);
        dob.setFont(general);
        add(dob);

        salaryLabel = new JLabel("Salary");
        salaryLabel.setForeground(Color.BLACK);
        salaryLabel.setFont(general);
        salaryLabel.setBounds(610, 190, 200, 50);
        add(salaryLabel);

        salary = new JTextField();
        salary.setBounds(830, 190, 290, 50);
        salary.setFont(general);
        add(salary);

        emailLabel = new JLabel("Email Address");
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(general);
        emailLabel.setBounds(80, 260, 200, 50);
        add(emailLabel);

        email = new JTextField();
        email.setBounds(300, 260, 290, 50);
        email.setFont(general);
        add(email);

        addressLabel = new JLabel("Physical Address");
        addressLabel.setForeground(Color.BLACK);
        addressLabel.setFont(general);
        addressLabel.setBounds(610, 260, 200, 50);
        add(addressLabel);

        address = new JTextField();
        address.setBounds(830, 260, 290, 50);
        address.setFont(general);
        add(address);

        phoneLabel = new JLabel("Phone Number");
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setFont(general);
        phoneLabel.setBounds(80, 330, 200, 50);
        add(phoneLabel);

        phone = new JTextField();
        phone.setBounds(300, 330, 290, 50);
        phone.setFont(general);
        add(phone);

        qualificationLabel = new JLabel("Highest Qualification");
        qualificationLabel.setForeground(Color.BLACK);
        qualificationLabel.setFont(general);
        qualificationLabel.setBounds(610, 330, 200, 50);
        add(qualificationLabel);

        String[] courses = {"MCA", "B.Tech", "MSc", "BSc", "MBA", "Other"};
        qualification = new JComboBox<>(courses);
        qualification.setBounds(830, 330, 290, 50);
        qualification.setFont(general);
        add(qualification);

        designationLabel = new JLabel("Designation");
        designationLabel.setForeground(Color.BLACK);
        designationLabel.setFont(general);
        designationLabel.setBounds(80, 400, 200, 50);
        add(designationLabel);

        designation = new JTextField();
        designation.setBounds(300, 400, 290, 50);
        designation.setFont(general);
        add(designation);

        aadhaarLabel = new JLabel("Aadhaar Number");
        aadhaarLabel.setForeground(Color.BLACK);
        aadhaarLabel.setFont(general);
        aadhaarLabel.setBounds(610, 400, 200, 50);
        add(aadhaarLabel);

        aadhaar = new JTextField();
        aadhaar.setBounds(830, 400, 290, 50);
        aadhaar.setFont(general);
        add(aadhaar);

        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from employees where id='" + id + "'");
            if (rs.next()) {
                idNumber.setText(rs.getString("id"));
                name.setText(rs.getString("name"));
                fatherName.setText(rs.getString("fatherName"));
                salary.setText(rs.getString("salary"));

                java.util.Date dobString= new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("dob"));
                dob.setDate(dobString);
                email.setText(rs.getString("email"));
                address.setText(rs.getString("address"));
                phone.setText(rs.getString("phone"));
                qualification.setSelectedItem(rs.getString("qualification"));
                designation.setText(rs.getString("designation"));
                address.setText(rs.getString("address"));
                aadhaar.setText(rs.getString("aadhaar"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        UpdateEmployee = new JButton("Update Employee");
        UpdateEmployee.setBackground(Color.BLACK);
        UpdateEmployee.setForeground(Color.WHITE);
        UpdateEmployee.setFont(accent);
        UpdateEmployee.setBounds(280, 550, 300, 80);
        UpdateEmployee.addActionListener(this);
        add(UpdateEmployee);

        back = new JButton("Go Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(accent);
        back.setBounds(600, 550, 300, 80);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);
        setTitle("Update Employee");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == UpdateEmployee) {
            String nameString = name.getText();
            String fatherString = fatherName.getText();

            java.sql.Date sqlDate = null;
            if(dob != null) {
                sqlDate = new java.sql.Date(dob.getDate().getTime());
            }
            Date dobString = sqlDate;
            String salaryString = salary.getText();
            String emailString = email.getText();
            String addressString = address.getText();
            String phoneString = phone.getText();
            String qualificationString = (String) qualification.getSelectedItem();
            String designationString = designation.getText();
            String aadhaarString = aadhaar.getText();
            try {
                Conn conn = new Conn();
                String query = "update employees set name='" + nameString + "', fatherName='" + fatherString + "', dob='" + dobString + "',salary='" + salaryString + "',email='" + emailString + "',address='" + addressString + "',phone='" + phoneString + "',qualification='" + qualificationString + "',designation='" + designationString + "',aadhaar='" + aadhaarString + "' where id='" + id + "'";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee " + nameString + " been update successfully");
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
}
