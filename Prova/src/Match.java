import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Match extends JDialog {
	private JTable table;
	private JTable table_1;
	

	public Match(Driver drive, Object item, String user, String pass) {
		setTitle("CALENDARIO");
		setBounds(100, 100, 682, 569);	
			getContentPane().setLayout(null);
			
			
			LineBorder line = new LineBorder(Color.blue, 3);
			JScrollPane scrollPane_1 = new JScrollPane();
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(5, 160, 646, 245);
			getContentPane().add(scrollPane);
			
			CreaTabellaMatch(scrollPane);
			
			
			drive.PopolaTabellaMatch(table, item);
			
			//CHANGE COLOR, FONT HEADER 1ST JTABLE
			JTableHeader header = table.getTableHeader();
			header.setBackground(new Color(65,105,225));
		    header.setForeground(Color.white);
		    header.setFont(new Font("Tahoma", Font.BOLD, 13));
		    
			
			
			//CHANGE BG COLOR WHEN ROW SELECTED
			table.setSelectionBackground(new Color(248, 0, 0));
			
			JLabel label = new JLabel("");
			scrollPane.setColumnHeaderView(label);
			label.setBorder(new LineBorder(new Color(0, 0, 255), 3));
			
			JLabel lblLogo = new JLabel("");
			ImageIcon LogoImage = new ImageIcon(Login.class.getResource("img/logo off.png"));
			lblLogo.setIcon(LogoImage);
			lblLogo.setBounds(5, 0, 200, 158);
			getContentPane().add(lblLogo);
			
			JLabel lblScrittaCalendario = new JLabel("SCRITTA CALENDARIO");
			ImageIcon CalendarioImage = new ImageIcon(Login.class.getResource("img/SCRITTA CALENDARIO.png"));
			lblScrittaCalendario.setIcon(CalendarioImage);
			lblScrittaCalendario.setBounds(214, 33, 431, 98);
			getContentPane().add(lblScrittaCalendario);
			
			JLabel lblBack = new JLabel("");
			ImageIcon BackImage = new ImageIcon(Login.class.getResource("img/back.png"));
			lblBack.setIcon(BackImage);
			lblBack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					drive.ShowCampionatoDialog(item, user, pass);
					drive.NotShowPartite();
					
				}
			});
			lblBack.setBounds(5, 421, 211, 92);
			getContentPane().add(lblBack);
			
			
			//CREATE 2ND PANEL WHEN ROW SELECTED
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {

					
					//CHANGE COLOR, FONT HEADER 1ST JTABLE
					
//					header.setBackground(new Color(192,192,192));
//				    header.setForeground(Color.black);
				    
					
					
					//VISUALIZZAZIONE SECONDA TABELLA
		
					setBounds(100, 100, 1135, 569);
					scrollPane_1.setBounds(665, 160, 450, 213);
					scrollPane.setBounds(15, 160, 646, 245);
					label.setBounds(25, 85, 501, 246);
					
					
					getContentPane().add(scrollPane);
					getContentPane().add(scrollPane_1);
					
					table_1 = new JTable();
					table_1.setShowVerticalLines(false);
					table_1.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null},
						},
						new String[] {
							"Nome", "Cognome", "Partita", "Squadra", "NMaglia", "Time"
						}
					));
					
					 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

				        table_1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
				        table_1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				        table_1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
				        table_1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				        table_1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
				        table_1.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
				        
						table_1.setRowHeight(25);
						table_1.getTableHeader().setOpaque(false);
					
					
					scrollPane_1.setViewportView(table_1);
					
					//CHANGE COLOR, FONT HEADER 2ND JTABLE
					JTableHeader header2 = table_1.getTableHeader();
					header2.setBackground(new Color(255, 0, 0));
				    header2.setForeground(Color.white);
				    header2.setFont(new Font("Tahoma", Font.BOLD,13));
					
					
					drive.PopolatabellaGoal(table_1, table);
						
				}
			});
			
			
			drive.NotShowCampionatoDialog();
	}
	
	
	
	public void CreaTabellaMatch(JScrollPane scrollPane) {
		
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setShowVerticalLines(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "GIORNATA", "CASA", "OSPITE", "GOAL CASA", "GOAL OSPITE", "ARBITRO"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
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
		

		    
			
			
		
		scrollPane.setViewportView(table);
		
	}
}
