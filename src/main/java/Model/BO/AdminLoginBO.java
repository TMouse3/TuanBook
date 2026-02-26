package Model.BO;

import Model.BEAN.AdminLogin;
import Model.DAO.AdminLoginDAO;

public class AdminLoginBO {
	AdminLoginDAO adminLoginDAO = new AdminLoginDAO();

	public AdminLogin KiemTraDangNhap(String user, String pass) {
		return adminLoginDAO.KiemTraDangNhap(user, pass);
	}
}
