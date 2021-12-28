package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import dto.NhanVien;

public class NhanVienDAO extends BaseDAO {
	public NhanVienDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addNV(NhanVien ad) {
		//add user function
		 String hoTen = ad.getHoten();
		 String ngaySinh = ad.getNgaySinh().toString();
		 String chucVu= ad.getChuVu();
		 int luong = ad.getLuong();
		 int capDo = ad.getCapDo();
		 CallableStatement stat;
		try {
			stat = conn.prepareCall("{call nhan_vien_add(? ,? ,? ,? , ?)");
			stat.setString(1, hoTen);
			stat.setString(2,ngaySinh);
			stat.setString(3, chucVu);
			stat.setInt(4, luong);
			stat.setInt(5, capDo);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
	}
	public void UpdateNV(NhanVien ad) {
		 int p_id = ad.getId();
		 String hoTen = ad.getHoten();
		 String ngaySinh = ad.getNgaySinh().toString();
		 String chucVu= ad.getChuVu();
		 int luong = ad.getLuong();
		 int capDo = ad.getCapDo();
		 try {
			 CallableStatement stat= conn.prepareCall("{call nhan_vien_update(?, ? ,? ,? ,?, ?)");
			 stat.setInt(1, p_id);
			 stat.setString(2, hoTen);
			 stat.setString(3,ngaySinh);
			 stat.setString(4, chucVu);
			 stat.setInt(5, luong);
			 stat.setInt(6, capDo);
			 stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String deleteNV(int p_id) {
		 try {
			 CallableStatement stat= conn.prepareCall("{call nhan_vien_delete(?)");
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
}
