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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.JLabel;
import java.awt.Color;

public class Classifica extends JDialog {
	private JTable table;


	public Classifica(Driver drive){
		
		
		setBounds(100, 100, 638, 378);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 38, 568, 220);
		getContentPane().add(scrollPane);
		
		
		CreaTabella(scrollPane);
		
		drive.PopolaTabellaClassifica(table);
		
	}
	
	public void CreaTabella(JScrollPane scrollPane) {
		
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
		
	}
	
	
	
		
}
	
	

