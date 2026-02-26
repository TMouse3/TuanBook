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

/**
 * Servlet implementation class AdminLichSuChiTietDonHangController
 */
@WebServlet("/AdminLichSuChiTietDonHangController")
public class AdminLichSuChiTietDonHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLichSuChiTietDonHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("admin") == null) {
			response.sendRedirect("AdminDangNhapController"); return;
		}

		try {
			String mhd = request.getParameter("mhd");
			if(mhd != null) {
				AdminDonHangBO adbo = new AdminDonHangBO();
				
				XacNhanHoaDon hoaDon = adbo.getHoaDonLichSu(Long.parseLong(mhd));
				//Kiểm tra cho an toàn
				if (hoaDon == null) {
					response.sendRedirect("AdminLichSuDonHangController");
					return;
				}
				
				ArrayList<ChiTietDonHangAdmin> ds = adbo.getLichSuChiTiet(Long.parseLong(mhd));
				
				request.setAttribute("hoaDon", hoaDon);
				request.setAttribute("dsChiTietLichSu", ds);
				request.setAttribute("mhd", mhd);
				request.getRequestDispatcher("AdminLichSuChiTietDonHang.jsp").forward(request, response);
			} else {
				response.sendRedirect("AdminLichSuDonHangController");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
