package Model.BO;

import java.util.ArrayList;

import Model.BEAN.KhachHang;
import Model.DAO.KhachHangDAO;

public class KhachHangBO {
	KhachHangDAO khDAO = new KhachHangDAO();
	public ArrayList<KhachHang> getKhachHang() throws Exception {
		return khDAO.getKhachHang();
	}
	
	public KhachHang KiemTraDangNhap(String tendn, String matkhau) throws Exception {
		return khDAO.KiemTraDangNhap(tendn, matkhau);
	}
	
	public boolean dangKyKhachHang(KhachHang kh) throws Exception {
        int result = khDAO.themKhachHang(kh);
        return result > 0;
    }
}
