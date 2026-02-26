package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.loai;
import Model.BO.loaiBO;

/**
 * Servlet implementation class AdminQuanLyLoaiController
 */
@WebServlet("/AdminQuanLyLoaiController")
public class AdminQuanLyLoaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminQuanLyLoaiController() {
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
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {
			response.sendRedirect("AdminDangNhapController");
			return;
		}

		loaiBO lbo = new loaiBO();
		try {
			// Xử lý Thêm
			if (request.getParameter("btnThem") != null) {
				String ml = request.getParameter("txtmaloai");
				String tl = request.getParameter("txttenloai");
				lbo.Them(ml, tl);
			}
			// Xử lý Sửa
			if (request.getParameter("btnSua") != null) {
				String ml = request.getParameter("txtmaloai");
				String tl = request.getParameter("txttenloai");
				lbo.Sua(ml, tl);
			}
			// Xử lý Xóa
			String action = request.getParameter("action");
			String ml = request.getParameter("ml");
			if ("xoa".equals(action) && ml != null) {
				lbo.Xoa(ml);
			}
			// Xử lý Chọn để Sửa (đổ dữ liệu lên form)
			ArrayList<loai> ds = lbo.getLoai();
			if ("sua".equals(action) && ml != null) {
				for (loai l : ds) {
					if (l.getMaloai().equals(ml)) {
						request.setAttribute("loaiSua", l);
						break;
					}
				}
			}

			request.setAttribute("dsloai", ds);
			request.getRequestDispatcher("AdminQuanLyLoai.jsp").forward(request, response);
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
