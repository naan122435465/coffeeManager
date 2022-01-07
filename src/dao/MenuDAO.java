package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.Menu;



public class MenuDAO extends BaseDAO{

	public MenuDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addMenu(Menu ad) {	
		 String tenMon = ad.getTenMon();
		 int gia = ad.getGia();
		 
		try {
			CallableStatement stat = conn.prepareCall("{call menu_add(? ,?)}");
			
			stat.setString(1, tenMon);
			stat.setInt(2, gia);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	public void updateMenu(Menu up) {
		int p_id = up.getId();
		 String p_tenMon = up.getTenMon();
		 int p_gia = up.getGia();
		 try {
			 CallableStatement stat= conn.prepareCall("{call menu_update(?, ? ,? )}"); 
			 stat.setInt(1, p_id);
			 stat.setString(2, p_tenMon);
			 stat.setInt(3, p_gia);
			 stat.executeUpdate();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
	public String deleteMenu(int id) {
		 try {
			 CallableStatement stat= conn.prepareCall("{call menu_delete(?)}");
			 stat.setInt(1, id);
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
			 CallableStatement stat = conn.prepareCall("{call menu_all()}");
			 ResultSet result = stat.executeQuery();
			 DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
			 if(result.next()) {
				 do {
					 int id =result.getInt(1);
					 String tenMon = result.getString(2);
					 int gia =result.getInt(3);
					 tbModel.addRow(new Object[] { id, tenMon, gia});	 
				 }while(result.next());
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getMenuById(String tenMon) {
		 
		 try {
			 CallableStatement stat = conn.prepareCall("{call menu_getById(?)}");
			 stat.setString(1, tenMon);
			 ResultSet result = stat.executeQuery();
			
			 int giamon = result.getInt(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	public void gettenMon(JComboBox tenmon) {
		 try {
			 CallableStatement stat = conn.prepareCall("{call menu_ten_all()}");
			 ResultSet result = stat.executeQuery();
			 if(result.next()) {
				 do {
					 String ten = result.getString(1);
					 
					tenmon.addItem(ten);
				 }while(result.next());
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int getGia(String tenmon) {
		int gia = 0;
		 try {
			 CallableStatement stat = conn.prepareCall("{call menu_gia(?,?)}");
			 stat.setString(1, tenmon);
			 stat.registerOutParameter(2, java.sql.Types.INTEGER);
			 stat.executeQuery();
			 gia =stat.getInt(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gia;
	}
	
}
