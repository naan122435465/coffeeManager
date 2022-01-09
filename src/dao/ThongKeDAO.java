package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

public class ThongKeDAO extends BaseDAO {
	
	public ThongKeDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public int doanhthu (int thang, int nam ) throws SQLException {
		
		CallableStatement stat = conn.prepareCall("{call doanh_thu_thang (? ,?, ?   )}");
   		stat.setInt(1, thang);
   		stat.setInt(2, nam);
   		stat.registerOutParameter(3, java.sql.Types.INTEGER);
   		stat.executeUpdate();
   		int dt = stat.getInt(3);
   		stat.close();
   		return dt;
		
	}
	public int chiphi (int thang, int nam) throws SQLException {
		 ;
		CallableStatement stat = conn.prepareCall("{call chi_phi_thang (? ,?, ?)}");
		stat.setInt(1, thang);
		stat.setInt(2, nam);
		stat.registerOutParameter(3, java.sql.Types.INTEGER);
		stat.executeUpdate();;
		int cp = stat.getInt(3);
		stat.close();
		return cp;
	}
	public int loiNhuan (int thang, int nam) throws SQLException {
		;
		CallableStatement stat = conn.prepareCall("{call ln_thang (? ,?, ?)}");
		stat.setInt(1, thang);
		stat.setInt(2, nam);
		stat.registerOutParameter(3, java.sql.Types.INTEGER);
		stat.executeUpdate();;
		int ln = stat.getInt(3);
		stat.close();
		return ln;
	}

}
