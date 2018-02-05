package model;
import java.sql.Connection;
import java.util.List;

import bean.BeszallitoBean;
import bean.ErtekelesBean;
import bean.FelhasznaloBean;
import bean.KategoriaBean;
import bean.RendelesBean;
import bean.TelepulesBean;
import bean.TermekBean;

	/**
	 * Az interf�sz a BookShop app adatel�r�si reteg�t reprezent�lja.
	 */
public interface MagDigitalDAO {

		public String addFelhasznalo(String username, String password, String fullname, String email, String phone, String zipcode, String address);

	    public String getFelhasznalok();

	    public boolean addRendeles(String rendeles);

	    public String getRendelesek();

	    public boolean addBeszallito(BeszallitoBean beszallito);

	    public String getBeszallitok();

	    public boolean addErtekeles(ErtekelesBean ertekeles);

	    public String getErtekelesek();

	    public boolean addKategoria(KategoriaBean kategoria);

	    public String getkategoriak();
	    	    
	    public String getTelepulesek();
	    
	    public boolean addTermek(TermekBean termek);
	  
	    public String geTermekek();
	    
}
