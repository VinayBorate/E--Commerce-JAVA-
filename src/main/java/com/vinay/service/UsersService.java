package com.vinay.service;

import com.vinay.model.Users;

public interface UsersService {
	
	
	/*------------  CLOSE DB CONNECTION WHEN NEEDED ----------------  */
	public void closeConnection();
	
    void registerUser(Users user) throws Exception;
    public boolean getUserByUserName(String user_name) throws Exception;
    public boolean getUserByEmail(String user_email)throws Exception;
    public boolean doesAdminUserExist();
    
    Users loginUser(String username, String password) throws Exception;
    public String decrypt(String s) throws Exception;

}
