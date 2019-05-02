package hospitalmanagementsystem;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import hospitalmanagementsystem.departments.Department;
import hospitalmanagementsystem.users.User;


/**
 * @author Asger Conradsen (S151607)
 */
public class PersistenceLayer {
	//Defining the types of departments and objects we have
	static final String[] departments = {"Emergency", "Inpatient", "Outpatient", "Management", "Temp"};
	static final String[] subfolders = {"Users", "Patients", "Beds"};

	/* *
	 * Default constructor for the PersistencyLayer that creates the
	 * folder structure if it doesn't already exist.
	 */
	public PersistenceLayer() {
		File folder = new File("Departments");
		folder.mkdir();
		for (String department : departments) {
			folder = new File("Departments" + File.separator + department);
			folder.mkdir();
			for (String subfolder : subfolders) {
				folder = new File("Departments" + File.separator + department + File.separator + subfolder);
				folder.mkdir();
			}
		}
	}

	/**
	 * 
	 * @return
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
	 * Saves an object in a specified department to the appropriate folder. Same return as before.
	 * @param obj
	 * @param ID
	 * @param department
	 * @return
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
	 * 
	 * @param department
	 */
	public void loadDepartment(Department department) {
		String dir = "Departments" + File.separator + department.getName()
		+ File.separator + department.getName();

		File departmentFile = new File(dir);

		//Loads department
		if(departmentFile.isFile()) {
			XMLDecoder d = null;
			try {
				d = new XMLDecoder(
		                new BufferedInputStream(
		                    new FileInputStream(dir)));
			}catch(FileNotFoundException fileNotFound) {
				System.out.println(fileNotFound.getMessage());
			}

			department = (Department) d.readObject();
		}


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
		ArrayList<Object> objs = new ArrayList<Object>();
		type = type.toLowerCase();
		
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

		for(File file : files) {
			if(file.isFile()/* && Character.toUpperCase(file.getName().charAt(0)) == charType*/) {
				try{
					d = new XMLDecoder(
		                new BufferedInputStream(
		                    new FileInputStream(dir + File.separator + file.getName())));
				} catch(FileNotFoundException fileNotFound) {
					System.out.println(fileNotFound.getMessage());
				}
				Object obj = new Object();
				obj = d.readObject();
				objs.add(obj);
			}
		}

		return objs;
	}

	public Boolean saveCounter(int counter) {
		// Creates directory path for the department
		String dir = "Departments";

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
	
	
	public int loadCounter() {
		// Gets a list of files in the directory and checks if they are valid
		XMLDecoder d = null;
		File[] files = new File("Departments").listFiles();

		Object obj = new Object();
		
		for(File file : files) {
			if(file.getName().equals("Counter")) {
				try{
					d = new XMLDecoder(
		                new BufferedInputStream(
		                    new FileInputStream("Departments" + File.separator + file.getName())));
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
	 * Deletes the file
	 * @param department
	 * @return
	 */
	public Boolean delete(Department department) {
		// Creates directory path for the department
		String dir = "Departments" + File.separator + department.getName()
			+ File.separator + department.getName();
		File delFile = new File(dir);
		if(delFile.delete()) return true;
		else return false;
	}

	/**
	 * 
	 * @param ID
	 * @param department
	 * @return
	 */
	public Boolean delete(String ID, String department) {
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