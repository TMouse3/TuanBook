package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BO.GioHangBO;

/**
 * Servlet implementation class GioHangController
 */
@WebServlet("/GioHangController")
public class GioHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GioHangController() {
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
		
		GioHangBO ghbo = (GioHangBO) session.getAttribute("giohangbo");
		
		if (ghbo == null) {
			ghbo = new GioHangBO();
			session.setAttribute("giohangbo", ghbo);
		}
		
		// ---- Xử lý xóa từng cái ----
	    String action = request.getParameter("action");
	    if ("xoa".equals(action)) {
	        String masach = request.getParameter("ms");
	        ghbo.xoaHang(masach);
	        response.sendRedirect("GioHangController");
	        return;
	    }
	    
	    // ---- Xử lý nút sửa ----
	    String masachSua = request.getParameter("butsua");
	    if (masachSua != null) {
	        String slStr = request.getParameter(masachSua);
	        if (slStr != null) {
	            int soluong = Integer.parseInt(slStr);
	            ghbo.suaHang(masachSua, soluong);
	        }
	        response.sendRedirect("GioHangController");
	        return;
	    }

	    // ---- Xử lý xóa nhiều ----
	    String butxoa = request.getParameter("butxoa");// lấy để chắc chắn người dùng ấn vào nút butXoa
	    if (butxoa != null && butxoa.equals("xoaChon")) {
	        String[] dsXoa = request.getParameterValues("cxoa");
	        if (dsXoa != null) {
	            for (String ms : dsXoa) {
	                ghbo.xoaHang(ms);
	            }
	        }
	        response.sendRedirect("GioHangController");
	        return;
	    }
	    
	    // ---- Hiển thị giỏ hàng ----
	    request.getRequestDispatcher("GioHang.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
