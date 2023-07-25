import java.awt.Choice;
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

public class AddRoom extends JFrame implements ActionListener{
    
    private JPanel contentPane;
    private JTextField t1,t2;
    private JComboBox comboBox, comboBox2, comboBox3;
    JButton b1,b2;

    public AddRoom(){
        setTitle("ADD ROOM");
        setBounds(350, 200, 1000, 450);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500,300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(400, 30, 500, 370);
        contentPane.add(l1);

        JLabel l2 = new JLabel("Add Rooms");
        l2.setFont(new Font("Tahoma", Font.BOLD, 18));
        l2.setBounds(194,10, 120, 22);
        contentPane.add(l2);

        JLabel l3 = new JLabel("Room Number");
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(64,70, 102, 22);
        l3.setForeground(new Color(25,25,112));
        contentPane.add(l3);

        t1 = new JTextField();
        t1.setBounds(174, 70, 156, 20);
        contentPane.add(t1);

        JLabel l4 = new JLabel("Availability");
        l4.setForeground(new Color(25,25,112));
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(64,110, 102, 22);
        contentPane.add(l4);
        
        comboBox = new JComboBox<>(new String[]{"Available","Occupied"});
        comboBox.setBounds(176, 110, 154, 20);
        contentPane.add(comboBox);

        JLabel l5 = new JLabel("Clenning Status");
        l5.setForeground(new Color(25,25,112));
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        l5.setBounds(64,150, 102, 22);
        contentPane.add(l5);

        comboBox2 = new JComboBox<>(new String[]{"Cleaned","Dirty"});
        comboBox2.setBounds(176, 150, 154, 20);
        contentPane.add(comboBox2);

        JLabel l6 = new JLabel("Price");
        l6.setForeground(new Color(25,25,112));
        l6.setFont(new Font("Tahoma", Font.BOLD, 14));
        l6.setBounds(64,190, 102, 22);
        contentPane.add(l6);

        t2 = new JTextField();
        t2.setBounds(174,190,156,20);
        contentPane.add(t2);

        JLabel l7 = new JLabel("Bed Type");
        l7.setForeground(new Color(25,25,112));
        l7.setFont(new Font("Tahoma", Font.BOLD, 14));
        l7.setBounds(64,230, 102, 22);
        contentPane.add(l7);

        comboBox3 = new JComboBox<>(new String[]{"Single bed", "Double Bed"});
        comboBox3.setBounds(176, 230, 154, 20);
        contentPane.add(comboBox3);

        b1 = new JButton("Add");
        b1.addActionListener(this);
        b1.setBounds(64, 321, 111, 33);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        contentPane.add(b1);

        b2 = new JButton("Back");
        b2.addActionListener(this);
        b2.setBounds(198, 321, 111, 33);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        contentPane.add(b2);

        contentPane.setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if(ae.getSource() == b1){
                try {
                    Conn c = new Conn();
                    String room = t1.getText();
                    String available = (String) comboBox.getSelectedItem();
                    String status = (String) comboBox2.getSelectedItem();
                    String price = t2.getText();
                    String type = (String) comboBox3.getSelectedItem();
                    String q = "insert into room values('"+room+"','"+available+"','"+status+"','"+price+"','"+type+"')";
                    c.s.executeUpdate(q);
                    JOptionPane.showMessageDialog(null,"Romm Successfully Added");
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
        new AddRoom().setVisible(true);
    }

}
