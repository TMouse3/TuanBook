package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.loai;
import Model.BEAN.sach;
import Model.BO.loaiBO;
import Model.BO.sachBO;

/**
 * Servlet implementation class AdminQuanLySachController
 */
@WebServlet("/AdminQuanLySachController")
public class AdminQuanLySachController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminQuanLySachController() {
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
		if (request.getSession().getAttribute("admin") == null) {
			response.sendRedirect("AdminDangNhapController");
			return;
		}

		sachBO sachbo = new sachBO();
		try {
			// Xóa sách
			String action = request.getParameter("action");
			String ms = request.getParameter("ms");
			
			if ("xoa".equals(action) && ms != null) {
				sachbo.XoaSach(ms);
			}
			
			if ("sua".equals(action) && ms != null) {
				// Lấy thông tin sách cần sửa
		        sachBO sbo = new sachBO();
		        sach s = sbo.getSachTheoMa(ms);
		        
		        loaiBO lbo = new loaiBO();
		        ArrayList<loai> dsloai = lbo.getLoai();
		        
		        request.setAttribute("sach", s);
		        request.setAttribute("dsloai", dsloai);
		        
		        request.getRequestDispatcher("AdminSuaSach.jsp").forward(request, response);
		        return;
			}
			
			

			// Lấy danh sách
			String tukhoa = request.getParameter("tukhoa");
			ArrayList<sach> ds;
			
			if (tukhoa != null && !tukhoa.trim().isEmpty()) {
				ds = sachbo.getSachTheoTuKhoa(tukhoa);
			} else {
				ds = sachbo.getSach();
			}
			request.setAttribute("dssach", ds);
			request.setAttribute("tukhoa", tukhoa);

			request.getRequestDispatcher("AdminQuanLySach.jsp").forward(request, response);
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
