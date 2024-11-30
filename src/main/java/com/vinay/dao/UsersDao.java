package com.vinay.dao;

import java.util.List;
import java.util.Optional;

import com.vinay.model.Users;

public interface UsersDao {
	
	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
	
	public List<String> getAllUsernames();
	public List<String> getAllPasswords();
	public List<String> getAllEmails() ;
	void saveUser(Users user);
	
	public List<Users> fetchAllUsers();
    Optional<Users> getUserByUsername(String username);
    public Optional<Users> getUserByEmail(String email); // not using
    
    public boolean doesAdminUserExist() ;

}
