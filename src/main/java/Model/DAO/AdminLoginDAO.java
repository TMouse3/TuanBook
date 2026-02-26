package Model.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Model.BEAN.AdminLogin;

public class AdminLoginDAO {
    public AdminLogin KiemTraDangNhap(String user, String pass) {
    	AdminLogin admin = null;
        try {
            KetNoiDB kn = new KetNoiDB(); kn.KetNoi();
            // Kiểm tra bảng DangNhap, Quyen = 1 (True) là Admin
            String sql = "SELECT * FROM DangNhap WHERE TenDangNhap=? COLLATE Latin1_General_CS_AS AND MatKhau=? AND Quyen=1";
            PreparedStatement cmd = kn.cn.prepareStatement(sql);
            cmd.setString(1, user);
            cmd.setString(2, pass);
            ResultSet rs = cmd.executeQuery();
            if(rs.next()) {
                admin = new AdminLogin(user, pass, true);
            }
            rs.close();
            kn.DongKetNoi();
        } catch(Exception e) { e.printStackTrace(); }
        return admin;
    }
}