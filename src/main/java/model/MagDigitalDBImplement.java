package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.BeszallitoBean;
import bean.ErtekelesBean;
import bean.FelhasznaloBean;
import bean.KategoriaBean;
import bean.RendelesBean;
import bean.TelepulesBean;
import bean.TermekBean;

public class MagDigitalDBImplement implements MagDigitalDAO {
	public static Connection connection;
	public static Statement statement, st;
	public static PreparedStatement pst;
	private File dbPath;
	private File database;		
	private static final String SQL_ADD_FELHASZNALO = "INSERT INTO Felhasznalo (username,password,nev,email,telefon,irsz,utca) VALUES (?, ?, ?, ?, ?, ?, ?);";
	
	
	List<FelhasznaloBean> felhasznalo = new ArrayList<FelhasznaloBean>();
	
	
	public MagDigitalDBImplement(String database){
		System.out.println("Ctor");
		if(database == null) {
			database="magdigital.sql";
		}
		try{
            dbPath = new File(getClass().getClassLoader().getResource(database).getFile());
            this.database = dbPath;
            
            Class.forName("org.sqlite.JDBC");

        }catch(Exception e){
            System.err.println(e.getMessage());
            return;
        }
        
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:"+database);
            statement = connection.createStatement();
            startDB();
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }
        
    }

	private boolean startDB() {
		boolean rvSuccess = false;
		try {
			statement.addBatch(Sql.Beszallito.toString());
			statement.addBatch(Sql.Ertekeles.toString());
			statement.addBatch(Sql.Felhasznalo.toString());
			statement.addBatch(Sql.Kategoria.toString());
			statement.addBatch(Sql.Kosar.toString());
			statement.addBatch(Sql.Rendeles.toString());
			statement.addBatch(Sql.Telepules.toString());
			statement.addBatch(Sql.Termek.toString());
			
			statement.executeBatch();
			rvSuccess = true;
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return rvSuccess;
	}
	
	public static String logIN(String email, String pw) {
		ResultSet rs;
		String SQL_GET_FELHASZNALO = "SELECT * FROM Felhasznalo WHERE email = '"+ email +"' AND password = '"+ pw +"';";
		String username = "basic";
		FelhasznaloBean felh;
		try {
			st = connection.createStatement();
			rs = st.executeQuery(SQL_GET_FELHASZNALO);
			felh = new FelhasznaloBean();
			
			while (rs.next()) {				
			    felh.setUsername(rs.getString("username"));
			    felh.setNev(rs.getString("nev"));
                felh.setEmail(email);
                felh.setIrSzam(rs.getInt("irsz"));
                felh.setTelefon(rs.getString("telefon"));
                felh.setUtcaHaz(rs.getString("utca"));
			}
			
			System.out.println(statement);
			String s = felh.getUsername() + "&" + felh.getNev() + "&" + felh.getEmail() + "&" + felh.getTelefon() + "&" + felh.getIrSzam() + "&" + felh.getUtcaHaz();
			
			System.out.println(s);
			return s;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close statement when listing customers.");
                e.printStackTrace();
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when listing customers.");
                e.printStackTrace();
            }
        }

		return username;
	}
	
	
	public String addFelhasznalo(String username, String password, String fullname, String email, String phone, String zipcode, String address) {
		boolean rvSucceeded = false;
		System.out.println("0.");
		//String SQL_ADD_FELHASZNALO = "INSERT INTO Felhasznalo (username,password,nev,email,telefon,irsz,utca-hazszam) VALUES (?, ?, ?, ?, ?, ?, ?);";
		
        System.out.println("1.");

        try {
          
        	pst = connection.prepareStatement(SQL_ADD_FELHASZNALO); 
        	System.out.println("2.");
            int index = 1;
            pst.setString(index++, username);
            pst.setString(index++, password);
            pst.setString(index++, fullname);
            pst.setString(index++, email);
            pst.setString(index++, phone);
            pst.setInt(index++, Integer.parseInt(zipcode));
            pst.setString(index++, address);
            
            System.out.println("3.");
            System.out.println(pst);
            int rowsAffected = pst.executeUpdate();
            System.out.println("4.");
            
            if (rowsAffected == 1) {
            	System.out.println("5.");
                rvSucceeded = true;
            }
            System.out.println("6.");
            return rvSucceeded+"";
        } catch (SQLException e) {
            System.out.println("Failed to execute adding customer.");
            e.printStackTrace();
        } finally {
            // NAGYON FONTOS!
            // Minden adatb�zis objektumot le kell z�rni, mivel ha ezt nem
            // tessz�k meg, akkor el�fordulhat, hogy nyitott kapcsolatok
            // maradnak az adatb�zis fel�. Az adatb�zis pedig korl�tozott
            // sz�mban tart fenn kapcsolatokat, ez�rt egy id� ut�n akar ez be is
            // telhet!
            // Minden egyes objektumot k�l�n try-catch �gban kell megpr�b�lni
            // bez�rni!
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close statement when adding customer.");
                e.printStackTrace();
            }

            try {
                if (connection != null) {
                	connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when adding customer.");
                e.printStackTrace();
            }
        }
        System.out.println(rvSucceeded + "");
        return rvSucceeded+"";
	}

	@Override
	public String getFelhasznalok() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public boolean addRendeles(String rendeles) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getRendelesek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addBeszallito(BeszallitoBean beszallito) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getBeszallitok() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addErtekeles(ErtekelesBean ertekeles) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getErtekelesek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addKategoria(KategoriaBean kategoria) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getkategoriak() {
		// TODO Auto-generated method stub
		return null;
	} 

	@Override
	public String getTelepulesek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addTermek(TermekBean termek) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String geTermekek() {
		// TODO Auto-generated method stub
		return null;
	}


}
