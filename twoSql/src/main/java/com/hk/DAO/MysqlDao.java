package com.hk.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.hk.DTO.Mysql.Employee;

@Repository
public class MysqlDao {
	
	@Autowired
	@Qualifier("mysqlSession")
	SqlSession session;
	
	private String namespace = "mysql.Mapper.";

	public List<Employee> select(){
		List<Employee> list = session.selectList(namespace+"selectEmployee");
		return list;
	}
}
