import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

public class Hotel extends JFrame implements ActionListener{

    JLabel l1;
    JButton b1;

    public Hotel(){

        setSize(1366, 430);
        setLayout(null);
        setLocation(100,100);

        l1 = new JLabel("");
        b1 = new JButton("Next");

        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1366, 390, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l1 = new JLabel(i3);
        l1.setBounds(0, 0, 1366, 390);
        add(l1);

        JLabel lid = new JLabel("HOTEL MANAGEMENT SYSTEM");
        lid.setBounds(30, 300, 1500, 100);
        lid.setFont(new Font("serif",Font.PLAIN,70));
        lid.setForeground(Color.red);
        l1.add(lid);

        b1.setBounds(1170,325,150,50);
        
        l1.add(b1);
        
        b1.addActionListener(this);
        setVisible(true);
        
        while(true){
            lid.setVisible(false);
            try {
                Thread.sleep(500); // 1000 = 1 second
            } catch (Exception e) {}
            
            lid.setVisible(true);

            try {
                Thread.sleep(500);
            } catch (Exception e) {}

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Login().setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new Hotel().setVisible(true);
    }

}
