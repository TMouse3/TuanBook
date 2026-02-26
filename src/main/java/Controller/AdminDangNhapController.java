package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.AdminLogin;
import Model.BO.AdminLoginBO;
import nl.captcha.Captcha;

/**
 * Servlet implementation class AdminDangNhapController
 */
@WebServlet("/AdminDangNhapController")
public class AdminDangNhapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminDangNhapController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		HttpSession session = request.getSession();

		if (user != null && pass != null) {
			// Check Captcha nếu sai quá 3 lần
			int dem = 0;
			if (session.getAttribute("demAdmin") != null)
				dem = (int) session.getAttribute("demAdmin");

			if (dem >= 3) {
				Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
				String answer = request.getParameter("captcha");
				if (answer == null || !captcha.isCorrect(answer)) {
					request.setAttribute("thongbao", "Captcha sai!");
					request.getRequestDispatcher("AdminDangNhap.jsp").forward(request, response);
					return;
				}
			}

			AdminLoginBO adbo = new AdminLoginBO();
			AdminLogin admin = adbo.KiemTraDangNhap(user, pass);
			if (admin != null) {
				session.setAttribute("admin", admin.getTenDangNhap());
				session.setAttribute("demAdmin", 0);
				response.sendRedirect("AdminTrangChuController");
			} else {
				dem++;
				session.setAttribute("demAdmin", dem);
				request.setAttribute("thongbao", "Sai tài khoản hoặc không có quyền Admin!");
				request.getRequestDispatcher("AdminDangNhap.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("AdminDangNhap.jsp").forward(request, response);
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
