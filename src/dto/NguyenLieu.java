package dto;


import java.time.LocalDate;

public class NguyenLieu {
	private int id;
	private String tenNL;
	private int gia;
	private double soLuong;
	private LocalDate ngayNhap;
	public NguyenLieu(int id2, String tenNL2, int gia2, int so_luong, LocalDate ngay_nhap) {
		// TODO Auto-generated constructor stub
		this.id = id2;
		this.tenNL = tenNL2;
		this.gia =gia2;
		this.soLuong =so_luong;
		this.ngayNhap = ngay_nhap;
		
	}
	public NguyenLieu(String tenNL2, int gia2, int so_luong, LocalDate ngay_nhap) {
		// TODO Auto-generated constructor stub
		this.tenNL = tenNL2;
		this.gia =gia2;
		this.soLuong =so_luong;
		this.ngayNhap = ngay_nhap;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenNL() {
		return tenNL;
	}
	public void setTenNL(String tenNL) {
		this.tenNL = tenNL;
	}
	public int getGia() {
		return gia;
	}
	public void setGia(int gia) {
		this.gia = gia;
	}
	public double getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(double soLuong) {
		this.soLuong = soLuong;
	}
	public LocalDate getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(LocalDate ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

}
