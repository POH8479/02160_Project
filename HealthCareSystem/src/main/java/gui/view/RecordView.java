package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import gui.controller.ManagementController;
import gui.controller.RecordController;
import gui.model.Session;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RecordView extends JFrame {

	private static final long serialVersionUID = -4273488500574966476L;
	private RecordController controller;
	private JTextArea textArea;
	private JScrollPane jScrollPane1;
	private JLabel lblSession;
	private JLabel medicalRecord;

	public RecordView(RecordController recordController) {
		this.controller = recordController;
		initGUI();
	}
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(String.format("Patient %s Medical Record",controller.getPatientId()));
		setPreferredSize(new Dimension(800, 600));
		
		// buttons
		JButton btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 
				controller.saveRecord(textArea.getText());
			}
		});
		
		// labels
		String formatted = (controller.getRecord() + "\n").replace("\n", "<br>");
		formatted = "<html><font size='9'>Medical Record<br></font><font size='3'><div WIDTH=1>" + formatted + "</div></font></html>";
		medicalRecord = new JLabel(formatted);
		medicalRecord.setHorizontalAlignment(SwingConstants.LEFT);
		
		JScrollPane recordPane = new JScrollPane(medicalRecord);
		recordPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(recordPane, BorderLayout.WEST);
		
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// toolbar
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnSave);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		add(toolbar, BorderLayout.NORTH);
		
		// text area
		textArea = new JTextArea();
		textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setWrapStyleWord(true);
         
        jScrollPane1 = new JScrollPane(textArea);
        jScrollPane1.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // listener
        textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) { }

            @Override
            public void insertUpdate(DocumentEvent e) {
            	btnSave.setEnabled(true);
            }

			@Override
			public void changedUpdate(DocumentEvent e) {}
        });
		
        
        add(jScrollPane1,BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(null);
	}

	public void setSession(Session sessionModel) {
		lblSession.setText("<html>" + sessionModel.getUsername() + " <i>(" + sessionModel.getRole() + ")</i></html>");
	}

}
