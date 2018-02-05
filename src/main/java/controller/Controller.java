package controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.path;
import static spark.Spark.before;
import java.sql.*;

import bean.FelhasznaloBean;
import model.MagDigitalDAO;
import model.MagDigitalDBImplement;

public class Controller {
	// Data Access Object - az adat el�r�s�t szolg�l� objektum
    // FONTOS!!! A BookShopDAO az adatel�r�si r�teg interf�sze (absztraktci�ja)
    // a r�teget mindig az interf�szen kereszt�l �rj�k el.
    // A r�teg implement�ci�j�t egyszer haszn�ljuk, p�ld�nyos�t�skor,
    // visszacastolni TILOS!!!
	
	String database = "magdigital.sql";
	static MagDigitalDBImplement mddb;
			
	private MagDigitalDAO dao = new MagDigitalDBImplement(database);
	
	public static void setupDB(MagDigitalDBImplement conn) {
		mddb = conn;
	}
	
	public static void setupRoutes() {
		
		before("/*", (q, a) -> System.out.println("Received api call"));
		before((request, response) -> {
			response.header("Access-Control-Request-Method", "POST, GET, OPTIONS, DELETE, PUT");
			response.header("Access-Control-Allow-Origin", "null");
		});
		
		path("/user", () -> {
			get("/login/:email/:password", (request, response) -> 
				mddb.logIN(request.params(":email"), request.params(":password"))
			);
			//onclick a reg gombra, majd átadni a url-re az adatokat, éééés post-al átvenni, majd bekurni adatbázisba
			get("/reg/:username/:password/:fullname/:email/:phone/:zipcode/:address", (request, response) -> 
				{mddb.addFelhasznalo(
						request.params(":username"),
						request.params(":password"),
						request.params(":fullname"),
						request.params(":email"),
						request.params(":phone"), 
						request.params(":zipcode"),
						request.params(":address"));
						return "ok";
				}
			);
		});
		get("/hello", (req, res) -> {
			return "hello world";
		});
		
		//post -get kb. ugyanaz, csak get nem jeleniti meg url-ben az adatokat.
		//path-on belül meghivom öket, aztán bizonyos url-en belül meghivom a függvényeket.
		
		//return false;
	}
}
