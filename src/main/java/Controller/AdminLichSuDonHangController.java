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
 * Servlet implementation class AdminLichSuDonHangController
 */
@WebServlet("/AdminLichSuDonHangController")
public class AdminLichSuDonHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLichSuDonHangController() {
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
			AdminDonHangBO adbo = new AdminDonHangBO();
			// Lấy danh sách các hóa đơn đã mua (damua=1)
			ArrayList<XacNhanHoaDon> ds = adbo.getLichSu(); 
			request.setAttribute("dsLichSu", ds);
			request.getRequestDispatcher("AdminLichSuDonHang.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
