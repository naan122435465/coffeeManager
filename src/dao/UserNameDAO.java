package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.UserName;

public class UserNameDAO extends BaseDAO{

	
	public UserNameDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public void addUser(UserName user) {
		//add user function
		 String username = user.getUsername();
		 String password= user.getPassword();
		 int idNV = user.getIdNV();
		 int role = user.getRole();
		 CallableStatement stat;
		try {
			stat = conn.prepareCall("{call username_add(? ,? ,? ,?)");
			stat.setString(1, username);
			stat.setString(2, password);
			stat.setInt(3, idNV);
			stat.setInt(4, role);
			stat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	public void updateUser(UserName user) {
		 int p_id = user.getId();
		 String p_username = user.getUsername();
		 String p_password= user.getPassword();
		 int p_idNV = user.getIdNV();
		 int role = user.getRole();
		 try {
			 CallableStatement stat= conn.prepareCall("{call username_update(?, ? ,? ,? ,?)");
			 stat.setInt(1, p_id);
			 stat.setString(2, p_username);
			 stat.setString(3, p_password);
			 stat.setInt(4, p_idNV);
			 stat.setInt(5, role);
			 stat.executeUpdate();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
	public String deleteUser(int p_id) {
		 try {
			 CallableStatement stat= conn.prepareCall("{call username_delete(?)");
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
	public void getAllUser(JTable tb) {
		 try {
			
			 CallableStatement stat = conn.prepareCall("{call username_getAll()");
			 ResultSet result = stat.executeQuery();
			 DefaultTableModel tbModel = (DefaultTableModel)tb.getModel();
			 if(result.next()) {
				 do {
					 int id = result.getInt(1);
					 String username = result.getString(2);
					 String password = result.getString(3);
					 int idNV =result.getInt(4);
					 int role = result.getInt(5);
					 tbModel.addRow(new Object[] {id, username, password, idNV, role});	 
				 }while(result.next());
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
