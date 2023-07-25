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

public class Employee extends JFrame{
    
    private String name,age,gender,job,salary,phone,email,socialNo;
    private JPanel contentPane;
    private JTable table;
    private JLabel nameLabel, ageLabel,genderLabel,jobLabel,salaryLabel;
    private JLabel phoneLabel,emailLabel,socailNoLabel;

    public Employee(String name,String age,String gender,String job,String salary,String phone,String email,String socialNo){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.job = job;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
        this.socialNo = socialNo;
    }
    public Employee(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(430, 200, 1000, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        table = new JTable();
        table.setBounds(0, 34, 1000, 450);
        contentPane.add(table);

        JButton btnLoad = new JButton("Load Data");
        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<Employee> empList = new ArrayList<>();
                String[] col = {"name","age","gender","job","salary","phone","email","socialNo"};

                try {
                    Conn c = new Conn();
                    String displayEmployeeSql = "select * from employee";
                    ResultSet rs = c.s.executeQuery(displayEmployeeSql);
                    Employee emp;
                    while(rs.next()){
                        emp = new Employee(
                            rs.getString("name"),
                            rs.getString("age"),
                            rs.getString("gender"),
                            rs.getString("job"),
                            rs.getString("salary"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("social_no"));
                        empList.add(emp);    
                    }
                    DefaultTableModel model = new DefaultTableModel(null, col);
                    Object[] row = new Object[8];
                    for(int i=0;i<empList.size();i++){
                        row[0] = empList.get(i).getName();
                        row[1] = empList.get(i).getAge();
                        row[2] = empList.get(i).getGender();
                        row[3] = empList.get(i).getJob();
                        row[4] = empList.get(i).getSalary();
                        row[5] = empList.get(i).getPhone();
                        row[6] = empList.get(i).getEmail();
                        row[7] = empList.get(i).getSocialNo();
                        model.addRow(row);
                    }
                    table.setModel(model);
                    table.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);    
                }
            }
            
        });
        btnLoad.setBackground(Color.BLACK);
        btnLoad.setForeground(Color.WHITE);
        btnLoad.setBounds(350, 500, 120, 30);
        contentPane.add(btnLoad);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnBack.setBounds(510, 500, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(41, 11, 46, 14);
        contentPane.add(nameLabel);

        ageLabel = new JLabel("Age");
        ageLabel.setBounds(159,11,46,14);
        contentPane.add(ageLabel);

        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(273,11,46,14);
        contentPane.add(genderLabel);

        jobLabel = new JLabel("Job");
        jobLabel.setBounds(416,11,86,14);
        contentPane.add(jobLabel);

        salaryLabel = new JLabel("Salary");
        salaryLabel.setBounds(536, 11, 86, 14);
        contentPane.add(salaryLabel);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(656, 11, 86, 14);
        contentPane.add(phoneLabel);

        socailNoLabel = new JLabel("Social No");
        socailNoLabel.setBounds(896, 11, 86, 14);
        contentPane.add(socailNoLabel);

        emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(786, 11, 86, 14);
        contentPane.add(emailLabel);

        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try {
                    new Employee().setVisible(true);
                } catch (Exception e) {
                    System.out.println();
                } 
            }
        });
    }

    public String getName() {
        return name;
    }
    public String getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public String getJob() {
        return job;
    }
    public String getSalary() {
        return salary;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    public String getSocialNo() {
        return socialNo;
    }
}
