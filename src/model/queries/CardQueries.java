package model.queries;

public class CardQueries {
	
	private String createCardQuery = "CREATE TABLE card ("
		    + "id INT(11) PRIMARY KEY AUTO_INCREMENT, "
		    + "stok_kodu VARCHAR(50) UNIQUE, "
		    + "stok_adi VARCHAR(100), "
		    + "stok_tipi INT(2), "
		    + "birim VARCHAR(10), "
		    + "barkod VARCHAR(30), "
		    + "kdv_tip_id INT, "
		    + "aciklama TEXT, "
		    + "tarih DATETIME)";
	
	private String saveCardQuery = "INSERT INTO card (stok_kodu, stok_adi, stok_tipi, birim, barkod, kdv_tip_id, aciklama, tarih)"
			+ "VALUES (?,?,?,?,?,?,?,?)";
	
	private String allCardsQuery = "SELECT * FROM card";
	
	private String deleteCardQuery= "DELETE FROM card WHERE stok_kodu = ?";
	
	private String findCardQuery = "SELECT * FROM card WHERE stok_kodu = ?";
	
	private String ascQuery = "SELECT * FROM card ORDER BY id ASC";
	
	private String firstQuery = "SELECT * FROM card ORDER BY id LIMIT 1";
	
	private String updateQuery = "UPDATE card SET stok_adi=?, stok_tipi=?, birim=?, barkod=?, kdv_tip_id=?, aciklama=?, tarih=? "
			+ "WHERE stok_kodu=?";
	
	private String listByCode = "SELECT * FROM card ORDER BY stok_kodu ASC";
	
	private String searchQuery = "SELECT * FROM card WHERE stok_kodu LIKE ?";
	
	//GETTERS
	public String getCreateCardQuery() {
		return this.createCardQuery;
	}
	
	public String getSaveCardQuery() {
		return saveCardQuery;
	}

	public String getAllCardsQuery() {
		return allCardsQuery;
	}
	
	public String getDeleteCardQuery() {
		return deleteCardQuery;
	}
	
	public String getFindCardQuery() {
		return findCardQuery;
	}
	
	public String getAscQuery(){
		return ascQuery;
	}
	
	public String getFirstQuery() {
		return firstQuery;
	}
	
	public String getUpdateQuery() {
		return updateQuery;
	}
	
	public String getlistByCode() {
		return listByCode;
	}
	
	public String getSearchQuery() {
		return searchQuery;
	}
}