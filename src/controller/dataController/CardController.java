package controller.dataController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Database;
import model.entity.Card;
import model.queries.CardQueries;

public class CardController implements BaseController{
	
	private Database database = new Database();
	private CardQueries cardQueries = new CardQueries();
	
	//SAVE
	public void save(Card card) {
		try {
			PreparedStatement saveSt = database.connect().prepareStatement(cardQueries.getSaveCardQuery());
			
			saveSt.setString(1, card.getStokKodu());
			saveSt.setString(2, card.getStokAdi());
			saveSt.setInt(3, card.getStokTipi());
			saveSt.setString(4, card.getBirim());
			saveSt.setString(5, card.getBarkod());
			saveSt.setDouble(6, card.getkdvTipId());
			saveSt.setString(7, card.getAciklama());
			saveSt.setDate(8, card.getTarih());
			
			saveSt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	//GET BY STOCK CODE
	public ResultSet findByStockCode(String kod) {
		try {
			PreparedStatement getSt = database.connect().prepareStatement(cardQueries.getFindCardQuery());
			getSt.setString(1, kod);
			return getSt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//DELETE
	@Override
	public void delete(String kod) {
		try {
			PreparedStatement deleteSt = database.connect().prepareStatement(cardQueries.getDeleteCardQuery());
			deleteSt.setString(1, kod);
			deleteSt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//CHECK
	@Override
	public boolean check(String kod) {
		try {
			PreparedStatement checkSt= database.connect().prepareStatement(cardQueries.getFindCardQuery());
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
	public ResultSet search(String kod) {
		try {
			PreparedStatement searchSt = database.connect().prepareStatement(cardQueries.getFindCardQuery());
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
			sortSt = database.connect().prepareStatement(cardQueries.getAscQuery(),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
			PreparedStatement firstSt = database.connect().prepareStatement(cardQueries.getFirstQuery());
			ResultSet rs = firstSt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//UPDATE
	public void update(Card card) {
		try {
			PreparedStatement upSt = database.connect().prepareStatement(cardQueries.getUpdateQuery());
			
			upSt.setString(8, card.getStokKodu());
			upSt.setString(1, card.getStokAdi());
			upSt.setInt(2, card.getStokTipi());
			upSt.setString(3, card.getBirim());
			upSt.setString(4, card.getBarkod());
			upSt.setDouble(5, card.getkdvTipId());
			upSt.setString(6, card.getAciklama());
			upSt.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
			upSt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//GET ALL
	@Override
	public ResultSet findAll() {
		try {
			PreparedStatement getAllSt = database.connect().prepareStatement(cardQueries.getAllCardsQuery());
			return getAllSt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//LIST BY STOCK CODE
	public ResultSet listByCode() {
		try {
			PreparedStatement listSt = database.connect().prepareStatement(cardQueries.getlistByCode());
			return listSt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//SEARCH BY INPUT
	public ResultSet listSearch(String kod) {
		try {
			PreparedStatement searchSt = database.connect().prepareStatement(cardQueries.getSearchQuery());
			searchSt.setString(1, kod + "%");
			ResultSet rs = searchSt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}