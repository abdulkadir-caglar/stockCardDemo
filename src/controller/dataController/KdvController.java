package controller.dataController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Database;
import model.entity.KdvTip;
import model.queries.KdvQueries;

public class KdvController implements BaseController{
	private Database database = new Database();
	private KdvQueries kdvQueries = new KdvQueries();
	
	//SAVE
	public void save(KdvTip kdv) {
		try {
			PreparedStatement saveSt = database.connect().prepareStatement(kdvQueries.getSaveKdvQuery());
			
			saveSt.setString(1, kdv.getKod());
			saveSt.setString(2, kdv.getAd());
			saveSt.setDouble(3, kdv.getOran());
			saveSt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	//GET BY STOCK CODE
	public ResultSet findByCode(String kod) {
		try {
			PreparedStatement getSt = database.connect().prepareStatement(kdvQueries.getFindKdvQuery());
			return getSt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	//GET BY ID
	public ResultSet findById(Integer id) {
		try {
			PreparedStatement idSt = database.connect().prepareStatement(kdvQueries.getFindByIdQuery());
			idSt.setInt(1, id);
			return idSt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//DELETE
	@Override
	public void delete(String stokKodu) {
		try {
			PreparedStatement deleteSt = database.connect().prepareStatement(kdvQueries.getDeleteKdvQuery());
			deleteSt.setString(1, stokKodu);
			deleteSt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//CHECK
	@Override
	public boolean check(String kod) {
		try {
			PreparedStatement checkSt= database.connect().prepareStatement(kdvQueries.getFindKdvQuery());
			checkSt.setString(1, kod);
			
			ResultSet rs= checkSt.executeQuery();
			
			if(rs.next()) {
				return true;
			}else return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//SEARCH
	@Override
	public ResultSet search(String kod) {
		try {
			PreparedStatement searchSt = database.connect().prepareStatement(kdvQueries.getFindKdvQuery());
			searchSt.setString(1, kod);
			
			ResultSet rs = searchSt.executeQuery();
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//SORT BY ID
	@Override
	public ResultSet sortAsc() {
		try {
			PreparedStatement sortSt;
			sortSt = database.connect().prepareStatement(kdvQueries.getAscQuery(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sortSt.executeQuery();
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
	}

	//GET FIRST DATA
	@Override
	public ResultSet findFirst() {
		try {
			PreparedStatement firstSt = database.connect().prepareStatement(kdvQueries.getFirstQuery());
			ResultSet rs = firstSt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//UPDATE
	public void update(KdvTip kdv) {
		try {
			PreparedStatement upSt = database.connect().prepareStatement(kdvQueries.getUpdateQuery());
			
			upSt.setString(3, kdv.getKod());
			upSt.setString(1, kdv.getAd());
			upSt.setDouble(2, kdv.getOran());
			upSt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//GET ALL
	@Override
	public ResultSet findAll() {
		try {
			PreparedStatement getAllSt = database.connect().prepareStatement(kdvQueries.getAllKdvsQuery(),
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			return getAllSt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}