package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.BEAN.loai;

public class loaiDAO {
	public ArrayList<loai> getLoai() throws Exception {
		ArrayList<loai> ds = new ArrayList<loai>();
		try {
			KetNoiDB kn = new KetNoiDB();
			kn.KetNoi();
			String sql = "SELECT maloai, tenloai FROM loai";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				String maloai = rs.getString("maloai");
				String tenloai = rs.getString("tenloai");
				ds.add(new loai(maloai, tenloai));
			}
			rs.close();
			kn.DongKetNoi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	public int Them(String maloai, String tenloai) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "INSERT INTO loai(maloai, tenloai) VALUES(?, ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, maloai);
		cmd.setString(2, tenloai);
		int kq = cmd.executeUpdate();
		kn.DongKetNoi();
		return kq;
	}

	public int Sua(String maloai, String tenloai) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "UPDATE loai SET tenloai=? WHERE maloai=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, tenloai);
		cmd.setString(2, maloai);
		int kq = cmd.executeUpdate();
		kn.DongKetNoi();
		return kq;
	}

	public int Xoa(String maloai) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		// Lưu ý: Cần xử lý ràng buộc khóa ngoại bên bảng Sách trước nếu có
		String sql = "DELETE FROM loai WHERE maloai=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, maloai);
		int kq = cmd.executeUpdate();
		kn.DongKetNoi();
		return kq;
	}
}
