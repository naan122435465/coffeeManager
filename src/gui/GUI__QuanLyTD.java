package gui;

import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import dao.MenuDAO;
import dto.Menu;

import utilities.CoffeeDataSource;

public class GUI__QuanLyTD extends JPanel {
	private JTextField textField;
	private JTable table;
	private JTextField tf_tenMon;
	private JTextField tf_gia;

	private JLabel tf_id;
	private MenuDAO menu_dao;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public GUI__QuanLyTD() throws SQLException, ClassNotFoundException, IOException {
		CoffeeDataSource.init("database.properties");
		Connection conn = CoffeeDataSource.getConnection();
	    menu_dao = new MenuDAO(conn);
		setLayout(null);
		
		this.setBounds(197, 14, 657, 374);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.scrollbar);
		panel.setBounds(10, 11, 657, 363);
		add(panel);
		
		JScrollPane dataMenu = new JScrollPane();
		dataMenu.setBounds(10, 39, 469, 268);
		panel.add(dataMenu);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "T\u00EAn m\u00F3n", "Gi\u00E1"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		menu_dao.getAllMenu(table);
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		dataMenu.setViewportView(table);
		
		JButton btn_addMenu = new JButton("Thêm Món");
		btn_addMenu.setBounds(489, 39, 155, 45);
		panel.add(btn_addMenu);
		
		JButton updateMenu = new JButton("Sửa Món");
		updateMenu.setBounds(489, 96, 155, 45);
		panel.add(updateMenu);
		
		JButton delMenu = new JButton("Xoá Món");
		delMenu.setBounds(489, 152, 155, 45);
		panel.add(delMenu);
		
		JLabel lblNewLabel_1 = new JLabel("Tổng Số Món");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(10, 11, 105, 14);
		panel.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(10, 307, 469, 45);
		panel.add(panel_2);
		JLabel ld_id = new JLabel("ID");
		ld_id.setHorizontalAlignment(SwingConstants.CENTER);
		ld_id.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ld_id.setBounds(0, 0, 37, 21);
		panel_2.add(ld_id);
		
		JLabel lb_tenMon = new JLabel("Tên Món");
		lb_tenMon.setHorizontalAlignment(SwingConstants.CENTER);
		lb_tenMon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lb_tenMon.setBounds(47, 0, 264, 21);
		panel_2.add(lb_tenMon);
		
		JLabel lb_gia = new JLabel("Giá");
		lb_gia.setHorizontalAlignment(SwingConstants.CENTER);
		lb_gia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lb_gia.setBounds(321, 0, 148, 21);
		panel_2.add(lb_gia);
		
		tf_id = new JLabel();
		
		tf_id.setBounds(0, 22, 30, 20);
		panel_2.add(tf_id);
		
		tf_tenMon = new JTextField();
		tf_tenMon.setColumns(10);
		tf_tenMon.setBounds(47, 22, 273, 20);
		panel_2.add(tf_tenMon);
		
		tf_gia = new JTextField();
		tf_gia.setColumns(10);
		tf_gia.setBounds(321, 22, 148, 20);
		panel_2.add(tf_gia);
		btn_addMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ten_mon= tf_tenMon.getText();
				int gia = Integer.parseInt(tf_gia.getText());
				Menu menu = new Menu(ten_mon, gia);
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				tab.getDataVector().removeAllElements();
				tab.fireTableDataChanged();
				menu_dao.addMenu(menu);
				menu_dao.getAllMenu(table);
				dataMenu.setViewportView(table);
			}
		});
		updateMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(tf_id.getText());
				String ten_mon= tf_tenMon.getText();
				int gia = Integer.parseInt(tf_gia.getText());
				Menu menu = new Menu(id,ten_mon, gia);
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				tab.getDataVector().removeAllElements();
				tab.fireTableDataChanged();
				menu_dao.updateMenu(menu);
				menu_dao.getAllMenu(table);
				dataMenu.setViewportView(table);
			}
			
		});
		delMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = table.getSelectedRow();
				if (r != -1){
					int r_model = table.convertRowIndexToModel(r);
					int id = (int) table.getValueAt(r_model, 0);
					
					menu_dao.deleteMenu(id);
					DefaultTableModel tab = (DefaultTableModel)table.getModel();
					tab.getDataVector().removeAllElements();
					tab.fireTableDataChanged();
					menu_dao.getAllMenu(table);
					dataMenu.setViewportView(table);
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
				tf_tenMon.setText((String) table.getValueAt(r_model, 1));
				tf_gia.setText(String.valueOf(table.getValueAt(r_model, 2)));
			}
		
		});
	}
}
