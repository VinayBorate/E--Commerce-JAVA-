package com.vinay.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.vinay.dao.UsersDao;
import com.vinay.daoImpl.UsersDAOImpl;
import com.vinay.exception.UsersException;
import com.vinay.model.Users;
import com.vinay.service.UsersService;
import com.vinay.util.RSAUtil_DECRYPTION;

public class UsersServiceImpl implements UsersService {

    private UsersDao userDao;
    private RSAUtil_DECRYPTION decryption;

    public UsersServiceImpl() {
        this.userDao = new UsersDAOImpl();
        this.decryption = new RSAUtil_DECRYPTION();
        
        
    }

    
  
    /* -------CLOSE  DB   CONNECTION -----------   */
    @Override
    public void closeConnection() {
    	userDao.closeConnection();
    	
    }
    
    
 /* -------------------  REGISTATION MODULE STARTS  WITH RSA Approach  ---------------------------------*/   

    
    @Override
    public void registerUser(Users user) throws Exception {
    	
    	
    	
    	
    	boolean existing_user_email = getUserByEmail(user.getEmail());
    	boolean existing_user_username = getUserByUserName(user.getUsername());
    	
  

        if (existing_user_username) {
            throw new UsersException("Username already exists.");
        }
        else if(existing_user_email) {
        	 throw new UsersException("Email Id already exists.");
        }
        userDao.saveUser(user);
    }

    
    @Override
    public boolean getUserByUserName(String user_name) throws Exception {
List<String> allencrypted_user_names_list =userDao.getAllUsernames(); // fetch all encrypted usernames from db 
		
		String user_entered_username = decryption.decrypt(user_name); // user enetered this username during registation  in form 
		
		for(int i=0; i<allencrypted_user_names_list.size(); i++)
		{
			
			
			if(decryption.decrypt(allencrypted_user_names_list.get(i)).equals(user_entered_username)) {
				return true; // username alredy exists so user must use another username
			}
			
		}
		
		
		return false; // username dont exists in db so user can register 
    }
    
    
    
 @Override
    public boolean getUserByEmail(String user_email) throws Exception {

		List<String> allencrypted_user_email_list =userDao.getAllEmails(); // fetch all encrypted email from db 
		
		String user_entered_email = decryption.decrypt(user_email); // user enetered this email during registation  in form 
		
		for(int i=0; i<allencrypted_user_email_list.size(); i++)
		{
			
			
			if(decryption.decrypt(allencrypted_user_email_list.get(i)).equals(user_entered_email)) {
				return true; // email alredy exists so user must use another email address  
			}
			
		}
		
		
		return false; // emial dont exists in db so user can register 
    }   


    
 /* -------------------  REGISTATION MODULE ENDS  WITH RSA Approach  ---------------------------------*/   

 

 
 
 
 /* -------------------  LOGIN MODULE STARTS  WITH RSA Approach  ---------------------------------*/   


    @Override
    public Users loginUser(String username, String password) throws Exception {
    	
    	List<Users>  all_user_list = userDao.fetchAllUsers();
    	 Users matchedUser = null;

    	    for (Users user : all_user_list) {
    	        if (decryption.decrypt(user.getUsername()).equals(decryption.decrypt(username))) {
    	            matchedUser = user; // Username matches

    	            // Check if the password matches
    	            if (decryption.decrypt(user.getPassword()).equals(decryption.decrypt(password))) {
    	            	// Both username and password matched
    	            
    	    //Before returning the User we will set username , email in decoded way so that session can get decoded values         	
    	            	matchedUser.setUsername(decryption.decrypt(username));
    	            	matchedUser.setEmail(decryption.decrypt(matchedUser.getEmail()));
    	            	
    	                return matchedUser; 
    	            } else {
    	                throw new UsersException("Invalid Password"); // Password does not match
    	            }
    	        }
    	    }

    	    // If no user is found
    	    throw new UsersException("Invalid Username or Password"); // Neither username nor password found
   
    	
    	
    }	
    
    public boolean doesAdminUserExist()
    {
    	
    	return userDao.doesAdminUserExist();
    	
    }
    
    /* -------------------  REGISTATION MODULE ENDS  WITH RSA Approach  ---------------------------------*/   

    
    
    @Override
	public String decrypt(String s) throws Exception {
	
	RSAUtil_DECRYPTION obj = new RSAUtil_DECRYPTION();
	return (obj.decrypt(s));
	
	}
    
    
    
}
