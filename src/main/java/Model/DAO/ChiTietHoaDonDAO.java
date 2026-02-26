package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.BEAN.ChiTietHoaDon;

public class ChiTietHoaDonDAO {
	// Thêm chi tiết hóa đơn vào DB
	public void themChiTiet(String maSach, int soLuong, long maHoaDon) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "insert into ChiTietHoaDon(MaSach, SoLuongMua, MaHoaDon, DaMua) values (?, ?, ?, 0)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, maSach);
		cmd.setInt(2, soLuong);
		cmd.setLong(3, maHoaDon);
		cmd.executeUpdate();
		kn.DongKetNoi();
	}

	// Cập nhật số lượng chi tiết vào DB
	public void updateSoLuong(long maChiTiet, int soLuong) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "UPDATE ChiTietHoaDon SET SoLuongMua = ? WHERE MaChiTietHD = ?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setInt(1, soLuong);
		cmd.setLong(2, maChiTiet);
		cmd.executeUpdate();
		kn.DongKetNoi();
	}
	// Xóa chi tiết thừa (không mua trong hóa đơn đã mua)
	public void XoaChiTietThua() throws Exception {
		 KetNoiDB kn = new KetNoiDB();
		 kn.KetNoi();
		 String sql = "DELETE ChiTietHoaDon "
		 		+ "FROM ChiTietHoaDon JOIN hoadon ON ChiTietHoaDon.MaHoaDon = hoadon.MaHoaDon "
		 		+ "WHERE ChiTietHoaDon.DaMua = 0 AND hoadon.damua = 1";
		 PreparedStatement cmd = kn.cn.prepareStatement(sql);
		 cmd.executeUpdate();
		 kn.DongKetNoi();
	 }
	
	// Xóa chi tiết thừa theo mã hóa đơn (không mua trong hóa đơn đã mua)
	public void XoaChiTietThuaTheoMaHD(long maHoaDon) throws Exception {
	    KetNoiDB kn = new KetNoiDB();
	    kn.KetNoi();
	    String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ? AND DaMua = 0";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, maHoaDon);
	    cmd.executeUpdate();
	    kn.DongKetNoi();
	}
	
	// Lấy chi tiết theo mã hóa đơn
	public ArrayList<ChiTietHoaDon> getChiTietTheoMaHoaDon(long maHoaDon) throws Exception {
	    ArrayList<ChiTietHoaDon> ds = new ArrayList<>();
	    KetNoiDB kn = new KetNoiDB();
	    kn.KetNoi();
	    String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon = ? AND DaMua = 1";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, maHoaDon);
	    ResultSet rs = cmd.executeQuery();
	    while (rs.next()) {
	        long maChiTietHD = rs.getLong("MaChiTietHD");
	        String maSach = rs.getString("MaSach");
	        int soLuongMua = rs.getInt("SoLuongMua");
	        ds.add(new ChiTietHoaDon(maChiTietHD, maSach, soLuongMua, maHoaDon, true));
	    }
	    rs.close();
	    kn.DongKetNoi();
	    return ds;
	}
}
