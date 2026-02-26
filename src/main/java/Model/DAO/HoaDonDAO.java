package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HoaDonDAO {
	 public long themHoaDon(long maKh) throws Exception {
	        KetNoiDB kn = new KetNoiDB();
	        kn.KetNoi();
	        String sql = "insert into hoadon(makh, NgayMua, damua) values (?, GETDATE(), 0)";
	        PreparedStatement cmd = kn.cn.prepareStatement(sql);
	        cmd.setLong(1, maKh);
	        cmd.executeUpdate();

	        // Lấy mã hóa đơn vừa thêm
	        sql = "select max(MaHoaDon) as MaHoaDon from hoadon where makh = ?";
	        cmd = kn.cn.prepareStatement(sql);
	        cmd.setLong(1, maKh);
	        ResultSet rs = cmd.executeQuery();
	        long maHD = 0;
	        if (rs.next()) 
	        	maHD = rs.getLong("MaHoaDon");
	        rs.close();
	        kn.DongKetNoi();
	        return maHD;
	    }
	 
	 public void XoaHoaDonQua30Ngay() throws Exception {
	        KetNoiDB kn = new KetNoiDB();
	        kn.KetNoi();
	        String sql = "DELETE FROM hoadon WHERE damua = 0 and DATEDIFF(DAY, NgayMua, CAST(GETDATE() AS Date)) > 30";
	        PreparedStatement cmd = kn.cn.prepareStatement(sql);
	        cmd.executeUpdate();
	        kn.DongKetNoi();
	    }
}
