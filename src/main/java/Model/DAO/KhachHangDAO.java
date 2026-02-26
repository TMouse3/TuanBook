package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.BEAN.KhachHang;

public class KhachHangDAO {
	public ArrayList<KhachHang> getKhachHang() throws Exception {
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();

		try {
			KetNoiDB kn = new KetNoiDB();
			kn.KetNoi();
			String sql = "SELECT * FROM khachhang";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			while (rs.next()) {
				long makh = rs.getLong("makh");
				String tenkh = rs.getString("hoten");
				String diachi = rs.getString("diachi");
				String sodt = rs.getString("sodt");
				String email = rs.getString("email");
				String tendn = rs.getString("tendn");
				String matkhau = rs.getString("pass");
				ds.add(new KhachHang(makh, tenkh, diachi, sodt, email, tendn, matkhau));
			}
			rs.close();
			kn.DongKetNoi();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ds;
	}

	public KhachHang KiemTraDangNhap(String tendn, String matkhau) throws Exception {
		KhachHang kh = null;

		try {
			KetNoiDB kn = new KetNoiDB();
			kn.KetNoi();
			String sql = "SELECT * FROM khachhang WHERE tendn = ? COLLATE Latin1_General_CS_AS AND pass = ?";
			PreparedStatement cmd = kn.cn.prepareStatement(sql);
			cmd.setString(1, tendn);
			cmd.setString(2, matkhau);
			ResultSet rs = cmd.executeQuery();
			if (rs.next()) {
				long makh = rs.getLong("makh");
				String tenkh = rs.getString("hoten");
				String diachi = rs.getString("diachi");
				String sodt = rs.getString("sodt");
				String email = rs.getString("email");
				kh = new KhachHang(makh, tenkh, diachi, sodt, email, tendn, matkhau);
			}

			rs.close();
			kn.DongKetNoi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kh;
	}
	
	 public int themKhachHang(KhachHang kh) throws Exception {
	        int kq = 0;
	        try {
	            KetNoiDB kn = new KetNoiDB();
	            kn.KetNoi();
	            String sql = "INSERT INTO KhachHang(hoten, diachi, sodt, email, tendn, pass) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement cmd = kn.cn.prepareStatement(sql);
	            cmd.setString(1, kh.getHoTen());
	            cmd.setString(2, kh.getDiaChi());
	            cmd.setString(3, kh.getSoDienThoai());
	            cmd.setString(4, kh.getEmail());
	            cmd.setString(5, kh.getTenDangNhap());
	            cmd.setString(6, kh.getMatKhau());
	            
	            kq = cmd.executeUpdate();
	            kn.DongKetNoi();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return kq;
	    }
}
