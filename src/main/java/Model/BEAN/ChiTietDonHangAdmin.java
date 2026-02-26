package Model.BEAN;

import java.util.Date;

public class ChiTietDonHangAdmin {
	private long maChiTietHD;
	private long maHoaDon;
	private String tenSach;
	private long gia;
	private int soLuongMua;
	private long thanhTien;
	private Date ngayMua;
	private boolean daMua;

	public ChiTietDonHangAdmin(long maChiTietHD, long maHoaDon, String tenSach, long gia, int soLuongMua,
			long thanhTien, Date ngayMua, boolean daMua) {
		this.maChiTietHD = maChiTietHD;
		this.maHoaDon = maHoaDon;
		this.tenSach = tenSach;
		this.gia = gia;
		this.soLuongMua = soLuongMua;
		this.thanhTien = thanhTien;
		this.ngayMua = ngayMua;
		this.daMua = daMua;
	}

	public ChiTietDonHangAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getMaChiTietHD() {
		return maChiTietHD;
	}

	public void setMaChiTietHD(long maChiTietHD) {
		this.maChiTietHD = maChiTietHD;
	}

	public long getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(long maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public long getGia() {
		return gia;
	}

	public void setGia(long gia) {
		this.gia = gia;
	}

	public int getSoLuongMua() {
		return soLuongMua;
	}

	public void setSoLuongMua(int soLuongMua) {
		this.soLuongMua = soLuongMua;
	}

	public long getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(long thanhTien) {
		this.thanhTien = thanhTien;
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