package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.BEAN.sach;

public class sachDAO {
	public ArrayList<sach> getSach() throws Exception {
		ArrayList<sach> ds = new ArrayList<sach>();
		try {
			KetNoiDB kn = new KetNoiDB();
			kn.KetNoi();
			String sql = "SELECT masach, tensach, tacgia, soluong, gia, anh, maloai FROM sach";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				String masach = rs.getString("masach");
				String tensach = rs.getString("tensach");
				String tacgia = rs.getString("tacgia");
				long soluong = rs.getLong("soluong");
				long gia = rs.getLong("gia");
				String anh = rs.getString("anh");
				String maloai = rs.getString("maloai");

				ds.add(new sach(masach, tensach, tacgia, soluong, gia, anh, maloai));
			}
			rs.close();
			kn.DongKetNoi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	public int ThemSach(sach s) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "INSERT INTO sach(masach, tensach, tacgia, soluong, gia, anh, maloai) VALUES(?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, s.getMasach());
		cmd.setString(2, s.getTensach());
		cmd.setString(3, s.getTacgia());
		cmd.setLong(4, s.getSoluong());
		cmd.setLong(5, s.getGia());
		cmd.setString(6, s.getAnh());
		cmd.setString(7, s.getMaloai());
		int kq = cmd.executeUpdate();
		kn.DongKetNoi();
		return kq;
	}

	public int XoaSach(String ms) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "DELETE FROM sach WHERE masach=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, ms);
		int kq = cmd.executeUpdate();
		kn.DongKetNoi();
		return kq;
	}

	public int SuaSach(sach s) throws Exception {
		KetNoiDB kn = new KetNoiDB();
		kn.KetNoi();
		String sql = "UPDATE sach SET tensach=?, tacgia=?, soluong=?, gia=?, anh=?, maloai=? WHERE masach=?";
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, s.getTensach());
		cmd.setString(2, s.getTacgia());
		cmd.setLong(3, s.getSoluong());
		cmd.setLong(4, s.getGia());
		cmd.setString(5, s.getAnh());
		cmd.setString(6, s.getMaloai());
		cmd.setString(7, s.getMasach());
		int kq = cmd.executeUpdate();
		kn.DongKetNoi();
		return kq;
	}
	
	public sach getSachTheoMa(String maSach) throws Exception {
	    KetNoiDB kn = new KetNoiDB();
	    kn.KetNoi();
	    String sql = "SELECT masach, tensach, tacgia, soluong, gia, anh, maloai FROM sach WHERE masach = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setString(1, maSach);
	    ResultSet rs = cmd.executeQuery();
	    sach s = null;
	    if (rs.next()) {
	        String tensach = rs.getString("tensach");
	        String tacgia = rs.getString("tacgia");
	        long soluong = rs.getLong("soluong");
	        long gia = rs.getLong("gia");
	        String anh = rs.getString("anh");
	        String maloai = rs.getString("maloai");
	        s = new sach(maSach, tensach, tacgia, soluong, gia, anh, maloai);
	    }
	    rs.close();
	    kn.DongKetNoi();
	    return s;
	}
	
	public void truSoLuong(String maSach, int soLuongMua) throws Exception {
	    KetNoiDB kn = new KetNoiDB();
	    kn.KetNoi();
	    String sql = "UPDATE sach SET soluong = soluong - ? WHERE masach = ?";
	    PreparedStatement cmd = kn.cn.prepareStatement(sql);
	    cmd.setLong(1, soLuongMua);
	    cmd.setString(2, maSach);
	    cmd.executeUpdate();
	    kn.DongKetNoi();
	}
}
