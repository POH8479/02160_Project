package gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import gui.controller.UserController;
import gui.model.Session;
import hospitalmanagementsystem.Query;

/**
 * The view for the General User Users
 * @author Pieter O'Hearn
 *
 */
public class UserView extends JFrame {
	// Static variables
	private static final long serialVersionUID = 2990788311108997547L;
	
	// Instance variables
	private UserController controller;
	private JTable tblPatients;
	private JLabel lblSession;
	private JScrollPane patientPane;
	private JTextField idSearchField;
	private JTextField nameSearchField;
	private JTextField surnameSearchField;
	private JTextField departmentSearchField;
	
	/**
	 * Constructor for the User View sets up the view by calling the initGUI() method.
	 * @param controller The User Controller
	 */
	public UserView(UserController controller) {
		// set the UserView and call initGUI
		this.controller = controller;
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
		// Create a new button "Edit" with an Action Listener and set Enabled to false
		JButton btnEdit = new JButton("Edit");
		btnEdit.setEnabled(false);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked edit the selected patient
				controller.editPatient(tblPatients.getSelectedRow());
			}
		});
		
		// Create a new button "Register Patient" with an Action Listener and set Enabled to false
		JButton btnRegisterPatient = new JButton("Register Patient");
		btnRegisterPatient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked add a new patient
				controller.addPatient();
			}
		});
		
		// Create a new button "Log Out" with an Action Listener
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked log Out
				controller.logOut();
			}
		});
		
		// Create a new button "Clear" with an Action Listener and set Enabled to false
		JButton btnClear = new JButton("Clear");
		btnClear.setEnabled(false);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked reset to the original patient model
				controller.clearSearch();
				btnClear.setEnabled(false);
			}
		});
		
		// Create a new button "Search" with an Action Listener
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked log Out
				btnClear.setEnabled(true);
				controller.patientSearch(idSearchField.getText(), nameSearchField.getText(), surnameSearchField.getText(), departmentSearchField.getText());
			}
		});
		
		// Create a new button "Query" with an Action Listener
		JButton btnQuery = new JButton("Query");
		btnQuery.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked Display the JPanel using a JOptionPane and store the confirmation result
				JPanel queryPanel = new JPanel();
				String[] typesOfQuerys = { "Department with most Patients", "Department with least Patients", "Department with most Users", "Department with least Users", "Total number of Patients", "Total number of Users" };
				JComboBox<String> querys = new JComboBox<String>(typesOfQuerys);
				queryPanel.add(querys);
	
		        int confirmation = JOptionPane.showConfirmDialog(null, queryPanel, "Please Choose an Advanced Query", JOptionPane.OK_CANCEL_OPTION);

		        // check the confirmation result
		        if (confirmation == JOptionPane.OK_OPTION) {
		        	// if OK was selected create a new result label
		        	JLabel result = new JLabel();
		        	
		        	// create a new Advanced Query Object
		        	Query q = new Query();
		        	
		        	// check what query was selected
		        	if(querys.getSelectedIndex() == 0) {
		        		// Print the result
		        		result.setText("Department with most Patients:  " + q.depMostPatients());
		        	} else if(querys.getSelectedIndex() == 1) {
		        		// Print the result
		        		result.setText("Department with least Patients:  " + q.depLeastPatients());
		        	} else if(querys.getSelectedIndex() == 2) {
		        		// Print the result
		        		result.setText("Department with most Users:  " + q.depMostUsers());
		        	} else if(querys.getSelectedIndex() == 3) {
		        		// Print the result
		        		result.setText("Department with least Users:  " + q.depLeastUsers());
		        	} else if(querys.getSelectedIndex() == 4) {
		        		// Print the result
		        		result.setText("Total number of Patients:  " + q.totPatients());
		        	} else if(querys.getSelectedIndex() == 5) {
		        		// Print the result
		        		result.setText("Total number of Users:  " + q.totUsers());
		        	} 
		        	
		        	JOptionPane.showConfirmDialog(null, result, "Advanced Query Result", JOptionPane.OK_CANCEL_OPTION);
		        }
			}
		});
		
		// TOOLBAR
		// create a new label and set Horizontal Alignment right
		lblSession = new JLabel();
		lblSession.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// create a new JToolBar and add all buttons and the above label
		JToolBar toolbar = new JToolBar();
		toolbar.add(btnRegisterPatient);
		toolbar.add(btnEdit);
		toolbar.add(btnQuery);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(lblSession);
		toolbar.add(btnLogOut);
		
		// create a label and Text are for the search bar
		JLabel lblSearchID = new JLabel("ID:");
		idSearchField = new JTextField(5);
		JLabel lblSearchName = new JLabel("First Name:");
		nameSearchField = new JTextField(5);
		JLabel lblSurnameSearch = new JLabel("Last Name:");
		surnameSearchField = new JTextField(5);
		JLabel lblSearchDepartment = new JLabel("Department:");
		departmentSearchField = new JTextField(5);
		
		// create a new JToolBar and add all search features
		JToolBar searchBar = new JToolBar();
		searchBar.add(lblSearchID);
		searchBar.add(idSearchField);
		searchBar.add(lblSearchName);
		searchBar.add(nameSearchField);
		searchBar.add(lblSurnameSearch);
		searchBar.add(surnameSearchField);
		searchBar.add(lblSearchDepartment);
		searchBar.add(departmentSearchField);
		searchBar.add(btnSearch);
		searchBar.add(btnClear);
		
		// Place the toolbars in a JPanel with a Grid Layout and add to the Frame
		JPanel toolbars = new JPanel(new GridLayout(0, 1));
		toolbars.add(toolbar);
		toolbars.add(searchBar);
		add(toolbars, BorderLayout.NORTH);
		
		// TABLES
		// create a table for the patients with an Selection Listener and set the Selection mode to single selection
		tblPatients = new JTable();
		tblPatients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblPatients.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// When a row is selected enable the following buttons
				btnEdit.setEnabled((tblPatients.getSelectedRow() >= 0));
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

	/**
	 * Shows an Error Dialog with a given message
	 * @param message The given message
	 */
	public void showError(String errorMessage) {
		// Show an Error message in a new JOptionPane
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
}