package controller.dataController;

import java.sql.ResultSet;

public interface BaseController {
	void delete(String kod);
	
	boolean check(String kod);
	
	ResultSet search(String kod);
	
	ResultSet sortAsc();
	
	ResultSet findFirst();
	
	public ResultSet findAll();
}