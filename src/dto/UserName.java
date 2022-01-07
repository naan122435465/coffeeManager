package dto;

public class UserName {
	private int id;
	private String username;
	private String password;
	private int idNV;
	private int role;
	public UserName(String username2, String passWord2, int id_nv, int role2) {
		
		// TODO Auto-generated constructor stub
		this.username = username2;
		this.password = passWord2;
		this.idNV = id_nv;
		this.role = role2;
	}
	public UserName(int id2, String username2, String passWord2, int id_nv, int role2) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.username = username2;
		this.password = passWord2;
		this.idNV = id_nv;
		this.role = role2;
	}
	public UserName(String username2, String pass) {
		// TODO Auto-generated constructor stub
		this.username = username2;
		
		this.password = pass;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdNV() {
		return idNV;
	}
	public void setIdNV(int idNV) {
		this.idNV = idNV;
	}
	
	

}
