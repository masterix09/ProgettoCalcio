import java.sql.*;

public class Driver {

	private Persona People;
	private Giocatore Player;
	private Squadra Team;	
	private Stadio Stadium;
	private Goal Gol;
	private Partita Match;
	private GUI gui;
	private Aggiornamento Upload;
	
	public static void main(String[] args) throws SQLException {
		
		Driver main = new Driver();
		GUI gui = new GUI(main);
		gui.setVisible(true);
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			String connectionString="jdbc:mysql://localhost:3306/serie_a?user=root&password=";
			Connection connection;
			connection = DriverManager.getConnection(connectionString);
			
			Statement stmt=connection.createStatement();
			//Eseguo la Query
			ResultSet rs=stmt.executeQuery("select * from partita");
			connection.close();  
			
			}catch(Exception e){ System.out.println(e);}  
			}  

		
	
	
	public Driver(){
		

	}
	
	public void ShowSquadre() {
		
		Squadra Team = new Squadra(this);
		Team.setVisible(true);
		
		
	}
	
	
	public void ShowPartite() {
		
		Partita match = new Partita(this);
		match.setVisible(true);
		
	}
	
	
	
	public void ShowGui() {
			
			GUI gui = new GUI(this);
			gui.setVisible(true);
			
			
		}
	
	public void NotShowPartite() {
		
		Partita match = new Partita(this);
		match.setVisible(false);
			
	}
	
	public void NotShowSquadre() {
		
		Squadra Team = new Squadra(this);
		Team.setVisible(false);
			
	}
	
	
	public void NotShowGui() {
			
			GUI gui = new GUI(this);
			gui.setVisible(false);
				
		}
		
	public void ShowAggiorna() {
		
		Aggiornamento Upload = new Aggiornamento(this);
		Upload.setVisible(true);
		
	}
	
	
	public void NotShowAggiorna() {
		Aggiornamento Upload = new Aggiornamento(this);
		Upload.setVisible(false);
		
	}
	
}
