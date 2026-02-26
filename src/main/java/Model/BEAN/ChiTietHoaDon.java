package Model.BEAN;

public class ChiTietHoaDon {
	private long maChiTietHD;
	private String maSach;
	private int soLuongMua;
	private long maHoaDon;
	private boolean daMua;

	public ChiTietHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietHoaDon(long maChiTietHD, String maSach, int soLuongMua, long maHoaDon, boolean daMua) {
		super();
		this.maChiTietHD = maChiTietHD;
		this.maSach = maSach;
		this.soLuongMua = soLuongMua;
		this.maHoaDon = maHoaDon;
		this.daMua = daMua;
	}

	public long getMaChiTietHD() {
		return maChiTietHD;
	}

	public void setMaChiTietHD(long maChiTietHD) {
		this.maChiTietHD = maChiTietHD;
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public int getSoLuongMua() {
		return soLuongMua;
	}

	public void setSoLuongMua(int soLuongMua) {
		this.soLuongMua = soLuongMua;
	}

	public long getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(long maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public boolean isDaMua() {
		return daMua;
	}

	public void setDaMua(boolean daMua) {
		this.daMua = daMua;
	}

}
