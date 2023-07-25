import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Reception extends JFrame{

    JPanel contentPane;

    public Reception(){
        super("Reception");
        setBounds( 530, 200, 850, 570);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(250, 30, 500, 470);
        add(l1);
        
        JButton btnCustomerForm = new JButton("New Customer Form");
        btnCustomerForm.setBounds(10, 30, 200, 30);
        btnCustomerForm.setForeground(Color.WHITE);
        btnCustomerForm.setBackground(Color.BLACK);
        contentPane.add(btnCustomerForm);
        btnCustomerForm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    NewCustomer customer = new NewCustomer();
                    customer.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
        });

        JButton btnRoom = new JButton("Rooms");
        btnRoom.setBounds(10, 70, 200, 30);
        btnRoom.setForeground(Color.WHITE);
        btnRoom.setBackground(Color.BLACK);
        contentPane.add(btnRoom);
        btnRoom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Room room = new Room();
                    room.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
        });

        JButton btnDepart = new JButton("Department");
        btnDepart.setBounds(10, 110, 200, 30);
        btnDepart.setForeground(Color.WHITE);
        btnDepart.setBackground(Color.BLACK);
        contentPane.add(btnDepart);
        btnDepart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Department department = new Department();
                    department.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        JButton btnEmployeeInfo = new JButton("All Employee Info");
        btnEmployeeInfo.setBounds(10, 150, 200, 30);
        btnEmployeeInfo.setForeground(Color.WHITE);
        btnEmployeeInfo.setBackground(Color.BLACK);
        contentPane.add(btnEmployeeInfo);
        btnEmployeeInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Employee employee = new Employee();
                    employee.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        JButton btnCustomerInfo = new JButton("Customer Info");
        btnCustomerInfo.setBounds(10, 190, 200, 30);
        btnCustomerInfo.setForeground(Color.WHITE);
        btnCustomerInfo.setBackground(Color.BLACK);
        contentPane.add(btnCustomerInfo);
        btnCustomerInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    CustomerInfo  customerInfo  = new CustomerInfo ();
                    customerInfo.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        JButton btnManagerInfo = new JButton("Manager Info");
        btnManagerInfo.setBounds(10, 230, 200, 30);
        btnManagerInfo.setForeground(Color.WHITE);
        btnManagerInfo.setBackground(Color.BLACK);
        contentPane.add(btnManagerInfo);
        btnManagerInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    ManagerInfo  managerInfo  = new ManagerInfo ();
                    managerInfo.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        JButton btnCheckOut = new JButton("Check Out");
        btnCheckOut.setBounds(10, 270, 200, 30);
        btnCheckOut.setForeground(Color.WHITE);
        btnCheckOut.setBackground(Color.BLACK);
        contentPane.add(btnCheckOut);
        btnCheckOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    CheckOut  checkOut  = new CheckOut();
                    checkOut.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        JButton btnUpdateCheck = new JButton("Update Check Status");
        btnUpdateCheck.setBounds(10, 310, 200, 30);
        btnUpdateCheck.setForeground(Color.WHITE);
        btnUpdateCheck.setBackground(Color.BLACK);
        contentPane.add(btnUpdateCheck);
        btnUpdateCheck.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    UpdateCheck  update  = new UpdateCheck();
                    update.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        JButton btnUpdateRoom = new JButton("Update Room Status");
        btnUpdateRoom.setBounds(10, 350, 200, 30);
        btnUpdateRoom.setForeground(Color.WHITE);
        btnUpdateRoom.setBackground(Color.BLACK);
        contentPane.add(btnUpdateRoom);
        btnUpdateRoom.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    UpdateRoom  updateRoom  = new UpdateRoom();
                    updateRoom.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        JButton btnPickUpServices = new JButton("Pick up Services");
        btnPickUpServices.setBounds(10, 390, 200, 30);
        btnPickUpServices.setForeground(Color.WHITE);
        btnPickUpServices.setBackground(Color.BLACK);
        contentPane.add(btnPickUpServices);
        btnPickUpServices.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    PickUp  pick  = new PickUp();
                    pick.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        JButton btnSearchRoom = new JButton("Search Room");
        btnSearchRoom.setBounds(10, 430, 200, 30);
        btnSearchRoom.setForeground(Color.WHITE);
        btnSearchRoom.setBackground(Color.BLACK);
        contentPane.add(btnSearchRoom);
        btnSearchRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    SearchRoom  searchRoom  = new SearchRoom();
                    searchRoom.setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        JButton btnLoginOut = new JButton("Login Out");
        btnLoginOut.setBounds(10, 470, 200, 30);
        btnLoginOut.setForeground(Color.WHITE);
        btnLoginOut.setBackground(Color.BLACK);
        contentPane.add(btnLoginOut);
        btnLoginOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new Login().setVisible(true);
                    setVisible(false);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        getContentPane().setBackground(Color.WHITE);
    }

    public static void main(String[] args){
        new Reception().setVisible(true);
    }
    
}
