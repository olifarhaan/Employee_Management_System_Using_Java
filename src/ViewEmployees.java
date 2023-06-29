import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewEmployees extends JFrame implements ActionListener {
    JButton search, print, update, back;
    JTable employeesTable;
    Choice selectFieldById;
    JScrollPane employeesTableScroller;

    ViewEmployees() {
        setLayout(null);

        Font accent = new Font("Verdana", Font.BOLD, 25);
        JLabel searchLabel = new JLabel("Search Employee by ID ");
        searchLabel.setForeground(Color.BLACK);
        searchLabel.setFont(accent);
        searchLabel.setBounds(40, 40, 340, 50);
        add(searchLabel);

        selectFieldById = new Choice();
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from employees");
            while (rs.next()) {
                selectFieldById.add(rs.getString("id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        selectFieldById.setBounds(390, 40, 290, 50);
        selectFieldById.setFont(accent);
        add(selectFieldById);

        search = new JButton("Search");
        search.setBackground(Color.PINK);
        search.setForeground(Color.BLACK);
        search.setFont(accent);
        search.setBounds(40, 100, 250, 50);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.YELLOW);
        print.setForeground(Color.BLACK);
        print.setFont(accent);
        print.setBounds(300, 100, 250, 50);
        print.addActionListener(this);
        add(print);

        update = new JButton("Update");
        update.setBackground(Color.MAGENTA);
        update.setForeground(Color.BLACK);
        update.setFont(accent);
        update.setBounds(560, 100, 250, 50);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(accent);
        back.setBounds(820, 100, 250, 50);
        back.addActionListener(this);
        add(back);

        employeesTable = new JTable();
        employeesTable.setRowHeight(30);
        employeesTable.setDefaultEditor(Object.class, null);

        try {
            Conn conn = new Conn();
            ResultSet employeesSet = conn.s.executeQuery("select * from employees");
            employeesTable.setModel(DbUtils.resultSetToTableModel(employeesSet));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        employeesTable.setFont(new Font("Verdana", Font.PLAIN, 16));

        JTableHeader header = employeesTable.getTableHeader();
        header.setFont(new Font("Verdana", Font.PLAIN, 18));
        header.setBackground(Color.BLACK);
        header.setForeground(Color.WHITE);
        JTableUtilities.setCellsAlignment(employeesTable, SwingConstants.CENTER);
        employeesTableScroller = new JScrollPane(employeesTable);
        employeesTableScroller.setBounds(40, 170, 1120, 550);
        add(employeesTableScroller);


        getContentPane().setBackground(Color.WHITE);
        setTitle("View Employee");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new ViewEmployees();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String id = selectFieldById.getSelectedItem();
            try {
                Conn conn = new Conn();
                ResultSet employeesSet = conn.s.executeQuery("select * from employees where id='" + id + "'");
                employeesTable.setModel(DbUtils.resultSetToTableModel(employeesSet));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                employeesTable.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            new UpdateEmployee(selectFieldById.getSelectedItem());
            setVisible(false);

        } else if(ae.getSource()==back){
            new Home();
            setVisible(false);
        }
    }
}
