import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class Team extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	public Team(Driver drive) {
		setBounds(100, 100, 1038, 300);
		
			{
				JScrollPane scrollPane = new JScrollPane();
				getContentPane().add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.setRowSelectionAllowed(false);
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
						String Query = "select  squadra.id_Team, squadra.Nome, squadra.Vinte, squadra.Pareggiate, squadra.Perse, squadra.Giocate, squadra.GF, squadra.GS, squadra.Punti, squadra.Presidente, allenatore.cognome AS Allenatore, squadra.Stadio from allenatore, squadra where squadra.Allenatore = allenatore.id_allenatore";
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
					         
//					       //CREO OGGETTO CON ALL'INTERNO INTERNO RECORD (OGGETTO DI TIPO JSON)
					         Object o[] = { record.get("id_Team"), record.get("Nome"), record.get("Vinte"), record.get("Pareggiate"), record.get("Perse"), record.get("Giocate"), record.get("GF"), record.get("GS"), record.get("Punti"), record.get("Presidente"), record.get("Allenatore"), record.get("Stadio")};
						  //AGGIUNGO RECORD ALLA TABELLA
					         dtm.addRow(o);
					         
					         //CREAZIONE DI UN FILE CONTENENTE LA TABELLA SQUADRA IN FORMATO JSON
//					         try (BufferedWriter writer = new BufferedWriter(new FileWriter(
//					                 "C:\\Users\\power\\Desktop\\file1.json", true));) {
//					 			writer.write(record.toString());
//					 			System.out.println("Successfully Copied JSON Object to File...");
//					 			System.out.println("\nJSON Object: " + record);
//					 		} 
					         
					         
				}
						 
					connection.close();  
					}catch(Exception e){
						System.out.println(e);
						}

				}
		
			}
	}

}
