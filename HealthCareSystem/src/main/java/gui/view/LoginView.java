package gui.view;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.controller.*;
import gui.utils.*;

/**
 * The view for the Login Screen
 * @author pieterohearn
 *
 */
public class LoginView extends JFrame {
	// Static variables
	private static final long serialVersionUID = 8981053836072595592L;
	
	// Instance variables
	private JButton btnLogin;
	private JTextField txtLogin;
	private JPasswordField txtPass;
	private LoginController controller;

	/**
	 * Constructor for the Login View sets up the view by calling the initGUI() method.
	 * @param controller for the Login View
	 */
	public LoginView(LoginController controller) {
		// set the LoginView and call initGUI
		this.controller = controller;
		initGUI();
	}
	
	/**
	 * Initialises the Graphical User Interface
	 */
	private void initGUI() {
		// set the Default Close Operation, Tile and Window Size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Login");
		setLayout(new GridBagLayout());
		
		// Create Text fields for the Login and password 
		txtLogin = new JTextField(15);
		txtPass = new JPasswordField(15);
		
		// Create a new button "Login" with an Action Listener
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// When clicked validate the given credentials
				controller.validateCredentials(txtLogin.getText(), String.valueOf(txtPass.getPassword()));
			}
		});
		
		// Add Labels, Text Fields and the Login button using the Grid Layout to the Login Frame
		add(new JLabel("Username:"), GridBagLayoutUtils.constraint(0, 0, 5));
		add(txtLogin, GridBagLayoutUtils.constraint(1, 0, 5));
		add(new JLabel("Password:"), GridBagLayoutUtils.constraint(0, 1, 5));
		add(txtPass, GridBagLayoutUtils.constraint(1, 1, 5));
		add(btnLogin, GridBagLayoutUtils.constraint(1, 2, 5));
		
		// Pack the content to the window and place in the window in the center of the screen
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Shows an Error when the Wrong Username or password are entered.
	 */
	public void showError() {
		// Open an OptionOane with a Login Error message
		JOptionPane.showMessageDialog(this, "Wrong username/password combination", "Login error", JOptionPane.ERROR_MESSAGE);
	}
}