package Model.BO;

import java.util.ArrayList;
import java.util.Iterator;
import Model.BEAN.GioHang;

public class GioHangBO {
    private ArrayList<GioHang> dsGioHang = new ArrayList<>();

    // Thêm hàng vào giỏ
    public void themHang(String masach, String tensach, int soluong, long gia, String anh) {
        // Nếu đã có sách này rồi thì tăng số lượng
        for (GioHang gh : dsGioHang) {
            if (gh.getMasach().trim().toLowerCase().equals(masach.trim().toLowerCase())) {
                gh.setSoluong(gh.getSoluong() + soluong);
                gh.setThanhtien(gh.getSoluong() * gh.getGia());
                return;
            }
        }
        // Nếu chưa có, thêm mới
        dsGioHang.add(new GioHang(masach, tensach, soluong, gia, anh));
    }

    // Xóa hàng khỏi giỏ
    public void xoaHang(String masach) {
        Iterator<GioHang> it = dsGioHang.iterator();
        while (it.hasNext()) {
            GioHang gh = it.next();
            if (gh.getMasach().trim().toLowerCase().equals(masach.trim().toLowerCase())) {
                it.remove();
                break;
            }
        }
    }

    // Sửa số lượng hàng
    public void suaHang(String masach, int soluongMoi) {
        for (GioHang gh : dsGioHang) {
            if (gh.getMasach().trim().toLowerCase().equals(masach.trim().toLowerCase())) {
                gh.setSoluong(soluongMoi);
                gh.setThanhtien(gh.getSoluong() * gh.getGia());
                break;
            }
        }
    }

    // Tổng tiền giỏ hàng
    public long tongTien() {
        long tong = 0;
        for (GioHang gh : dsGioHang) {
            tong += gh.getThanhtien();
        }
        return tong;
    }

    // Lấy danh sách giỏ
    public ArrayList<GioHang> getDsGioHang() {
        return dsGioHang;
    }
}
