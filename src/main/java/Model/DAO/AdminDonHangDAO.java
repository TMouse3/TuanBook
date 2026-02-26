package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.BEAN.XacNhanHoaDon;
import Model.BEAN.ChiTietDonHangAdmin;

public class AdminDonHangDAO {
	// Lấy danh sách hóa đơn CHƯA mua (damua = 0)
	public ArrayList<XacNhanHoaDon> getDsXacNhan() throws Exception {
		ArrayList<XacNhanHoaDon> ds = new ArrayList<>();
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "SELECT * FROM XacNhanHoaDon ORDER BY MaHoaDon DESC";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(new XacNhanHoaDon(rs.getLong("MaHoaDon"), rs.getString("hoten"), rs.getDate("NgayMua"),
					rs.getLong("TongSoLuong"), rs.getLong("TongThanhTien"), false));
		}
		rs.close();
		kn.DongKetNoi();
		return ds;
	}

	// Lấy danh sách chi tiết của 1 hóa đơn
	public ArrayList<ChiTietDonHangAdmin> getChiTiet(long maHoaDon) throws Exception {
		ArrayList<ChiTietDonHangAdmin> ds = new ArrayList<>();
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "SELECT * FROM XacNhanChiTietHoaDon WHERE MaHoaDon = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, maHoaDon);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(new ChiTietDonHangAdmin(rs.getLong("MaChiTietHD"), rs.getLong("MaHoaDon"), rs.getString("tensach"),
					rs.getLong("gia"), rs.getInt("SoLuongMua"), rs.getLong("ThanhTien"), rs.getDate("NgayMua"),
					rs.getBoolean("DaMua")));
		}
		rs.close();
		kn.DongKetNoi();
		return ds;
	}

	// Xác nhận 1 hóa đơn (damua = 1)
	public void XacNhanHoaDon(long mhd) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "UPDATE hoadon SET damua = 1 WHERE MaHoaDon = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mhd);
		cmd.executeUpdate();
		// Cập nhật luôn các chi tiết con thành đã mua
		String sql2 = "UPDATE ChiTietHoaDon SET DaMua = 1 WHERE MaHoaDon = ?";
		PreparedStatement cmd2 = kn.cn.prepareStatement(sql2);
		cmd2.setLong(1, mhd);
		cmd2.executeUpdate();
		kn.DongKetNoi();
	}

	// Xác nhận chi tiết hóa đơn
	public void XacNhanChiTiet(long mact) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "UPDATE ChiTietHoaDon SET DaMua = 1 WHERE MaChiTietHD = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mact);
		cmd.executeUpdate();
		kn.DongKetNoi();
	}

	// Cập nhật trạng thái hóa đơn thành đã mua
	public void CapNhatTrangThaiHoaDon(long mhd) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "UPDATE hoadon SET damua = 1 WHERE MaHoaDon = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mhd);
		cmd.executeUpdate();
		kn.DongKetNoi();
	}

	// Lịch sử: Lấy danh sách đã xác nhận (damua của hóa đơn và chi tiết đều = 1
	// không sẽ sai số lượng và tổng tiền)
	public ArrayList<XacNhanHoaDon> getLichSu() throws Exception {
		ArrayList<XacNhanHoaDon> ds = new ArrayList<>();
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "SELECT * FROM LichSuHoaDon ORDER BY MaHoaDon DESC"; // View LichSuHoaDon filter damua=1 va daMua=1
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(new XacNhanHoaDon(rs.getLong("MaHoaDon"), rs.getString("hoten"), rs.getDate("NgayMua"),
					rs.getLong("TongSoLuong"), rs.getLong("TongThanhTien"), true));
		}
		rs.close();
		kn.DongKetNoi();
		return ds;
	}

	// Danh sách chi tiết lịch sử của 1 hóa đơn đã xác nhận
	public ArrayList<ChiTietDonHangAdmin> getLichSuChiTiet(long mhd) throws Exception {
		ArrayList<ChiTietDonHangAdmin> ds = new ArrayList<>();
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "SELECT * FROM LichSuChiTietHoaDon WHERE MaHoaDon = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mhd);
		ResultSet rs = cmd.executeQuery();
		while (rs.next()) {
			ds.add(new ChiTietDonHangAdmin(rs.getLong("MaChiTietHD"), rs.getLong("MaHoaDon"), rs.getString("tensach"),
					rs.getLong("gia"), rs.getInt("SoLuongMua"), rs.getLong("ThanhTien"), rs.getDate("NgayMua"), true));
		}
		rs.close();
		kn.DongKetNoi();
		return ds;
	}

	// Lấy thông tin 1 hóa đơn chờ xác nhận
	public XacNhanHoaDon getHoaDonXacNhan(long mhd) throws Exception {
		XacNhanHoaDon hd = null;
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "SELECT * FROM XacNhanHoaDon WHERE MaHoaDon = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mhd);
		ResultSet rs = cmd.executeQuery();
		if (rs.next()) {
			hd = new XacNhanHoaDon(rs.getLong("MaHoaDon"), rs.getString("hoten"), rs.getDate("NgayMua"),
					rs.getLong("TongSoLuong"), rs.getLong("TongThanhTien"), false);
		}
		rs.close();
		kn.DongKetNoi();
		return hd;
	}

	// Lấy thông tin 1 hóa đơn lịch sử
	public XacNhanHoaDon getHoaDonLichSu(long mhd) throws Exception {
		XacNhanHoaDon hd = null;
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "SELECT * FROM LichSuHoaDon WHERE MaHoaDon = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setLong(1, mhd);
		ResultSet rs = cmd.executeQuery();
		if (rs.next()) {
			hd = new XacNhanHoaDon(rs.getLong("MaHoaDon"), rs.getString("hoten"), rs.getDate("NgayMua"),
					rs.getLong("TongSoLuong"), rs.getLong("TongThanhTien"), true);
		}
		rs.close();
		kn.DongKetNoi();
		return hd;
	}

	// Nếu tất cả chi tiết đều đã mua thì cập nhật hóa đơn cha thành đã mua
	public boolean KiemTraVaCapNhatHoaDon(long mhd) throws Exception {
		boolean check = false;
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		// Đếm số lượng chi tiết chưa mua của hóa đơn này
		String sqlCount = "SELECT 1 FROM ChiTietHoaDon WHERE MaHoaDon = ? AND DaMua = 0";
		PreparedStatement cmdCount = kn.cn.prepareStatement(sqlCount);
		cmdCount.setLong(1, mhd);
		ResultSet rs = cmdCount.executeQuery();

		if (!rs.next()) {
			String sqlUpdate = "UPDATE hoadon SET damua = 1 WHERE MaHoaDon = ?";
			PreparedStatement cmdUpdate = kn.cn.prepareStatement(sqlUpdate);
			cmdUpdate.setLong(1, mhd);
			cmdUpdate.executeUpdate();
			check = true;

		}
		rs.close();
		kn.DongKetNoi();
		return check;
	}
}