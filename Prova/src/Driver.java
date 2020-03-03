import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JTable;
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

	private HomePage Home;
	private Persona People;
	private Giocatore Player;
	private Stadio Stadium;
	private Goal Gol;
	private CampionatoHome CampionatoDialog;
	private Update Upload;
	private Match Match;
	private Team Team;
	private Classifica Ranking;
	private static Connection connection;
	private ResultSet rs;
	private Statement smnt;
	
	public static void main(String[] args) throws SQLException {
		
		Driver main = new Driver();
		main.ShowHomePage();
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			String connectionString="jdbc:mysql://den1.mysql1.gear.host/footballlea?user=footballlea&password=Zq10?s7AH2H!";
			connection = DriverManager.getConnection(connectionString);		
		}catch(Exception e){
			System.out.println(e);
		}  
	}  

	
	public Driver() {
		
		
	}
	
	public void ShowSquadre(Object item) {
		Team = new Team(this, item);
		Team.setVisible(true);
	}
	
	public void ShowClassifica(Object item) {
		Ranking = new Classifica(this, item);
		Ranking.setVisible(true);
	}

	public void ShowPartite(Object item) {
		Match = new Match(this, item);
		Match.setVisible(true);		
	}

	public void ShowCampionatoDialog(Object item) {
		CampionatoDialog = new CampionatoHome(this, item);
		CampionatoDialog.setVisible(true);
		}
	
	public void ShowHomePage() {
		Home = new HomePage(this);
		Home.setVisible(true);
		}
	
	
	
	public void ShowGiocatore(Object item, String id_giocatore) throws SQLException {
		Player = new Giocatore(this, item, id_giocatore);
		Player.setVisible(true);
		
	}
	
	public void NotShowPartite() {
		Match.setVisible(false);
	}
	
	
	public void NotShowClassifica() {
		Ranking.setVisible(false);
	}
	
	public void NotShowSquadre(boolean flag) {
		Team.setVisible(flag);
	}
	
	
	public void NotShowCampionatoDialog() {
		CampionatoDialog.setVisible(false);	
	}
	
	
	public void NotShowHomePage() {
		Home.setVisible(false);	
	}
	
	public void ShowAggiorna() {
		Upload = new Update(this);
		Upload.setVisible(true);
	}
	
	public void NotShowAggiorna() {
		Upload.setVisible(false);
	}
	

	
	public void NotShowGiocatore() {
		Player.setVisible(false);		
	}
	
	
	public int Connessione() {
		int f = 1;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			String connectionString="jdbc:mysql://den1.mysql1.gear.host/footballlea?user=footballlea&password=Zq10?s7AH2H!";
			connection = DriverManager.getConnection(connectionString);
			
		}catch(Exception e){
			System.out.println(e);
			f = 0;
		}  
		
		return f;
	}
	
	
	public void PopolaTabellaClassifica(JTable table, Object item){
		int f = Connessione();
		if (f == 1) {
			try{  
				//INIZIO FORMULAZIONE QUERY
				String Query = "select  squadra.Nome, squadra.punti, squadra.vinte, squadra.pareggio, squadra.perse, squadra.gf, squadra.gs from squadra where campionato =(SELECT id from campionato where nome = '"+item.toString()+"') ORDER BY squadra.punti DESC, GF-GS ASC";
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
			         record.put("vinte", rs.getString("vinte"));
			         record.put("perse", rs.getInt("perse"));
			         record.put("pareggio", rs.getInt("pareggio"));
			         record.put("gf", rs.getInt("gf"));
			         record.put("gs", rs.getInt("gs"));
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("Nome"), record.get("Punti"),record.get("vinte"),record.get("perse"), record.get("pareggio"), record.get("gf"), record.get("gs")};
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
	
	public void PopolaTabellaTeam(JTable table, Object item) {
		int f = Connessione();
		if (f == 1) {
		try{  
			//INIZIO FORMULAZIONE QUERY
			String Query = "select  squadra.id, squadra.Nome, squadra.presidente, allenatore.cognome AS Allenatore, squadra.Stadio from allenatore, squadra where squadra.Allenatore = allenatore.id && squadra.campionato = (SELECT id from campionato where Nome='"+item.toString()+"')";
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
		         record.put("id", rs.getString("id"));
		         record.put("Nome", rs.getString("Nome"));
		         record.put("Presidente", rs.getString("Presidente"));
		         record.put("Allenatore", rs.getString("Allenatore"));
		         record.put("Stadio", rs.getString("Stadio"));       
//		       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
		         Object o[] = { record.get("id"), record.get("Nome"),record.get("Presidente"), record.get("Allenatore"), record.get("Stadio")};
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

	public void PopolaTabellaMatch(JTable table, Object item) {		
		int f = Connessione();
		if (f == 1) {
			try{  
				//INIZIO FORMULAZIONE QUERY
				String Query = "SELECT partita.id, partita.giornata, s.nome as Casa, s2.nome AS Ospite, partita.goal_casa AS GoalCasa, partita.goal_ospite as GoalOspite, partita.arbitro\r\n" + 
						"FROM partita\r\n" + 
						"INNER JOIN squadra as s\r\n" + 
						"ON partita.casa = s.id\r\n" + 
						"INNER JOIN squadra as s2\r\n" + 
						"ON partita.ospite = s2.id \r\n" + 
						"WHERE partita.campionato = (SELECT id from campionato where nome = '"+item.toString()+"')\r\n" + 
						"ORDER BY partita.giornata DESC";
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
					 record.put("id", rs.getString("id"));
			         record.put("Casa", rs.getString("Casa"));
			         record.put("Ospite", rs.getString("Ospite"));
			         record.put("Giornata", rs.getString("Giornata"));
			         record.put("Gol_C", rs.getInt("GoalCasa"));
			         record.put("Gol_F", rs.getInt("GoalOspite"));
			         record.put("Arbitro", rs.getString("Arbitro"));
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("id"), record.get("Giornata"), record.get("Casa"), record.get("Ospite"), record.get("Gol_C"), record.get("Gol_F"), record.get("Arbitro")};
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
	
	public void PopolaTabellaMarcatori(JTable table, Object item){
		int f = Connessione();
		if (f == 1) {
			try{  
				 
				//INIZIO FORMULAZIONE QUERY
				String Query = "SELECT giocatore.id, giocatore.nome, giocatore.cognome, giocatore.ngoal, giocatore.nmaglia, squadra.nome AS Squadra from giocatore INNER JOIN squadra ON giocatore.squadra = squadra.id where squadra.campionato = (SELECT id from campionato where nome = '"+item.toString()+"') order by giocatore.ngoal desc";
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
	
			         record.put("id", rs.getString("id"));
			         record.put("nome", rs.getString("nome"));
			         record.put("cognome", rs.getString("cognome"));
			         record.put("ngoal", rs.getInt("ngoal"));
			         record.put("nmaglia", rs.getInt("nmaglia"));
			         record.put("squadra", rs.getString("squadra"));
	
			                
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("id"), record.get("nome"), record.get("cognome"), record.get("ngoal"), record.get("nmaglia"), record.get("squadra")};
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
				String Query = "SELECT g.nome, g.cognome, goal.partita, squadra.nome as Squadra, g.nmaglia, goal.time FROM goal INNER JOIN giocatore AS g ON goal.player = g.id INNER JOIN partita AS p ON goal.partita = p.id INNER JOIN squadra ON goal.squadra = squadra.id where p.id = '"+table2.getValueAt(table2.getSelectedRow(), 0).toString()+"'";
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
			         
			         
			         record.put("nome", rs.getString("nome"));
			         record.put("cognome", rs.getString("cognome"));
			         record.put("partita", rs.getInt("partita"));
			         record.put("squadra", rs.getString("squadra"));
			         record.put("nmaglia", rs.getInt("nmaglia"));
			         record.put("time", rs.getString("time"));
	
			                
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("nome"), record.get("cognome"), record.get("partita"), record.get("squadra"), record.get("nmaglia"), rs.getObject("time")};
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
	
public void PopolaTabellaListaGiocatore(JTable table_2, Object item, String id_squadra) throws SQLException {
		
		int f = Connessione();
		
		if(f == 1) {
			try {
			
			String Query = "select giocatore.id, giocatore.nome, giocatore.cognome, squadra.nome as squadra from giocatore inner join squadra on giocatore.squadra = squadra.id where giocatore.squadra = '"+id_squadra+"'";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			//Creo un oggetto JSON			
			JSONObject jsonObject = new JSONObject();
			
		    //CREAZIONE TABELLA
				DefaultTableModel dtm =(DefaultTableModel)table_2.getModel();
				//SETTAGGIO ALLA RIGA NUMERO 0
				dtm.setRowCount(0);
		      
		      
			//Inserisco i valori di ritorno della query nell'oggetto JSON
			 while(rs.next()) {
				 
		         JSONObject record = new JSONObject();
		         //Inserting key-value pairs into the json object

		         record.put("id", rs.getString("id"));
		         record.put("nome", rs.getString("nome"));
		         record.put("cognome", rs.getString("cognome"));
		         record.put("squadra", rs.getString("squadra"));
		         

		                
		       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
		         Object o[] = {record.get("id"), record.get("nome"), record.get("cognome"), record.get("squadra")};
			  //AGGIUNGO RECORD ALLA TABELLA
		         dtm.addRow(o);
			 }		
			
		}catch(Exception e){
			System.out.println(e);
		} 
			
		}else {
			System.out.println("Errore connessione");
		}
		
		
	}
	
	
	public void PopolaTabellaListaVittimeGiocatore(JTable table, JTable table2) {
		
		

		int f = Connessione();
		if (f == 1) {
			try{  
				 
				//INIZIO FORMULAZIONE QUERY
				String Query = "SELECT giocatore.nome, giocatore.cognome, g.partita\r\n" + 
						"FROM giocatore\r\n" + 
						"INNER JOIN goal AS g\r\n" + 
						"ON giocatore.id = g.player\r\n" + 
						"where g.player = '"+table2.getValueAt(table2.getSelectedRow(), 0).toString()+"'";
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
	
			         
			         record.put("nome", rs.getString("nome"));
			         record.put("cognome", rs.getString("cognome"));
			         record.put("partita", rs.getInt("partita"));
			         
	
			                
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("nome"), record.get("cognome"), record.get("partita")};
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
			
			
			
			String id_goal = "", autogol ="", time="", player="", squadra="", partita="", campionato2="";
			
			
		//ESTRAZIONE DATI DELLA PARTITA
		
			String id = (String)jsonob.get("id");
			System.out.print("id: "+id+"\n");
			
			String casa = (String)jsonob.get("casa");
			System.out.println("casa: "+casa+"\n");
			
			String ospite = (String)jsonob.get("ospite");
			System.out.println("Ospite: "+ospite+"\n");
						
			String goal_casa = (String)jsonob.get("goal_casa");
			System.out.print("Goal Casa: "+goal_casa+"\n");
			
			String goal_ospite = (String)jsonob.get("goal_ospite");
			System.out.print("Goal Ospite: "+goal_ospite+"\n");
			
			String giornata = (String)jsonob.get("giornata");
			System.out.print("Giornata: "+giornata+"\n");
			
			
			String campionato = (String)jsonob.get("campionato");
			System.out.print("campionati: "+campionato+"\n");			
			
			String arbitro = (String)jsonob.get("arbitro");
			System.out.print("arbitro: "+arbitro+"\n");
			
			String stadio = (String)jsonob.get("stadio");
			System.out.print("stadio: "+stadio+"\n");			

			
			
			
			//SET VALUE IN TABLE PARTITA
			
			
		    String Query = "insert into partita (id, casa, ospite, goal_casa, goal_ospite, giornata, campionato, arbitro, stadio) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		    
		    PreparedStatement preparedStmt = connection.prepareStatement(Query);
		      preparedStmt.setString(1, id);
		      preparedStmt.setString(2, casa);
		      preparedStmt.setString(3, ospite);
		      preparedStmt.setString (4, goal_casa);
		      preparedStmt.setString(5, goal_ospite);
		      preparedStmt.setString(6, giornata);
		      preparedStmt.setString(7, campionato);
		      preparedStmt.setString(8, arbitro);
		      preparedStmt.setString(9, stadio);
		      preparedStmt.execute();
		
			
			
		    //ESTRAZIONE DATI DEI GOAL
		         
		   
		      JSONArray jsonArray = (JSONArray) jsonob.get("goal");
		         System.out.println("");
		         System.out.println("Contact details: ");
		         //Iterating the contents of the array
		         Iterator iterator = jsonArray.iterator();
		         org.json.simple.JSONObject slide = (org.json.simple.JSONObject) iterator.next();
		         String Query2 = "insert into goal (id, autogol, time, partita, player, squadra, campionato) values (?, ?, ?, ?, ?, ?, ?)";
		         PreparedStatement preparedStmt2 = connection.prepareStatement(Query2);
//		         while(iterator.hasNext()) {
		        	 
		         
		         JSONArray c = (JSONArray) jsonob.get("goal");
		         for (int i = 0 ; i < c.size(); i++) {
		             org.json.simple.JSONObject obj2 = (org.json.simple.JSONObject) c.get(i);
		             id_goal = (String) obj2.get("id_goal");
		             autogol = (String) obj2.get("autogol");
		             time  = (String) obj2.get("time");
		             partita = (String) obj2.get("partita");
		             player = (String) obj2.get("player");
		             squadra = (String) obj2.get("squadra");
		             campionato2 = (String) obj2.get("campionato");
		             System.out.println(id_goal + " " + autogol + " " + time+ " "+partita+ " "+player+ " "+squadra+" "+campionato2+" ");
					  preparedStmt2.setString(1, id_goal);
				      preparedStmt2.setString(2, autogol);
				      preparedStmt2.setString(3, time);
				      preparedStmt2.setString (4, partita);
				      preparedStmt2.setString(5, player);
				      preparedStmt2.setString(6, squadra);
				      preparedStmt2.setString(7, campionato2);
				      preparedStmt2.execute();
		         }
		      
		         
//		        	    
//				       id_goal =(String) slide.get("id_goal");
//						System.out.print("id_goal: "+id_goal+"\n");
//						
//						 autogol = (String)slide.get("autogol");
//						System.out.println("autogol: "+autogol+"\n");
//						
//						 time = (String)slide.get("time");
//						System.out.println("time: "+time+"\n");
//									
//						 partita = (String)slide.get("partita");
//						System.out.print("partita: "+partita+"\n");
//						
//						 player = (String)slide.get("player");
//						System.out.print("player: "+player+"\n");
//						
//						 squadra = (String)slide.get("squadra");
//						System.out.print("squadra: "+squadra+"\n");
//						
//						
//						campionato2 = (String)slide.get("campionato");
//						System.out.print("campionato2: "+campionato2+"\n");			
//						
//						
//						

					      
//		            System.out.println(iterator.next());
//		            
//		            
//		         }
		      
		      
		      
		  
			      
					
		      
		    
		}else{
			System.out.println("Connessione al DB non riuscita");
		}
	}
	
	
	public void AggiuntaItemCombo(JComboBox comboBox) throws SQLException {
		
		int f = Connessione();
		
		if ( f==1) {
			
			
			String Query = "select nome from campionato";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				
				comboBox.addItem(rs.getString("nome"));
				
			}
			
		
			
		}else {
			System.out.println("Errore connessione");
		}
	
	}
	

	

	public byte[] immagine(Object item, String id_giocatore) throws SQLException {
		
		byte[] img = null;
		int f = Connessione();
		if(f == 1) {
			
			String Query = "SELECT giocatore.foto, squadra.campionato FROM giocatore INNER JOIN squadra on giocatore.squadra = squadra.id where giocatore.id = '"+id_giocatore+"' && squadra.campionato = (SELECT id FROM campionato WHERE campionato.nome = '"+item.toString()+"')";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				
				img = rs.getBytes("foto");
				
			}
			
			
		}else {
			System.out.println("Errore connessione");
		}
		
		
		
		return img;
		
	}


	
	public String Nome(Object item, String id_giocatore) throws SQLException {
		String nome = null;
		int f = Connessione();
		if(f == 1) {
			
			String Query = "SELECT giocatore.nome FROM giocatore INNER JOIN squadra on giocatore.squadra = squadra.id where giocatore.id = '"+id_giocatore+"' && squadra.campionato = (SELECT id FROM campionato WHERE campionato.nome = '"+item.toString()+"')";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				
				nome = rs.getString("nome");
				
			}
			
			
			
		}else {
			System.out.println("Errore connessione");
		}	
		
		return nome;
		
	}
	
	
	public String Cognome(Object item, String id_giocatore) throws SQLException {
		String cognome = null;
		int f = Connessione();
		if(f == 1) {
			
			String Query = "SELECT giocatore.cognome FROM giocatore INNER JOIN squadra on giocatore.squadra = squadra.id where giocatore.id = '"+id_giocatore+"' && squadra.campionato = (SELECT id FROM campionato WHERE campionato.nome = '"+item.toString()+"')";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				
				cognome = rs.getString("cognome");
				
			}
			
			
			
		}else {
			System.out.println("Errore connessione");
		}	
		
		return cognome;
		
	}
	
	
	public String NGoal(Object item, String id_giocatore) throws SQLException {
		String ngoal = null;
		int f = Connessione();
		if(f == 1) {
			
			String Query = "SELECT giocatore.ngoal FROM giocatore INNER JOIN squadra on giocatore.squadra = squadra.id where giocatore.id = '"+id_giocatore+"' && squadra.campionato = (SELECT id FROM campionato WHERE campionato.nome = '"+item.toString()+"')";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				
				ngoal = rs.getString("ngoal");
				
			}
			
			
			
		}else {
			System.out.println("Errore connessione");
		}	
		
		return ngoal;
		
	}
	
	
	public String Squadra(Object item, String id_giocatore) throws SQLException {
		String squadra = null;
		int f = Connessione();
		if(f == 1) {
			
			String Query = "SELECT giocatore.squadra FROM giocatore INNER JOIN squadra on giocatore.squadra = squadra.id where giocatore.id = '"+id_giocatore+"' && squadra.campionato = (SELECT id FROM campionato WHERE campionato.nome = '"+item.toString()+"')";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				
				squadra = rs.getString("squadra");
				
			}
			
			
			
		}else {
			System.out.println("Errore connessione");
		}	
		
		return squadra;
		
	}
	
	
	
	
}