package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.BEAN.LichSuDatMua;

public class LichSuDatMuaDAO {
	public ArrayList<LichSuDatMua> getLichSuDatMua(long makh) throws Exception {
		ArrayList<LichSuDatMua> ds = new ArrayList<>();
		try {
			KetNoiDB kn = new KetNoiDB();
			kn.KetNoi();
			String sql = "SELECT * FROM LichSuDatMua WHERE makh = ? ORDER BY MaChiTietHD DESC";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setLong(1, makh);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				LichSuDatMua ls = new LichSuDatMua();
				ls.setHoTen(rs.getString("hoten"));
				ls.setTenSach(rs.getString("tensach"));
				ls.setSoLuongMua(rs.getInt("SoLuongMua"));
				ls.setGia(rs.getLong("gia"));
				ls.setDaMua(rs.getInt("DaMua"));
				ls.setThanhTien(rs.getLong("ThanhTien"));
				ls.setMakh(rs.getLong("makh"));
				ls.setMaChiTietHD(rs.getLong("MaChiTietHD"));

				ds.add(ls);
			}
			rs.close();
			kn.DongKetNoi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
}
