import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Dashboard extends JFrame{

    public Dashboard(){

        super("HOTEL MANAGEMENT SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1950, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel newLabel = new JLabel(i3);
        add(newLabel);

        JLabel AirLineManagementSystem = new JLabel("THE TAJ GROUP WELCOMES YOU");
        AirLineManagementSystem.setForeground(Color.WHITE);
        AirLineManagementSystem.setFont(new Font("Tahoma",Font.PLAIN,46));
        AirLineManagementSystem.setBounds(600, 60, 1000, 85);
        newLabel.add(AirLineManagementSystem);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        //JMenu to create Menu
        JMenu AirlineSystem = new JMenu("HOTEL MANAGEMENT");
        AirlineSystem.setForeground(Color.BLUE);
        menuBar.add(AirlineSystem);
        //JMenu to create Menu Items
        JMenuItem flightDetails = new JMenuItem("RECEPTION");
        AirlineSystem.add(flightDetails);
        
        JMenu AirlineSystemAdmin = new JMenu("ADMIN");
        AirlineSystemAdmin.setForeground(Color.RED);
        menuBar.add(AirlineSystemAdmin);
        
        JMenuItem flightDetailsEmp = new JMenuItem("ADD EMPLOYEE");
        AirlineSystemAdmin.add(flightDetailsEmp);

        flightDetailsEmp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddEmployee().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
                
            }
        });

        JMenuItem flightDetailsRooms = new JMenuItem("ADD ROOMS");
        AirlineSystemAdmin.add(flightDetailsRooms);

        flightDetailsRooms.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddRoom().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        flightDetails.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new Reception().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
        });

        JMenuItem flightDetailsHello3 = new JMenuItem("ADD DRIVERS");
        AirlineSystemAdmin.add(flightDetailsHello3);

        flightDetailsHello3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new AddDrivers().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
        });

        setSize(1950,1090);
	    setVisible(true);
        getContentPane().setBackground(Color.WHITE);

    }

    public static void main(String[] args){
        new Dashboard().setVisible(true);
    }
}
