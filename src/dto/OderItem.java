package dto;

public class OderItem {

	private int id;
	private int idOder;
	private int tenMon;
	private int soLuong;
	private double discount;
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
	
	public int getTenMon() {
		return tenMon;
	}
	public void setTenMon(int tenMon) {
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
}
