package gui;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JPanel;

import dao.NhanVienDAO;
import utilities.CoffeeDataSource;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;

public class NhanVienJPanel extends JPanel {

	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public NhanVienJPanel() throws ClassNotFoundException, IOException, SQLException {
		
		JPanel panel = new JPanel();
		add(panel);
		CoffeeDataSource.init("database.properties");
	    Connection conn = CoffeeDataSource.getConnection();
	    NhanVienDAO nhan_vien_dao = new NhanVienDAO(conn);
	}
}
