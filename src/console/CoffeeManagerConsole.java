package console;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import utilities.SimpleDataSource;

public class CoffeeManagerConsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SimpleDataSource.init("database.properties");
			try {
				Connection conn = SimpleDataSource.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
