package Model.BEAN;

import java.util.Date;

public class XacNhanHoaDon {
	private long maHoaDon;
	private String hoTen;
	private Date ngayMua;
	private long tongSoLuong;
	private long tongThanhTien;
	private boolean daMua;

	public XacNhanHoaDon(long maHoaDon, String hoTen, Date ngayMua, long tongSoLuong, long tongThanhTien,
			boolean daMua) {
		this.maHoaDon = maHoaDon;
		this.hoTen = hoTen;
		this.ngayMua = ngayMua;
		this.tongSoLuong = tongSoLuong;
		this.tongThanhTien = tongThanhTien;
		this.daMua = daMua;
	}

	public XacNhanHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(long maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public Date getNgayMua() {
		return ngayMua;
	}

	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}

	public long getTongSoLuong() {
		return tongSoLuong;
	}

	public void setTongSoLuong(long tongSoLuong) {
		this.tongSoLuong = tongSoLuong;
	}

	public long getTongThanhTien() {
		return tongThanhTien;
	}

	public void setTongThanhTien(long tongThanhTien) {
		this.tongThanhTien = tongThanhTien;
	}

	public boolean isDaMua() {
		return daMua;
	}

	public void setDaMua(boolean daMua) {
		this.daMua = daMua;
	}

}