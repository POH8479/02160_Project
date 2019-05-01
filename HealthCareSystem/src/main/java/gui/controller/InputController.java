package gui.controller;

/**
 * 
 * @author Jack Rodman
 *
 */

public class InputController {
	
	/**
	 * This method makes sure a patient data entry follows correct format requirements
	 * @param first
	 * @param last
	 * @param bday
	 * @param address
	 * @param phone
	 * @return error message string or null if no error
	 */
	public String checkPatientInput(String first, String last, String bday, String address, String phone){
		
		//make sure first name does not contain numbers
		if(first.matches(".*\\d.*")) {
			return "Incorrect format for name entry, first name cannot contain numbers";
		}
		//make sure last name does not contain numbers
		if(last.matches(".*\\d.*")) {
			return "Incorrect format for name entry, last name cannot contain numbers";
		}
		//make sure birth date is in correct format
		if ((bday.length() != 10) | !bday.contains("/")) {
    		return "Incorrect format for Birth Date entry - must be in DD/MM/YYYY format";
		}
		//make sure phone number does only contains numbers
		//TODO verify correct length of input
		for(int i = 0; i < phone.length(); i++) {
			if((Character.isLetter(phone.charAt(i)) == true)){
				return "Incorrect format for phone number entry, must only contain numbers";
			}
		}
		return null;
	}
	
	/**
	 * This method checks the format of the info entered for a new user
	 * @param name
	 * @param phone
	 * @return error message string or null for no error
	 */
	public String checkUserInput(String name, String phone) {
		//make sure name does not contain numbers
		if(name.matches(".*\\d.*")) {
			return "Incorrect format for name entry, name cannot contain numbers";
		}
		//make sure phone number does only contains numbers
		//TODO verify correct length of input
		for(int i = 0; i < phone.length(); i++) {
			if((Character.isLetter(phone.charAt(i)) == true)){
				return "Incorrect format for phone number entry, must only contain numbers";
			}
		}
		return null;
	}

}
