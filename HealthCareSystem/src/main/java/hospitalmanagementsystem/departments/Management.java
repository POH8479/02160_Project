package hospitalmanagementsystem.departments;

import java.util.ArrayList;
import hospitalmanagementsystem.users.*;

public class Management extends Department {
	// static variable single_instance of type Management 
    private static Management single_instance = null; 
    ArrayList<User> userList;
    
    private Management() {
    	userList = new ArrayList<User>();
    }
	
    // static method to create instance of Management class 
    public static Management getInstance() 
    { 
        if (single_instance == null) {
            single_instance = new Management();
        }
  
        return single_instance; 
    }
}
