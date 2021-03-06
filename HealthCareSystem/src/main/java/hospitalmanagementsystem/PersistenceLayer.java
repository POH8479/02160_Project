package hospitalmanagementsystem;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import hospitalmanagementsystem.departments.Department;
import hospitalmanagementsystem.users.User;

/**
 * The Persistence Layer Class Saves the Users, Patients and Beds as XML Files in Department folders.
 * The Class can save, delete and load these files.
 * @author Asger Conradsen
 */
public class PersistenceLayer {
	// STATIC VARIABLES
	static final String[] departments = {"Emergency", "Inpatient", "Outpatient", "Management", "Temp"};
	static final String[] subfolders = {"Users", "Patients", "Beds"};

	// CONSTRUCTOR
	/**
	 * Default constructor for the PersistencyLayer that creates the
	 * folder structure if it doesn't already exist.
	 */
	public PersistenceLayer() {
		// Create a Folder called Departments
		File folder = new File("Departments");
		folder.mkdir();
		
		// inside Departments make a folder for each department
		for (String department : departments) {
			folder = new File("Departments" + File.separator + department);
			folder.mkdir();
			
			if(department.equals("Management")) {
				// inside the Management folder make a folder Users
				folder = new File("Departments" + File.separator + department + File.separator + "Users");
				folder.mkdir();
			} else if(department.equals("Outpatient")) {
				// inside the Outpatient folder make folders for the Users and Patients
				folder = new File("Departments" + File.separator + department + File.separator + "Users");
				folder.mkdir();
				folder = new File("Departments" + File.separator + department + File.separator + "Patients");
				folder.mkdir();
			} else {
				// inside each department folder make folders for the Users, Beds and Patients
				for (String subfolder : subfolders) {
					folder = new File("Departments" + File.separator + department + File.separator + subfolder);
					folder.mkdir();
				}
			}
		}
	}

	// METHODS
	/**
	 * Loads a given department into the HMS from file by loading its Users, Patients and Beds.
	 * @param department The Department to load
	 */
	public void loadDepartment(Department department) {
		//Loads users in the department
		ArrayList<Object> loadedUsers = loadObjs(department, "users");
		if(!loadedUsers.isEmpty()) {
			ArrayList<User> users = new ArrayList<User>();
			for(Object o : loadedUsers) {
				users.add((User) o);
			}
			department.setUserList(users);
		}

		//Loads patients in the department
		ArrayList<Object> loadedPatients = loadObjs(department, "patients");
		if(!loadedPatients.isEmpty()) {
			ArrayList<Patient> patients = new ArrayList<Patient>();
			for(Object o : loadedPatients) {
				patients.add((Patient) o);
			}
			department.setPatientList(patients);
		}

		//Loads beds in the department
		ArrayList<Object> loadedBeds = loadObjs(department, "beds");
		if(!loadedBeds.isEmpty()) {
			ArrayList<Bed> beds = new ArrayList<Bed>();
			for(Object o : loadedBeds) {
				beds.add((Bed) o);
			}
			department.setBedList(beds);
		}
	}

	/**
	 * Returns an ArrayList of either users, patients or beds in the given departmen
	 * based on which "type" is passed to the method.
	 */
	private ArrayList<Object> loadObjs(Department department, String type){
		// create an ArrayList and set type to LowerCase
		ArrayList<Object> objs = new ArrayList<Object>();
		type = type.toLowerCase();
		
		// if not a valid load then return the empty list
		if((department.getName().equals("Management") && (type.equals("patients") || type.equals("beds")))
				|| (department.getName().equals("Outpatient") && type.equals("beds"))) {
			return objs;
		}
		
		// set the file directory string
		String dir = "Departments" + File.separator + department.getName();
		switch (type) {
			case "users":
				dir = dir + File.separator + "Users";
				break;
			case "patients":
				dir = dir + File.separator + "Patients";
				break;
			case "beds":
				dir = dir + File.separator + "Beds";
				break;
			default:
				return objs;
		}

		// Gets a list of files in the directory and checks if they are valid
		XMLDecoder d = null;
		File[] files = new File(dir).listFiles();

		// go through each file and try and load them
		for(File file : files) {
			if(file.isFile()) {
				try{
					d = new XMLDecoder(
		                new BufferedInputStream(
		                    new FileInputStream(dir + File.separator + file.getName())));
				} catch(FileNotFoundException fileNotFound) {
					System.out.println(fileNotFound.getMessage());
				}
				// read the object and add to the list
				Object obj = d.readObject();
				objs.add(obj);
			}
		}

		return objs;
	}
	
	/**
	 * Loads the Temp Objects who do not have a department by passing either 'U' for Users, 'P' for Patients or 'B' for Beds.
	 * @param type A Character 'U', 'P' or 'B' to describe the type of Objects
	 * @return An ArrayList of Objects
	 */
	public ArrayList<Object> loadTemps(char type) {
		
		// Creates directory path for the department
		String dir = "Departments" + File.separator + "Temp";
		ArrayList<Object> objects = new ArrayList<Object>();

		switch (type) {
			case 'U':
				dir = dir + File.separator + "Users";
				break;
			case 'P':
				dir = dir + File.separator + "Patients";
				break;
			case 'B':
				dir = dir + File.separator + "Beds";
				break;
			default:
				return null;
		}
		
		XMLDecoder d = null;
		File[] files = new File(dir).listFiles();

		for(File file : files) {
			// Writes department object to the file specified by the directory
			try{
				d = new XMLDecoder(
		                new BufferedInputStream(
		                    new FileInputStream(dir + File.separator + file.getName())));
			} catch(FileNotFoundException fileNotFound) {
				System.out.println(fileNotFound.getMessage());
			}
			objects.add(d.readObject());
		}
		return objects;
	}

	/**
	 * Loads the Counter Integer which is used to give each object a Unique ID.
	 * @return The Counter
	 */
	public int loadCounter() {
		// Gets a list of files in the directory and checks if they are valid
		XMLDecoder d = null;
		String dir = "Departments" + File.separator + "Temp";
		File[] files = new File(dir).listFiles();

		Object obj = new Object();
		
		for(File file : files) {
			if(file.getName().equals("Counter")) {
				try{
					d = new XMLDecoder(
		                new BufferedInputStream(
		                    new FileInputStream(dir + File.separator + file.getName())));
				} catch(FileNotFoundException fileNotFound) {
					System.out.println(fileNotFound.getMessage());
				}
				obj = d.readObject();
				d.close();
				return (int) obj;
			}
		}
		// else return 0
		return 0;
	}

	/**
	 * Saves an object in a specified department to the appropriate folder.
	 * @param obj The Object you want to save
	 * @param ID The ID of the Object
	 * @param department The department of the Object
	 * @return True or False
	 */
	public boolean save(Object obj, String ID, String department) {
		// if department is null change to Temp
		department = department==null?"Temp":department;
		
		// Creates the directory for the object to be stored
		String dir = "Departments" + File.separator + department;
		char type = ID.charAt(0);

		// if type is another type of user set type to 'U'
		if(type == 'A' || type == 'N' || type == 'D') {
			type = 'U';
		}

		switch (type) {
			case 'U':
				dir = dir + File.separator + "Users";
				break;
			case 'P':
				dir = dir + File.separator + "Patients";
				break;
			case 'B':
				dir = dir + File.separator + "Beds";
				break;
			default:
				return false;
		}
		dir = dir + File.separator + ID;

		// Writes object to the file specified by the directory
		XMLEncoder e = null;
		try{
			e = new XMLEncoder(
                new BufferedOutputStream(
                    new FileOutputStream(dir)));
		} catch(FileNotFoundException fileNotFound) {
			return false;
		}
		e.writeObject(obj);
		e.close();
		return true;
	}
	
	/**
	 * Saves the counter int to file.
	 * @param counter The Counter
	 * @return True or False
	 */
	public Boolean saveCounter(int counter) {
		// Creates directory path for the department
		String dir = "Departments" + File.separator + "Temp";

		// Writes department object to the file specified by the directory
		XMLEncoder e = null;
		try{
			e = new XMLEncoder(
                new BufferedOutputStream(
                    new FileOutputStream(dir + File.separator + "Counter")));
		} catch(FileNotFoundException fileNotFound) {
			return false;
		}
		
		e.writeObject(counter);
		e.close();
		return true;
	}

	/**
	 * Deletes a specific object from file. 
	 * @param ID The ID of the Object
	 * @param department The Department the file is in
	 * @return True or False
	 */
	public Boolean delete(String ID, String department) {
		// if department is null set to "Temp"
		department = (department==null) ? "Temp":department;
		
		// Creates the directory for the object to be stored
		String dir = "Departments" + File.separator + department;
		char type = ID.charAt(0);
		
		// if type is another type of user set type to 'U'
		if(type == 'A' || type == 'N' || type == 'D') {
			type = 'U';
		}
		
		switch (type) {
			case 'U':
				dir = dir + File.separator + "Users";
				break;
			case 'P':
				dir = dir + File.separator + "Patients";
				break;
			case 'B':
				dir = dir + File.separator + "Beds";
				break;
			default:
				return false;
		}
		dir = dir + File.separator + ID;

		//Attempts to delete the file
		File delFile = new File(dir);
		if(delFile.delete()) return true;
		else return false;
	}
}
