import java.sql.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;

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
		Ranking = new Classifica(this);
		Team = new Team(this);
		Match = new Match(this);
		gui = new GUI(this);
		Upload = new Update(this);
	}
	
	public void ShowSquadre() {
		
		
		Team.setVisible(true);
		
		
	}
	
	public void ShowClassifica() {
		
		
		Ranking.setVisible(true);
		
		
	}


	public void ShowPartite() {
		
		
		Match.setVisible(true);
		
	}
	
	
	
	public void ShowGui() {
			
			
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
			String Query = "SELECT partita.Giornata, s.Nome as Casa, s2.Nome AS Ospite, partita.Gol_C AS GoalCasa, partita.Gol_F as GoalOspite, partita.Arbitro\r\n" + 
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
		         record.put("Giornata", rs.getString("Giornata"));
		         record.put("Gol_C", rs.getInt("GoalCasa"));
		         record.put("Gol_F", rs.getInt("GoalOspite"));
		         record.put("Arbitro", rs.getString("Arbitro"));
		        
		         
//		       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
		         Object o[] = {record.get("Giornata"), record.get("Casa"), record.get("Ospite"), record.get("Gol_C"), record.get("Gol_F"), record.get("Arbitro")};
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
	
	public void PopulaTabellaMarcatori(JTable table) {
		
		
		
		
	}
	
	
	public void PopolaTabellaMarcatori(JTable table){
		
		
		int f = Connessione();
		if (f == 1) {
		try{  
			 
			//INIZIO FORMULAZIONE QUERY
			String Query = "SELECT giocatore.Nome, giocatore.Cognome, giocatore.n_goal, squadra.Nome AS Squadra from giocatore INNER JOIN squadra ON giocatore.Squadra = squadra.id_Team order by giocatore.n_goal desc";
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
		         record.put("n_goal", rs.getInt("n_goal"));
		         record.put("Squadra", rs.getString("Squadra"));

		                
		       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
		         Object o[] = {record.get("Nome"), record.get("Cognome"), record.get("n_goal"), record.get("Squadra")};
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
	
	
}


