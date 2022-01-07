package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.NhanVien;

public class NhanVienDAO extends BaseDAO {
	public NhanVienDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addNV(NhanVien ad) {
		//add user function
		 String ho_ten = ad.getHoten();
		 String ngay_sinh = ad.getNgaySinh().toString();	 
		 String chuc_vu= ad.getChuVu();
		 int luong = ad.getLuong();
		
		 CallableStatement stat;
		try {
			stat = conn.prepareCall("{call nhan_vien_add(? ,? ,? ,?)}");
			stat.setString(1, ho_ten);
			stat.setString(2,ngay_sinh);
			stat.setString(3, chuc_vu);
			stat.setInt(4, luong);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
	}
	public void UpdateNV(NhanVien ad) {
		 int p_id = ad.getId();
		 String p_hoTen = ad.getHoten();
		 String p_ngaySinh = ad.getNgaySinh().toString();
		 String p_chucVu= ad.getChuVu();
		 int p_luong = ad.getLuong();
	
		 try {
			 CallableStatement stat= conn.prepareCall("{call nhan_vien_update(?, ? ,? ,? ,?)}");
			 stat.setInt(1, p_id);
			 stat.setString(2, p_hoTen);
			 stat.setString(3,p_ngaySinh);
			 stat.setString(4, p_chucVu);
			 stat.setInt(5, p_luong);
			 stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String deleteNV(int p_id) {
		 try {
			 CallableStatement stat= conn.prepareCall("{call nhan_vien_delete(?)}");
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
	public void getAllNhanVien(JTable tab) {
		 try {
			 CallableStatement stat = conn.prepareCall("{call nhan_vien_all()}");
			 ResultSet result = stat.executeQuery();
			 DefaultTableModel tbModel = (DefaultTableModel) tab.getModel();
			 if(result.next()) {
				 do {
					 int id = result.getInt(1);
					 String hoTen = result.getString(2);
					 LocalDate ngaySinh =LocalDate.parse(result.getString(3));
					 String chucVu = result.getString(4);
					 int luong =result.getInt(5);
					 tbModel.addRow(new Object[] {id, hoTen, ngaySinh ,chucVu , luong });	 
				 }while(result.next());
			 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
