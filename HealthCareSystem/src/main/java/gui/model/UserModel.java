package gui.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import hospitalmanagementsystem.users.User;

public class UserModel extends AbstractTableModel {

	private static final long serialVersionUID = -8100080945085186023L;
	private ArrayList<User> users;
	
	public UserModel() {
		users = new ArrayList<User>();
	}
	
	public void addNewUser(User newUser) {
		users.add(newUser);
		fireTableDataChanged(); // notify the views that data changed
	}
	
	public void removeUser(String name) {
		
		fireTableDataChanged(); // notify the views that data changed
	}
	
	
	// methods below to extend table model
	
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
			return users.get(rowIndex).getDepartment().getName();
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
			return "Address";
		} else if (column == 4) {
			return "Phone Number";
		}
		return null;
	}
}
