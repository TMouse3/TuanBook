package Model.BO;

import Model.DAO.HoaDonDAO;

public class HoaDonBO {
	HoaDonDAO hdDAO = new HoaDonDAO();

	public long themHoaDon(long maKh) throws Exception {
		return hdDAO.themHoaDon(maKh);
	}
	
	public void XoaHoaDonQua30Ngay() throws Exception {
		hdDAO.XoaHoaDonQua30Ngay();
	}

}
