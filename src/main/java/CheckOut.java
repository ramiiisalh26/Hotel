import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckOut extends JFrame{
    private JPanel contentPane;
    private JLabel titelLabel, roomLabel, customerIdLabel,checkInLabel, checkOutLabel,txt_checkIn,txt_check_out;
    private JTextField txt_room;
    Choice c1;

    public CheckOut(){
        setBounds(530, 200, 800, 394);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 325, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(300, 0, 500, 325);
        contentPane.add(l1);

        titelLabel = new JLabel("Check Out ");
        titelLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        titelLabel.setBounds(125, 11, 140, 35);
        contentPane.add(titelLabel);

        customerIdLabel = new JLabel("Customer ID :");
        customerIdLabel.setBounds(20, 85, 120, 14);
        contentPane.add(customerIdLabel);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                c1.add(rs.getString("number"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        c1.setBounds(150, 82, 150, 20);
        contentPane.add(c1);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i5 = i4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JButton l2 = new JButton(i6);
        l2.setBounds(310, 82, 20, 20);
        contentPane.add(l2);

        l2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Date date = new Date();
                try {
                    Conn c = new Conn();
                    String number = c1.getSelectedItem();
                    ResultSet rs = c.s.executeQuery("select * from customer where number = "+number);
                    while(rs.next()){
                        txt_room.setText(rs.getString("room"));
                        txt_checkIn.setText(rs.getString("check_in_time"));
                    }
                    txt_check_out.setText(date.toString());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        
        roomLabel = new JLabel("Room Number :");
        roomLabel.setBounds(20, 132, 120, 14);
        contentPane.add(roomLabel);

        txt_room = new JTextField();
        txt_room.setBounds(150, 132, 150, 20);
        contentPane.add(txt_room);

        checkInLabel = new JLabel("Checkin Time :");
        checkInLabel.setBounds(20, 179, 120, 14);
        contentPane.add(checkInLabel);

        txt_checkIn = new JLabel();
        txt_checkIn.setBounds(150, 175, 170, 20);
        contentPane.add(txt_checkIn);

        checkOutLabel = new JLabel("Check Out :");
        checkOutLabel.setBounds(20,225 ,120 , 14);
        contentPane.add(checkOutLabel);

        txt_check_out = new JLabel();
        txt_check_out.setBounds(150, 225, 170, 14);
        contentPane.add(txt_check_out);

        JButton btnCheckOut = new JButton("Check Out");
        btnCheckOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String id = c1.getSelectedItem();
                    String s2 = txt_room.getText();
                    if(!id.isEmpty() && !s2.isEmpty()){    
                        String deleteSql = "delete from customer where number = "+id;
                        String updateSql = "update room set availability = 'Available' where room_number = "+s2;
                        Conn c = new Conn();
                        c.s.executeUpdate(deleteSql);
                        c.s.executeUpdate(updateSql);
                        JOptionPane.showMessageDialog(null,"Check Out Successfully");
                        new Reception().setVisible(true);
                        setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"Please confirm Check Out");

                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        });
        btnCheckOut.setBackground(Color.BLACK);
        btnCheckOut.setForeground(Color.WHITE);
        btnCheckOut.setBounds(50, 300, 100, 25);
        contentPane.add(btnCheckOut);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new Reception().setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        });
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(200, 300, 100, 25);
        contentPane.add(btnBack);
    }


    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    new CheckOut().setVisible(true);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            
        });
    }
}
