package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import gui.controller.AdvancedQueryController;
import gui.model.Session;

public class AdvancedQueryView extends JFrame {
	// Static variables
	private static final long serialVersionUID = -390956995315198339L;
	
	//Instance variables
	private AdvancedQueryController controller;
	private JTable tblPatients;
	private JLabel lblSession;
	private JScrollPane patientPane;
	private JTextField idSearchField;
	private JTextField nameSearchField;
	private JTextField surnameSearchField;
	private JTextField departmentSearchField;

	/**
	 * Constructor for the AdvancedQuery View sets up the view by calling the initGUI() method.
	 * @param controller The AdvancedQuery Controller
	 */
	public AdvancedQueryView(AdvancedQueryController advancedQueryController) {
		// set the AdvancedQueryView and call initGUI
		this.controller = advancedQueryController;
		initGUI();
	}
	
	/**
	 * Initialises the Graphical User Interface
	 */
	private void initGUI() {
		// set the Default Close Operation, Tile and Window Size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hospital Management System");
		setPreferredSize(new Dimension(800, 600));
		
		// BUTTONS
		// Create a new button "Back" with an Action Listener
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked go back
				controller.back();
			}
		});
		
		// Create a new button "Query" with an Action Listener
		JButton btnQuery = new JButton("Query");
		btnQuery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked reset to the original patient model
				controller.query();
			}
		});
		
		// TOOLBAR
		// create a new label and set Horizontal Alignment right
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// create a label and Text are for the search bar
		JLabel lblSearchID = new JLabel("  ID:");
		idSearchField = new JTextField(5);
		JLabel lblSearchName = new JLabel("First Name:");
		nameSearchField = new JTextField(5);
		JLabel lblSurnameSearch = new JLabel("Last Name:");
		surnameSearchField = new JTextField(5);
		JLabel lblSearchDepartment = new JLabel("Department:");
		departmentSearchField = new JTextField(5);
		
		// create a new JToolBar and add all search features
		JToolBar searchBar = new JToolBar();
		searchBar.add(btnBack);
		searchBar.add(lblSearchID);
		searchBar.add(idSearchField);
		searchBar.add(lblSearchName);
		searchBar.add(nameSearchField);
		searchBar.add(lblSurnameSearch);
		searchBar.add(surnameSearchField);
		searchBar.add(lblSearchDepartment);
		searchBar.add(departmentSearchField);
		searchBar.add(btnQuery);
		searchBar.add(lblSession);
		
		// Place the searchBar in the Frame
		add(searchBar, BorderLayout.NORTH);
		
		// TABLES
		// create a table for the patients with an Selection Listener and set the Selection mode to single selection
		tblPatients = new JTable();
		tblPatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPatients.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// When a row is selected enable the following buttons
//				btnEdit.setEnabled((tblPatients.getSelectedRow() >= 0));
			}
		});
		
		// add the patient table to a new JScrollPane and that pane to the User Frame
		patientPane = new JScrollPane(tblPatients);
		add(patientPane);
		
		// Pack the content to the window and place in the window in the center of the screen
		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * Sets the table model for the Patients tables
	 * @param patientModel The Model for the Patient table
	 */
	public void setTableModel(TableModel patientModel) {
		// set the model of the table user
		tblPatients.setModel(patientModel);
	}

	/**
	 * Sets session label with current Users name.
	 * @param sessionModel the Session User
	 */
	public void setSession(Session sessionModel) {
		// set the text of the lblSession
		lblSession.setText("<html>" + sessionModel.getUser().getUserName() + " <i>(" + sessionModel.getUser().getType()+ ") </i></html>");
	}
}