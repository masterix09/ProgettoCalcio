import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JScrollPane;

public class Match extends JDialog {
	private JTable table;
	

	public Match(Driver drive) {
		setBounds(100, 100, 702, 313);	
	
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null},
					},
					new String[] {
						"GIORNATA", "CASA", "OSPITE", "GOAL CASA", "GOAL OSPITE", "ARBITRO"
					}
				));
				
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(2).setResizable(false);
				table.getColumnModel().getColumn(3).setResizable(false);
				table.getColumnModel().getColumn(4).setResizable(false);
				table.getColumnModel().getColumn(5).setResizable(false);
				
				
				scrollPane.setViewportView(table);
			}
			
			
			
			try{  
				
				// INIZIO CONNESSIONE DB
				Class.forName("com.mysql.cj.jdbc.Driver");  
				String connectionString="jdbc:mysql://localhost:3306/serie_a?user=root&password=";
				Connection connection;
				connection = DriverManager.getConnection(connectionString);
				//here sonoo is database name, root is username and password  
				Statement stmt=connection.createStatement();  
				
				//FINE CONNESSIONE DB
				//INIZIO FORMULAZIONE QUERY
				String Query = "SELECT partita.Giornata, s.Nome as Casa, s2.Nome AS Ospite, partita.Gol_C AS GoalCasa, partita.Gol_F as GoalOspite, partita.Arbitro\r\n" + 
						"FROM partita\r\n" + 
						"INNER JOIN squadra as s\r\n" + 
						"ON partita.Casa = s.id_Team\r\n" + 
						"INNER JOIN squadra as s2\r\n" + 
						"ON partita.Ospite = s2.id_Team";
				Statement smnt = connection.createStatement();
				ResultSet rs = smnt.executeQuery( Query );
				//FINE FORMULAZIONE QUERY	
				
				//Creo un oggetto JSON			
				JSONObject jsonObject = new JSONObject();
				 //Creo un array di tipo json
			      JSONArray array = new JSONArray();
			      Vector<JSONArray> vettore = new Vector<JSONArray>();
			    //CREAZIONE TABELLA
					DefaultTableModel dtm =(DefaultTableModel)table.getModel();
					//SETTAGGIO ALLA RIGA NUMERO 0
					dtm.setRowCount(0);
			      
			      
				//Inserisco i valori di ritorno della query nell'oggetto JSON
				 while(rs.next()) {
					 
			         JSONObject record = new JSONObject();
			         //Inserting key-value pairs into the json object
//			         record.put("id_partita", rs.getInt("id_partita"));
//			         record.put("Data", rs.getString("Data"));
			         record.put("Casa", rs.getString("Casa"));
			         record.put("Ospite", rs.getString("Ospite"));
			         record.put("Giornata", rs.getString("Giornata"));
			         record.put("Gol_C", rs.getInt("GoalCasa"));
			         record.put("Gol_F", rs.getInt("GoalOspite"));
			         record.put("Arbitro", rs.getString("Arbitro"));
			         array.put(record);
			         vettore.add(array);
			         
//			       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
			         Object o[] = {record.get("Giornata"), record.get("Casa"), record.get("Ospite"), record.get("Gol_C"), record.get("Gol_F"), record.get("Arbitro")};
				  //AGGIUNGO RECORD ALLA TABELLA
			         dtm.addRow(o);
			         
			     }
				 
				
				 
				
				 
				
				connection.close();  
			}catch(Exception e){
				System.out.println(e);
				} 
			
			
			
		}
		
	}

}
