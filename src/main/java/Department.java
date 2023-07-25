import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Department extends JFrame{
    private String department,budget; 
    private JPanel contentPane;
    private JTable table;
    private JLabel departLabel, budgetLabel;
    
    public Department(String department, String budget){
        this.department = department;
        this.budget = budget;
    }

    public Department(){
        setBounds(500, 200, 700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JTable table = new JTable();
        table.setBounds(0, 40, 700, 350);
        contentPane.add(table);
        
        String[] column = {"Department","Budget"};
        JButton btnLoad = new JButton("Load Data");
        btnLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<Department> departList = new ArrayList<>();
                try {
                    Conn c = new Conn();
                    String displayDepartSql = "select * from department";
                    ResultSet rs = c.s.executeQuery(displayDepartSql);
                    DefaultTableModel model = new DefaultTableModel(null,column);
                    Department department; 
                    while(rs.next()){
                        department = new Department(
                            rs.getString("department"),
                            rs.getString("budget")
                        );
                        departList.add(department);
                    }
                    Object[]  row  = new Object[2];
                    for(int i=0;i<departList.size();i++){
                        row[0] = departList.get(i).getDepartment();
                        row[1] = departList.get(i).getBudget();
                        model.addRow(row);
                    }
                    table.setModel(model);
                    table.setVisible(true);


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        });
        btnLoad.setBackground(Color.BLACK);
        btnLoad.setForeground(Color.WHITE);
        btnLoad.setBounds(170, 410, 120, 30);
        contentPane.add(btnLoad);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(400, 410, 120, 30);
        contentPane.add(btnBack);


        departLabel = new JLabel("Department");
        departLabel.setBounds(145, 11, 105, 14);
        contentPane.add(departLabel);

        budgetLabel = new JLabel("Budget");
        budgetLabel.setBounds(431,11,75,14);
        contentPane.add(budgetLabel);

        getContentPane().setBackground(Color.WHITE);
    }

    public String getDepartment() {
        return department;
    }

    public String getBudget() {
        return budget;
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try {
                    new Department().setVisible(true);                
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
