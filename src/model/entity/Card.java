package model.entity;

import java.sql.Date;

public class Card {
	
	private Integer id;
	private String stokKodu;
	private String stokAdi;
	private int stokTipi;
	private String birim;
	private String barkod;
	private Integer kdvTipId;
	private String aciklama;
	private Date tarih;
	
	//GETTERS & SETTERS
	public Integer getId() {
		return id;
	}
	public String getStokKodu() {
		return stokKodu;
	}
	public String getStokAdi() {
		return stokAdi;
	}
	public int getStokTipi() {
		return stokTipi;
	}
	public String getBirim() {
		return birim;
	}
	public String getBarkod() {
		return barkod;
	}
	public Integer getkdvTipId() {
		return kdvTipId;
	}
	public String getAciklama() {
		return aciklama;
	}
	public Date getTarih() {
		return tarih;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setStokKodu(String stokKodu) {
		this.stokKodu = stokKodu;
	}
	public void setStokAdi(String stokAdi) {
		this.stokAdi = stokAdi;
	}
	public void setStokTipi(int stokTipi) {
		this.stokTipi = stokTipi;
	}
	public void setBirim(String birim) {
		this.birim = birim;
	}
	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}
	public void setkdvTipId(Integer kdvTipId) {
		this.kdvTipId = kdvTipId;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}
}
