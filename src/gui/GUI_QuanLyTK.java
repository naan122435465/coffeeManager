package gui;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDAO;
import dao.UserNameDAO;
import dto.UserName;
import utilities.CoffeeDataSource;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_QuanLyTK extends JPanel {
	private JTable table;
	private JLabel tf_id;
	private JTextField tf_username;
	private JTextField tf_password;
	private JTextField tf_idnv;
	private JTextField tf_role;

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public GUI_QuanLyTK() throws ClassNotFoundException, IOException, SQLException {
		CoffeeDataSource.init("database.properties");
	    Connection conn = CoffeeDataSource.getConnection();
	    UserNameDAO user_name= new UserNameDAO(conn);
	    
		setLayout(null);
		this.setBounds(197, 14, 657, 374);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(0, 0, 657, 363);
		add(panel);
		ImageIcon iconLogo = new ImageIcon("images/logo.png");
		
		
		
		ImageIcon icon_QLMenu = new ImageIcon("images/mailinglist.jpg");
		
		ImageIcon icon_QLNhanVien = new ImageIcon("images/qluser.png");
		
		ImageIcon icon_QLTaiKhoan = new ImageIcon("images/qluser.png");
		
		
		ImageIcon icon_AddMenu = new ImageIcon("images/file_add.png");
		
		ImageIcon icon_EditMenu = new ImageIcon("images/document_edit.png");
		
		ImageIcon icon_DeleteMenu = new ImageIcon("images/file_delete.png");
		
		ImageIcon iconBack = new ImageIcon(new ImageIcon("images/back.png").getImage().getScaledInstance(40,40,20));
		panel.setLayout(null);
		
		JScrollPane dataview = new JScrollPane();
		dataview.setBounds(10, 39, 469, 267);
		panel.add(dataview);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Username", "Password", "ID_NV", "Role"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(45);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		dataview.setViewportView(table);
		user_name.getAllUser(table);
		dataview.setViewportView(table);
		JButton btn_addUser = new JButton("Thêm tài khoản");
		
		btn_addUser.setBounds(489, 39, 155, 45);
		panel.add(btn_addUser);
		btn_addUser.setIcon(icon_AddMenu);
		
		JButton btn_update_user = new JButton("Sửa tài khoản");
		btn_update_user.setBounds(489, 96, 155, 45);
		panel.add(btn_update_user);
		btn_update_user.setIcon(icon_EditMenu);
		
		JButton btn_delUser = new JButton("Xóa tài khoản");
		btn_delUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_delUser.setBounds(489, 152, 155, 45);
		panel.add(btn_delUser);
		btn_delUser.setIcon(icon_DeleteMenu);
		
		JLabel lblNewLabel_1 = new JLabel("Tổng số tài khoản");
		lblNewLabel_1.setBounds(10, 11, 105, 14);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.BLACK);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 308, 469, 45);
		panel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(0, 0, 62, 21);
		panel_1.add(lblNewLabel);
		
		JLabel lblHTn = new JLabel("Username");
		lblHTn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHTn.setBounds(67, 0, 126, 21);
		panel_1.add(lblHTn);
		
		JLabel lblNgySinh = new JLabel("Password");
		lblNgySinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgySinh.setBounds(203, 0, 87, 21);
		panel_1.add(lblNgySinh);
		
		JLabel lblChcV = new JLabel("ID_NV");
		lblChcV.setHorizontalAlignment(SwingConstants.CENTER);
		lblChcV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChcV.setBounds(310, 0, 77, 21);
		panel_1.add(lblChcV);
		
		JLabel lblLng = new JLabel("Role");
		lblLng.setHorizontalAlignment(SwingConstants.CENTER);
		lblLng.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLng.setBounds(392, 0, 77, 21);
		panel_1.add(lblLng);
		
		tf_id = new JLabel();
		tf_id.setBounds(0, 22, 62, 20);
		panel_1.add(tf_id);
		
		tf_username = new JTextField();
		tf_username.setColumns(10);
		tf_username.setBounds(67, 22, 126, 20);
		panel_1.add(tf_username);
		
		tf_password = new JTextField();
		tf_password.setColumns(10);
		tf_password.setBounds(194, 22, 109, 20);
		panel_1.add(tf_password);
		
		tf_idnv = new JTextField();
		tf_idnv.setColumns(10);
		tf_idnv.setBounds(304, 22, 97, 20);
		panel_1.add(tf_idnv);
		
		tf_role = new JTextField();
		tf_role.setColumns(10);
		tf_role.setBounds(403, 22, 66, 20);
		panel_1.add(tf_role);
		btn_addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = tf_username.getText();
				String passWord = tf_password.getText();
				int id_nv = Integer.parseInt(tf_idnv.getText());
				int role = Integer.parseInt(tf_role.getText());
				UserName user = new UserName(username, passWord, id_nv, role);
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				tab.getDataVector().removeAllElements();
				tab.fireTableDataChanged();
				user_name.addUser(user);
				user_name.getAllUser(table);
				dataview.setViewportView(table);
			};
		});
		btn_update_user.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int id = Integer.parseInt(tf_id.getText());
				String username = tf_username.getText();
				String passWord = tf_password.getText();
				int id_nv = Integer.parseInt(tf_idnv.getText());
				int role = Integer.parseInt(tf_role.getText());
				UserName user = new UserName(id, username, passWord, id_nv, role);
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				tab.getDataVector().removeAllElements();
				tab.fireTableDataChanged();
				user_name.updateUser(user);
				user_name.getAllUser(table);
				dataview.setViewportView(table);
			}
		});
		btn_delUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r = table.getSelectedRow();
				if (r != -1){
					int r_model = table.convertRowIndexToModel(r);
					int id = (int) table.getValueAt(r_model, 0);
					user_name.deleteUser(id);
					DefaultTableModel tab = (DefaultTableModel)table.getModel();
					tab.getDataVector().removeAllElements();
					tab.fireTableDataChanged();
					user_name.getAllUser(table);
					dataview.setViewportView(table);
				}
			}
		});
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int r = table.getSelectedRow();
				int r_model = -1;
				if (r != -1){
					r_model = table.convertRowIndexToModel(r);
				}
				
				int c = table.getSelectedColumn();
				int c_model = -1;
				if (c != -1){
					c_model = table.convertColumnIndexToModel(c);
				}
				tf_id.setText(String.valueOf(table.getValueAt(r_model, 0)));
				tf_username.setText((String) table.getValueAt(r_model, 1));
				tf_password.setText((String) table.getValueAt(r_model, 2));
				tf_idnv.setText(String.valueOf(table.getValueAt(r_model, 3)));
				tf_role.setText(String.valueOf(table.getValueAt(r_model, 4)));
			}
		});
	}
}
