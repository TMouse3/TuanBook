package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.KhachHang;
import Model.BO.KhachHangBO;
import Model.BO.MaHoaBO;

/**
 * Servlet implementation class DangKyController
 */
@WebServlet("/DangKyController")
public class DangKyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangKyController() {
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
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			String action = request.getParameter("action");

			if (action == null) {
				request.getRequestDispatcher("DangKy.jsp").forward(request, response);
				return;
			} else if (!action.equals("dangky")) {
				request.getRequestDispatcher("DangKy.jsp").forward(request, response);
				return;
			} else if (action.equals("dangky")) {
				MaHoaBO mahoa = new MaHoaBO();
				String hoten = request.getParameter("hoten");
				String diachi = request.getParameter("diachi");
				String sodt = request.getParameter("sodt");
				String email = request.getParameter("email");
				String tendn = request.getParameter("tendn");
				String pass = request.getParameter("pass");
				pass = mahoa.ecrypt(pass);
				

				KhachHang kh = new KhachHang();
				kh.setHoTen(hoten);
				kh.setDiaChi(diachi);
				kh.setSoDienThoai(sodt);
				kh.setEmail(email);
				kh.setTenDangNhap(tendn);
				kh.setMatKhau(pass);

				KhachHangBO khbo = new KhachHangBO();
				boolean kq = khbo.dangKyKhachHang(kh);

				if (kq) {
					response.sendRedirect("DangNhapController");
					return;
				} else {
					request.setAttribute("msg", "Đăng ký thất bại!");
					request.getRequestDispatcher("DangKy.jsp").forward(request, response);
					return;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Có lỗi xảy ra trong quá trình đăng ký!");
			request.getRequestDispatcher("DangKy.jsp").forward(request, response);
			// TODO: handle exception
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
