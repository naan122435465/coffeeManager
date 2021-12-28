package dto;

import java.time.LocalDate;

public class Oders {
	private int id;
	private int idNV;
	private LocalDate ngayOrder;
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
