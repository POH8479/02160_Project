package gui.model;

import java.util.ArrayList;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;
import hospitalmanagementsystem.PersistenceLayer;
import hospitalmanagementsystem.departments.*;
import hospitalmanagementsystem.users.Admin;
import hospitalmanagementsystem.users.HealthStaff;
import hospitalmanagementsystem.users.User;

/**
 * The Model for the User's Table.
 * @author pieterohearn
 *
 */
public class UserModel extends AbstractTableModel {
	// static variables
	private static PersistenceLayer persist = new PersistenceLayer();
	
	// Instance variables
	private static final long serialVersionUID = -8100080945085186023L;
	private ArrayList<User> users;

	/**
	 * The Constructor of the UserModel. Creates an array of patients loaded from memory.
	 */
	public UserModel() {// create a new ArrayList of Users
		users = new ArrayList<User>();

		// add any saved patients into the ArrayList
		users.addAll(Emergency.getInstance().getUserList());
		users.addAll(Inpatient.getInstance().getUserList());
		users.addAll(Outpatient.getInstance().getUserList());
		users.addAll(Management.getInstance().getUserList());
		
		// load the temps
		for(Object o : persist.loadTemps('U')) {
			users.add((User) o);
		}
	}

	/**
	 * Adds a new User to the HMS.
	 * @param newUser The new User
	 */
	public void addNewUser(User newUser) {
		// add the new User to the users List
		users.add(newUser);
		
		// notify the views that data changed
		fireTableDataChanged();
	}

	/**
	 * Remove a User from the HMS.
	 * @param userID The user ID of the User
	 * @param admin The Admin Removing them
	 */
	public void removeUser(String userID, Admin admin) {
		// Find the user with this userID
		User toRemove = findUser(userID);

		// remove from list
		users.remove(toRemove);

		// remove form HMS
		switch(toRemove.getDepartment()) {
			case "Emergency":
				Emergency.getInstance().getUserList().remove(toRemove);
				break;
			case "Outpatient":
				Outpatient.getInstance().getUserList().remove(toRemove);
				break;
			case "Inpatient":
				Inpatient.getInstance().getUserList().remove(toRemove);
				break;
			case "Management":
				Management.getInstance().getUserList().remove(toRemove);
		}
		
		// delete from persist
		persist.delete(toRemove.getUserID(), toRemove.getDepartment());
		
		// notify the views that data changed
		fireTableDataChanged();
	}

	/**
	 * Updates the department of the given user
	 * @param userID The Users ID
	 * @param dept The department
	 */
	public void updateDepartment(String userID, Department dept) {
		// find the user with this userID
		HealthStaff u = (HealthStaff) findUser(userID);

		// update the users department
		u.moveDepartment(dept.getName());

		// notify the views that data changed
		fireTableDataChanged();
	}

	/**
	 * Finds the User with a given unique userID.
	 * @param userId The user ID
	 * @return The User
	 */
	public User findUser(String userId) {
		// search all users in the user list
		for(User u : users) {
			// if the userIDs match, return u
			if(u.getUserID().equals(userId)) {
				return u;
			}
		}
		
		// else return null
		return null;
	}

	/**
	 * Edits the Users Information.
	 * @param userId The User ID
	 * @param name The Users Name
	 * @param department
	 * @param address The Users Address
	 */
	public void edit(String userId, String name, String phone, String department) {
		// find the user with this userID
		User toEdit = findUser(userId);
		
		if(toEdit != null) {
			// set the users information to that provided by the arguments
			toEdit.editUser(name,phone);

			// if a healthStaff Worker
			if(!(toEdit.getType().equals("Admin") || toEdit.getType().equals("User"))) {
				// cast toEdit to HealthStaff
				HealthStaff healthUser = (HealthStaff) toEdit;

				// and change the department
				switch(department) {
					case "Emergency":
						healthUser.moveDepartment(Emergency.getInstance().getName());
						break;
					case "Inpatient":
						healthUser.moveDepartment(Inpatient.getInstance().getName());
						break;
					case "Outpatient":
						healthUser.moveDepartment(Outpatient.getInstance().getName());
					}
			}

			// notify the views that data changed
			fireTableDataChanged();
		}
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
				return users.get(rowIndex).getDepartment();
			}
		} else if (columnIndex == 3) {
			return users.get(rowIndex).getEmail();
		} else if (columnIndex == 4) {
			return users.get(rowIndex).getNumber();
		} else if (columnIndex == 5) {
			return users.get(rowIndex).getEmail();
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
