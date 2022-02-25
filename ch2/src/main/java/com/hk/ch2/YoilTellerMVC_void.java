package com.hk.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTellerMVC_void { // http://localhost:8080/ch2/getYoilMVC?year=2022&month=2&day=4
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException{
	@RequestMapping("getYoilMVC_void")
	public void main(int year, int month, int day, Model model) throws IOException{	
		
		//1.유효성 검사
		//if(!isValid(year, month, day))
			//return "yoilError";
		
		//2.요일계산
		char yoil = getYoil(year, month, day);

		//3.계산한 결과를 Model에 저장
		model.addAttribute("year",year);
		model.addAttribute("month",month);
		model.addAttribute("day",day);
		model.addAttribute("yoil",yoil);
        
	}

private boolean isValid(int year, int month, int day) {
	return true;
}

private char getYoil(int year, int month, int day) {
	Calendar cal = Calendar.getInstance();
	cal.set(year, month - 1, day);

	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	return "일월화수목금토".charAt(dayOfWeek);
}
}
