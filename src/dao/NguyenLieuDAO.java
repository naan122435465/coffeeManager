package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.NguyenLieu;



public class NguyenLieuDAO extends BaseDAO {

	public NguyenLieuDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public void addNguyenLieu(NguyenLieu ad) {
		String tenNL = ad.getTenNL();
		int gia = ad.getGia();
		double soLuong  = ad.getSoLuong();
		String ngayNhapDate = ad.getNgayNhap().toString();
		 
		try {
			CallableStatement stat = conn.prepareCall("{call nguyen_lieu_add(? ,? , ? , ? )}");
			stat.setString(1, tenNL);
			stat.setInt(2, gia);
			stat.setDouble(3, soLuong);
			stat.setString(4, ngayNhapDate);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateNguyenLieu(NguyenLieu up) {
		 int p_id = up.getId();
		 String p_tenNL = up.getTenNL();
		 int p_gia = up.getGia();
		 double p_soLuong  = up.getSoLuong();
		 String p_ngayNhapDate = up.getNgayNhap().toString();
			 
		 try {
			 CallableStatement stat= conn.prepareCall("{call nguyen_lieu_update(?, ? ,? , ? ,? )}");
			 stat.setInt(1, p_id);
			 stat.setString(2, p_tenNL);
			 stat.setInt(3, p_gia);
			 stat.setDouble(4, p_soLuong);
			 stat.setString(5, p_ngayNhapDate);
			 stat.executeUpdate();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
	public String deleteNguyenLieu(int p_id) {
		 try {
			 CallableStatement stat= conn.prepareCall("{call nguyen_lieu_delete(?)}");
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
	public void getAllNguyenLieu(JTable tb) {
		 try {
			 CallableStatement stat = conn.prepareCall("{call nguyen_lieu_all()}");
			 ResultSet result = stat.executeQuery();
			 DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
			 if(result.next()) {
				 do {
					 int id = result.getInt(1);
					 String tenNL = result.getString(2);
					 int gia =result.getInt(3);
					 int soLuong =result.getInt(4);
					 LocalDate ngayNhap = LocalDate.parse(result.getString(5));
					 tbModel.addRow(new Object[] {id, tenNL, gia ,soLuong , ngayNhap });	 
				 }while(result.next());
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
