package Model.BEAN;

import java.util.Date;

public class AdminDashboard {
	private String hoTen;
	private String soDT;
	private String diaChi;
	private long maHoaDon;
	private long tongTien;
	private Date ngayMua;
	private boolean daMua;

	public AdminDashboard(String hoTen, String soDT, String diaChi, long maHoaDon, long tongTien, Date ngayMua,
			boolean daMua) {
		super();
		this.hoTen = hoTen;
		this.soDT = soDT;
		this.diaChi = diaChi;
		this.maHoaDon = maHoaDon;
		this.tongTien = tongTien;
		this.ngayMua = ngayMua;
		this.daMua = daMua;
	}

	public AdminDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public long getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(long maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public long getTongTien() {
		return tongTien;
	}

	public void setTongTien(long tongTien) {
		this.tongTien = tongTien;
	}

	public Date getNgayMua() {
		return ngayMua;
	}

	public void setNgayMua(Date ngayMua) {
		this.ngayMua = ngayMua;
	}

	public boolean isDaMua() {
		return daMua;
	}

	public void setDaMua(boolean daMua) {
		this.daMua = daMua;
	}
}
