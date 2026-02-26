package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.XacNhanHoaDon;
import Model.BO.AdminDonHangBO;

/**
 * Servlet implementation class AdminXacNhanDonHangController
 */
@WebServlet("/AdminXacNhanDonHangController")
public class AdminXacNhanDonHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminXacNhanDonHangController() {
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
			// Xử lý xác nhận nhiều
			String btn = request.getParameter("btnXacNhan");
			if ("xacnhan".equals(btn)) {
				// Lấy danh sách value của các checkbox có name="ckbMHD"
				String[] dsMHD = request.getParameterValues("ckbMHD");
				if (dsMHD != null) {
					for (String mhd : dsMHD) {
						adbo.XacNhanHoaDon(Long.parseLong(mhd));
					}
				}
				// Sau khi xử lý xong, load lại trang
				response.sendRedirect("AdminXacNhanDonHangController");
				return;
			}

			//Xác nhận 1 đơn hàng
			String action = request.getParameter("action");
			String id = request.getParameter("id");
			if ("xacnhan1".equals(action) && id != null) {
				adbo.XacNhanHoaDon(Long.parseLong(id));
				response.sendRedirect("AdminXacNhanDonHangController");
				return;
			}

			ArrayList<XacNhanHoaDon> ds = adbo.getDsXacNhan();
			request.setAttribute("dsDonHang", ds);
			request.getRequestDispatcher("AdminXacNhanDonHang.jsp").forward(request, response);
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
