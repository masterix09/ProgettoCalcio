import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.awt.event.ActionEvent;

public class Update extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	public Update(Driver drive) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(32, 16, 356, 151);
		getContentPane().add(textArea);
		
		JButton btnScegli = new JButton("SCEGLI");
		btnScegli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Scegli File");
				int result = fc.showSaveDialog(null);

				File file = fc.getSelectedFile();
				Reader in = null;
				try {
					in = new FileReader(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
			}
		});
		btnScegli.setBounds(298, 199, 115, 29);
		getContentPane().add(btnScegli);
		
		
		
	}
}
