package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.OderItem;



public class OderItemDAO extends BaseDAO {


	public OderItemDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addOderItem(OderItem ad) {
	
		 int idOder = ad.getIdOder();
		 int idMenu= ad.getIdMenu();
		 int soLuong =  ad.getSoLuong();
		 double discount = ad.getDiscount(); 
		try {
			CallableStatement stat = conn.prepareCall("{call oderItem_add(? ,? , ? , ? )");
			stat.setInt(1, idOder);
			stat.setInt(2, idMenu);
			stat.setInt(3, soLuong);
			stat.setDouble(4, discount);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	public void updateOderItem(OderItem up) {
		 int p_id = up.getId();
		 int p_idOder = up.getIdOder();
		 int p_idMenu= up.getIdMenu();
		 int p_soLuong =  up.getSoLuong();
		 double p_discount = up.getDiscount(); 
		 try {
			 CallableStatement stat= conn.prepareCall("{call oderItem_update(?, ? ,? , ? ,? )");
			 stat.setInt(1, p_id);
			 stat.setInt(2, p_idOder);
			 stat.setInt(3, p_idMenu);
			 stat.setInt(4, p_soLuong);
			 stat.setDouble(5, p_discount);
			 stat.executeUpdate();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
	public String deleteOderItem(int p_id) {
		 try {
			 CallableStatement stat= conn.prepareCall("{call oderItem_delete(?)");
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
	public void getAllOderItem() {
		 try {
			 CallableStatement stat = conn.prepareCall("{call oderItem_getAll()");
			 ResultSet result = stat.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getOdeItemById(int id) {
		
		
		try {
			CallableStatement stat = conn.prepareCall("{call oderItem_getById(?)");
			stat.setInt(1, id);
			ResultSet result = stat.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void getOdeItemByOderId(int oderId) {	
		try {
			CallableStatement stat = conn.prepareCall("{call oderItem_getByOderId(?)");
			stat.setInt(1, oderId);
			ResultSet result = stat.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
