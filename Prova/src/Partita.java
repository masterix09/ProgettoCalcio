import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextArea;




public class Partita extends JFrame{

	private Driver drive;
	private String stadio;
	private int orario;
	private String Squadra1;
	private String Squadra2;
	private JTable table;
	private JSONObject jsonObject;
	private JSONObject record;
	
	
//	public Partita(String Name, int time, String team1, String team2) {
//		
//		this.setStadio(Name);
//		this.setOrario(time);
//		this.setSquadra1(team1);
//		this.setSquadra2(team2);
//		
//	}

	public Partita(Driver drive) {
		
		setResizable(false);
		setTitle("LISTA MATCH") ;
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\power\\git\\atomHuni\\ProgettoOODB\\img\\logo2.png"));
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 339);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 16, 711, 119);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"ID", "DATA", "GIORNATA", "GOAL CASA", "GOAL TRASFERTA", "ARBITRO"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				drive.ShowGui();
				drive.NotShowPartite();
				
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\power\\git\\atomHuni\\ProgettoOODB\\img\\back.png"));
		lblNewLabel.setBounds(34, 188, 70, 64);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblNewLabel);
		
		
	
		
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
			String Query = "Select * from  partita";
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
		         record.put("id_partita", rs.getInt("id_partita"));
		         record.put("Data", rs.getString("Data"));
		         record.put("Giornata", rs.getString("Giornata"));
		         record.put("Gol_C", rs.getInt("Gol_C"));
		         record.put("Gol_F", rs.getInt("Gol_F"));
		         record.put("Arbitro", rs.getString("Arbitro"));
		         array.put(record);
		         vettore.add(array);
		         
//		       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
		         Object o[] = { record.get("id_partita"), record.get("Data"), record.get("Giornata"), record.get("Gol_C"), record.get("Gol_F"), record.get("Arbitro")};
			  //AGGIUNGO RECORD ALLA TABELLA
		         dtm.addRow(o);
		         
		     }
			 
			
			 
			
			 
			
			connection.close();  
		}catch(Exception e){
			System.out.println(e);
			}  

	}


	public String getStadio() {
		return stadio;
	}



	public void setStadio(String stadio) {
		this.stadio = stadio;
	}



	public int getOrario() {
		return orario; 
	}



	public void setOrario(int orario) {
		this.orario = orario;
	}



	public String getSquadra1() {
		return Squadra1;
	}



	public void setSquadra1(String squadra1) {
		Squadra1 = squadra1;
	}



	public String getSquadra2() {
		return Squadra2;
	}



	public void setSquadra2(String squadra2) {
		Squadra2 = squadra2;
	}
}
