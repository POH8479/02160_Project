package gui.model;

import java.util.ArrayList;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;
import hospitalmanagementsystem.users.User;

/**
 * The Model for the User's Table when a search has been performed.
 * @author Pieter O'Hearn
 *
 */
public class UserSearchModel extends AbstractTableModel {
	// Static variables
	private static final long serialVersionUID = 3253845545497395850L;
	
	// Instance variables
	private ArrayList<User> users;
	
	/**
	 * Constructor of the UserSearchModel. Creates an array of patients found in a search.
	 */
	public UserSearchModel(ArrayList<User> searchedUsers) {
		// set the UserSearchModel user list to searchedUsers
		this.users = searchedUsers;
	}
	
	/////////////////////////////////////////
	// METHODS BELOW TO EXTEND TABLE MODEL //
	/////////////////////////////////////////
	
	@Override
	public int getColumnCount() {
		return 5; // this is fixed: product and quantity
	}

	@Override
	public int getRowCount() {
		return users.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return users.get(rowIndex).getUserID();
		} else if (columnIndex == 1) {
			return users.get(rowIndex).getUserName();
		} else if (columnIndex == 2) {
			if(Objects.equals(null, users.get(rowIndex).getDepartment())) {
				return "-";
			} else {
				return users.get(rowIndex).getDepartment().getName();
			}
		} else if (columnIndex == 3) {
			return users.get(rowIndex).getAddress();
		} else if (columnIndex == 4) {
			return users.get(rowIndex).getNumber();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "User ID";
		} else if (column == 1) {
			return "Name";
		} else if (column == 2) {
			return "Department";
		} else if (column == 3) {
			return "Email";
		} else if (column == 4) {
			return "Phone Number";
		}
		return null;
	}
}