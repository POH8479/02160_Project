package gui.model;

import java.util.ArrayList;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;

import hospitalmanagementsystem.departments.Department;
import hospitalmanagementsystem.users.Admin;
import hospitalmanagementsystem.users.HealthStaff;
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
	
	public void removeUser(String userID, Admin admin) {
		User toRemove = findUser(userID);
		
		// remove from list
		users.remove(toRemove);
		
		// remove form HMS
		admin.removeUser(toRemove);
		
		
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
			return "Address";
		} else if (column == 4) {
			return "Phone Number";
		}
		return null;
	}

	public void updateDepartment(String userID, Department dept) {
		// find the user
		HealthStaff u = (HealthStaff) findUser(userID);
		
		// update the users department
		u.moveDepartment(dept);
		
		fireTableDataChanged(); // notify the views that data changed
	}
	
	private User findUser(String userId) {
		// search for a match in the user List
		for(User u : users) {
			if(u.getUserID().equals(userId)) {
				// return
				return u;
			}
		}
		// else return null
		return null;
	}

	public void edit(String userId, String name, String address, String phone) {
		// find the user
		User toEdit = findUser(userId);
		
		// edit
		toEdit.setAddress(address);
		toEdit.setPhone(phone);
		toEdit.setUserName(name);
		
		fireTableDataChanged(); // notify the views that data changed
	}
}
