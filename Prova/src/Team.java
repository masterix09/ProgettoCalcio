import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.ImageIcon;

public class Team extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTable table_2;
	private int index;
	private int index2;
	private String id_squadra;
	private String id_giocatore;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private JLabel lblBack;
	
	public Team(Driver drive, Object item, String user, String pass) {
		index = 0;
		index2 = 0;
		id_squadra = "";
		id_giocatore = "";
		
		setTitle("LISTA SQUADRE");
		setBounds(100, 100, 787, 621);
		getContentPane().setLayout(null);
		
			scrollPane = new JScrollPane();
			 scrollPane2 = new JScrollPane();
			scrollPane.setBounds(15, 203, 727, 244);
			getContentPane().add(scrollPane);
			
			//CREAZIONE TABELLA LISTA SQUADRE
			
					
					CreaTabella(scrollPane);
					
					drive.PopolaTabellaTeam(table, item);
					drive.NotShowCampionatoDialog();
					
					JLabel lblLogo = new JLabel("\t");
					lblLogo.setIcon(new ImageIcon("C:\\Users\\Pablo\\git\\ProgettoCalcio-miaCopia\\Prova\\img\\logo off.png"));
					lblLogo.setBounds(15, 16, 274, 185);
					getContentPane().add(lblLogo);
					
					JLabel lblScrittaSquadre = new JLabel("");
					lblScrittaSquadre.setIcon(new ImageIcon("C:\\Users\\Pablo\\git\\ProgettoCalcio-miaCopia\\Prova\\img\\SCRITTA SQUADRE.png"));
					lblScrittaSquadre.setBounds(291, 16, 432, 185);
					getContentPane().add(lblScrittaSquadre);
					
					lblBack = new JLabel("");
					lblBack.setIcon(new ImageIcon("C:\\Users\\Pablo\\git\\ProgettoCalcio-miaCopia\\Prova\\img\\back.png"));
					lblBack.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							
							drive.ShowCampionatoDialog(item, user, pass);
							drive.NotShowSquadre();
						}
					});
					lblBack.setBounds(15, 467, 201, 98);
					getContentPane().add(lblBack);
					
				
							
							
							table.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent arg0) {
									
									//SALVATAGGIO RIGA SELEZIONATA
									index = table.getSelectedRow();
									TableModel model = table.getModel();
									
									//SALVATAGGIO ID DELLA RIGA SELEZIONATA IN PRECEDENZA
									id_squadra = model.getValueAt(index, 0).toString();
									
									//PASSAGGIO AL 2ND PANEL
									scrollPane2.setBounds(15, 203, 727, 244);
									scrollPane.setVisible(false);
									getContentPane().add(scrollPane2);
									scrollPane2.setVisible(true);
									
									//CREAZIONE LISTA GIOCATORI
									CreaTabellaLista(scrollPane2);
									
									try {
										drive.PopolaTabellaListaGiocatore(table_2, item, id_squadra);
									} catch (SQLException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
									
									
									
									table_2.addMouseListener(new MouseAdapter() {
										@Override
										public void mouseClicked(MouseEvent arg0) {
											//SALVATAGGIO RIGA SELEZIONATA
											index2 = table_2.getSelectedRow();
											TableModel model = table_2.getModel();
											//SALVATAGGIO ID DELLA RIGA SELEZIONATA IN PRECEDENZA
											id_squadra = model.getValueAt(index2, 0).toString();
											
											//MOSTRO DIALOG GIOCATORE
											
												try {
													drive.ShowGiocatore(item, id_squadra);
													drive.NotShowSquadre();
												} catch (SQLException e) {
													// TODO Auto-generated catch block
													drive.ShowError("ERRORE CONNESSIONE");
												}
											
											
											
										}
									});
									
									
								}
							});
							
							
							
							
							
							
							
							
						
					
			
	}
	
	
	public void CreaTabella(JScrollPane scrollPane) {
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id_Team", "Nome", "Presidente", "Allenatore", "Stadio"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
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
		
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

	        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	        
	        
			table.setRowHeight(25);
			table.getTableHeader().setOpaque(false);
		
		     //CHANGE COLOR, FONT HEADER 1ST JTABLE
				JTableHeader header = table.getTableHeader();
				header.setBackground(new Color(65,105,225));
			    header.setForeground(Color.white);
			    header.setFont(new Font("Tahoma", Font.BOLD, 13));
			
		scrollPane.setViewportView(table);
	}
	
	
	
public void CreaTabellaLista(JScrollPane scrollPane2) {
		
		table_2 = new JTable();
		table_2.setShowVerticalLines(false);
		table_2.setRowSelectionAllowed(false);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id", "Nome", "Cognome", "Squadra"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(0).setResizable(false);
		table_2.getColumnModel().getColumn(1).setResizable(false);
		table_2.getColumnModel().getColumn(2).setResizable(false);
		table_2.getColumnModel().getColumn(3).setResizable(false);
		
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

	        table_2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	        table_2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	        table_2.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        table_2.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	        
			table_2.setRowHeight(25);
			table_2.getTableHeader().setOpaque(false);
		
			
		     //CHANGE COLOR, FONT HEADER 1ST JTABLE
				JTableHeader header = table_2.getTableHeader();
				header.setBackground(new Color(65,105,225));
			    header.setForeground(Color.white);
			    header.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		scrollPane2.setViewportView(table_2);
	}
	
}
