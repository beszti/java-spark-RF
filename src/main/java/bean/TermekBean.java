package bean;

public class TermekBean {
	private int termekId;
	private String nev;
	private String leiras;
	private int ar;
	private boolean akcio;
	private int raktarMennyiseg;
	private String beszallito;
	
	public int getTermekId() {
		return termekId;
	}
	public void setTermekId(int termekId) {
		this.termekId = termekId;
	}
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	public String getLeiras() {
		return leiras;
	}
	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}
	public int getAr() {
		return ar;
	}
	public void setAr(int ar) {
		this.ar = ar;
	}
	public boolean isAkcio() {
		return akcio;
	}
	public void setAkcio(boolean akcio) {
		this.akcio = akcio;
	}
	public int getRaktarMennyiseg() {
		return raktarMennyiseg;
	}
	public void setRaktarMennyiseg(int raktarMennyiseg) {
		this.raktarMennyiseg = raktarMennyiseg;
	}
	public String getBeszallito() {
		return beszallito;
	}
	public void setBeszallito(String beszallito) {
		this.beszallito = beszallito;
	}
	
}
