package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class BaseDAO {

	public Connection conn;
	
	public BaseDAO( Connection conn) {
		this.conn = conn;
	}
	
}