package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.KhachHang;
import Model.BO.GioHangBO;

/**
 * Servlet implementation class DatHangController
 */
@WebServlet("/DatHangController")
public class DatHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		KhachHang kh = (KhachHang) session.getAttribute("user");
		
		if (kh == null) {
			request.getRequestDispatcher("DangNhap.jsp").forward(request, response);
		} else {
			String masach = request.getParameter("ms");
			String tensach = request.getParameter("ts");
			long gia = Long.parseLong(request.getParameter("g"));
			String anh = request.getParameter("anh");
			int soluong = Integer.parseInt(request.getParameter("sl"));

			// Lấy giỏ hàng từ session
			GioHangBO ghbo = (GioHangBO) session.getAttribute("giohangbo");
			if (ghbo == null) {
				ghbo = new GioHangBO();
			}

			ghbo.themHang(masach, tensach, soluong, gia, anh);
			session.setAttribute("giohangbo", ghbo);

			response.sendRedirect("TrangChuController?dathem=1");
//			request.getRequestDispatcher("TrangChu.jsp?dathem=1").forward(request, response);
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
