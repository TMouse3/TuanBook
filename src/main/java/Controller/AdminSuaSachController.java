package Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import Model.BEAN.sach;
import Model.BO.sachBO;

@WebServlet("/AdminSuaSachController")
public class AdminSuaSachController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminSuaSachController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                String masach = "", tensach = "", tacgia = "", maloai = "", anh = "";
                long gia = 0, soluong = 0;
                String anhCu = ""; 

                List<FileItem> items = upload.parseRequest(request);
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString("UTF-8");
                        switch (name) {
                            case "txtmasach": masach = value; break;
                            case "txttensach": tensach = value; break;
                            case "txttacgia": tacgia = value; break;
                            case "txtgia": gia = Long.parseLong(value); break;
                            case "txtsoluong": soluong = Long.parseLong(value); break;
                            case "cmbmaloai": maloai = value; break;
                            case "anhcu": anhCu = value; break;
                        }
                    } else {
                        // Xử lý file ảnh
                        if (item.getName() != null && !item.getName().isEmpty()) {
                            String fileName = item.getName();
                            String path = request.getServletContext().getRealPath("") + File.separator + "image_sach";
                            File dir = new File(path);
                            if (!dir.exists()) dir.mkdirs();
                            
                            File file = new File(dir, fileName);
                            item.write(file);
                            anh = "image_sach"+ File.separator + fileName;
                        }
                    }
                }

                if (anh.equals("")) {
                    anh = anhCu;
                }

                sach s = new sach(masach, tensach, tacgia, soluong, gia, anh, maloai);
                sachBO sbo = new sachBO();
                sbo.SuaSach(s);

                response.sendRedirect("AdminQuanLySachController");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("AdminQuanLySachController");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}