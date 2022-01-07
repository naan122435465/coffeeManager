package gui;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.NhanVienDAO;
import dto.NhanVien;
import utilities.CoffeeDataSource;
import java.awt.GridLayout;
import javax.swing.JToolBar;
import java.awt.Font;

public class GUI_QuanLyNV extends JPanel {
	private JTable table;
	private NhanVienDAO nhan_vien_dao;	
	private JLabel tf_id;
	private JTextField tf_hoTen;
	private JTextField tf_ngaySinh;
	private JTextField tf_chucVu;
	private JTextField tfLuong;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public GUI_QuanLyNV() throws ClassNotFoundException, IOException, SQLException {
		CoffeeDataSource.init("database.properties");
	    Connection conn = CoffeeDataSource.getConnection();
	    nhan_vien_dao = new NhanVienDAO(conn);
		//JPanel panel = new JPanel();
		this.setBackground(SystemColor.controlHighlight);
		this.setBounds(197, 14, 657, 363);
		//this.setVisible(true);
		//add(panel);
		
		ImageIcon iconLogo = new ImageIcon("images/logo.png");
		
		
		
		ImageIcon icon_QLMenu = new ImageIcon("images/mailinglist.jpg");
		
		ImageIcon icon_QLNhanVien = new ImageIcon("images/qluser.png");
		
		ImageIcon icon_QLTaiKhoan = new ImageIcon("images/qluser.png");
		
		
		ImageIcon icon_AddMenu = new ImageIcon("images/file_add.png");
		
		ImageIcon icon_EditMenu = new ImageIcon("images/document_edit.png");
		
		ImageIcon icon_DeleteMenu = new ImageIcon("images/file_delete.png");
		
		ImageIcon iconBack = new ImageIcon(new ImageIcon("images/back.png").getImage().getScaledInstance(40,40,20));
		setLayout(null);
		
		
		JScrollPane dataNV = new JScrollPane();
		dataNV.setBounds(10, 39, 469, 250);
		add(dataNV);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "H\u1ECD t\u00EAn", "Ng\u00E0y sinh", "Ch\u1EE9c v\u1EE5", "L\u01B0\u01A1ng"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		nhan_vien_dao.getAllNhanVien(table);
		dataNV.setViewportView(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		dataNV.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Tổng số");
		lblNewLabel_1.setBounds(26, 14, 67, 14);
		lblNewLabel_1.setForeground(Color.BLACK);
		add(lblNewLabel_1);
		
		JButton bnt_add_nv = new JButton("Thêm nhân viên");
		bnt_add_nv.setBounds(489, 39, 121, 45);
		add(bnt_add_nv);
		bnt_add_nv.setIcon(icon_AddMenu);
		
		JButton btn_update = new JButton("Sửa thông tin");
		btn_update.setBounds(489, 96, 121, 45);
		add(btn_update);
		btn_update.setIcon(icon_EditMenu);
		
		JButton btn_del = new JButton("Xóa nhân viên");
		btn_del.setBounds(489, 152, 121, 45);
		add(btn_del);
		btn_del.setIcon(icon_DeleteMenu);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 308, 469, 45);
		add(panel);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(0, 0, 46, 21);
		panel.add(lblNewLabel);
		
		JLabel lblHTn_1 = new JLabel("Họ Tên");
		lblHTn_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHTn_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHTn_1.setBounds(45, 0, 148, 21);
		panel.add(lblHTn_1);
		
		JLabel lblNgySinh_1 = new JLabel("Ngày sinh");
		lblNgySinh_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNgySinh_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgySinh_1.setBounds(203, 0, 87, 21);
		panel.add(lblNgySinh_1);
		
		JLabel lblChcV_1 = new JLabel("Chức vụ");
		lblChcV_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblChcV_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChcV_1.setBounds(300, 0, 87, 21);
		panel.add(lblChcV_1);
		
		JLabel lblLng_1 = new JLabel("Lương");
		lblLng_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLng_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLng_1.setBounds(397, 0, 72, 21);
		panel.add(lblLng_1);
		
		tf_id = new JLabel();
		tf_id.setBounds(0, 22, 46, 20);
		panel.add(tf_id);
		
		tf_hoTen = new JTextField();
		tf_hoTen.setColumns(10);
		tf_hoTen.setBounds(45, 22, 148, 20);
		panel.add(tf_hoTen);
		
		tf_ngaySinh = new JTextField();
		tf_ngaySinh.setColumns(10);
		tf_ngaySinh.setBounds(194, 22, 96, 20);
		panel.add(tf_ngaySinh);
		
		tf_chucVu = new JTextField();
		tf_chucVu.setColumns(10);
		tf_chucVu.setBounds(291, 22, 96, 20);
		panel.add(tf_chucVu);
		
		tfLuong = new JTextField();
		tfLuong.setColumns(10);
		tfLuong.setBounds(388, 22, 81, 20);
		panel.add(tfLuong);
		
		bnt_add_nv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String Hoten = tf_hoTen.getText(); 
				LocalDate ngaySinh = LocalDate.parse(tf_ngaySinh.getText());
				String chuVu = tf_chucVu.getText();
				int luong =Integer.parseInt(tfLuong.getText());
			
				NhanVien nhan_vien = new NhanVien(Hoten, ngaySinh, chuVu, luong) ;
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				tab.getDataVector().removeAllElements();
				tab.fireTableDataChanged();
				nhan_vien_dao.addNV(nhan_vien);
				nhan_vien_dao.getAllNhanVien(table);
				dataNV.setViewportView(table);
				
			}
		});
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int id= Integer.parseInt(tf_id.getText());
				String Hoten = tf_hoTen.getText(); 
				LocalDate ngaySinh = LocalDate.parse(tf_ngaySinh.getText());
				String chuVu = tf_chucVu.getText();
				int luong =Integer.parseInt(tfLuong.getText());
				NhanVien nhan_vien = new NhanVien(id, Hoten, ngaySinh, chuVu, luong) ;
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				tab.getDataVector().removeAllElements();
				tab.fireTableDataChanged();
				nhan_vien_dao.UpdateNV(nhan_vien);
				nhan_vien_dao.getAllNhanVien(table);
				dataNV.setViewportView(table);
			}
		});
		btn_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r = table.getSelectedRow();
				if (r != -1){
					int r_model = table.convertRowIndexToModel(r);
					int id = (int) table.getValueAt(r_model, 0);
				
				nhan_vien_dao.deleteNV(id);
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				tab.getDataVector().removeAllElements();
				tab.fireTableDataChanged();
				nhan_vien_dao.getAllNhanVien(table);
				dataNV.setViewportView(table);
			
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
				tf_hoTen.setText((String) table.getValueAt(r_model, 1));
				tf_ngaySinh.setText((String) table.getValueAt(r_model,2 ).toString());
				tf_hoTen.setText((String) table.getValueAt(r_model, 3));
				tfLuong.setText(String.valueOf(table.getValueAt(r_model, 4)));
			
			}
		});
	}
}
