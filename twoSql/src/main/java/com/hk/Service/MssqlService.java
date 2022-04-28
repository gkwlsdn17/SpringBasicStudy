package com.hk.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.DAO.MssqlDao;
import com.hk.DTO.Mssql.A;

@Service
public class MssqlService {
	
	private final MssqlDao dao;
	
	@Autowired
	public MssqlService(MssqlDao dao) {
		this.dao = dao;
	}

	public List<A> select(){
		return dao.select();
	}
}
