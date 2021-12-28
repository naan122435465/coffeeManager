package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.Menu;



public class MenuDAO extends BaseDAO{

	public MenuDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addMenu(Menu ad) {
		
		
		 int gia = ad.getGia();
		 String tenMon = ad.getTenMon();
		 
		try {
			CallableStatement stat = conn.prepareCall("{call menu_add(? ,?)");
			stat.setInt(1, gia);
			stat.setString(2, tenMon);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	public void updateMenu(Menu up) {
		
		 String p_tenMon = up.getTenMon();
		 int p_gia = up.getGia();
		 try {
			 CallableStatement stat= conn.prepareCall("{call menu_update(?, ?  )");
			 
			
			 stat.setString(1, p_tenMon);
			 stat.setInt(2, p_gia);
			 stat.executeUpdate();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
	public String deleteMenu(int p_id) {
		 try {
			 CallableStatement stat= conn.prepareCall("{call menu_delete(?)");
			 stat.setInt(1, p_id);
			 if(stat.executeUpdate()>0) {
				 
				  
				    return "Delete is sucess";
				 
			 }
			 return " Delete is failed";
			
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 return null;
		
	}
	public void getAllMenu(JTable tb) {
		 try {
			 CallableStatement stat = conn.prepareCall("{call menu_getAll()");
			 ResultSet result = stat.executeQuery();
			 DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
			 if(result.next()) {
				 do {
					 int id = result.getInt(1);
					 String tenMon = result.getString(2);
					 int gia =result.getInt(3);
					 tbModel.addRow(new Object[] {id, tenMon, gia});	 
				 }while(result.next());
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int getMenuById(int id) {
		 int giamon = 0;
		 try {
			 CallableStatement stat = conn.prepareCall("{call menu_getById(?)");
			 stat.setInt(1, id);
			 ResultSet result = stat.executeQuery();
			  giamon = result.getInt(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return giamon;
		
	}
}
