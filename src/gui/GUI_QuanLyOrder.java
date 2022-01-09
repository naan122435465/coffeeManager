package gui;

import java.awt.SystemColor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;


import dao.NhanVienDAO;
import dao.OderItemDAO;
import utilities.CoffeeDataSource;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_QuanLyOrder extends JPanel {
	private JTable table;
	private JTextField tf_tenNv;
	private OderItemDAO orde_item_dao;
	private NhanVienDAO nhan_vien_dao;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public GUI_QuanLyOrder() throws ClassNotFoundException, IOException, SQLException {
		CoffeeDataSource.init("database.properties");
	    Connection conn = CoffeeDataSource.getConnection();
	    orde_item_dao = new OderItemDAO(conn);
	    nhan_vien_dao = new NhanVienDAO(conn);
		this.setBackground(SystemColor.controlHighlight);
		this.setBounds(197, 14, 657, 363);
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 667, 374);
		add(panel_1);
		
		JScrollPane dataMenu = new JScrollPane();
		dataMenu.setBounds(10, 41, 469, 323);
		panel_1.add(dataMenu);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ng\u00E0y Order", "ID ho\u00E1 \u0111\u01A1n", "ID item", "Nh\u00E2n Vi\u00EAn", "T\u00EAn M\u00F3n", "Discount", "SL", "T\u1ED5ng ti\u1EC1n"
			}
		));
		orde_item_dao.getAllOderItem(table);
		dataMenu.setViewportView(table);
		
		JButton btn_search = new JButton("Search");
		btn_search.setBounds(538, 10, 75, 26);
		panel_1.add(btn_search);
		
		JButton btn_xoa = new JButton("Xoá");
		
		btn_xoa.setBounds(489, 97, 129, 45);
		panel_1.add(btn_xoa);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(10, 307, 469, 56);
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(329, 16, 22, -5);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Time ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(142, 12, 46, 20);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("time");
		lblNewLabel_4.setBounds(352, 12, 28, 18);
		panel_1.add(lblNewLabel_4);
		LocalDate now = LocalDate.now();
		
		
		DatePicker comm_date1 = new DatePicker((DatePickerSettings) null);
		comm_date1.setBounds(194, 8, 155, 28);
		panel_1.add(comm_date1);
		
		DatePicker comm_date2 = new DatePicker((DatePickerSettings) null);
		comm_date2.setBounds(373, 8, 155, 28);
		panel_1.add(comm_date2);
		
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				String ngay1 = comm_date1.getDateStringOrEmptyString();
				String ngay2 = comm_date2.getDateStringOrEmptyString();	
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
				orde_item_dao.getOdeItemBytime(ngay1, ngay2, table);
				dataMenu.setViewportView(table);
				
				}
				
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
				DefaultTableModel tbModel = (DefaultTableModel)table.getModel();
				int id_item_xoa =  Integer.parseInt(String.valueOf(table.getValueAt(r_model, 3)));
				tbModel.removeRow(table.getSelectedRow());
				orde_item_dao.deleteOderItem(id_item_xoa);
			}
		});
	}
}
