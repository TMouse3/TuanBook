package Model.BO;

import java.util.ArrayList;

import Model.BEAN.sach;
import Model.DAO.sachDAO;

public class sachBO {
	private sachDAO sachdao = new sachDAO();
	
	public ArrayList<sach> getSach() throws Exception{
		return sachdao.getSach();
	}
	
	public ArrayList<sach> getSachTheoLoai(String maloai) throws Exception{
		ArrayList<sach> ds = new ArrayList<sach>();
		for(sach s : getSach()) {
			if(s.getMaloai().equals(maloai)) {
				ds.add(s);
			}
		}
		return ds;
	}
	
	public ArrayList<sach> getSachTheoTuKhoa(String tukhoa) throws Exception{
		ArrayList<sach> ds = new ArrayList<sach>();
		for(sach s : getSach()) {
			if(s.getTensach().trim().toLowerCase().contains(tukhoa.trim().toLowerCase()) 
					|| s.getTacgia().toLowerCase().contains(tukhoa.toLowerCase().trim())) {
				ds.add(s);
			}
		}
		return ds;
	}
	
	public int ThemSach(sach s) throws Exception {
		return new sachDAO().ThemSach(s);
	}
	
	public int XoaSach(String ms) throws Exception {
		return new sachDAO().XoaSach(ms);
	}
	
	public int SuaSach(sach s) throws Exception {
		return new sachDAO().SuaSach(s);
	}
	
	public sach getSachTheoMa(String masach) throws Exception {
		return sachdao.getSachTheoMa(masach);
	}
	
	public void truSoLuong(String masach, int soLuong) throws Exception {
		sachdao.truSoLuong(masach, soLuong);
	}
}
