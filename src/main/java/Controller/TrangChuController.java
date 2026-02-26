package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.sach;
import Model.BO.loaiBO;
import Model.BO.sachBO;

/**
 * Servlet implementation class TrangChuController
 */
@WebServlet("/TrangChuController")
public class TrangChuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrangChuController() {
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
			
			// hiển thị loại
			loaiBO lbo = new loaiBO();
			request.setAttribute("dsLoai", lbo.getLoai());
			
			//hiển thị sách
			sachBO sbo = new sachBO();
			ArrayList<sach> dsSach = sbo.getSach();
			String maloai = request.getParameter("ml");
			String tukhoa = request.getParameter("tukhoa");
			if (maloai != null) {
                dsSach = sbo.getSachTheoLoai(maloai);
            } else if (tukhoa != null) {
                dsSach = sbo.getSachTheoTuKhoa(tukhoa);
                request.setAttribute("tukhoa", tukhoa);
            }
			request.setAttribute("dsSach", dsSach);
			
			
			request.getRequestDispatcher("TrangChu.jsp").forward(request, response);
		}
		catch(Exception e) {
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
