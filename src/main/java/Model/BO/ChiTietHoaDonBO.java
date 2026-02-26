package Model.BO;

import java.util.ArrayList;

import Model.BEAN.ChiTietHoaDon;
import Model.BEAN.GioHang;
import Model.DAO.ChiTietHoaDonDAO;

public class ChiTietHoaDonBO {
	ChiTietHoaDonDAO cthdDAO = new ChiTietHoaDonDAO();

	// Thêm chi tiết từ giỏ hàng vào hóa đơn
	public void themChiTietTuGio(ArrayList<GioHang> ds, long maHD) throws Exception {
		for (GioHang g : ds) {
			cthdDAO.themChiTiet(g.getMasach(), g.getSoluong(), maHD);
		}
	}

	// Cập nhật số lượng trong chi tiết hóa đơn
	public void updateSoLuong(long maChiTiet, int soLuong) throws Exception {
		cthdDAO.updateSoLuong(maChiTiet, soLuong);
	}
	
	// Xóa chi tiết thừa (không mua trong hóa đơn đã mua)
	public void XoaChiTietThua() throws Exception {
		cthdDAO.XoaChiTietThua();
	}
	
	// Lấy chi tiết theo mã hóa đơn
	public ArrayList<ChiTietHoaDon> getChiTietTheoMaHoaDon(long maHoaDon) throws Exception {
		return cthdDAO.getChiTietTheoMaHoaDon(maHoaDon);
	}
	
	// Xóa chi tiết thừa theo mã hóa đơn (không mua trong hóa đơn đã mua)
	public void XoaChiTietThuaTheoMaHD(long maHoaDon) throws Exception {
	    cthdDAO.XoaChiTietThuaTheoMaHD(maHoaDon);
	}
}
