package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.OderItem;



public class OderItemDAO extends BaseDAO {


	public OderItemDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addOderItem(OderItem ad) {
	
		 int idOder = ad.getIdOder();
		 String tenMon= ad.getTenMon();
		 int soLuong =  ad.getSoLuong();
		 double discount = ad.getDiscount(); 
		 
		try {
			CallableStatement stat = conn.prepareCall("{call order_item_add(? ,? , ? , ?)}");
			stat.setInt(1, idOder);
			stat.setString(2, tenMon);
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
		 String tenMon= up.getTenMon();
		 int p_soLuong =  up.getSoLuong();
		 double p_discount = up.getDiscount(); 
		 try {
			 CallableStatement stat= conn.prepareCall("{call oderItem_update(?, ? ,? , ? ,? )}");
			 stat.setInt(1, p_id);
			 stat.setInt(2, p_idOder);
			 stat.setString(3, tenMon);
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
			 CallableStatement stat= conn.prepareCall("{call oderItem_delete(?)}");
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
	public void getAllOderItem(JTable tb) {
		 try {
			 CallableStatement stat = conn.prepareCall("{call oderItem_all()}");
			 ResultSet result = stat.executeQuery();
			 DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
			 if(result.next()) {
				 do {
					 int id = result.getInt(1);
					 int idNV =result.getInt(2);
					 LocalDate ngayOrder = LocalDate.parse(result.getString(3));
					 tbModel.addRow(new Object[] {id, idNV,ngayOrder });	 
				 }while(result.next());
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int tongDon(int id, JTable tb) {
		
		int tong = 0;
		try {
			CallableStatement stat = conn.prepareCall("{call order_item_tongdon(?,?)}");
			stat.setInt(1, id);
			stat.registerOutParameter(2, java.sql.Types.INTEGER);
			stat.executeUpdate();
			tong = stat.getInt(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tong;
	}

	public void getOdeItemByOderId(int oderId, JTable tb) {	
		try {
			CallableStatement stat = conn.prepareCall("{call order_item_getByOrderID(?)}");
			stat.setInt(1, oderId);
			ResultSet result = stat.executeQuery();
			DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
			 if(result.next()) {
				 do {
					 int id = result.getInt(1);
					 String ten_mon =result.getString(2);
					 int so_luong =result.getInt(3);
					 int tong =result.getInt(3);
					 tbModel.addRow(new Object[] { ten_mon, so_luong, tong,id });	 
				 }while(result.next());
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
