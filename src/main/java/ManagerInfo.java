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

public class ManagerInfo extends JFrame{

    private String name, age, gender, job, salary, phone, email, socialNo;
    private JLabel namelLabel, agelLabel, genderlLabel, joblLabel, salarylLabel, phonelLabel, emaillLabel, socialNolLabel;
    private JPanel contentPane;
    private JTable table;

    public ManagerInfo(String name, String age, String gender, String job, String salary, String phone, String email,String socialNo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.job = job;
        this.salary = salary;
        this.phone = phone;
        this.email = email;
        this.socialNo = socialNo;
    }

    public ManagerInfo(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(430, 200, 1000, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        table = new JTable();
        table.setBounds(0, 34, 1000, 450);
        contentPane.add(table);

        JButton btnLoad = new JButton("Load Data");
        btnLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<ManagerInfo> managerList = new ArrayList<>();
                String[] col = {"name","age","gender","job","salary","phone","email","social_no"};
                try {
                    Conn c = new Conn();
                    String managerSql = "select * from employee where job ='manager'";
                    ResultSet rs = c.s.executeQuery(managerSql);
                    ManagerInfo managerInfo;
                    while(rs.next()){
                        managerInfo = new ManagerInfo(
                            rs.getString("name"),
                            rs.getString("age"),
                            rs.getString("gender"),
                            rs.getString("job"),
                            rs.getString("salary"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getString("social_no"));
                        managerList.add(managerInfo);
                    }
                    DefaultTableModel model = new DefaultTableModel(null, col);
                    Object[] row = new Object[8];
                    for(int i=0;i<managerList.size();i++){
                        row[0] = managerList.get(i).getName();
                        row[1] = managerList.get(i).getAge();
                        row[2] = managerList.get(i).getGender();
                        row[3] = managerList.get(i).getJob();
                        row[4] = managerList.get(i).getSalary();
                        row[5] = managerList.get(i).getPhone();
                        row[6] = managerList.get(i).getEmail();
                        row[7] = managerList.get(i).getSocialNo();
                        model.addRow(row);
                    }
                    table.setModel(model);
                    // table.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }            
        });
        btnLoad.setBackground(Color.BLACK);
        btnLoad.setForeground(Color.WHITE);
        btnLoad.setBounds(350, 500, 120, 30);
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
        btnBack.setBounds(510, 500, 120, 30);
        contentPane.add(btnBack);

        namelLabel = new JLabel("Name");
        namelLabel.setBounds(41, 11, 46, 14);
        contentPane.add(namelLabel);

        agelLabel = new JLabel("Age");
        agelLabel.setBounds(159, 11, 46, 14);
        contentPane.add(agelLabel);

        genderlLabel = new JLabel("Gender");
        genderlLabel.setBounds(273, 11, 46, 14);
        contentPane.add(genderlLabel);

        joblLabel = new JLabel("Job");
        joblLabel.setBounds(416, 11, 86, 14);
        contentPane.add(joblLabel);

        salarylLabel = new JLabel("Salary");
        salarylLabel.setBounds(536, 11, 86, 14);
        contentPane.add(salarylLabel);

        phonelLabel = new JLabel("Phone");
        phonelLabel.setBounds(656, 11, 86, 14);
        contentPane.add(phonelLabel);

        emaillLabel = new JLabel("E-mail");
        emaillLabel.setBounds(786, 11, 86, 14);
        contentPane.add(emaillLabel);

        socialNolLabel = new JLabel("Social No");
        socialNolLabel.setBounds(896, 11, 86, 14);
        contentPane.add(socialNolLabel);


    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new ManagerInfo().setVisible(true);                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
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
