package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import dao.NguyenLieuDAO;
import dto.NguyenLieu;
import utilities.CoffeeDataSource;

import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class GUI_Kho_NVL extends JPanel {
	private JTable table;
	NguyenLieuDAO nguyen_lieu_dao;
	NguyenLieu nguyen_lieu;
	private JTextField tf_tenNL;
	private JTextField tf_ngay_nhap;
	private JTextField tf_so_luong;
	private JTextField tf_tong_tien;
	private JTextField tf_gia;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public GUI_Kho_NVL() throws ClassNotFoundException, IOException, SQLException {
		
		CoffeeDataSource.init("database.properties");
		Connection conn = CoffeeDataSource.getConnection();
		nguyen_lieu_dao = new NguyenLieuDAO(conn);
		this.setBounds(10, 62, 864, 388);
		this.setLayout(null);
		//this.setVisible(true);
		
		JPanel panel_1 = new JPanel();
		
		panel_1.setBounds(0, 0, 864, 388);
		add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 58, 791, 277);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "T\u00EAn Nguy\u00EAn Li\u1EC7u", "Ng\u00E0y Nh\u1EADp", "Gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "T\u1ED5ng ti\u1EC1n"
			}
		));
		nguyen_lieu_dao.getAllNguyenLieu(table);
		scrollPane.setViewportView(table);
		
		JButton btn_xoa = new JButton("X\u00F3a");
		
		btn_xoa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_xoa.setBounds(801,  113, 55, 34);
		panel_1.add(btn_xoa);
		
		JButton btn_search = new JButton("T\u00ECm ki\u1EBFm");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_search.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_search.setBounds(40, 25, 89, 23);
		panel_1.add(btn_search);
		
		DatePicker datePicker_1 = new DatePicker((DatePickerSettings) null);
		
		datePicker_1.getComponentToggleCalendarButton().setText("");
		datePicker_1.setBounds(139, 24, 155, 28);
		panel_1.add(datePicker_1);
		
		DatePicker datePicker_2 = new DatePicker((DatePickerSettings) null);
		datePicker_2.getComponentToggleCalendarButton().setText("");
		datePicker_2.setBounds(329, 24, 155, 28);
		panel_1.add(datePicker_2);
		
		JLabel lblNewLabel = new JLabel("\u0111\u1EBFn");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(299, 24, 23, 28);
		panel_1.add(lblNewLabel);
		
		JButton btn_sua = new JButton("S\u1EEDa");
		
		btn_sua.setBounds(801, 69, 55, 34);
		panel_1.add(btn_sua);
		
		tf_tenNL = new JTextField();
		tf_tenNL.setBounds(0, 359, 186, 29);
		panel_1.add(tf_tenNL);
		tf_tenNL.setColumns(10);
		
		tf_ngay_nhap = new JTextField();
		tf_ngay_nhap.setBounds(185, 359, 180, 29);
		panel_1.add(tf_ngay_nhap);
		tf_ngay_nhap.setColumns(10);
		
		tf_so_luong = new JTextField();
		tf_so_luong.setBounds(475, 359, 163, 29);
		panel_1.add(tf_so_luong);
		tf_so_luong.setColumns(10);
		
		tf_tong_tien = new JTextField();
		tf_tong_tien.setBounds(636,359, 155, 29);
		panel_1.add(tf_tong_tien);
		tf_tong_tien.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("T\u00EAn Nguy\u00EAn Li\u1EC7u");
		lblNewLabel_2.setBounds(10,332, 153, 29);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ng\u00E0y nh\u1EADp");
		lblNewLabel_3.setBounds(216, 332, 149, 29);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("S\u1ED1 l\u01B0\u1EE3ng");
		lblNewLabel_4.setBounds(495,332, 106, 29);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("T\u1ED5ng ti\u1EC1n");
		lblNewLabel_5.setBounds(644, 332, 106, 29);
		panel_1.add(lblNewLabel_5);
		
		JButton btn_add = new JButton("ADD");
		
		btn_add.setBounds(791, 361, 73, 27);
		panel_1.add(btn_add);
		
		tf_gia = new JTextField();
		tf_gia.setBounds(365, 359, 116, 29);
		panel_1.add(tf_gia);
		tf_gia.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Gi\u00E1");
		lblNewLabel_6.setBounds(392, 332, 78, 29);
		panel_1.add(lblNewLabel_6);
		btn_sua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
				int id =Integer.parseInt(String.valueOf((table.getValueAt(r_model, 0))));
				String tenNL = (String) table.getValueAt(r_model, 1) ;
				String ngayString = table.getValueAt(r_model, 2).toString();
				LocalDate ngay_nhap = LocalDate.parse(ngayString);
				int gia  = Integer.parseInt(String.valueOf((table.getValueAt(r_model, 3)))); ;
				int so_luong =Integer.parseInt(String.valueOf((table.getValueAt(r_model, 4)))); ;
				nguyen_lieu = new NguyenLieu(id, tenNL, gia,so_luong,ngay_nhap);
				nguyen_lieu_dao.updateNguyenLieu(nguyen_lieu);
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				tab.getDataVector().removeAllElements();
				tab.fireTableDataChanged();
				nguyen_lieu_dao.getAllNguyenLieu(table);
				scrollPane.setViewportView(table);
				
			}
		});
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenNL =tf_tenNL.getText() ;
				LocalDate ngay_nhap = LocalDate.parse(tf_ngay_nhap.getText())  ;
				int gia = Integer.parseInt(tf_gia.getText()) ;  ;
				int so_luong =Integer.parseInt(tf_so_luong.getText()) ; 			
				nguyen_lieu = new NguyenLieu( tenNL, gia,so_luong,ngay_nhap);
				nguyen_lieu_dao.addNguyenLieu(nguyen_lieu);
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				tab.getDataVector().removeAllElements();
				tab.fireTableDataChanged();
				nguyen_lieu_dao.getAllNguyenLieu(table);
				scrollPane.setViewportView(table);
			}
		});
		btn_xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				int id =(int) table.getValueAt(r_model, 0) ;
				nguyen_lieu_dao.deleteNguyenLieu(id);
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				tab.getDataVector().removeAllElements();
				tab.fireTableDataChanged();
				nguyen_lieu_dao.getAllNguyenLieu(table);
				scrollPane.setViewportView(table);
			}
			
		});
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ngay1 = datePicker_1.getDateStringOrEmptyString();
				String ngay2 = datePicker_2.getDateStringOrEmptyString();	
				System.out.print(ngay1);
				if(ngay2 == "") {
					ngay2 =ngay1;
					
				}
				if(ngay1 == "") {
					ngay1 = ngay2;

				}
				if(ngay1 != "" && ngay2 != "") {
					DefaultTableModel tab = (DefaultTableModel)table.getModel();
					tab.getDataVector().removeAllElements();
					tab.fireTableDataChanged();
					nguyen_lieu_dao.Search(ngay1, ngay2, table);
					scrollPane.setViewportView(table);
					
				}
			}
		});
	}
}
