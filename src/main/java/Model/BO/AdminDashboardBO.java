package Model.BO;

import java.util.ArrayList;
import Model.BEAN.AdminDashboard;
import Model.DAO.AdminDashboardDAO;

public class AdminDashboardBO {
    AdminDashboardDAO dao = new AdminDashboardDAO();

    public ArrayList<AdminDashboard> getDanhSachDonHangHomNay(boolean daMua) throws Exception {
        return dao.getDanhSachDonHangHomNay(daMua);
    }

    public long getTongDoanhThu(String loaiThoiGian) throws Exception {
        return dao.getTongDoanhThu(loaiThoiGian);
    }
    
    public ArrayList<Long> getDoanhThuTungThang(int nam) throws Exception {
        return dao.getDoanhThuTungThang(nam);
    }

    public ArrayList<Integer> getDanhSachNam() throws Exception {
        return dao.getDanhSachNam();
    }
}