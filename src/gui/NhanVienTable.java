package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.NhanVienDAO;
import utilities.CoffeeDataSource;
import javax.swing.table.DefaultTableModel;

public class NhanVienTable  extends JFrame implements ActionListener  {

	private JTable table;
	private NhanVienDAO nhan_vien_dao;	
	//private JTable table_1;
	
	public NhanVienTable(String title) throws ClassNotFoundException, IOException, SQLException {
		CoffeeDataSource.init("database.properties");
	    Connection conn = CoffeeDataSource.getConnection();
	    nhan_vien_dao = new NhanVienDAO(conn);
		setBounds(10,10,400,400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(10, 10, 350, 343);
	    getContentPane().add(scrollPane);
	    
	    table = new JTable();
	    //DefaultTableModel model = (DefaultTableModel) table.getModel();
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
	    		false, false, false, false, false
	    	};
	    	public boolean isCellEditable(int row, int column) {
	    		return columnEditables[column];
	    	}
	    });
	    nhan_vien_dao.getAllNhanVien(table);
	    scrollPane.setViewportView(table);
	    
	    
		
	}
	      
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		 NhanVienTable myApp = new NhanVienTable("bang nhan vien");
		 myApp.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
