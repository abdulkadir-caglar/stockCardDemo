package controller.frameController;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.dataController.CardController;
import model.entity.Card;
import view.internalFrame.StockCardFrame;

public class StockCardController {
	private static StockCardController instance;
	private CardController cardController = new CardController();
	
	public static StockCardController getInstance() {
		if(instance == null) {
			instance = new StockCardController();
		}return instance;
	}
	
	public Card setCard() {
		Card card = new Card();
		
		card.setStokKodu(StockCardFrame.getInstance().codeTF.getText());
		card.setStokAdi(StockCardFrame.getInstance().nameTF.getText());
		card.setStokTipi((Integer)StockCardFrame.getInstance().stockTypeCB.getSelectedItem());
		card.setBirim((String)StockCardFrame.getInstance().unitCB.getSelectedItem());
		card.setBarkod(StockCardFrame.getInstance().barcodeTF.getText());
		card.setkdvTipId((Integer)StockCardFrame.getInstance().kdvTypeCB.getSelectedItem());
		card.setAciklama(StockCardFrame.getInstance().descriptionTA.getText());
		card.setTarih(new Date(new java.util.Date().getTime()));
		
		return card;
	}
	
	public void setContainers(ResultSet rs) {
		try {
			StockCardFrame.getInstance().codeTF.setText(rs.getString("stok_kodu"));
			StockCardFrame.getInstance().nameTF.setText(rs.getString("stok_adi"));
			StockCardFrame.getInstance().stockTypeCB.setSelectedItem(rs.getInt("stok_tipi"));
			StockCardFrame.getInstance().unitCB.setSelectedItem(rs.getString("birim"));
			StockCardFrame.getInstance().barcodeTF.setText(rs.getString("barkod"));
			StockCardFrame.getInstance().kdvTypeCB.setSelectedItem(rs.getInt("kdv_tip_id"));
			StockCardFrame.getInstance().descriptionTA.setText(rs.getString("aciklama"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setContainersNull() {
		StockCardFrame.getInstance().codeTF.setText(null);
		StockCardFrame.getInstance().nameTF.setText(null);
		StockCardFrame.getInstance().stockTypeCB.setSelectedIndex(0);;
		StockCardFrame.getInstance().unitCB.setSelectedIndex(0);
		StockCardFrame.getInstance().barcodeTF.setText(null);
		StockCardFrame.getInstance().kdvTypeCB.setSelectedIndex(0);
		StockCardFrame.getInstance().descriptionTA.setText(null);
	}
}