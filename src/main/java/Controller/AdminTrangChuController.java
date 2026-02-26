package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BO.ChiTietHoaDonBO;
import Model.BO.HoaDonBO;
import Model.BO.AdminDashboardBO;
import Model.BEAN.AdminDashboard;

/**
 * Servlet implementation class AdminTrangChuController
 */
@WebServlet("/AdminTrangChuController")
public class AdminTrangChuController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminTrangChuController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("admin") == null) {
			response.sendRedirect("AdminDangNhapController");
			return;
		}
		try {
			HoaDonBO hdbo = new HoaDonBO();
			hdbo.XoaHoaDonQua30Ngay();// Xóa hóa đơn quá 30 ngày chưa mua
			ChiTietHoaDonBO cthdbo = new ChiTietHoaDonBO();
			cthdbo.XoaChiTietThua(); // Xóa chi tiết thừa (không mua trong hóa đơn đã mua)
			
            AdminDashboardBO dashboardBO = new AdminDashboardBO();

            long dtNgay = dashboardBO.getTongDoanhThu("Ngay");
            long dtThang = dashboardBO.getTongDoanhThu("Thang");
            long dtTong = dashboardBO.getTongDoanhThu("TatCa");

            ArrayList<AdminDashboard> listChuaXacNhan = dashboardBO.getDanhSachDonHangHomNay(false); 
            ArrayList<AdminDashboard> listDaMua = dashboardBO.getDanhSachDonHangHomNay(true);

            // Xử lý năm được chọn
            String yearParam = request.getParameter("year");
            int selectedYear;
            
            if (yearParam != null && !yearParam.isEmpty()) {
                selectedYear = Integer.parseInt(yearParam); // Lấy năm từ dropdown
            } else {
                selectedYear = Calendar.getInstance().get(Calendar.YEAR); // Mặc định năm nay
            }

            // Lấy dữ liệu biểu đồ theo năm đó
            ArrayList<Long> listDoanhThuThang = dashboardBO.getDoanhThuTungThang(selectedYear);
            
            //Lấy danh sách các năm để hiển thị dropdown
            ArrayList<Integer> listYears = dashboardBO.getDanhSachNam();
            // Nếu list rỗng (chưa có đơn nào), thêm năm hiện tại vào để không bị lỗi view
            if(listYears.isEmpty()) listYears.add(selectedYear);

            request.setAttribute("listDoanhThuThang", listDoanhThuThang);
            request.setAttribute("listYears", listYears);
            request.setAttribute("selectedYear", selectedYear);
            
            request.setAttribute("dtNgay", dtNgay);
            request.setAttribute("dtThang", dtThang);
            request.setAttribute("dtTong", dtTong);
            request.setAttribute("listChuaXacNhan", listChuaXacNhan);
            request.setAttribute("listDaMua", listDaMua);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("AdminTrangChu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
