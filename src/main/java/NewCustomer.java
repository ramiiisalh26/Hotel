import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class NewCustomer extends JFrame{
    private JPanel contentPane;
    private JTextField t1,t2,t3,t4,t5,t6;
    JComboBox comboBox,comboBox2;
    JRadioButton r1,r2;
    Choice c1;
    
    public NewCustomer(){
        super("New Customer Form");
        setBounds(530, 200, 850, 550);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(480, 10, 300, 500);
        contentPane.add(l1);

        JLabel title = new JLabel("NEW CUSTOMER FORM");
        title.setFont(new Font("Yu Mincho",Font.PLAIN,20));
        title.setBounds(118, 11, 260, 53);
        contentPane.add(title);

        JLabel idLabel = new JLabel("ID : ");
        idLabel.setBounds(35, 76, 200, 14);
        contentPane.add(idLabel);

        comboBox = new JComboBox<>(new String[]{"Passport", "Social Card", "Voter Id", "Driving license"});
        comboBox.setBounds(271, 73, 150, 20);
        contentPane.add(comboBox);

        JLabel l2 = new JLabel("Number :");
        l2.setBounds(35, 111, 150, 20);
        contentPane.add(l2);

        t1 = new JTextField();
        t1.setBounds(271, 111, 150, 20);
        t1.setColumns(10);
        contentPane.add(t1);

        JLabel nameLabel = new JLabel("Name :");
        nameLabel.setBounds(35, 151, 200, 14);
        contentPane.add(nameLabel);

        t2 = new JTextField();
        t2.setBounds(271, 151, 150, 20);
        t2.setColumns(10);
        contentPane.add(t2);

        JLabel genderLabel = new JLabel("Gender :");
        genderLabel.setBounds(35, 191, 200, 14);
        contentPane.add(genderLabel);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Raleway",Font.BOLD,14));
        r1.setBounds(271, 191, 80, 12);
        r1.setBackground(Color.WHITE);
        contentPane.add(r1);
        
        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Raleway",Font.BOLD,14));
        r2.setBounds(350, 191, 100, 12);
        r2.setBackground(Color.WHITE);
        contentPane.add(r2);

        ButtonGroup genderbtn = new ButtonGroup();
        genderbtn.add(r1);
        genderbtn.add(r2);

        JLabel countryLabel = new JLabel("Country :");
        countryLabel.setBounds(35, 231, 200, 14);
        contentPane.add(countryLabel);

        JLabel reserveRoomNumberLabel = new JLabel("Allocated Room Number : ");
        reserveRoomNumberLabel.setBounds(35, 274, 200, 14);
        contentPane.add(reserveRoomNumberLabel);

        c1 = new Choice();
        c1.setBounds(271, 274, 150, 20);
        contentPane.add(c1);
        try {
            Conn c = new Conn();
            // ResultSet rs = c.s.executeQuery("select * from room where availability = 'Available'");
            ResultSet rs = c.s.executeQuery("select * from room where availability = 'Available'");
            String ans;
            while(rs.next()){
                ans = rs.getString("room_number") + " - "+ rs.getString("bed_type");
                c1.add(ans);
            }
            
            //rs == null => for empty rs if didn't return any result
            if(!rs.next() || rs == null){
                c1.add("There are no more rooms");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        
        JLabel checkInStatusLabel = new JLabel("Check-In :");
        checkInStatusLabel.setBounds(35, 316, 200, 14);
        contentPane.add(checkInStatusLabel);

        JLabel depositLabel = new JLabel("Deposit :");
        depositLabel.setBounds(35, 350, 200, 14);
        contentPane.add(depositLabel);

        t3 = new JTextField();
        t3.setBounds(271, 231, 150, 20);
        t3.setColumns(10);
        contentPane.add(t3);

        Date date = new Date();
        
        t4 = new JTextField(date.toString());
        t4.setBounds(271, 316, 150, 20);
        t4.setColumns(10);
        contentPane.add(t4);

        t5 = new JTextField();
        t5.setBounds(271, 359, 150, 20);
        contentPane.add(t5);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Conn c = new Conn();
                String gender = null;

                if(r1.isSelected()){
                    gender = "Male";
                }else if(r2.isSelected()){
                    gender = "Female";
                }

                String roomNo = c1.getSelectedItem().substring(0, 1);

                try {
                    String s1 = (String)comboBox.getSelectedItem();
                    String s2 = t1.getText();
                    String s3 = t2.getText();
                    String s4 = gender;
                    String s5 = t3.getText();
                    String s6 = roomNo;
                    String s7 = t4.getText();
                    String s8 = t5.getText();

                    String q1 = "insert into customer values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')";
                    String q2 = "update room set availability = 'Occupied' where room_number = "+s6;
                    c.s.executeUpdate(q1);
                    c.s.executeUpdate(q2);

                    JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
                    new Reception().setVisible(true);
                    setVisible(false);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            }
            
        });

        btnAdd.setBounds(100, 430, 120, 30);
        btnAdd.setBackground(Color.BLACK);
        btnAdd.setForeground(Color.WHITE);
        contentPane.add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(260, 430, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                new Reception().setVisible(true);
                setVisible(false);
            }
            
        });


        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                try {
                    new NewCustomer().setVisible(true);                    
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
}
