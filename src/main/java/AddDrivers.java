import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddDrivers extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JTextField t1,t2,t3,t4,t5;
    private JComboBox comboBox,comboBox2;
    JButton b1,b2;

    public AddDrivers(){
        setBounds(450, 200, 1000, 500);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(400, 30, 500, 370);
        contentPane.add(l1); 
        
        JLabel l2 = new JLabel("Add Drivers");
        l2.setBounds(194, 10, 120, 22);
        l2.setFont(new Font("Tahoma",Font.BOLD,18));
        contentPane.add(l2);

        JLabel l3 = new JLabel("Name");
        l3.setForeground(new Color(25,25,112));
        l3.setFont(new Font("Tahoma",Font.BOLD,14));
        l3.setBounds(64, 70, 102, 22);
        contentPane.add(l3);

        t1 = new JTextField();
        t1.setBounds(174, 70, 156, 20);
        contentPane.add(t1);

        JLabel l4 = new JLabel("Age");
        l4.setForeground(new Color(25,25,112));
        l4.setFont(new Font("Tahoma",Font.BOLD,14));
        l4.setBounds(64, 110, 102, 22);
        contentPane.add(l4); 

        t2 = new JTextField();
        t2.setBounds(174, 110, 156, 20);
        contentPane.add(t2);

        
        JLabel l5 = new JLabel("Gender");
        l5.setForeground(new Color(25,25,112));
        l5.setFont(new Font("Tahoma",Font.BOLD,14));
        l5.setBounds(64, 150, 102, 22);
        contentPane.add(l5); 

        comboBox = new JComboBox<>(new String[]{"Male","Female"});
        comboBox.setBounds(176, 150, 154, 20);
        contentPane.add(comboBox);

        JLabel l6 = new JLabel("Car Company");
        l6.setForeground(new Color(25,25,112));
        l6.setFont(new Font("Tahoma",Font.BOLD,14));
        l6.setBounds(64, 190, 102, 22);
        contentPane.add(l6);

        t3 = new JTextField();
        t3.setBounds(174, 190, 156, 20);
        contentPane.add(t3);

        JLabel l7 = new JLabel("Car Brand");
        l7.setForeground(new Color(25,25,112));
        l7.setFont(new Font("Tahoma",Font.BOLD,14));
        l7.setBounds(64, 230, 102, 22);
        contentPane.add(l7);

        t4 = new JTextField();
        t4.setBounds(174, 230, 156, 20);
        contentPane.add(t4);

        JLabel l8 = new JLabel("Available");
        l8.setForeground(new Color(25,25,112));
        l8.setFont(new Font("Tahoma",Font.BOLD,14));
        l8.setBounds(64, 270, 102, 22);
        contentPane.add(l8);

        comboBox2 = new JComboBox<>(new String[]{"Yes","No"});
        comboBox2.setBounds(176, 270, 154, 20);
        contentPane.add(comboBox2);

        JLabel l9 = new JLabel("Location");
        l9.setForeground(new Color(25,25,112));
        l9.setFont(new Font("Tahoma",Font.BOLD,14));
        l9.setBounds(64, 310, 102, 22);
        contentPane.add(l9);

        t5 = new JTextField();
        t5.setBounds(174, 310, 156, 20);
        contentPane.add(t5);

        b1 = new JButton("Add");
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLACK);
        b1.addActionListener(this);
        b1.setBounds(64, 380, 111, 33);
        contentPane.add(b1);
        
        b2 = new JButton("Back");
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.BLACK);
        b2.addActionListener(this);
        b2.setBounds(198, 380, 111, 33);
        contentPane.add(b2);

        contentPane.setBackground(Color.WHITE);
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if(ae.getSource() == b1){
            try {
                String name = t1.getText();
                String age = t2.getText();
                String gender = (String) comboBox.getSelectedItem();
                String company = t3.getText();
                String brand = t4.getText();
                String available = (String)comboBox2.getSelectedItem();
                String location = t5.getText();

                String q = "insert into driver values('"+name+"','"+age+"','"+gender+"','"+company+"','"+brand+"','"+available+"','"+location+"')";
                
                Conn c = new Conn();

                c.s.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Driver Successfully Added");
                setVisible(false);
            } catch (Exception ee) {
                System.out.println(ee);
            }    
                
            }else if(ae.getSource() == b2){
                setVisible(false);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        new AddDrivers().setVisible(true);
    }
    
}
