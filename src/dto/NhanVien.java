package dto;

import java.time.LocalDate;

public class NhanVien {

	private int id;
	private String Hoten; 
	private LocalDate ngaySinh;
	private String chuVu;
	private int luong;
	public NhanVien(String hoten2, LocalDate ngaySinh2, String chuVu2, int luong2 ) {
		this.Hoten = hoten2;
		this.ngaySinh =ngaySinh2;
		this.chuVu =chuVu2;
		this.luong =luong2;
		
		// TODO Auto-generated constructor stub
	}
	public NhanVien(int id2, String hoten2, LocalDate ngaySinh2, String chuVu2, int luong2 ) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.Hoten = hoten2;
		this.ngaySinh =ngaySinh2;
		this.chuVu =chuVu2;
		this.luong =luong2;
	
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHoten() {
		return Hoten;
	}
	public void setHoten(String hoten) {
		Hoten = hoten;
	}
	public LocalDate getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getChuVu() {
		return chuVu;
	}
	public void setChuVu(String chuVu) {
		this.chuVu = chuVu;
	}
	public int getLuong() {
		return luong;
	}
	public void setLuong(int luong) {
		this.luong = luong;
	}
	
	
}
