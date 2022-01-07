package gui;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import dao.MenuDAO;
import dao.OderItemDAO;
import dao.OdersDAO;
import dto.Menu;
import dto.OderItem;
import dto.Oders;
import dto.UserName;
import utilities.CoffeeDataSource;

import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class GUI_Order extends JPanel {
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	private MenuDAO menu_dao;
	private JTable table;
	private OdersDAO order_dao;
	private OderItemDAO order_item_dao;
	private JTextField tf_GiamGia;
	private JTextField tf_SoLuong;
	private JTextField tf_TenMon;
	private JTextField tf_SL;
	private JTextField tf_ThanhTien;
	int id_orderItem;
	private JTable table_Order;
	public GUI_Order(UserName user) throws ClassNotFoundException, IOException, SQLException {
		CoffeeDataSource.init("database.properties");
	    Connection conn = CoffeeDataSource.getConnection();
	    order_dao = new OdersDAO(conn);
	    menu_dao = new MenuDAO(conn);
	    order_item_dao = new OderItemDAO(conn);
	   
		setLayout(null);
		
		//JPanel panel = new JPanel();
		this.setBackground(SystemColor.controlHighlight);
		this.setBounds(10, 62, 864, 388);
		//add(panel);
		this.setLayout(null);
		
		ImageIcon HinhNen = new ImageIcon(new ImageIcon("images/background1.jpg").getImage().getScaledInstance(864, 393,20));
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 864, 388);
		add(panel);
		panel.setLayout(null);
		//.setIcon(HinhNen);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(0, 0, 864, 125);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaption);
		panel_2.setBounds(0, 0, 384, 42);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ch\u1ECDn m\u00F3n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 320, 42);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("UserName");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(401, 11, 101, 30);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("T\u00EAn m\u00F3n");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1.setBounds(20, 53, 68, 30);
		panel_1.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2.setBounds(318, 53, 68, 30);
		panel_1.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("Gi\u1EA3m gi\u00E1");
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_3.setBounds(454, 55, 68, 30);
		panel_1.add(lblNewLabel_4_3);
		
		JLabel nameJLabel = new JLabel(user.getUsername());
		nameJLabel.setBounds(512, 13, 200, 30);
		panel_1.add(nameJLabel);
		
		JComboBox comboBox_TenMon = new JComboBox();
		menu_dao.gettenMon(comboBox_TenMon);
		comboBox_TenMon.setBounds(98, 55, 200, 30);
		panel_1.add(comboBox_TenMon);
		
		
		tf_GiamGia = new JTextField();
		tf_GiamGia.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_GiamGia.setText("1");
		tf_GiamGia.setBounds(532, 60, 86, 20);
		panel_1.add(tf_GiamGia);
		tf_GiamGia.setColumns(10);
		
		tf_SoLuong = new JTextField();
		tf_SoLuong.setText("1");
		tf_SoLuong.setHorizontalAlignment(SwingConstants.RIGHT);
		tf_SoLuong.setBounds(396, 60, 30, 20);
		panel_1.add(tf_SoLuong);
		tf_SoLuong.setColumns(10);
		
		JButton bnt_add = new JButton("ADD");
		
		bnt_add.setForeground(new Color(51, 204, 51));
		bnt_add.setFont(new Font("Tahoma", Font.BOLD, 15));
		bnt_add.setBounds(670, 52, 113, 33);
		panel_1.add(bnt_add);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(191, 60, 29, 21);
		panel_1.add(comboBox);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.LIGHT_GRAY);
		panel_1_1.setBounds(0, 136, 863, 252);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(393, 10, 466, 241);
		panel_1_1.add(scrollPane);
		
		table_Order = new JTable();
		table_Order.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"T\u00EAn m\u00F3n", "Discount", "SL", "Th\u00E0nh ti\u1EC1n"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_Order.getColumnModel().getColumn(0).setPreferredWidth(110);
		table_Order.getColumnModel().getColumn(1).setPreferredWidth(51);
		table_Order.getColumnModel().getColumn(2).setPreferredWidth(21);
		table_Order.getColumnModel().getColumn(3).setPreferredWidth(80);
		scrollPane.setViewportView(table_Order);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(-1, 47, 384, 45);
		panel_3.setLayout(null);
		panel_1_1.add(panel_3);
		
		JLabel lblTenMon = new JLabel("T\u00EAn m\u00F3n");
		lblTenMon.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenMon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenMon.setBounds(6, 0, 178, 21);
		panel_3.add(lblTenMon);
		
		JLabel lblSL = new JLabel("SL");
		lblSL.setHorizontalAlignment(SwingConstants.CENTER);
		lblSL.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSL.setBounds(186, 0, 30, 21);
		panel_3.add(lblSL);
		
		JLabel lblThanhTien = new JLabel("Th\u00E0nh ti\u1EC1n");
		lblThanhTien.setHorizontalAlignment(SwingConstants.LEFT);
		lblThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblThanhTien.setBounds(226, 0, 158, 21);
		panel_3.add(lblThanhTien);
		
		tf_TenMon = new JTextField();
		tf_TenMon.setColumns(10);
		tf_TenMon.setBounds(3, 22, 181, 20);
		panel_3.add(tf_TenMon);
		
		tf_SL = new JTextField();
		tf_SL.setColumns(10);
		tf_SL.setBounds(186, 22, 30, 20);
		panel_3.add(tf_SL);
		
		tf_ThanhTien = new JTextField();
		tf_ThanhTien.setColumns(10);
		tf_ThanhTien.setBounds(218, 22, 166, 20);
		panel_3.add(tf_ThanhTien);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(0, 0, 384, 42);
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(SystemColor.activeCaption);
		panel_1_1.add(panel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Thanh to\u00E1n");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(0, 0, 384, 42);
		panel_2_1.add(lblNewLabel_1);
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.setBounds(99, 103, 89, 23);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1_1.add(btnSua);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.setBounds(202, 103, 89, 23);
		panel_1_1.add(btnXoa);
		
		JButton btn_thanhToan = new JButton("Thanh to\u00E1n");
		btn_thanhToan.setBounds(134, 155, 132, 33);
		btn_thanhToan.setFont(new Font("Tahoma", Font.BOLD, 15));
		btn_thanhToan.setForeground(new Color(205, 133, 63));
		panel_1_1.add(btn_thanhToan);
		 DefaultTableModel tbModel = (DefaultTableModel)table_Order.getModel();
		bnt_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ten_mon = (String) comboBox_TenMon.getSelectedItem();
				int so_luong = Integer.parseInt(tf_SoLuong.getText());
				int  discount = Integer.parseInt(tf_GiamGia.getText());
				int gia = menu_dao.getGia(ten_mon);
				int tong = gia*(100-discount);
			//	OderItem order_item = new OderItem(id_order, ten_mon, so_luong, discount);
				tbModel.addRow(new Object[] {ten_mon,discount,so_luong ,tong });
				scrollPane.setViewportView(table_Order);
			}
		});
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int r = table.getSelectedRow();
				if (r != -1){
				
					
				}
			}
		});
		btn_thanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 int idNV = user.getIdNV();
					LocalDate ngay_or = LocalDate.now();
					Oders oders = new Oders(idNV, ngay_or);
					int id_order = order_dao.addOder(oders);
					for(int i = 1; i <= table_Order.getRowCount(); i++) {
						
						String ten_mon =  (String) table.getValueAt(i, 1);
						int so_luong  =Integer.parseInt(String.valueOf(table.getValueAt(i, 2)));
						int discount  =Integer.parseInt(String.valueOf(table.getValueAt(i, 2)));
						OderItem order_item = new OderItem(id_order, ten_mon, so_luong, discount);
						
					}
				
				
			}
		});
		
	}
}
