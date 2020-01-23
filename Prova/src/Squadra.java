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

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Squadra extends JFrame{

	private String Nome;
	private int punti;
	private String Presidente;
	private JTable table;
	private JSONObject jsonObject;
	private JSONObject record;
	
	public Squadra(String Name, int pt, String Boss) {
		
		this.setNome(Name);
		this.setPunti(pt);
		this.setPresidente(Boss);
				
	}
	
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public Squadra(Driver drive) {
		setResizable(false);
		setTitle("LIST SQUADRE") ;
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\logo2.png"));
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 988, 388);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 16, 927, 190);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"id_Team", "Nome", "Vinte", "Pareggiate", "Perse", "Giocate", "GF", "GS", "Punti ", "Presidente", "Allenatore", "Stadio"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(8).setResizable(false);
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				drive.ShowGui();
				drive.NotShowSquadre();
				
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\back.png"));
		lblNewLabel.setBounds(29, 252, 103, 64);
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setBounds(117, 222, 837, 110);
		contentPane.add(label);
		

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
			String Query = "Select * from  squadra";
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
		         
		         
		        
		         array.put(record);
		         vettore.add(array);
		         
//		       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
		         Object o[] = { record.get("id_Team"), record.get("Nome"), record.get("Vinte"), record.get("Pareggiate"), record.get("Perse"), record.get("Giocate"), record.get("GF"), record.get("GS"), record.get("Punti"), record.get("Presidente"), record.get("Allenatore"), record.get("Stadio")};
			  //AGGIUNGO RECORD ALLA TABELLA
		         dtm.addRow(o);
		         
		         
		     }
			 
			
			 
			
			 
			
			connection.close();  
		}catch(Exception e){
			System.out.println(e);
			}  
		
		
	}
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}
	public String getPresidente() {
		return Presidente;
	}
	public void setPresidente(String presidente) {
		Presidente = presidente;
	}
}
