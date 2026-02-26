package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BEAN.KhachHang;
import Model.BO.ChiTietHoaDonBO;
import Model.BO.GioHangBO;
import Model.BO.HoaDonBO;

/**
 * Servlet implementation class ThanhToanController
 */
@WebServlet("/XacNhanDatMuaController")
public class XacNhanDatMuaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XacNhanDatMuaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession();
			
			KhachHang kh = (KhachHang) session.getAttribute("user");
			if (kh == null) {
				response.sendRedirect("DangNhapController");
				return;
			}
			
			GioHangBO ghbo = (GioHangBO) session.getAttribute("giohangbo");
			
			if (ghbo.getDsGioHang().isEmpty()) {
				response.sendRedirect("GioHangController");
				return;
			}
			
			HoaDonBO hdbo = new HoaDonBO();
			ChiTietHoaDonBO cthdbo = new ChiTietHoaDonBO();
			
			long maHD = hdbo.themHoaDon(kh.getMaKH());
			cthdbo.themChiTietTuGio(ghbo.getDsGioHang(), maHD);
			
			
			// Lấy tổng tiền trước khi xóa
			long tongtien = ghbo.tongTien();
			request.setAttribute("tongtien", tongtien);
			
			// Xóa giỏ hàng sau khi đặt mua
			ghbo.getDsGioHang().clear();
			// Hoặc ghbo = new GioHangBO();
			
			session.setAttribute("giohangbo", ghbo);
			
			// Chuyển qua trang XacNhanDatMua.jsp
			request.getRequestDispatcher("XacNhanDatMua.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
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
