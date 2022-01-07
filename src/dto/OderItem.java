package dto;

public class OderItem {

	private int id;
	private int idOder;
	private String tenMon;
	private int soLuong;
	private double discount;
	private int tong;
	public OderItem(int id_order, String ten_mon, int so_luong, int discount2) {
		// TODO Auto-generated constructor stub
		this.tenMon = ten_mon;
		this.soLuong = so_luong;
		this.discount = discount2;
		this.idOder = id_order;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdOder() {
		return idOder;
	}
	public void setIdOder(int idOder) {
		this.idOder = idOder;
	}
	
	public String getTenMon() {
		return tenMon;
	}
	public void setTenMon(String tenMon) {
		this.tenMon = tenMon;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getTong() {
		return tong;
	}
	public void setTong(int tong) {
		this.tong = tong;
	}
}
