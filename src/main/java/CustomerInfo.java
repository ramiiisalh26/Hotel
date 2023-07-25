import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerInfo extends JFrame{
    
    private String document, number, name, gender, country, room, check_in, deposit;
    private JLabel documentLabel, numberLabel, nameLabel, genderLabel, countryLabel, roomLabel, checkInLabel, depositLabel;
    private JPanel contentPane;
    private JTable table;
    

    public CustomerInfo(String document,String number,String name,String gender,String country,String room,String check_in,String deposit){
        this.document = document;
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.room = room;
        this.check_in = check_in;
        this.deposit = deposit;
    }

    public CustomerInfo(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 900, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);

        table = new JTable();
        table.setBounds(0, 40, 900, 450);
        contentPane.add(table);

        JButton btnLoad = new JButton("Load Data");
        btnLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<CustomerInfo> customerList = new ArrayList<>();
                String[] col = {"document","number","name","gender","country","room","check_in_time","deposit"};
                try {
                    Conn c = new Conn();
                    String customerSql = "select * from customer";
                    ResultSet rs = c.s.executeQuery(customerSql);
                    CustomerInfo custInfo;
                    while(rs.next()){
                        custInfo = new CustomerInfo(
                            rs.getString("document"),
                            rs.getString("number"),
                            rs.getString("name"),
                            rs.getString("gender"),
                            rs.getString("country"),
                            rs.getString("room"),
                            rs.getString("check_in_time"),
                            rs.getString("deposit")
                        );
                        customerList.add(custInfo);
                    }
                    DefaultTableModel model = new DefaultTableModel(null,col);
                    Object[] row = new Object[8];
                    for(int i =0 ;i<customerList.size();i++){
                        row[0] = customerList.get(i).getDocument();
                        row[1] = customerList.get(i).getNumber();
                        row[2] = customerList.get(i).getName();
                        row[3] = customerList.get(i).getGender();
                        row[4] = customerList.get(i).getCountry();
                        row[5] = customerList.get(i).getRoom();
                        row[6] = customerList.get(i).getCheck_in();
                        row[7] = customerList.get(i).getDeposit();
                        model.addRow(row);
                    }
                    table.setModel(model);
                    table.setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        });
        btnLoad.setBackground(Color.BLACK);
        btnLoad.setForeground(Color.WHITE);
        btnLoad.setBounds(300, 510, 120, 30);
        contentPane.add(btnLoad);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                new Reception().setVisible(true);
                setVisible(false);
            }
            
        });
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        btnBack.setBounds(450, 510, 120, 30);
        contentPane.add(btnBack);

        documentLabel = new JLabel("ID");
        documentLabel.setBounds(31, 11, 46, 14);
        contentPane.add(documentLabel);

        numberLabel = new JLabel("Number");
        numberLabel.setBounds(150,11,46,14);
        contentPane.add(numberLabel);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(270, 11, 46, 14);
        contentPane.add(nameLabel);

        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(360, 11, 46, 14);
        contentPane.add(genderLabel);

        countryLabel = new JLabel("Country");
        countryLabel.setBounds(480, 11, 46, 14);
        contentPane.add(countryLabel);

        roomLabel = new JLabel("Room");
        roomLabel.setBounds(600, 11, 46, 14);
        contentPane.add(roomLabel);

        checkInLabel = new JLabel("Check-in Status");
        checkInLabel.setBounds(680, 11, 46, 14);
        contentPane.add(checkInLabel);

        depositLabel = new JLabel("Deposit");
        depositLabel.setBounds(800, 11, 46, 14);
        contentPane.add(depositLabel);
        
    }

    

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                try {
                    new CustomerInfo().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

        });
    }

    public String getDocument() {
        return document;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getRoom() {
        return room;
    }

    public String getCheck_in() {
        return check_in;
    }

    public String getDeposit() {
        return deposit;
    }

}
