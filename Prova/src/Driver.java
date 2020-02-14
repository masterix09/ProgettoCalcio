import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import org.json.simple.JSONArray;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;

public class Driver {

	private Persona People;
	private Giocatore Player;
	private Stadio Stadium;
	private Goal Gol;
	private GUI gui;
	private Update Upload;
	private Match Match;
	private Team Team;
	private Classifica Ranking;
	private static Connection connection;
	private ResultSet rs;
	private Statement smnt;
	
	public static void main(String[] args) throws SQLException {
		
		Driver main = new Driver();
		main.ShowGui();
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			String connectionString="jdbc:mysql://localhost:3306/serie_a?user=root&password=";
			connection = DriverManager.getConnection(connectionString);		
		}catch(Exception e){
			System.out.println(e);
		}  
	}  

	
	public Driver() {
		
		
	}
	
	public void ShowSquadre() {
		Team = new Team(this);
		Team.setVisible(true);
	}
	
	public void ShowClassifica() {
		Ranking = new Classifica(this);
		Ranking.setVisible(true);
	}

	public void ShowPartite() {
		Match = new Match(this);
		Match.setVisible(true);		
	}

	public void ShowGui() {
		gui = new GUI(this);
		gui.setVisible(true);
		}
	
	public void NotShowPartite() {
		Match.setVisible(false);
	}
	
	
	public void NotShowClassifica() {
		Ranking.setVisible(false);
	}
	
	public void NotShowSquadre() {
		Team.setVisible(false);
	}
	
	
	public void NotShowGui() {
		gui.setVisible(false);	
	}
		
	public void ShowAggiorna() {
		Upload = new Update(this);
		Upload.setVisible(true);
	}
	
	public void NotShowAggiorna() {
		Upload.setVisible(false);
	}
	
	
	public int Connessione() {
		int f = 1;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			String connectionString="jdbc:mysql://localhost:3306/serie_a?user=root&password=";
			connection = DriverManager.getConnection(connectionString);
			
		}catch(Exception e){
			System.out.println(e);
			f = 0;
		}  
		
		return f;
	}
	
	
	public void PopolaTabellaClassifica(JTable table){
		int f = Connessione();
		if (f == 1) {
			try{  
				//INIZIO FORMULAZIONE QUERY
				String Query = "select  squadra.Nome, squadra.punti, squadra.GF, squadra.GS from squadra order by squadra.punti desc";
				smnt = connection.createStatement();
				rs = smnt.executeQuery( Query );
				//FINE FORMULAZIONE QUERY	
				//Creo un oggetto JSON			
				JSONObject jsonObject = new JSONObject();
			    //CREAZIONE TABELLA
					DefaultTableModel dtm =(DefaultTableModel)table.getModel();
					//SETTAGGIO ALLA RIGA NUMERO 0
					dtm.setRowCount(0);
				//Inserisco i valori di ritorno della query nell'oggetto JSON
				 while(rs.next()) {
			         JSONObject record = new JSONObject();
			         //Inserting key-value pairs into the json object
			         record.put("Nome", rs.getString("Nome"));
			         record.put("Punti", rs.getInt("Punti"));
			         record.put("GF", rs.getInt("GF"));
			         record.put("GS", rs.getInt("GS"));
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("Nome"), record.get("Punti"), record.get("GF"), record.get("GS")};
				  //AGGIUNGO RECORD ALLA TABELLA
			         dtm.addRow(o);
				 }		
			}catch(Exception e){
				System.out.println(e);
				}
		}else {
			System.out.println("Errore");
		}
	}
	
	public void PopolaTabellaTeam(JTable table) {
		int f = Connessione();
		if (f == 1) {
		try{  
			//INIZIO FORMULAZIONE QUERY
			String Query = "select  squadra.id_Team, squadra.Nome, squadra.Vinte, squadra.Pareggiate, squadra.Perse, squadra.Giocate, squadra.GF, squadra.GS, squadra.Punti, squadra.Presidente, allenatore.cognome AS Allenatore, squadra.Stadio from allenatore, squadra where squadra.Allenatore = allenatore.id_allenatore";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			//FINE FORMULAZIONE QUERY	
			//Creo un oggetto JSON			
			JSONObject jsonObject = new JSONObject();
		    //CREAZIONE TABELLA
				DefaultTableModel dtm =(DefaultTableModel)table.getModel();
				//SETTAGGIO ALLA RIGA NUMERO 0
				dtm.setRowCount(0);
			//Inserisco i valori di ritorno della query nell'oggetto JSON
			 while(rs.next()) {
		         JSONObject record = new JSONObject();
		         //Inserting key-value pairs into the json object
		         record.put("id_Team", rs.getString("id_Team"));
		         record.put("Nome", rs.getString("Nome"));
		         record.put("Vinte", rs.getInt("Vinte"));
		         record.put("Pareggiate", rs.getInt("Pareggiate"));
		         record.put("Perse", rs.getInt("Perse"));
		         record.put("Giocate", rs.getInt("Giocate"));
		         record.put("GF", rs.getInt("GF"));
		         record.put("GS", rs.getInt("GS"));
		         record.put("Punti", rs.getInt("Punti"));
		         record.put("Presidente", rs.getString("Presidente"));
		         record.put("Allenatore", rs.getString("Allenatore"));
		         record.put("Stadio", rs.getString("Stadio"));       
//		       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
		         Object o[] = { record.get("id_Team"), record.get("Nome"), record.get("Vinte"), record.get("Pareggiate"), record.get("Perse"), record.get("Giocate"), record.get("GF"), record.get("GS"), record.get("Punti"), record.get("Presidente"), record.get("Allenatore"), record.get("Stadio")};
			  //AGGIUNGO RECORD ALLA TABELLA
		         dtm.addRow(o);
			}
			 
		connection.close();  
		}catch(Exception e){
			System.out.println(e);
			}
		}else {
			System.out.println("Errore");
		}
		
	}

	public void PopolaTabellaMatch(JTable table) {		
		int f = Connessione();
		if (f == 1) {
			try{  
				//INIZIO FORMULAZIONE QUERY
				String Query = "SELECT partita.id_partita, s.Nome as Casa, s2.Nome AS Ospite, partita.Gol_C AS GoalCasa, partita.Gol_F as GoalOspite, partita.Arbitro\r\n" + 
						"FROM partita\r\n" + 
						"INNER JOIN squadra as s\r\n" + 
						"ON partita.Casa = s.id_Team\r\n" + 
						"INNER JOIN squadra as s2\r\n" + 
						"ON partita.Ospite = s2.id_Team";
				smnt = connection.createStatement();
				rs = smnt.executeQuery( Query );
				//FINE FORMULAZIONE QUERY	
				//Creo un oggetto JSON			
				JSONObject jsonObject = new JSONObject();
			    //CREAZIONE TABELLA
					DefaultTableModel dtm =(DefaultTableModel)table.getModel();
					//SETTAGGIO ALLA RIGA NUMERO 0
					dtm.setRowCount(0);
				//Inserisco i valori di ritorno della query nell'oggetto JSON
				 while(rs.next()) {
					 JSONObject record = new JSONObject();
			         //Inserisco i valori nel record di tipo JSONObject
			         record.put("Casa", rs.getString("Casa"));
			         record.put("Ospite", rs.getString("Ospite"));
			         record.put("id_partita", rs.getString("id_partita"));
			         record.put("Gol_C", rs.getInt("GoalCasa"));
			         record.put("Gol_F", rs.getInt("GoalOspite"));
			         record.put("Arbitro", rs.getString("Arbitro"));
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("id_partita"), record.get("Casa"), record.get("Ospite"), record.get("Gol_C"), record.get("Gol_F"), record.get("Arbitro")};
				  //AGGIUNGO RECORD ALLA TABELLA
			         dtm.addRow(o);
			     }
				 connection.close();  
			}catch(Exception e){
				System.out.println(e);
				}
			}else {
				System.out.println("Errore");
			}
	}
	
	public void PopolaTabellaMarcatori(JTable table){
		int f = Connessione();
		if (f == 1) {
			try{  
				 
				//INIZIO FORMULAZIONE QUERY
				String Query = "SELECT giocatore.id_giocatore, giocatore.Nome, giocatore.Cognome, giocatore.n_goal, squadra.Nome AS Squadra from giocatore INNER JOIN squadra ON giocatore.Squadra = squadra.id_Team order by giocatore.n_goal desc";
				smnt = connection.createStatement();
				rs = smnt.executeQuery( Query );
				//FINE FORMULAZIONE QUERY	
				
				//Creo un oggetto JSON			
				JSONObject jsonObject = new JSONObject();
				
			    //CREAZIONE TABELLA
					DefaultTableModel dtm =(DefaultTableModel)table.getModel();
					//SETTAGGIO ALLA RIGA NUMERO 0
					dtm.setRowCount(0);
			      
			      
				//Inserisco i valori di ritorno della query nell'oggetto JSON
				 while(rs.next()) {
					 
			         JSONObject record = new JSONObject();
			         //Inserting key-value pairs into the json object
	
			         record.put("id_giocatore", rs.getString("id_giocatore"));
			         record.put("Nome", rs.getString("Nome"));
			         record.put("Cognome", rs.getString("Cognome"));
			         record.put("n_goal", rs.getInt("n_goal"));
			         record.put("Squadra", rs.getString("Squadra"));
	
			                
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("id_giocatore"), record.get("Nome"), record.get("Cognome"), record.get("n_goal"), record.get("Squadra")};
				  //AGGIUNGO RECORD ALLA TABELLA
			         dtm.addRow(o);
				 }		
				
			}catch(Exception e){
				System.out.println(e);
			} 
		
		}else {
			System.out.println("Errore");
		}
	
	}
	
	
	public void PopolatabellaGoal(JTable table, JTable table2) {
		
		
		int f = Connessione();
		if (f == 1) {
			try{  
				 
				//INIZIO FORMULAZIONE QUERY
				String Query = "SELECT g.Nome, g.Cognome, goal.Partita, squadra.Nome as Squadra, g.n_maglia, goal.Time\r\n" + 
						"FROM goal \r\n" + 
						"INNER JOIN giocatore AS g\r\n" + 
						"ON goal.Player = g.id_giocatore\r\n" + 
						"INNER JOIN partita AS p\r\n" + 
						"ON goal.Partita = p.id_partita\r\n" + 
						"INNER JOIN squadra\r\n" + 
						"ON goal.Squadra = Squadra.id_Team\r\n" + 
						"where p.id_partita = '"+table2.getValueAt(table2.getSelectedRow(), 0).toString()+"'";
				smnt = connection.createStatement();
				rs = smnt.executeQuery( Query );
				//FINE FORMULAZIONE QUERY	
				
				//Creo un oggetto JSON			
				JSONObject jsonObject = new JSONObject();
				
			    //CREAZIONE TABELLA
					DefaultTableModel dtm =(DefaultTableModel)table.getModel();
					//SETTAGGIO ALLA RIGA NUMERO 0
					dtm.setRowCount(0);
			      
			      
				//Inserisco i valori di ritorno della query nell'oggetto JSON
				 while(rs.next()) {
					 
			         JSONObject record = new JSONObject();
			         //Inserting key-value pairs into the json object
	
			         record.put("Nome", rs.getString("Nome"));
			         record.put("Cognome", rs.getString("Cognome"));
			         record.put("Partita", rs.getInt("Partita"));
			         record.put("Squadra", rs.getString("Squadra"));
			         record.put("n_maglia", rs.getInt("n_maglia"));
			         record.put("Time", rs.getString("Time"));
	
			                
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("Nome"), record.get("Cognome"), record.get("Partita"), record.get("Squadra"), record.get("n_maglia"), rs.getObject("Time")};
				  //AGGIUNGO RECORD ALLA TABELLA
			         dtm.addRow(o);
				 }		
				
			}catch(Exception e){
				System.out.println(e);
			} 
		
		}else {
			System.out.println("Errore");
		}
		
	}
	
	
	
	public void PopolaTabellaListaVittimeGiocatore(JTable table, JTable table2) {
		
		

		int f = Connessione();
		if (f == 1) {
			try{  
				 
				//INIZIO FORMULAZIONE QUERY
				String Query = "SELECT giocatore.Nome, giocatore.Cognome, g.Partita\r\n" + 
						"FROM giocatore\r\n" + 
						"INNER JOIN goal AS g\r\n" + 
						"ON giocatore.id_giocatore = g.Player\r\n" + 
						"where g.Player = '"+table2.getValueAt(table2.getSelectedRow(), 0).toString()+"'";
				smnt = connection.createStatement();
				rs = smnt.executeQuery( Query );
				//FINE FORMULAZIONE QUERY	
				
				//Creo un oggetto JSON			
				JSONObject jsonObject = new JSONObject();
				
			    //CREAZIONE TABELLA
					DefaultTableModel dtm =(DefaultTableModel)table.getModel();
					//SETTAGGIO ALLA RIGA NUMERO 0
					dtm.setRowCount(0);
			      
			      
				//Inserisco i valori di ritorno della query nell'oggetto JSON
				 while(rs.next()) {
					 
			         JSONObject record = new JSONObject();
			         //Inserting key-value pairs into the json object
	
			         
			         record.put("Nome", rs.getString("Nome"));
			         record.put("Cognome", rs.getString("Cognome"));
			         record.put("Partita", rs.getInt("Partita"));
			         
	
			                
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = { record.get("Nome"), record.get("Cognome"), record.get("Partita")};
				  //AGGIUNGO RECORD ALLA TABELLA
			         dtm.addRow(o);
				 }		
				
			}catch(Exception e){
				System.out.println(e);
			} 
		
		}else {
			System.out.println("Errore");
		}
		
		
	}
	
	
	
	
	public void Update(File file) throws JSONException, ParseException, FileNotFoundException, IOException, SQLException {

		int f = Connessione();
		if(f == 1) {
		
		//ESTRAPOLAZIONE DATI DA UPDATE.JSON
			
			String path = ""+file.getPath()+"";
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(path));
			org.json.simple.JSONObject jsonob = (org.json.simple.JSONObject)obj;
			
			String id = (String)jsonob.get("Id_partita");
			System.out.print("ID partita: "+id+"\n");
			
			String Casa = (String)jsonob.get("Casa");
			System.out.println("Casa: "+Casa+"\n");
			
			String Ospite = (String)jsonob.get("Ospite");
			System.out.println("Ospite: "+Ospite+"\n");
			
			String Giornata = (String)jsonob.get("Giornata");
			System.out.print("Giornata: "+Giornata+"\n");
			
			String Gol_C = (String)jsonob.get("Gol_C");
			System.out.print("Goal Casa: "+Gol_C+"\n");
			
			String Gol_F = (String)jsonob.get("Gol_F");
			System.out.print("Goal Ospite: "+Gol_F+"\n");
			
			String Arbitro = (String)jsonob.get("Arbitro");
			System.out.print("Arbitro: "+Arbitro+"\n");
			
			
			//SET VALUE IN DATABASE
			
			
		    String Query = "insert into partita (id_partita, Casa, Ospite, Giornata, Gol_C, Gol_F, Arbitro) values (?, ?, ?, ?, ?, ?, ?)";

		    PreparedStatement preparedStmt = connection.prepareStatement(Query);
		      preparedStmt.setString(1, id);
		      preparedStmt.setString(2, Casa);
		      preparedStmt.setString(3, Ospite);
		      preparedStmt.setString (4, Giornata);
		      preparedStmt.setString(5, Gol_C);
		      preparedStmt.setString(6, Gol_F);
		      preparedStmt.setString(7, Arbitro);
		      preparedStmt.execute();
			
			
		}else{
			System.out.println("Connessione al DB non riuscita");
		}
	}
	
}


