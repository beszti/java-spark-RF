package bean;

public class RendelesBean {

	private int rendelesId;
	private String username;
	private String datum;
	private int termekId;
	private int mennyiseg;
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getRendelesId() {
		return rendelesId;
	}
	public void setRendelesId(int rendelesId) {
		this.rendelesId = rendelesId;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public int getTermekId() {
		return termekId;
	}
	public void setTermekId(int termekId) {
		this.termekId = termekId;
	}
	public int getMennyiseg() {
		return mennyiseg;
	}
	public void setMennyiseg(int mennyiseg) {
		this.mennyiseg = mennyiseg;
	}
	public String getFizetesTipus() {
		return fizetesTipus;
	}
	public void setFizetesTipus(String fizetesTipus) {
		this.fizetesTipus = fizetesTipus;
	}
	private String fizetesTipus;
	
	
}
