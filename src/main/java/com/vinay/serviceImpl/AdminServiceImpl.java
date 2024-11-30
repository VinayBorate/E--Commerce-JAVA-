package com.vinay.serviceImpl;

import java.util.List;

import com.vinay.dao.AdminDao;
import com.vinay.daoImpl.AdminDaoImpl;
import com.vinay.model.Revenues;
import com.vinay.model.Users;
import com.vinay.service.AdminService;
import com.vinay.util.RSAUtil_DECRYPTION;

public class AdminServiceImpl implements AdminService {

private  AdminDao adminDao;

public AdminServiceImpl() {
	this.adminDao = new AdminDaoImpl();
}


/* -------CLOSE  DB   CONNECTION -----------   */

@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		adminDao.closeConnection();
	}
/* -------CLOSE  DB   CONNECTION -----------   */


@Override
public List<Users> fetchAllApprovedUsers() {
	
	return adminDao.getAllApprovedUsers();
}

@Override
public List<Users> fetchAllNotApprovedUsers() {
	// TODO Auto-generated method stub
	return adminDao.getAllNotApprovedUsers();
}

@Override
public List<Users> fetchAllRejectedUsers() {
	// TODO Auto-generated method stub
	return adminDao.getAllRejectedUsers();
}

@Override
public List<Users> fetchAllDeletedUsers() {
	// TODO Auto-generated method stub
	return adminDao.getAllDeletedUsers();
}

@Override
public List<Users> fetchAllActive() {
	// TODO Auto-generated method stub
	return adminDao.getAllActive();
}

@Override
public void approvalService(int id) {
	
	adminDao.approveUser(id);
	
}

@Override
public void reapproveService(int id) {
	
	adminDao.reapproveUser(id);
	
}

@Override
public void rejectService(int id) {
	
	adminDao.rejectUser(id);
	
}


@Override
public void reactivateService(int id) {
	
	adminDao.reactivateAccount(id);
	
}


public void deactivateService(int id) {
	
	adminDao.deactivateAccount(id);
	
}



public Revenues fetchRevenueByOrderId(String orderId) {
	
	return adminDao.getRevenueByOrderId(orderId);
}


@Override
	public String decrypt(String s) throws Exception {
	
	RSAUtil_DECRYPTION obj = new RSAUtil_DECRYPTION();
	return (obj.decrypt(s));
	
	}


@Override
	public List<Revenues> fetchAllRevenues() {
		// TODO Auto-generated method stub
		return adminDao.fetchAllRevenues();
	}
}
