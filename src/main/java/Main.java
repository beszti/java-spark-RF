
import static spark.Spark.*;

import controller.Controller;
import model.MagDigitalDBImplement;

public class Main {

	public static void main(String[] args) {
		port(8081);							//port megadása
																			//ez nem tom mi
		
		Controller.setupDB(new MagDigitalDBImplement("magdigital.sql"));
		Controller.setupRoutes();
		
	}

}