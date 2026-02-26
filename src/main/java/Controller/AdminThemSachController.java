package Controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Model.BEAN.loai;
import Model.BEAN.sach;
import Model.BO.loaiBO;
import Model.BO.sachBO;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/AdminThemSachController")
public class AdminThemSachController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminThemSachController() {
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
		if(request.getSession().getAttribute("admin") == null) {
			response.sendRedirect("AdminDangNhapController"); return;
		}

		try {
			// Nếu là submit form (Multipart)
			if (ServletFileUpload.isMultipartContent(request)) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);

				String masach="", tensach="", tacgia="", maloai="", anh="";
				long soluong=0, gia=0;

				for(FileItem item : items) {
					if(item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString("utf-8");
						switch(name) {
							case "txtmasach": masach=value; break;
							case "txttensach": tensach=value; break;
							case "txttacgia": tacgia=value; break;
							case "txtsoluong": soluong=Long.parseLong(value); break;
							case "txtgia": gia=Long.parseLong(value); break;
							case "cmbmaloai": maloai=value; break;
						}
					} else {
						if(item.getName().length() > 0) {
							String fileName = item.getName();
							anh = "image_sach" + File.separator + fileName;
							String path = request.getServletContext().getRealPath("") + File.separator + "image_sach";
							File dir = new File(path);
							if(!dir.exists()) dir.mkdir();
							item.write(new File(path + File.separator + fileName));
						}
					}
				}

				sach s = new sach(masach, tensach, tacgia, soluong, gia, anh, maloai);
				sachBO sachbo = new sachBO();
				sachbo.ThemSach(s);
				
				response.sendRedirect("AdminQuanLySachController");
				return;
			}

			// Nếu là truy cập lần đầu (hiển thị trang)
			loaiBO ldao = new loaiBO();
			ArrayList<loai> dsloai = ldao.getLoai();
			request.setAttribute("dsloai", dsloai);
			request.getRequestDispatcher("AdminThemSach.jsp").forward(request, response);

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
