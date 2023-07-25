import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener{

    JLabel l1,l2;
    JTextField t1;
    JPasswordField pt;
    JButton b1,b2;

    Login(){
        super("Login");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1 = new JLabel("Username");
        l1.setBounds(40, 20, 100, 30);
        add(l1);

        l2 = new JLabel("Password");
        l2.setBounds(40, 70, 100, 30);
        add(l2);

        t1 = new JTextField();
        t1.setBounds(150, 20, 150, 30);
        add(t1);

        pt = new JPasswordField();
        pt.setBounds(150, 70, 150,30);
        add(pt);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        setBounds(350, 10, 150, 150);
        add(l3);

        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setFont(new Font("serif",Font.BOLD,15));
        b1.addActionListener(this);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.setFont(new Font("serif",Font.BOLD,15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        setSize(400, 300);
        setLocation(100, 350);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == b1){
            try {
                Conn c1 = new Conn();
                String username = t1.getText();
                String pass = pt.getText();

                // String q = "select * from login where username = '"+username+"' and password = '"+pass+"'";
                String q = "select * from login where username='"+username+"' and password='"+pass+"'";
                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()){
                    new Dashboard().setVisible(true);
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null,"Invaild login");
                    setVisible(false);
                    new Login().setVisible(true);
                }
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println(e);
            }
        }else if(ae.getSource() == b2){
            System.exit(0);
        }
    }   
    
    public static void main(String[] args) {
        new Login();
    }
}
