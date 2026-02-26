package Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.BEAN.AdminDashboard;

public class AdminDashboardDAO {

    // Lấy danh sách đơn hàng trong ngày hôm nay theo trạng thái
    public ArrayList<AdminDashboard> getDanhSachDonHangHomNay(boolean daMua) throws Exception {
        ArrayList<AdminDashboard> ds = new ArrayList<AdminDashboard>();
        KetNoiDB kn = new KetNoiDB();
        kn.KetNoi();
        
        String sql = "SELECT * FROM AdminDashboard WHERE TrangThaiHD = ? AND NgayMua = CAST(GETDATE() AS DATE)";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setBoolean(1, daMua);
        
        ResultSet rs = cmd.executeQuery();
        while (rs.next()) {
            String hoTen = rs.getString("hoten");
            String soDT = rs.getString("sodt");
            String diaChi = rs.getString("diachi");
            long maHD = rs.getLong("MaHoaDon");
            long tongTien = rs.getLong("TongTien");
            java.util.Date ngayMua = rs.getDate("NgayMua");
            
            ds.add(new AdminDashboard(hoTen, soDT, diaChi, maHD, tongTien, ngayMua, daMua));
        }
        rs.close();
        kn.DongKetNoi();
        return ds;
    }

    // Tính tổng doanh thu theo thời gian
    public long getTongDoanhThu(String loaiThoiGian) throws Exception {
        long tong = 0;
        KetNoiDB kn = new KetNoiDB();
        kn.KetNoi();
        String sql = "";
        
        if (loaiThoiGian.equals("Ngay")) {
            sql = "SELECT SUM(TongTien) FROM AdminDashboard WHERE TrangThaiHD = 1 AND NgayMua = CAST(GETDATE() AS DATE)";
        } else if (loaiThoiGian.equals("Thang")) {
            sql = "SELECT SUM(TongTien) FROM AdminDashboard WHERE TrangThaiHD = 1 AND MONTH(NgayMua) = MONTH(GETDATE()) AND YEAR(NgayMua) = YEAR(GETDATE())";
        } else {
            sql = "SELECT SUM(TongTien) FROM AdminDashboard WHERE TrangThaiHD = 1";
        }
        
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();
        if (rs.next()) {
            tong = rs.getLong(1);
        }
        rs.close();
        kn.DongKetNoi();
        return tong;
    }
    
    // Hàm lấy doanh thu 12 tháng của một năm
    public ArrayList<Long> getDoanhThuTungThang(int nam) throws Exception {
        ArrayList<Long> listDoanhThu = new ArrayList<>();
        for (int i = 0; i < 12; i++) listDoanhThu.add(0L); // Khởi tạo 12 tháng bằng 0

        KetNoiDB kn = new KetNoiDB();
        kn.KetNoi();

        String sql = "SELECT Thang, TongDoanhThu FROM DoanhThuThang WHERE Nam = ?";
                     
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        cmd.setInt(1, nam);
        ResultSet rs = cmd.executeQuery();
        
        while (rs.next()) {
            int thang = rs.getInt("Thang");
            long tien = rs.getLong("TongDoanhThu");
            if (thang >= 1 && thang <= 12) {
                listDoanhThu.set(thang - 1, tien);
            }
        }
        rs.close();
        kn.DongKetNoi();
        return listDoanhThu;
    }

    //Hàm lấy danh sách các năm có hóa đơn
    public ArrayList<Integer> getDanhSachNam() throws Exception {
        ArrayList<Integer> listNam = new ArrayList<>();
        KetNoiDB kn = new KetNoiDB();
        kn.KetNoi();
        
        String sql = "SELECT DISTINCT YEAR(NgayMua) as Nam FROM hoadon ORDER BY Nam DESC";
        PreparedStatement cmd = kn.cn.prepareStatement(sql);
        ResultSet rs = cmd.executeQuery();
        
        while (rs.next()) {
            listNam.add(rs.getInt("Nam"));
        }
        rs.close();
        kn.DongKetNoi();
        return listNam;
    }
}