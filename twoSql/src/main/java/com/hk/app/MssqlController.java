package com.hk.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hk.DTO.Mssql.A;
import com.hk.Service.MssqlService;

@Controller
public class MssqlController {
	
	private final MssqlService service;
	
	@Autowired
	public MssqlController(MssqlService service) {
		this.service = service;
	}

	@GetMapping("/mssql")
	public String select() {
		List<A> list = service.select();
		for(A a : list) {
			System.out.println(a.toString());
		}
		return "home";
	}
}
