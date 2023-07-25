import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SearchRoom extends JFrame{
    
    private Room room;
    private JPanel contentPane;
	private JTextField txt_Type;
	private JTable table;
    Choice c1;


    public SearchRoom(String roomNo, String available, String status, String price, String type) {
        this.room = new Room(roomNo, available, status, price, type); //Relationship is Composition.
    }

    public SearchRoom(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);

		JLabel lblSearchForRoom = new JLabel("Search For Room");
		lblSearchForRoom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearchForRoom.setBounds(250, 11, 186, 31);
		contentPane.add(lblSearchForRoom);
		
		JLabel lblRoomAvailable = new JLabel("Room Bed Type:");
		lblRoomAvailable.setBounds(50, 73, 96, 14);
		contentPane.add(lblRoomAvailable);
		
		JLabel lblRoomType = new JLabel("Room Number");
		lblRoomType.setBounds(23, 162, 96, 14);
		contentPane.add(lblRoomType);
		
		JLabel lblRoomAvailable_1 = new JLabel("Availability");
		lblRoomAvailable_1.setBounds(175, 162, 120, 14);
		contentPane.add(lblRoomAvailable_1);
		
        JLabel lblRoomStatus = new JLabel("Status");
        lblRoomStatus.setBounds(320, 162, 120, 14);
        contentPane.add(lblRoomStatus);

		JLabel lblPrice_1 = new JLabel("Price");
		lblPrice_1.setBounds(458, 162, 46, 14);
		contentPane.add(lblPrice_1);
                
        JLabel l1 = new JLabel("Bed Type");
		l1.setBounds(580, 162, 96, 14);
		contentPane.add(l1);
		
		JCheckBox checkRoom = new JCheckBox("Only display Available");
		checkRoom.setBounds(400, 69, 205, 23);
        checkRoom.setBackground(Color.WHITE);
		contentPane.add(checkRoom);
		
        c1 = new Choice();
        c1.add("Single Bed");
        c1.add("Double Bed");
        c1.setBounds(153, 70, 120, 20);
		contentPane.add(c1);


        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(200, 400, 120, 30);
        btnSearch.setBackground(Color.BLACK);
        btnSearch.setForeground(Color.WHITE);
        contentPane.add(btnSearch);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList<SearchRoom> srList = new ArrayList<>();
                String sql = "select * from room where bed_type = '"+c1.getSelectedItem()+"'";
                String sql1 = "select * from room where availability = 'Available' AND bed_type = '"+c1.getSelectedItem()+"'";
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(sql);
                    table.setModel(tableView(rs, srList));
                    if(checkRoom.isSelected()){
                        ResultSet rs1 = c.s.executeQuery(sql1);
                        srList.clear();
                        table.setModel(tableView(rs1, srList));
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        table = new JTable();
		table.setBounds(0, 187, 700, 300);
		contentPane.add(table);
    }

    public DefaultTableModel tableView(ResultSet rs, ArrayList<SearchRoom> srList ){
        String[] col = {"room_number","availability","cleaning_status","price","bed_type"};
        DefaultTableModel model = new DefaultTableModel(null, col);
        SearchRoom searchRoom;
        try {
            while(rs.next()){
                searchRoom = new SearchRoom(
                rs.getString("room_number"),
                rs.getString("availability"),
                rs.getString("cleaning_status"),
                rs.getString("price"),
                rs.getString("bed_type"));
                srList.add(searchRoom);    
            } 

            Object[] row = new Object[5];
            for(int i=0;i<srList.size();i++){
                row[0] = srList.get(i).room.getRoomNo();
                row[1] = srList.get(i).room.getAvailability();
                row[2] = srList.get(i).room.getStatus();
                row[3] = srList.get(i).room.getPrice();
                row[4] = srList.get(i).room.get_Type();
                model.addRow(row);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return model;
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new SearchRoom().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            
        });
    }
}
