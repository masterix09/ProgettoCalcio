import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class Classifica extends JDialog {
	private JTable table;
	private JButton btnsquadra;
	private JButton btnmarcatori;	
	private JTable table_1;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane_1;
	private JLabel lblLogo;
	private JLabel label;
	private JLabel lblBack;

	public Classifica(Driver drive, Object item, String user, String pass){
		setTitle("CLASSIFICA");
		
		setBounds(100, 100, 1058, 573);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 190, 584, 230);
		getContentPane().add(scrollPane);
		
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(15, 190, 571, 230);
		
		
			//CREAZIONE TABELLA CLASSIFICA SQUADRE
		
		CreaTabella(scrollPane);
		
		drive.PopolaTabellaClassifica(table, item);
		
		
		btnsquadra = new JButton("CLASSIFICA SQUADRA");
		btnsquadra.setVisible(false);
		btnsquadra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PASSAGGIO AL 1st PANEL
				scrollPane2.setVisible(false);
				scrollPane_1.setVisible(false);
				btnsquadra.setVisible(false);
				btnmarcatori.setVisible(true);
				scrollPane.setVisible(true);
				
				
				
				getContentPane().add(scrollPane);
				
				//CREAZIONE TABELLA CLASSIFICA SQUADR
				CreaTabella(scrollPane);
				drive.PopolaTabellaClassifica(table, item);
				scrollPane.setViewportView(table);
				
				
				
			}
		});
		
		
		
		btnmarcatori = new JButton("CLASSIFICA MARCATORI");
		btnmarcatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//PASSAGGIO AL 2 PANEL
				btnsquadra.setBounds(653, 190, 223, 29);
				scrollPane.setVisible(false);
				btnmarcatori.setVisible(false);
				btnsquadra.setVisible(true);
				scrollPane2.setVisible(true);

				getContentPane().add(scrollPane2);
				//CREAZIONE TABELLA CLASSIFICA MARCATORI
				CreaTabellaMarcatori(scrollPane2);
				
				drive.PopolaTabellaMarcatori(table, item);
				scrollPane2.setViewportView(table);
				

				//CREAZIONE 3RD PANEL PER VISUALIZZARE IN CHE PARTITA HA SEGNATO 
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setVisible(false);
				scrollPane_1.setBounds(663, 190, 358, 192);
				getContentPane().add(scrollPane_1);
				
				//CREAZIONE TABELLA VITTIME GIOCATORE
				table_1 = new JTable();
				table_1.setVisible(false);
				table_1.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null},
					},
					new String[] {
						"Nome", "Cognome", "Partita"
					}
				));
				
				table_1.setShowVerticalLines(false);
				table_1.setRowHeight(25);
				

				 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

			        table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			        table_1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
			        table_1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
			        
				
				
				//CHANGE COLOR, FONT HEADER 2ND JTABLE
				JTableHeader header2 = table_1.getTableHeader();
				header2.setBackground(new Color(255, 0, 0));
			    header2.setForeground(Color.white);
			    header2.setFont(new Font("Tahoma", Font.BOLD,13));
				
				
				scrollPane_1.setViewportView(table_1);
				
				
				
				table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						//PASSAGGIO AL 3RD PANEL CON CREAZIONE TABELLA
						btnsquadra.setBounds(653, 63, 195, 29);
						scrollPane_1.setVisible(true);
						table_1.setVisible(true);
						drive.PopolaTabellaListaVittimeGiocatore(table_1, table);
						
					}
				});
				
					
				
			}
		});
		btnmarcatori.setBounds(670, 195, 225, 29);
		getContentPane().add(btnmarcatori);
		btnsquadra.setBounds(653, 63, 195, 29);
		getContentPane().add(btnsquadra);
		
		lblLogo = new JLabel("");
		ImageIcon LogoImage = new ImageIcon(Login.class.getResource("img/logo off.png"));
		lblLogo.setIcon(LogoImage);
		lblLogo.setBounds(15, 0, 204, 174);
		getContentPane().add(lblLogo);
		
		label = new JLabel("New label");
		ImageIcon ImageClassifica = new ImageIcon(Login.class.getResource("img/scritta classifica.png"));
		label.setIcon(ImageClassifica);
		label.setBounds(223, 0, 415, 174);
		getContentPane().add(label);
		
		lblBack = new JLabel("");
		ImageIcon BackImage = new ImageIcon(Login.class.getResource("img/back.png"));
		lblBack.setIcon(BackImage);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				drive.ShowCampionatoDialog(item, user, pass);
				drive.NotShowClassifica();

			}
		});
		lblBack.setBounds(15, 427, 237, 90);
		getContentPane().add(lblBack);
		
		drive.NotShowCampionatoDialog();
			
	}
	
	public void CreaTabella(JScrollPane scrollPane) {
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null,null, null, null, null},
			},
			new String[] {
				"SQUADRA", "PT","VINTE", "PERSE", "PAREGGIATE", "GF", "GS", 
			}
		));
		
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

	        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
	        

	       table.setRowHeight(25);
	       table.getTableHeader().setOpaque(false);
	        
	     //CHANGE COLOR, FONT HEADER 1ST JTABLE
			JTableHeader header = table.getTableHeader();
			header.setBackground(new Color(65,105,225));
		    header.setForeground(Color.white);
		    header.setFont(new Font("Tahoma", Font.BOLD, 13));
		    
	       
		scrollPane.setViewportView(table);
		
	}
	
	
	
	public void CreaTabellaMarcatori(JScrollPane scrollPane2) {
		
		table = new JTable();
		table.setSelectionBackground(new Color(255, 0, 0));
		table.setShowVerticalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "NOME", "COGNOME", "NGOAL","MAGLIA", "SQUADRA"
			}
		));
		
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

	        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
	        
			table.setRowHeight(25);
			table.getTableHeader().setOpaque(false);

			//CHANGE COLOR, FONT HEADER 1ST JTABLE
			JTableHeader header = table.getTableHeader();
			header.setBackground(new Color(65,105,225));
		    header.setForeground(Color.white);
		    header.setFont(new Font("Tahoma", Font.BOLD, 13));
		    
			
			
		scrollPane2.setViewportView(table);
		
	}
}
	
	

