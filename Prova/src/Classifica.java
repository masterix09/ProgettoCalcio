import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.JLabel;
import java.awt.Color;

public class Classifica extends JDialog {
	private JTable table;


	public Classifica(Driver drive) {
		setBounds(100, 100, 638, 378);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 38, 568, 220);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"SQUADRA", "PT", "GF", "GS"
			}
		));
		scrollPane.setViewportView(table);
		
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
			String Query = "SELECT Punti, Nome, GF, GS FROM `squadra` ORDER BY Punti DESC";
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
		         record.put("Nome", rs.getString("Nome"));
		         record.put("Punti", rs.getInt("Punti"));
		         record.put("GF", rs.getInt("GF"));
		         record.put("GS", rs.getInt("GS"));
		         array.put(record);
		         vettore.add(array);
		         
//		       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
		         Object o[] = { record.get("Nome"), record.get("Punti"), record.get("GF"), record.get("GS")};
			  //AGGIUNGO RECORD ALLA TABELLA
		         dtm.addRow(o);
		         
		       
		         
		         
	}
			 
		connection.close();  
		}catch(Exception e){
			System.out.println(e);
			}
		
	}
}
