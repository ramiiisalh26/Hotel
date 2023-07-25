// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddEmployee extends JFrame{

    JTextField t1,t2,t3,t4,t5,t6,t7;
    JComboBox c1;

    public AddEmployee(){
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setForeground(Color.BLUE);
        setTitle("ADD EMPLOYEE DETAILS");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 600);
        setLocation(330, 200);
        getContentPane().setLayout(null);

        JLabel jname = new JLabel("NAME");
        jname.setFont(new Font("Tahoma",Font.PLAIN,17));
        jname.setBounds(60, 30, 150, 27);
        add(jname);

        t1 = new JTextField();
        t1.setBounds(200, 30, 150, 27);
        add(t1);

        JButton Next = new JButton("SAVE");
        Next.setBounds(200, 420, 150, 30);
        Next.setBackground(Color.BLACK);
        Next.setForeground(Color.WHITE);
        add(Next);

        JLabel jage = new JLabel("AGE");
        jage.setFont(new Font("Tahoma",Font.PLAIN,17));
        jage.setBounds(60, 80, 150, 27);
        add(jage);

        t2 = new JTextField();
        t2.setBounds(200, 80, 150, 27);
        add(t2);

        JLabel jgender = new JLabel("GENDER");
        jgender.setFont(new Font("Tahoma",Font.PLAIN,17));
        jgender.setBounds(60, 120, 150, 27);
        add(jgender);

        JRadioButton jmale = new JRadioButton("MALE");
        jmale.setBackground(Color.WHITE);
        jmale.setBounds(200, 120, 70, 27);
        add(jmale);

        JRadioButton jfemale = new JRadioButton("FEMALE");
        jfemale.setBackground(Color.WHITE);
        jfemale.setBounds(280, 120, 70, 27);
        add(jfemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(jfemale);
        genderGroup.add(jmale);

        JLabel jJob = new JLabel("JOB");
        jJob.setFont(new Font("Tahoma",Font.PLAIN,17));
        jJob.setBounds(60, 170, 150, 27);
        add(jJob);
        
        String jobType[] = {"Front Desk Clerks","Porters","Housekeeping","Kitchen Staff","Room Service","Waiter/Waitress","Manager","Accountant","Chef"};
        c1 = new JComboBox<>(jobType);
        c1.setBackground(Color.WHITE);
        c1.setBounds(200, 170, 150, 30);
        add(c1);

        JLabel jsalary = new JLabel("SALARY");
        jsalary.setFont(new Font("Tahoma",Font.PLAIN,17));
        jsalary.setBounds(60,220,150,27);
        add(jsalary);
        
        t3 = new JTextField();
        t3.setBounds(200, 220, 150, 27);
        add(t3);

        JLabel jphone = new JLabel("PHONE");
        jphone.setFont(new Font("Tahoma",Font.PLAIN,17));
        jphone.setBounds(60, 270, 150, 27);
        add(jphone);

        t4 = new JTextField();
        t4.setBounds(200, 270, 150, 27);
        add(t4);

        JLabel jsocialNo = new JLabel("SOCIAL NO");
        jsocialNo.setFont(new Font("Tahoma",Font.PLAIN,17));
        jsocialNo.setBounds(60, 320, 150, 27);
        add(jsocialNo);

        t5 = new JTextField();
        t5.setBounds(200, 320, 150, 27);
        add(t5);

        JLabel jemail = new JLabel("EMAIL");
        jemail.setFont(new Font("Tahoma",Font.PLAIN,17));
        jemail.setBounds(60, 370, 150, 27);
        add(jemail);

        t6 = new JTextField();
        t6.setBounds(200, 370, 150, 27);
        add(t6);

        setVisible(true);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(410, 80, 480, 410);
        add(image);

        JLabel jAddPassenger = new JLabel("ADD EMPLOYEE DETAILS");
        jAddPassenger.setFont(new Font("Tahoma",Font.PLAIN,31));
        jAddPassenger.setForeground(Color.BLUE);
        jAddPassenger.setBounds(450, 24, 442, 35);
        add(jAddPassenger);

        Next.addActionListener(new ActionListener() {         
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = t1.getText();
                String age = t2.getText();
                String salary = t3.getText();
                String phone = t4.getText();
                String socialNo = t5.getText();
                String email = t6.getText();

                String gender = null;

                if(jmale.isSelected()){
                    gender = "male";
                }else if(jfemale.isSelected()){
                    gender = "female";
                }
                
                String job = (String)c1.getSelectedItem();

                try {
                    Conn c = new Conn();
                    String q = "insert into employee values('"+name+"','"+age+"','"+gender+"','"+job+"','"+salary+"','"+phone+"','"+email+"','"+socialNo+"')";
                    c.s.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Employee Added");
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
        });
    }

    
    public static void main(String[] args){
        new AddEmployee();
    }
}
