package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.ChiTietDonHangAdmin;
import Model.BEAN.XacNhanHoaDon;
import Model.BO.AdminDonHangBO;
import Model.BO.ChiTietHoaDonBO;

/**
 * Servlet implementation class AdminXacNhanChiTietHoaDonController
 */
@WebServlet("/AdminXacNhanChiTietHoaDonController")
public class AdminXacNhanChiTietHoaDonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminXacNhanChiTietHoaDonController() {
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

		AdminDonHangBO adbo = new AdminDonHangBO();
		try {
			String mhdStr = request.getParameter("mhd");
			String action = request.getParameter("action");
			String btnHoanTat = request.getParameter("btnHoanTat");

			// Xác nhận hoàn tất đơn hàng
	        if ("hoantat".equals(btnHoanTat) && mhdStr != null) {
	            adbo.CapNhatTrangThaiHoaDon(Long.parseLong(mhdStr));
	            response.sendRedirect("AdminXacNhanDonHangController");
	            return;
	        }
			
			// Sửa số lượng
			String butsua = request.getParameter("butsua");
			if (butsua != null && mhdStr != null) {
				// butsua chứa mã chi tiết
				long mact = Long.parseLong(butsua);
				// Lấy số lượng mới từ input có name trùng với mã chi tiết
				String slStr = request.getParameter(butsua);
				if (slStr != null) {
					int slMoi = Integer.parseInt(slStr);
					if (slMoi > 0) {
						ChiTietHoaDonBO cthdbo = new ChiTietHoaDonBO();
						cthdbo.updateSoLuong(mact, slMoi);
					}
				}
				response.sendRedirect("AdminXacNhanChiTietHoaDonController?mhd=" + mhdStr);
				return;
			}

			// Xác nhận nhiều chi tiết
			String btn = request.getParameter("btnXacNhanChiTiet");
			if ("xacnhan".equals(btn) && mhdStr != null) {
				String[] dsCT = request.getParameterValues("ckbChiTiet");
				if (dsCT != null) {
					for (String mact : dsCT) {
						adbo.XacNhanChiTiet(Long.parseLong(mact));
					}
					adbo.CapNhatTrangThaiHoaDon(Long.parseLong(mhdStr));
				}
				response.sendRedirect("AdminXacNhanDonHangController");
				return;
			}

			// Xác nhận 1 chi tiết
			String mact = request.getParameter("mact");
			if ("xacnhan1".equals(action) && mact != null && mhdStr != null) {
				adbo.XacNhanChiTiet(Long.parseLong(mact));
				boolean check = adbo.KiemTraVaCapNhatHoaDon(Long.parseLong(mhdStr));
				if (check) {
					response.sendRedirect("AdminXacNhanDonHangController");
					return;
				}
				response.sendRedirect("AdminXacNhanChiTietHoaDonController?mhd=" + mhdStr);
				return;
			}

			// Hiển thị
			if (mhdStr != null) {
				long mhd = Long.parseLong(mhdStr);
				XacNhanHoaDon hd = adbo.getHoaDonXacNhan(mhd);
				
				// Nếu đã xác nhận hết chi tiết thì hóa đơn sẽ bay vào lịch sử
				if (hd == null) {
					hd = adbo.getHoaDonLichSu(mhd);
				}
				
				//Kiểm tra để an toàn thôi
				if(hd == null) {
					response.sendRedirect("AdminXacNhanDonHangController");
					return;
				}
				
				ArrayList<ChiTietDonHangAdmin> ds = adbo.getChiTiet(mhd);
				request.setAttribute("hoaDon", hd);
				request.setAttribute("dsChiTiet", ds);
				request.setAttribute("mhd", mhd);
				request.getRequestDispatcher("AdminXacNhanChiTietHoaDon.jsp").forward(request, response);
			} else {
				response.sendRedirect("AdminXacNhanDonHangController");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
