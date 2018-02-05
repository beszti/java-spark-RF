package model;

public enum Sql {
	Beszallito("CREATE TABLE IF NOT EXISTS Beszallito (nev TEXT PRIMARY KEY  NOT NULL , elerhetoseg TEXT NOT NULL );"),
	Ertekeles("CREATE TABLE IF NOT EXISTS \"Ertekeles\" (\"username\" TEXT NOT NULL , \"termekid\" INTEGER NOT NULL , \"datum\" TEXT NOT NULL , \"ertekszam\" INTEGER, \"velemeny\" TEXT, PRIMARY KEY (\"username\", \"termekid\", \"datum\"),FOREIGN KEY(username) REFERENCES Felhasznalo(username), FOREIGN KEY(termekid) REFERENCES Termek(termekid));\r\n" + 
			""),
	Felhasznalo("CREATE TABLE IF NOT EXISTS \"Felhasznalo\" (\"username\" TEXT PRIMARY KEY  NOT NULL , \"password\" TEXT NOT NULL , \"nev\" TEXT NOT NULL , \"email\" TEXT NOT NULL  UNIQUE , \"telefon\" TEXT, \"irsz\" INTEGER NOT NULL , \"utca-hazszam\" TEXT NOT NULL , FOREIGN KEY(irsz) REFERENCES Telepules(irszam));\r\n" + 
			""),
	Kategoria("CREATE TABLE IF NOT EXISTS \"Kategoria\" (\"kategoria\" TEXT NOT NULL , \"termekid\" INTEGER NOT NULL , PRIMARY KEY (\"kategoria\", \"termekid\"), FOREIGN KEY(termekid) REFERENCES Termek(termekid));\r\n" + 
			""),
	Kosar("CREATE TABLE IF NOT EXISTS \"Kosar\" (\"username\" TEXT NOT NULL , \"termekid\" INTEGER NOT NULL , \"mennyiseg\" INTEGER NOT NULL , PRIMARY KEY (\"username\", \"termekid\"), FOREIGN KEY(username) REFERENCES Felhasznalo(username), FOREIGN KEY(termekid) REFERENCES Termek(termekid));\r\n" + 
			""),
	Rendeles("CREATE TABLE IF NOT EXISTS \"Rendeles\" (\"rendelesid\" INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , \"username\" TEXT NOT NULL , \"termekid\" INTEGER NOT NULL , \"mennyiseg\" INTEGER NOT NULL , \"fizetestipus\" TEXT, \"datum\" TEXT, FOREIGN KEY(username) REFERENCES Felhasznalo(username), FOREIGN KEY(termekid) REFERENCES Termek(termekid));\r\n" + 
			""),
	Telepules("CREATE TABLE IF NOT EXISTS \"Telepules\" (\"irszam\" INTEGER PRIMARY KEY  NOT NULL , \"telepules\" TEXT NOT NULL );\r\n" + 
			""),
	Termek("CREATE TABLE IF NOT EXISTS \"Termek\" (\"termekid\" INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , \"nev\" TEXT NOT NULL , \"leiras\" TEXT NOT NULL , \"ar\" INTEGER NOT NULL , \"akcio\" REAL, \"raktarmennyiseg\" INTEGER, \"beszallito\" TEXT NOT NULL, FOREIGN KEY(beszallito) REFERENCES Beszallito(nev) );\r\n" + 
			"");
	private final String name;
	
	private Sql(String name) {
		this.name= name;
		
	}
	
	public String toString() {
		return this.name;
	}
}
