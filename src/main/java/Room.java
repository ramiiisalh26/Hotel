import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Room extends JFrame{
    private String roomNo, availability, status, price, type;
    private JPanel contentPane;
    private JTable table;
    private JLabel availabilityLabel, cleanStatusLabel;
    private JLabel priceLabel, bedTypeLabel; 
    private JLabel roomNumberLabel, idLabel;
    

    public Room(String roomNo, String available, String status, String price, String type){
        this.roomNo = roomNo;
        this.availability = available;
        this.status = status;
        this.price = price;
        this.type = type;
    }

    public Room(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(350, 200, 1100, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(500, 0, 600, 600);
        add(l1);

        table = new JTable();
        table.setBounds(0, 40, 500, 400);
        contentPane.add(table);
        
        JButton btnLoadData = new JButton("Load Data");

        btnLoadData.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                ArrayList <Room> roomList = new ArrayList<>();     
                try {
                    Conn c = new Conn();
                    String displayCustomersql = "select * from room";
                    ResultSet rs = c.s.executeQuery(displayCustomersql);
                    Room room; 
                    while(rs.next()){
                        room  = new Room(
                        rs.getString("room_number"),
                        rs.getString("availability"),
                        rs.getString("cleaning_status"),
                        rs.getString("price"),
                        rs.getString("bed_type"));
                        roomList.add(room);
                    }
                    Object[] columnNames  = {"Room No","Availability","Clean Status","Price","Type"};
                    DefaultTableModel model = new DefaultTableModel(null, columnNames);
                    Object[] row = new Object[5];
                    
                    for(int i=0;i<roomList.size();i++){
                        row[0] = roomList.get(i).getRoomNo();
                        row[1] = roomList.get(i).getAvailability();
                        row[2] = roomList.get(i).getStatus();
                        row[3] = roomList.get(i).getPrice();
                        row[4] = roomList.get(i).get_Type();
                        model.addRow(row);
                    }
                    table.setModel(model);
                    table.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        });
        btnLoadData.setBounds(100, 470, 120, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
            
        });
        btnBack.setBounds(290, 470, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        roomNumberLabel = new JLabel("Room Number");
        roomNumberLabel.setBounds(10, 15, 90, 14);
        contentPane.add(roomNumberLabel);

        availabilityLabel = new JLabel("Availability");
        availabilityLabel.setBounds(110, 15, 69, 14);
        contentPane.add(availabilityLabel);

        cleanStatusLabel = new JLabel("Clean Status");
        cleanStatusLabel.setBounds(216, 15, 76, 14);
        contentPane.add(cleanStatusLabel);

        priceLabel = new JLabel("Price");
        priceLabel.setBounds(330, 15, 46, 14);
        contentPane.add(priceLabel);

        bedTypeLabel = new JLabel("Bed Type");
        bedTypeLabel.setBounds(417, 15, 76, 14);
        contentPane.add(bedTypeLabel);

        getContentPane().setBackground(Color.WHITE);


    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String get_Type() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try {
                    Room roomFrame = new Room();
                    roomFrame.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                
            }
        });
    }

}
