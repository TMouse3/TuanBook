package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.KhachHang;
import Model.BEAN.LichSuDatMua;
import Model.BO.LichSuDatMuaBO;

/**
 * Servlet implementation class LichSuDatMuaController
 */
@WebServlet("/LichSuDatMuaController")
public class LichSuDatMuaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LichSuDatMuaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        
        KhachHang kh = (KhachHang) session.getAttribute("user");
        if (kh == null) {
            response.sendRedirect("DangNhapController");
            return;
        }
        
        try {
            long maKH = kh.getMaKH();

            LichSuDatMuaBO lsbo = new LichSuDatMuaBO();
            ArrayList<LichSuDatMua> ds = lsbo.getLichSuDatMua(maKH);

            request.setAttribute("lsdm", ds);
            request.getRequestDispatcher("LichSuDatMua.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Lỗi: " + e.getMessage());
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
