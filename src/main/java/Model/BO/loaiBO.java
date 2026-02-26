package Model.BO;

import java.util.ArrayList;

import Model.BEAN.loai;
import Model.DAO.loaiDAO;

public class loaiBO {
	loaiDAO dao = new loaiDAO();

	public ArrayList<loai> getLoai() throws Exception {
		return dao.getLoai();
	}

	public int Them(String ml, String tl) throws Exception {
		return dao.Them(ml, tl);
	}

	public int Sua(String ml, String tl) throws Exception {
		return dao.Sua(ml, tl);
	}

	public int Xoa(String ml) throws Exception {
		return dao.Xoa(ml);
	}
}
