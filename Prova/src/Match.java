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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.ImageIcon;

public class Match extends JDialog {
	private JTable table;
	private JTable table_1;
	

	public Match(Driver drive) {
		setTitle("CALENDARIO");
		setBounds(100, 100, 1121, 461);	
			getContentPane().setLayout(null);
			
			JLabel label = new JLabel("");
			label.setBounds(31, 87, 646, 245);
			
			
			LineBorder line = new LineBorder(Color.blue, 3);
		    label.setBorder(new LineBorder(new Color(0, 0, 255), 3));

			
			getContentPane().add(label);
	
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(31, 87, 646, 245);
			getContentPane().add(scrollPane);
			
			CreaTabellaMatch(scrollPane);
			
			drive.PopolaTabellaMatch(table);
			
			//CHANGE COLOR, FONT HEADER 1ST JTABLE
			JTableHeader header = table.getTableHeader();
			header.setBackground(new Color(65,105,225));
		    header.setForeground(Color.white);
		    header.setFont(new Font("Tahoma", Font.BOLD, 13));
		    
			
			
			//CHANGE BG COLOR WHEN ROW SELECTED
			table.setSelectionBackground(new Color(248, 0, 0));
			
			
			//CREATE 2ND PANEL WHEN ROW SELECTED
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {

					
					//CHANGE COLOR, FONT HEADER 1ST JTABLE
					
//					header.setBackground(new Color(192,192,192));
//				    header.setForeground(Color.black);
				    
					
					
					//VISUALIZZAZIONE SECONDA TABELLA
					JScrollPane scrollPane_1 = new JScrollPane();
					
					scrollPane_1.setBounds(550, 87, 500, 213);
					scrollPane.setBounds(31, 87, 496, 241);
					label.setBounds(29, 85, 501, 246);
					
					
					getContentPane().add(scrollPane);
					getContentPane().add(scrollPane_1);
					
					table_1 = new JTable();
					table_1.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null},
						},
						new String[] {
							"Nome", "Cognome", "Partita", "Squadra", "NMaglia", "Time"
						}
					));
					scrollPane_1.setViewportView(table_1);
					
					//CHANGE COLOR, FONT HEADER 2ND JTABLE
					JTableHeader header2 = table_1.getTableHeader();
					header2.setBackground(new Color(255, 0, 0));
				    header2.setForeground(Color.white);
				    header2.setFont(new Font("Tahoma", Font.BOLD,13));
					
					
					
					
				    
				   
					drive.PopolatabellaGoal(table_1, table);
					
					
				}
			});
		
		
	}
	
	
	
	public void CreaTabellaMatch(JScrollPane scrollPane) {
		
		
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
}
