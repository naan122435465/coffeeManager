package dto;

import java.time.LocalDate;

public class Oders {
	private int id;
	private int idNV;
	private LocalDate ngayOrder;
	public Oders(int idNV2, LocalDate ngay_or) {
		this.idNV = idNV2;
		this.ngayOrder = ngay_or;
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdNV() {
		return idNV;
	}
	public void setIdNV(int idNV) {
		this.idNV = idNV;
	}
	public LocalDate getNgayOrder() {
		return ngayOrder;
	}
	public void setNgayOrder(LocalDate ngayOrder) {
		this.ngayOrder = ngayOrder;
	}
	



}
