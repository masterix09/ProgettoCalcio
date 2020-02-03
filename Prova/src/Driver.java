import java.sql.*;

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
		
		Team Team = new Team(this);
		Team.setVisible(true);
		
		
	}
	
	public void ShowClassifica() {
		
		Classifica Ranking = new Classifica(this);
		Ranking.setVisible(true);
		
		
	}


	public void ShowPartite() {
		
		Match match = new Match(this);
		match.setVisible(true);
		
	}
	
	
	
	public void ShowGui() {
			
			GUI gui = new GUI(this);
			gui.setVisible(true);
			
			
		}
	
	public void NotShowPartite() {
		
		Match match = new Match(this);
		match.setVisible(false);
			
	}
	
	
	public void NotShowClassifica() {
		
		Classifica Ranking = new Classifica(this);
		Ranking.setVisible(false);
			
	}
	
	public void NotShowSquadre() {
		
		Team Team = new Team(this);
		Team.setVisible(false);
			
	}
	
	
	public void NotShowGui() {
			
			GUI gui = new GUI(this);
			gui.setVisible(false);
				
		}
		
	public void ShowAggiorna() {
		
		Update Upload = new Update(this);
		Upload.setVisible(true);
		
	}
	
	
	public void NotShowAggiorna() {
		Update Upload = new Update(this);
		Upload.setVisible(false);
		
	}
	
}
