import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
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

public class UpdateRoom extends JFrame{
    
    private JPanel contentPane;
    private JTextField tx_ID, txt_Ava, txt_Status, txt_Room;
    Choice c1;

    public UpdateRoom(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 1000, 450);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i3  = i1.getImage().getScaledInstance(550, 250, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(400, 80, 600, 250);
        contentPane.add(l1);

        JLabel updateStatusLabel = new JLabel("Update Room Status");
        updateStatusLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        updateStatusLabel.setBounds(85, 11, 206, 34);
        contentPane.add(updateStatusLabel);

        JLabel guestIdLabel = new JLabel("Guest ID:");
        guestIdLabel.setBounds(27, 87, 90, 14);
        contentPane.add(guestIdLabel);

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
        c1.setBounds(160, 84, 160, 20);
        contentPane.add(c1);

        JLabel availabilityLabel = new JLabel("Availability:");
        availabilityLabel.setBounds(27, 187, 90, 14);
        contentPane.add(availabilityLabel);

        JLabel cleanStatusLabel = new JLabel("Clean Status");
        cleanStatusLabel.setBounds(27, 240, 90, 14);
        contentPane.add(cleanStatusLabel);

        JLabel RoomIdLabel = new JLabel("Room Number:");
		RoomIdLabel.setBounds(27, 133, 100, 14);
		contentPane.add(RoomIdLabel);

        txt_Room = new JTextField();
        txt_Room.setBounds(160, 130, 160, 20);
        contentPane.add(txt_Room);

        txt_Ava = new JTextField();
        txt_Ava.setBounds(160, 184, 160, 20);
        contentPane.add(txt_Ava);        

        txt_Status = new JTextField();
        txt_Status.setBounds(160, 237, 160, 20);
        contentPane.add(txt_Status);
        JButton btnCheck = new JButton("Check");
        btnCheck.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Conn c = new Conn();
                try {
                    String roomNumSql = "select * from customer where number = "+c1.getSelectedItem();
                    ResultSet rs = c.s.executeQuery(roomNumSql);
                    while(rs.next()){
                        txt_Room.setText(rs.getString("room"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                try {
                    String roomInfoSql = "select * from room where room_number = "+txt_Room.getText();
                    ResultSet rs1 = c.s.executeQuery(roomInfoSql);
                    while(rs1.next()){
                        txt_Ava.setText(rs1.getString("availability"));
                        txt_Status.setText(rs1.getString("cleaning_status"));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        });
        btnCheck.setBounds(120, 315, 89, 23);
        btnCheck.setBackground(Color.BLACK);
        btnCheck.setForeground(Color.WHITE);
        contentPane.add(btnCheck);
    
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    String s1 = txt_Room.getText();
                    String s2 = txt_Status.getText();
                    if(!s1.isEmpty() && !s2.isEmpty()){
                        String sql = "update room set cleaning_status = '"+s2+"' where room_number = "+s1;
                        Conn c = new Conn();
                        c.s.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Update Successfully");
                        new Reception().setVisible(true);
                        setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"Please Check data");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        });
        btnUpdate.setBounds(260, 315, 89,23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try {
                    new UpdateRoom().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
