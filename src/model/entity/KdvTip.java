package model.entity;

public class KdvTip {
	
	private Integer id;
	private String kod;
	private String ad;
	private double oran;
	
	//GETTERS & SETTERS
	public Integer getId() {
		return id;
	}
	public String getKod() {
		return kod;
	}
	public String getAd() {
		return ad;
	}
	public double getOran() {
		return oran;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public void setOran(double oran) {
		this.oran = oran;
	}
}
