package com.hk.DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.hk.DTO.Mssql.A;

@Repository
public class MssqlDao {
	
	
	@Autowired
	@Qualifier("mssqlSession")
	SqlSession session;
	
	private String namespace = "mssql.Mapper.";
	
	public List<A> select(){
		List<A> list = session.selectList(namespace+"select");
		return list;
	}

}
