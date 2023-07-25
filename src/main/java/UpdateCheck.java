import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateCheck extends JFrame{
    
    private JPanel contentPane;
    private JLabel txt_payment;
    private JTextField txt_Room, txt_Status, txt_paid ,txt_name;
    Choice c1;

    public UpdateCheck(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 950, 500);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        JLabel updateStatusLabel = new JLabel("Check-In Details");
        updateStatusLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updateStatusLabel.setBounds(124, 11, 222, 25);
        contentPane.add(updateStatusLabel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel l1 = new JLabel(i1);
        l1.setBounds(450, 70, 476, 270);
        contentPane.add(l1);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(25, 88, 46, 14);
        contentPane.add(idLabel);

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
        c1.setBounds(248, 85, 140, 20);
        contentPane.add(c1);

        JLabel roomNumberLabel = new JLabel("Room Number :");
        roomNumberLabel.setBounds(25, 129, 107, 14);
        contentPane.add(roomNumberLabel);

        txt_Room = new JTextField();
        txt_Room.setBounds(248, 126, 140, 20);
        contentPane.add(txt_Room);

        JLabel nameLabel = new JLabel("Name :");
        nameLabel.setBounds(25, 174, 97, 14);
        contentPane.add(nameLabel);

        JLabel checkInLabel = new JLabel("Check-in :");
        checkInLabel.setBounds(25, 216, 107, 14);
        contentPane.add(checkInLabel);

        JLabel amountLabel = new JLabel("Amount Paid (Rs) :");
        amountLabel.setBounds(25, 261, 107, 14);
        contentPane.add(amountLabel);

        JLabel pendingLabel = new JLabel("Pending Amount (Rs) :");
        pendingLabel.setBounds(25, 302, 150, 14);
        contentPane.add(pendingLabel);

        txt_name = new JTextField();
        txt_name.setBounds(248, 171, 140, 20);
        contentPane.add(txt_name);

        txt_Status = new JTextField();
        txt_Status.setBounds(248,216,140,20);
        contentPane.add(txt_Status);

        txt_paid = new JTextField();
        txt_paid.setBounds(248, 258, 140, 20);
        contentPane.add(txt_paid);

        txt_payment = new JLabel();
        txt_payment.setBounds(248, 299, 140, 20);
        contentPane.add(txt_payment);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try{ 
                    Conn c = new Conn();
                    String s1 = c1.getSelectedItem();
                    String s2 = txt_Room.getText();
                    String s3 = txt_name.getText();
                    String s4 = txt_Status.getText();
                    String s5 = txt_paid.getText();
                    if(!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty() && !s4.isEmpty() && !s5.isEmpty() ){
                        c.s.executeUpdate("update customer set room = '"+s2+"', name = '"+s3+"',check_in_time = '"+s4+"', deposit = '"+s5+"' where number = '"+s1+"'");
                        JOptionPane.showMessageDialog(null, "Data Update Successfully");
                        new Reception().setVisible(true);
                        setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"Please insert full text field");
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            
        });
        btnUpdate.setBounds(168, 378, 89, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        JButton btnCheck = new JButton("Check");
        btnCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String s1 = c1.getSelectedItem();
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where number = '"+s1+"'");
                    while(rs.next()){
                        txt_Room.setText(rs.getString("room"));
                        txt_name.setText(rs.getString("name"));
                        txt_Status.setText(rs.getString("check_in_time"));
                        txt_paid.setText(rs.getString("deposit"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                try {
                    String total = "";
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from room where room_number = '"+txt_Room.getText()+"'");
                    while(rs.next()){
                        total = rs.getString("price");
                    }
                    String paid = txt_paid.getText();
                    int ans = Integer.parseInt(total) - Integer.parseInt(paid);
                    int pending = (ans) > 0 ? ans : 0 ;
                    
                    txt_payment.setText(Integer.toString(pending));
                } catch (Exception e) {
                    // System.out.println(e.getMessage());
                }
            }
        });
        btnCheck.setBounds(56, 378, 89, 23);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        contentPane.add(btnCheck);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
            
        });
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(Color.BLACK);
        btnBack.setBounds(281, 378, 89, 23);
        contentPane.add(btnBack);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try {
                    new UpdateCheck().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
