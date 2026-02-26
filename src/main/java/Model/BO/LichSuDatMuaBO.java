package Model.BO;

import java.util.ArrayList;

import Model.BEAN.LichSuDatMua;
import Model.DAO.LichSuDatMuaDAO;

public class LichSuDatMuaBO {
	LichSuDatMuaDAO lsdmDAO = new LichSuDatMuaDAO();
	
	public ArrayList<LichSuDatMua> getLichSuDatMua(long makh) throws Exception {
		return lsdmDAO.getLichSuDatMua(makh);
	}
}
