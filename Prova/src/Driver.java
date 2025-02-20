import java.awt.TextArea;
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
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
	private Giocatore Player;
	private CampionatoHome CampionatoDialog;
	private Update Upload;
	private Match Match;
	private Team Team;
	private Classifica Ranking;
	private Login Log;
	private Errore error;
	private static Connection connection;
	private ResultSet rs;
	private Statement smnt;
	
	public static void main(String[] args) throws SQLException {
		
		Driver main = new Driver();
		
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			String connectionString="jdbc:mysql://den1.mysql1.gear.host/footballlea?user=footballlea&password=Zq10?s7AH2H!";
			connection = DriverManager.getConnection(connectionString);
			main.ShowLogin();
		}catch(Exception e){
			
			main.ShowError("ERRORE CONNESSIONE");
		}
			
	}  

	
	public Driver() {
		
		
	}
	
	
	public void ShowLogin() {
		Log = new Login(this);
		Log.setVisible(true);
	}
	
	
	public void ShowError(String pane) {
		error = new Errore(this, pane);
		error.setVisible(true);
	}
	
	
	public void ShowSquadre(Object item, String user, String pass) {
		Team = new Team(this, item, user, pass);
		Team.setVisible(true);
	}
	
	public void ShowClassifica(Object item, String user, String pass) {
		Ranking = new Classifica(this, item, user, pass);
		Ranking.setVisible(true);
	}

	public void ShowPartite(Object item, String user, String pass) {
		Match = new Match(this, item, user, pass);
		Match.setVisible(true);		
	}

	public void ShowCampionatoDialog(Object item, String user, String pass) {
		CampionatoDialog = new CampionatoHome(this, item, user, pass);
		CampionatoDialog.setVisible(true);
		}
	
	public void ShowHomePage(String user, String pass) {
		Home = new HomePage(this, user, pass);
		Home.setVisible(true);
		}
	
	
	
	public void ShowGiocatore(Object item, String id_giocatore, String user, String pass) throws SQLException {
		Player = new Giocatore(this, item, id_giocatore, user, pass);
		Player.setVisible(true);
		
	}
	
	
	public void NotShowLogin() {
		Log.setVisible(false);
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
	
	public void NotShowError() {
		error.setVisible(false);
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
			ShowError(e.toString());
			f = 0;
		}  
		
		return f;
	}
	
	
	public void PopolaTabellaClassifica(JTable table, Object item){
		int f = Connessione();
		if (f == 1) {
			try{  
				//INIZIO FORMULAZIONE QUERY
				String Query = "select  squadra.id, squadra.Nome, squadra.vinte*3 + squadra.pareggio as Punti, squadra.vinte, squadra.pareggio, squadra.perse, (SELECT count(goal.squadra) from goal where goal.squadra = squadra.id) as GF, (SELECT count(goal.subito) from goal where goal.subito = squadra.id) as GS from squadra where campionato =(SELECT id from campionato where nome = '"+item.toString()+"') ORDER BY Punti DESC, GF-GS ASC";
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
			         Object o[] = {record.get("Nome"), record.get("Punti"),record.get("vinte"),record.get("perse"),record.get("pareggio"),record.get("gf"),record.get("gs")};
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
	
	
	public void PopolaTabellaTeam(JTable table, Object item, String idsquadre[]) {
		int cont = 0;
		int f = Connessione();
		if (f == 1) {
		try{  
			//INIZIO FORMULAZIONE QUERY
			String Query = "select  squadra.id, squadra.Nome, squadra.presidente, allenatore.cognome AS Allenatore, stadio.nome as Stadio from allenatore, squadra INNER JOIN stadio on squadra.Stadio = stadio.id where squadra.Allenatore = allenatore.id && squadra.campionato = (SELECT id from campionato where Nome='"+item.toString()+"')";
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
		         idsquadre[cont] = rs.getString("id");
		         record.put("Nome", rs.getString("Nome"));
		         record.put("Presidente", rs.getString("Presidente"));
		         record.put("Allenatore", rs.getString("Allenatore"));
		         record.put("Stadio", rs.getString("Stadio"));       
//		       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
		         Object o[] = { record.get("Nome"),record.get("Presidente"), record.get("Allenatore"), record.get("Stadio")};
			  //AGGIUNGO RECORD ALLA TABELLA
		         dtm.addRow(o);
		         
		         cont ++;
			}
			 
		connection.close();  
		}catch(Exception e){
			System.out.println(e);
			}
		}else {
			System.out.println("Errore");
		}
		
	}

	public void PopolaTabellaMatch(JTable table, Object item, String indicematch[]) {	
		int cont = 0;
		int f = Connessione();
		if (f == 1) {
			try{  

				String Query = "SELECT partita.id, partita.giornata, s.nome as Casa, s2.nome AS Ospite, partita.goal_casa AS GoalCasa, partita.goal_ospite as GoalOspite, arbitro.cognome AS Arbitro \r\n" + 
						"FROM partita\r\n" + 
						"INNER JOIN squadra as s\r\n" + 
						"ON partita.casa = s.id\r\n" + 
						"INNER JOIN squadra as s2\r\n" + 
						"ON partita.ospite = s2.id \r\n" + 
						"INNER JOIN arbitro\r\n" +
						"ON partita.arbitro = arbitro.id\r\n"+
						"WHERE partita.campionato = (SELECT id from campionato where nome = '"+item.toString()+"') \r\n" + 
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
					 indicematch[cont] = rs.getString("id");
			         record.put("Casa", rs.getString("Casa"));
			         record.put("Ospite", rs.getString("Ospite"));
			         record.put("Giornata", rs.getString("Giornata"));
			         record.put("Gol_C", rs.getInt("GoalCasa"));
			         record.put("Gol_F", rs.getInt("GoalOspite"));
			         record.put("Arbitro", rs.getString("Arbitro"));
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("Giornata"), record.get("Casa"), record.get("Ospite"), record.get("Gol_C"), record.get("Gol_F"), record.get("Arbitro")};
				  //AGGIUNGO RECORD ALLA TABELLA
			         dtm.addRow(o);
			         
			         cont ++;
			     }
				 connection.close();  
			}catch(Exception e){
				System.out.println(e);
				}
			}else {
				System.out.println("Errore");
			}
	}
	
	public void PopolaTabellaMarcatori(JTable table, Object item, String idGiocatori[]){
		int cont = 0;
		int f = Connessione();
		if (f == 1) {
			try{  
				 
				//INIZIO FORMULAZIONE QUERY
				String Query = "SELECT gi.id, gi.nome ,gi.cognome, COUNT(g.player) as ngoal, gi.nmaglia, s.nome as squadra FROM giocatore as gi INNER JOIN goal as g ON gi.id = g.player INNER JOIN squadra as s on gi.squadra = s.id  WHERE s.campionato = (SELECT id from campionato WHERE nome = '"+item.toString()+"') GROUP BY(g.player) order by ngoal desc";
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
	
			         idGiocatori[cont] = rs.getString("id");
			         record.put("nome", rs.getString("nome"));
			         record.put("cognome", rs.getString("cognome"));
			         record.put("ngoal", rs.getString("ngoal"));
			         record.put("nmaglia", rs.getString("nmaglia"));
			         record.put("squadra", rs.getString("squadra"));
	
			                
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("nome"), record.get("cognome"), record.get("ngoal"), record.get("nmaglia"), record.get("squadra")};
			      //AGGIUNGO RECORD ALLA TABELLA
			         dtm.addRow(o);
			         
			         cont ++;
				 }		
				
			}catch(Exception e){
				System.out.println(e);
			} 
		
		}else {
			System.out.println("Errore");
		}
	
	}
	
	
	public void PopolatabellaGoal(JTable table, JTable table2, String indicematch[]) {
		int index;
		index= table2.getSelectedRow();
		
		
		int f = Connessione();
		if (f == 1) {
			try{  
				 
				//INIZIO FORMULAZIONE QUERY
				String Query = "SELECT g.nome, g.cognome, squadra.nome as Squadra, g.nmaglia, goal.time FROM goal INNER JOIN giocatore AS g ON goal.player = g.id INNER JOIN partita AS p ON goal.partita = p.id INNER JOIN squadra ON goal.squadra = squadra.id where p.id = '"+indicematch[index]+"'";
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
			         record.put("squadra", rs.getString("squadra"));
			         record.put("nmaglia", rs.getInt("nmaglia"));
			         record.put("time", rs.getString("time"));
	
			                
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
   		             Object o[] = {record.get("nome"), record.get("cognome"), record.get("squadra"), record.get("nmaglia"), rs.getObject("time")};
			         //AGGIUNGO RECORD ALLA TABELLA
			         dtm.addRow(o);
				 }		
				
			}catch(Exception e){
				ShowError("ERRORE QUERY LISTA GOAL");
			} 
		
		}else {
			ShowError("ERRORE CONNESSIONE AL DB");
		}
		
	}
	
public void PopolaTabellaListaGiocatore(JTable table_2, Object item, String id_squadra, String idgiocatori[]) throws SQLException {
		
		int f = Connessione();
		int cont =0;
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

		         idgiocatori[cont] = rs.getString("id");
		         record.put("nome", rs.getString("nome"));
		         record.put("cognome", rs.getString("cognome"));
		         

		                
		       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
		         Object o[] = {record.get("nome"), record.get("cognome")};
			  //AGGIUNGO RECORD ALLA TABELLA
		         dtm.addRow(o);
		         
		         cont++;
			 }		
			
		}catch(Exception e){
			ShowError("ERRORE QUERY");
		} 
			
		}else {
			ShowError("CONNESIONE NON RIUSCITA AL DB");
		}
		
		
	}
	
	
	public void PopolaTabellaListaVittimeGiocatore(JTable table, JTable table2, String idGiocatori[]) {
		
		int index = table2.getSelectedRow();

		int f = Connessione();
		if (f == 1) {
			try{  
				 
				String Query = "SELECT p.giornata, s.nome, g.time\r\n" + 
						"from giocatore inner join goal as g\r\n" + 
						"on giocatore.id = g.player\r\n" + 
						"inner join partita as p\r\n" +
						"on g.partita = p.id\r\n" + 
						"inner join squadra as s\r\n" +
						"on s.id = g.subito\r\n" + 
						"where giocatore.id = '"+idGiocatori[index]+"'";
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
	
			         
			         record.put("giornata", rs.getString("giornata"));
			         record.put("nome", rs.getString("nome"));
			         record.put("time", rs.getString("time"));
			         
			         
	
			                
			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("giornata"), record.get("nome"), record.get("time")};
				  //AGGIUNGO RECORD ALLA TABELLA
			         dtm.addRow(o);
				 }		
				
			}catch(Exception e){
				ShowError("ERRORE QUERY");
			} 
		
		}else {
			ShowError("ERRORE CONNESIONE AL DB");
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

			String id="", casa="", ospite="", goal_casa="", goal_ospite="", giornata="", campionato="", arbitro="", data="";
			String id_goal = "", autogol ="", time="", player="", squadra="", partita="", campionato2="", subito="";			

			
			
			JSONArray jsonArray = (JSONArray) jsonob.get("partite");
		    System.out.println("");
		    System.out.println("Partite details: ");
		    //Iterating the contents of the array
		    Iterator iterator = jsonArray.iterator();
		    org.json.simple.JSONObject slide = (org.json.simple.JSONObject) iterator.next();
			
			//INSERT INTO PARTITA
		    String Query = "insert into partita (id, casa, ospite, goal_casa, goal_ospite, giornata, data, campionato, arbitro) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			  PreparedStatement preparedStmt = connection.prepareStatement(Query);
			
			  //ESTRAPOLAZIONE DATI PARTITE
			 JSONArray c = (JSONArray) jsonob.get("partite");
	         for (int i = 0 ; i < c.size(); i++) {
	             org.json.simple.JSONObject obje = (org.json.simple.JSONObject) c.get(i);
	             id = (String) obje.get("id");
	             casa = (String) obje.get("casa");
	             ospite  = (String) obje.get("ospite");
	             goal_casa = (String) obje.get("goal_casa");
	             goal_ospite = (String) obje.get("goal_ospite");
	             giornata = (String) obje.get("giornata");
	             data = (String) obje.get("data");
	             campionato = (String) obje.get("campionato");
	             arbitro = (String) obje.get("arbitro");
	             
	             System.out.println(id + " " + casa + " " + ospite+ " "+goal_casa+ " "+goal_ospite+ " "+giornata+" "+data+" "+campionato+" "+arbitro+" ");            
			      preparedStmt.setString(1, id);
			      preparedStmt.setString(2, casa);
			      preparedStmt.setString(3, ospite);
			      preparedStmt.setString (4, goal_casa);
			      preparedStmt.setString(5, goal_ospite);
			      preparedStmt.setString(6, giornata);
			      preparedStmt.setString(7, data);
			      preparedStmt.setString(8, campionato);
			      preparedStmt.setString(9, arbitro);
			      
			      preparedStmt.execute();
	         }
			
		
		 
		
			
			
		    //CREAZIONE JSONARRAY GOAL
		      JSONArray jsonArray2 = (JSONArray) jsonob.get("goal");
		         System.out.println("");
		         System.out.println("Contact details: ");
		         //Iterating the contents of the array
		         Iterator iterator2 = jsonArray2.iterator();
		         org.json.simple.JSONObject slide2 = (org.json.simple.JSONObject) iterator2.next();
		         
		   //INSERT INTO GOAL    
		         String Query2 = "insert into goal (id, autogol, time, partita, player, squadra, subito, campionato) values (?, ?, ?, ?, ?, ?, ?, ?)";
		         PreparedStatement preparedStmt2 = connection.prepareStatement(Query2);

		  //ESTRAPOLAZIONE DATI GOAL
		         JSONArray c2 = (JSONArray) jsonob.get("goal");
		         for (int i2 = 0 ; i2 < c2.size(); i2++) {
		             org.json.simple.JSONObject obj2 = (org.json.simple.JSONObject) c2.get(i2);
		             id_goal = (String) obj2.get("id_goal");
		             autogol = (String) obj2.get("autogol");
		             time  = (String) obj2.get("time");
		             partita = (String) obj2.get("partita");
		             player = (String) obj2.get("player");
		             squadra = (String) obj2.get("squadra");
		             subito = (String) obj2.get("subito");
		             campionato2 = (String) obj2.get("campionato");
		             System.out.println(id_goal + " " + autogol + " " + time+ " "+partita+ " "+player+ " "+squadra+" "+subito+" "+campionato2+" ");
					  preparedStmt2.setString(1, id_goal);
				      preparedStmt2.setString(2, autogol);
				      preparedStmt2.setString(3, time);
				      preparedStmt2.setString (4, partita);
				      preparedStmt2.setString(5, player);
				      preparedStmt2.setString(6, squadra);
				      preparedStmt2.setString(7, subito);
				      preparedStmt2.setString(8, campionato2);
				      preparedStmt2.execute();
		         }
		      
		         

		    
		}else{
			ShowError("ERRORE CONNESIONE AL DB");
		}
	}
	
	
	public void AggiuntaItemCombo(JComboBox comboBox) throws SQLException {
		
		int f = Connessione();
		
		if ( f==1) {
			
			
			String Query = "select nome from campionato";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				
				//AGGIUNTA DI ITEM ALLA COMBOBOX PER OGNI CAMPIONATO PRESENTE
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
				
				//ACQUISIZIONE DELL'IMMAGINE DAL DB
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
				
				//ESTRAPOLAIZONE NOME
				nome = rs.getString("nome");
				
			}
			
			
			
		}else {
			ShowError("Errore connessione");
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
				//ESTRAPOLAIZONE COGNOME
				cognome = rs.getString("cognome");
				
			}
			
			
			
		}else {
			ShowError("Errore connessione");
		}	
		
		return cognome;
		
	}
	
	
	public String NGoal(String id_giocatore) throws SQLException {
		String ngoal = null;
		int f = Connessione();
		if(f == 1) {
			
			String Query = "SELECT COUNT(g.player) as ngoal\r\n" + 
					"FROM giocatore as gi INNER JOIN goal as g\r\n" + 
					"ON gi.id = g.player\r\n" + 
					"where g.player = '"+id_giocatore+"'";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				
				//ESTRAPOLAIZONE NGOAL
				ngoal = rs.getString("ngoal");
				
			}
			
			
			
		}else {
			ShowError("Errore connessione");
		}	
		
		return ngoal;
		
	}
	
	
	public String Squadra(Object item, String id_giocatore) throws SQLException {
		String squadra = null;
		int f = Connessione();
		if(f == 1) {
			
			String Query = "SELECT squadra.nome as squadra FROM squadra INNER JOIN giocatore on giocatore.squadra = squadra.id where giocatore.id = '"+id_giocatore+"' && squadra.campionato = (SELECT id FROM campionato WHERE campionato.nome = '"+item.toString()+"')";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				//ESTRAPOLAIZONE SQUADRA
				squadra = rs.getString("squadra");
				
			}
			
			
			
		}else {
			ShowError("Errore connessione");
		}	
		
		return squadra;
		
	}
	
	
	
	public String NMaglia(Object item, String id_giocatore) throws SQLException {
		String maglia = null;
		int f = Connessione();
		if(f == 1) {
			
			String Query = "SELECT giocatore.nmaglia from giocatore where giocatore.id = '"+id_giocatore+"'";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				//ESTRAPOLAIZONE SQUADRA
				maglia = rs.getString("nmaglia");
				
			}
			
			
			
		}else {
			ShowError("Errore connessione");
		}	
		
		return maglia;
		
	}
	
	
	
	public String data(Object item, String id_giocatore) throws SQLException {
		String data = null;
		int f = Connessione();
		if(f == 1) {
			
			String Query = "SELECT giocatore.data_nasc from giocatore where giocatore.id = '"+id_giocatore+"'";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				//ESTRAPOLAIZONE SQUADRA
				data = rs.getString("data_nasc");
				
			}
			
			
			
		}else {
			ShowError("Errore connessione");
		}	
		
		return data;
		
	}
	
	
	
	
	public String ruolo(Object item, String id_giocatore) throws SQLException {
		String ruolo = null;
		int f = Connessione();
		if(f == 1) {
			
			String Query = "SELECT giocatore.Ruolo from giocatore where giocatore.id = '"+id_giocatore+"'";
			smnt = connection.createStatement();
			rs = smnt.executeQuery( Query );
			
			while(rs.next()) {
				//ESTRAPOLAIZONE SQUADRA
				ruolo = rs.getString("Ruolo");
				
			}
			
			
			
		}else {
			ShowError("Errore connessione");
		}	
		
		return ruolo;
		
	}
	
	

	
	
}