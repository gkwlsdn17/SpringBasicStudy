package com.hk.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.DAO.MysqlDao;
import com.hk.DTO.Mysql.Employee;

@Service
public class MysqlService {
	
	private final MysqlDao dao;
	
	@Autowired
	public MysqlService(MysqlDao dao) {
		this.dao = dao;
	}
	
	public List<Employee> select(){
		return dao.select();
	}
	
}
