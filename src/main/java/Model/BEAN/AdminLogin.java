package Model.BEAN;

public class AdminLogin {
	private String tenDangNhap;
	private String matKhau;
	private boolean quyen;

	public AdminLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminLogin(String tenDangNhap, String matKhau, boolean quyen) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.quyen = quyen;
	}

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public boolean isQuyen() {
		return quyen;
	}

	public void setQuyen(boolean quyen) {
		this.quyen = quyen;
	}

}