package model.queries;

public class KdvQueries {

	private String createKdvQuery = "CREATE TABLE kdv ("
		    + "id INT(11) PRIMARY KEY AUTO_INCREMENT, "
		    + "kod VARCHAR(15), "
		    + "ad VARCHAR(50), "
		    + "oran DOUBLE)";
	
	private String saveKdvQuery= "INSERT INTO kdv (kod, ad, oran) VALUES (?,?,?)";
	
	private String allKdvsQuery = "SELECT * FROM kdv";
	
	private String deleteKdvQuery = "DELETE FROM kdv WHERE kod = ?";
	
	private String findKdvQuery = "SELECT * FROM kdv WHERE kod = ?";
	
	private String ascQuery = "SELECT * FROM kdv ORDER BY id ASC";
	
	private String firstQuery = "SELECT * FROM kdv ORDER BY id LIMIT 1";
	
	private String updateQuery = "UPDATE kdv SET ad=?, oran=? WHERE kod = ?";
	
	private String findByIdQuery = "SELECT * FROM kdv WHERE id = ?";
	
	//GETTERS
	public String getCreateKdvQuery() {
		return this.createKdvQuery;
	}

	public String getSaveKdvQuery() {
		return saveKdvQuery;
	}
	
	public String getAllKdvsQuery() {
		return allKdvsQuery;
	}

	public String getDeleteKdvQuery() {
		return deleteKdvQuery;
	}

	public String getFindKdvQuery() {
		return findKdvQuery;
	}

	public String getAscQuery() {
		return ascQuery;
	}

	public String getFirstQuery() {
		return firstQuery;
	}

	public String getUpdateQuery() {
		return updateQuery;
	}
	
	public String getFindByIdQuery() {
		return findByIdQuery;
	}
}
