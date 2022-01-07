package dto;

public class Menu {
	private int id;
	
	private String tenMon;
	private int gia;
	
	public Menu(String ten_mon, int gia2) {
		this.tenMon = ten_mon;
		this.gia =gia2;
		// TODO Auto-generated constructor stub
	}
	public Menu(int id2, String ten_mon, int gia2) {
		this.id = id2;
		this.tenMon = ten_mon;
		this.gia =gia2;
		// TODO Auto-generated constructor stub
	}
	public String getTenMon() {
		return tenMon;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
