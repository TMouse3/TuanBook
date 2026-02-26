package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.KhachHang;
import Model.BO.KhachHangBO;
import Model.BO.MaHoaBO;
import nl.captcha.Captcha;

/**
 * Servlet implementation class DangNhapController
 */
@WebServlet("/DangNhapController")
public class DangNhapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangNhapController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			String user = request.getParameter("username");
			String pass = request.getParameter("password");

			KhachHangBO khBO = new KhachHangBO();
			HttpSession session = request.getSession();
			
			if (session.getAttribute("dem") == null) {
				session.setAttribute("dem", 0);
			}
			
			int dem = (int) session.getAttribute("dem");

			if (user != null && pass != null) {
				
				if (dem >= 3) {
					Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);
					String answer = request.getParameter("captcha");
					
					if (answer == null || !captcha.isCorrect(answer)) {
						request.setAttribute("thongbao", "Captcha không đúng!");
						request.getRequestDispatcher("DangNhap.jsp").forward(request, response);
						return;
					}
				}
				
				pass = MaHoaBO.ecrypt(pass);// hàm static nên xài được luôn hehe
				
				KhachHang kh = khBO.KiemTraDangNhap(user.trim(), pass.trim());
				
				if (kh != null) {
					session.setAttribute("user", kh);
					session.setAttribute("dem", 0);
					response.sendRedirect("TrangChuController");
					return;
				}
				dem++;
				session.setAttribute("dem", dem);
				request.setAttribute("thongbao", "Tài khoản hoặc mật khẩu không đúng!");
				request.getRequestDispatcher("DangNhap.jsp").forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("DangNhap.jsp").forward(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
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
