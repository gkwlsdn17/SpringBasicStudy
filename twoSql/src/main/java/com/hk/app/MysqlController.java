package com.hk.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.DTO.Mysql.Employee;
import com.hk.Service.MysqlService;

@Controller
public class MysqlController {
	
	private final MysqlService service; 

	@Autowired
	public MysqlController(MysqlService service) {
		this.service = service;
	}
	@GetMapping("/mysql")
	public String select() {
		List<Employee> list = service.select();
		for(Employee employee : list) {
			System.out.println(employee.toString());
		}
		return "home";
	}
}
