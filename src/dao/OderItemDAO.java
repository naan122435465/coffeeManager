package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;
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
			 CallableStatement stat= conn.prepareCall("{call order_item_delete(?)}");
			 stat.setInt(1, p_id);
			 if(stat.executeUpdate()>0) { 
				 JOptionPane.showMessageDialog(null,"Xoá Thành Công");
				 
			 }
			 
			
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			 JOptionPane.showMessageDialog(null,"Delete is failed");
		 }
		 return null;
		
	}
	public void getAllOderItem(JTable tb) {
		 try {
			 CallableStatement stat = conn.prepareCall("{call all_item()}");
			 ResultSet result = stat.executeQuery();
			 DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
			 if(result.next()) {
				 do {
					 int id_item = result.getInt(1);
					 int id_order = result.getInt(2);
					 String ten_mon = result.getString(3);
					 int so_luong = result.getInt(4);
					 int discount = result.getInt(5);
					 int tong = result.getInt(6);
					 LocalDate ngayOrder = LocalDate.parse(result.getString(7));
					 String ho_ten = result.getString(3);
					 tbModel.addRow(new Object[] {ngayOrder,ho_ten,id_item,id_order,ten_mon,so_luong,discount,tong });	 
				 }while(result.next());
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public int tongDon(int id, JTable tb) {
//		
//		int tong = 0;
//		try {
//			CallableStatement stat = conn.prepareCall("{call order_item_tongdon(?,?)}");
//			stat.setInt(1, id);
//			stat.registerOutParameter(2, java.sql.Types.INTEGER);
//			stat.executeUpdate();
//			tong = stat.getInt(2);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return tong;
//	}

//	public void getOdeItemByOderId(int oderId, JTable tb) {	
//		try {
//			CallableStatement stat = conn.prepareCall("{call order_item_getByOrderID(?)}");
//			stat.setInt(1, oderId);
//			ResultSet result = stat.executeQuery();
//			DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
//			 if(result.next()) {
//				 do {
//					 int id = result.getInt(1);
//					 String ten_mon =result.getString(2);
//					 int so_luong =result.getInt(3);
//					 int tong =result.getInt(3);
//					 tbModel.addRow(new Object[] { ten_mon, so_luong, tong,id });	 
//				 }while(result.next());
//			 }
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	public void getOdeItemBytime(String date1,String date2, JTable tb) {	
		try {
	
			CallableStatement stat = conn.prepareCall("{call order_getByTime(?,?)}");
			stat.setString(1, date1);
			stat.setString(2, date2);
			ResultSet result = stat.executeQuery();
			DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
			 if(result.next()) {
				 do {
					 int id_item = result.getInt(1);
					 int id_order = result.getInt(2);
					 String ten_mon = result.getString(3);
					 int so_luong = result.getInt(4);
					 int discount = result.getInt(5);
					 int tong = result.getInt(6);
					 LocalDate ngayOrder = LocalDate.parse(result.getString(7));
					 String ho_ten = result.getString(3);
					 tbModel.addRow(new Object[] {ngayOrder,ho_ten,id_item,id_order,ten_mon,so_luong,discount,tong });	 
				 }while(result.next());	 
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void searchOrderItem(String name, String date1,String date2, JTable tb) throws SQLException {	
	//	try {
			 String string = "n.ho_ten  =" + name + "and" ;
			if(date2 == null) {
				date2 = date1;
			}
			if(name == "tên nhân viên") {
				string = " "; 
			}
			String query = " SELECT i.id, i.id_order, i.ten_mon,i.so_luong,i.discount,i.tong, o.ngay_order, n.ho_ten \r\n " + 
					" FROM order_item i\r\n " + 
					" INNER JOIN orders o  on o.id = i.id_order\r\n " + 
					" INNER JOIN nhan_vien n on o.id_nv =n.id\r\n WHERE  " 
					+ string +" ngay_order  BETWEEN "+ date1 + " AND "   + date2+
					" ORDER BY o.ngay_order; ";
			System.out.print(query);
			PreparedStatement stat = conn.prepareStatement(query);
			ResultSet result = stat.executeQuery();
			DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
			 if(result.next()) {
				 do {
					 int id_item = result.getInt(1);
					 System.out.print(id_item);
					 int id_order = result.getInt(2);
					 String ten_mon = result.getString(3);
					 int so_luong = result.getInt(4);
					 int discount = result.getInt(5);
					 int tong = result.getInt(6);
					 LocalDate ngayOrder = LocalDate.parse(result.getString(7));
					 String ho_ten = result.getString(3);
					 tbModel.addRow(new Object[] {ngayOrder,ho_ten,id_item,id_order,ten_mon,so_luong,discount,tong });	 
				 }while(result.next());	 			 }
		
		
	}
}
