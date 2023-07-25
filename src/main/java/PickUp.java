import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

public class PickUp extends JFrame{

    private String name, age, gender, company, brand, available, location;
    private JPanel contentPane;
    private JTable table;
    Choice c1;
    public PickUp(String name, String age, String gender, String company, String brand, String available,
            String location) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.company = company;
        this.brand = brand;
        this.available = available;
        this.location = location;
    }

    public PickUp(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 800, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel pickUpServicesLbl = new JLabel("Pick Up Services");
        pickUpServicesLbl.setBounds(90, 11, 158, 25);
        pickUpServicesLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
        contentPane.add(pickUpServicesLbl);

        JLabel typeOfCarLbl = new JLabel("Types of Car");
        typeOfCarLbl.setBounds(32, 97, 89, 14);
        contentPane.add(typeOfCarLbl);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from driver");
            while(rs.next()){
                c1.add(rs.getString("brand"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        c1.setBounds(123, 94, 150, 25);
        contentPane.add(c1);

        JLabel nameLbl = new JLabel("Name");
        nameLbl.setBounds(24, 208, 46, 14);
        contentPane.add(nameLbl);

        JLabel lblNewLabel = new JLabel("Age");
		lblNewLabel.setBounds(165, 208, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(264, 208, 46, 14);
		contentPane.add(lblGender);
		
		JLabel lblTypeOfDriver = new JLabel("Company");
		lblTypeOfDriver.setBounds(366, 208, 80, 14);
		contentPane.add(lblTypeOfDriver);
		
		JLabel lblDateOfThe = new JLabel("Brand");
		lblDateOfThe.setBounds(486, 208, 105, 14);
		contentPane.add(lblDateOfThe);
	
		JLabel lblAirport = new JLabel("Available");
		lblAirport.setBounds(600, 208, 86, 14);
		contentPane.add(lblAirport);
		
		JLabel lblAvailable = new JLabel("Location");
		lblAvailable.setBounds(700, 208, 73, 14);
		contentPane.add(lblAvailable);

        table = new JTable();
        table.setBounds(0, 233, 800, 250);
        contentPane.add(table);

        JButton btnDisplay = new JButton("Display");
        btnDisplay.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<PickUp> pickUpList = new ArrayList<>();
                String[] col = {"name","age","gender","company","brand","available","location"};
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from driver where brand = '"+c1.getSelectedItem()+"'");
                    PickUp infoPickUp;
                    while(rs.next()){
                        infoPickUp = new PickUp(
                            rs.getString("name"),
                            rs.getString("age"),
                            rs.getString("gender"),
                            rs.getString("company"),
                            rs.getString("brand"),
                            rs.getString("available"),
                            rs.getString("location")
                            );
                        pickUpList.add(infoPickUp);
                    }
                    DefaultTableModel model = new DefaultTableModel(null, col);
                    Object[] row = new Object[7];
                    for(int i=0;i<pickUpList.size();i++){
                        row[0] = pickUpList.get(i).getName();
                        row[1] = pickUpList.get(i).getAge();
                        row[2] = pickUpList.get(i).getGender();
                        row[3] = pickUpList.get(i).getCompany();
                        row[4] = pickUpList.get(i).getBrand();
                        row[5] = pickUpList.get(i).getAvailable();
                        row[6] = pickUpList.get(i).get_Location();
                        model.addRow(row);
                    }
                    table.setModel(model);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        btnDisplay.setBounds(200, 500, 120, 30);
        btnDisplay.setBackground(Color.BLACK);
        btnDisplay.setForeground(Color.WHITE);
        contentPane.add(btnDisplay);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnBack.setBounds(420, 500, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try {
                    new PickUp().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getCompany() {
        return company;
    }

    public String getBrand() {
        return brand;
    }

    public String getAvailable() {
        return available;
    }

    public String get_Location() {
        return location;
    }

}
