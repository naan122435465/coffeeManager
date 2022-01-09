package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.Oders;



public class OdersDAO  extends BaseDAO{

	public OdersDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public Integer addOder(Oders ad) {
		
		int id = 0;
		 int idNV = ad.getIdNV();
		 String ngayOrder = ad.getNgayOrder().toString();
		 
		try {
			CallableStatement stat = conn.prepareCall("{call orders_add(? ,?, ?)}");
			stat.setInt(1, idNV);
			stat.setString(2, ngayOrder);
			stat.registerOutParameter(3, java.sql.Types.INTEGER);
			stat.executeUpdate();
			id = stat.getInt(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return id;
	}
	public void updateOder(Oders up) {
		 int p_id = up.getId();
		 int p_idNV = up.getIdNV();
		 String p_ngayOrder = up.getNgayOrder().toString();
		 try {
			 CallableStatement stat= conn.prepareCall("{call orders_update(?, ? ,? )}");
			 stat.setInt(1, p_id);
			 stat.setInt(2, p_idNV);
			 stat.setString(3, p_ngayOrder);
			 stat.executeUpdate();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
	public String deleteOder(int p_id) {
		 try {
			 CallableStatement stat= conn.prepareCall("{call orders_delete(?)}");
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
	public void getAllOder(JTable tb) {
		 try {
			 CallableStatement stat = conn.prepareCall("{call orders_all()}");
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
	
//	public void getOderByDate(LocalDate date, JTable tb) {
//		 String p_ngayOrder = date.toString();
//		 try {
//			 CallableStatement stat = conn.prepareCall("{call orders_getByDate(?)}");
//			 stat.setString(1, p_ngayOrder);
//			 ResultSet result = stat.executeQuery();
//			 DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
//			 if(result.next()) {
//				 do {
//					 int id = result.getInt(1);
//					 int idNV =result.getInt(2);
//					 LocalDate ngayOrder = LocalDate.parse(result.getString(3));
//					 tbModel.addRow(new Object[] {id, idNV,ngayOrder });	 
//				 }while(result.next());
//			 }
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void getOderByTime(LocalDate begin,LocalDate end, JTable tb) {
		String begin_date = begin.toString();
		String end_date = end.toString();
		 try {
			 CallableStatement stat = conn.prepareCall("{call orders_getByTime(? , ? )}");
			 stat.setString(1, begin_date);
			 stat.setString(2, end_date);
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

}
