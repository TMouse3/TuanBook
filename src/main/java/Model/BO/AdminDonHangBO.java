package Model.BO;

import java.util.ArrayList;

import Model.BEAN.ChiTietDonHangAdmin;
import Model.BEAN.ChiTietHoaDon;
import Model.BEAN.XacNhanHoaDon;
import Model.DAO.AdminDonHangDAO;

public class AdminDonHangBO {
	AdminDonHangDAO dao = new AdminDonHangDAO();
	ChiTietHoaDonBO chiTietHoaDonBO = new ChiTietHoaDonBO();
	sachBO sachBO = new sachBO();

	// Lấy danh sách hóa đơn CHƯA mua (damua = 0)
	public ArrayList<XacNhanHoaDon> getDsXacNhan() throws Exception {
		return dao.getDsXacNhan();
	}

	// Lấy danh sách chi tiết của 1 hóa đơn
	public ArrayList<ChiTietDonHangAdmin> getChiTiet(long maHoaDon) throws Exception {
		return dao.getChiTiet(maHoaDon);
	}

	// Xác nhận 1 hóa đơn (damua = 1)
	public void XacNhanHoaDon(long mhd) throws Exception {
		dao.XacNhanHoaDon(mhd);
		
		ArrayList<ChiTietHoaDon> dsChiTiet = chiTietHoaDonBO.getChiTietTheoMaHoaDon(mhd);
		for (ChiTietHoaDon ct : dsChiTiet) {
			sachBO.truSoLuong(ct.getMaSach(), ct.getSoLuongMua());
		}
	}

	// Xác nhận chi tiết hóa đơn
	public void XacNhanChiTiet(long mact) throws Exception {
		dao.XacNhanChiTiet(mact);
	}
	
	// Cập nhật trạng thái hóa đơn thành đã mua
	public void CapNhatTrangThaiHoaDon(long mhd) throws Exception {
		dao.CapNhatTrangThaiHoaDon(mhd);
		
		chiTietHoaDonBO.XoaChiTietThuaTheoMaHD(mhd);
		ArrayList<ChiTietHoaDon> dsChiTiet = chiTietHoaDonBO.getChiTietTheoMaHoaDon(mhd);
		for (ChiTietHoaDon ct : dsChiTiet) {
			sachBO.truSoLuong(ct.getMaSach(), ct.getSoLuongMua());
		}
	}

	// Lịch sử: Lấy danh sách đã xác nhận (damua hóa đơn và chi tiết = 1)
	public ArrayList<XacNhanHoaDon> getLichSu() throws Exception {
		return dao.getLichSu();
	}

	// Danh sách lịch sử chi tiết của 1 hóa đơn
	public ArrayList<ChiTietDonHangAdmin> getLichSuChiTiet(long mhd) throws Exception {

		return dao.getLichSuChiTiet(mhd);
	}

	// Lấy thông tin hóa đơn xác nhận
	public XacNhanHoaDon getHoaDonXacNhan(long mhd) throws Exception {
		return dao.getHoaDonXacNhan(mhd);
	}

	// Lấy thông tin hóa đơn lịch sử
	public XacNhanHoaDon getHoaDonLichSu(long mhd) throws Exception {
		return dao.getHoaDonLichSu(mhd);
	}
	
	// Kiểm tra và cập nhật trạng thái hóa đơn
	public boolean KiemTraVaCapNhatHoaDon(long mhd) throws Exception {
		boolean check = dao.KiemTraVaCapNhatHoaDon(mhd);
		
		if (check)
		{
			ArrayList<ChiTietHoaDon> dsChiTiet = chiTietHoaDonBO.getChiTietTheoMaHoaDon(mhd);
			for (ChiTietHoaDon ct : dsChiTiet) {
				sachBO.truSoLuong(ct.getMaSach(), ct.getSoLuongMua());
			}
		}
		return check;
	}

}
