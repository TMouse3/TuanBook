package Model.BEAN;

public class LichSuDatMua {
	private String hoTen;
	private String tenSach;
	private int soLuongMua;
	private long gia;
	private int daMua;
	private long thanhTien;
	private long makh;
	private long maChiTietHD;

	public LichSuDatMua() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LichSuDatMua(String hoTen, String tenSach, int soLuongMua, long gia, int daMua, long thanhTien, long makh, long maChiTietHD) {
		super();
		this.hoTen = hoTen;
		this.tenSach = tenSach;
		this.soLuongMua = soLuongMua;
		this.gia = gia;
		this.daMua = daMua;
		this.thanhTien = thanhTien;
		this.makh = makh;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public int getSoLuongMua() {
		return soLuongMua;
	}

	public void setSoLuongMua(int soLuongMua) {
		this.soLuongMua = soLuongMua;
	}

	public long getGia() {
		return gia;
	}

	public void setGia(long gia) {
		this.gia = gia;
	}

	public int getDaMua() {
		return daMua;
	}

	public void setDaMua(int daMua) {
		this.daMua = daMua;
	}

	public long getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(long thanhTien) {
		this.thanhTien = thanhTien;
	}

	public long getMakh() {
		return makh;
	}

	public void setMakh(long makh) {
		this.makh = makh;
	}
	
	public long getMaChiTietHD() {
		return maChiTietHD;
	}
	public void setMaChiTietHD(long maChiTietHD) {
		this.maChiTietHD = maChiTietHD;
	}

}
