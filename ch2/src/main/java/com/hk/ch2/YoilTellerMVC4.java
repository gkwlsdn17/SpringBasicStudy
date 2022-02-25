package com.hk.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YoilTellerMVC4 { // http://localhost:8080/ch2/getYoilMVC?year=2022&month=2&day=4
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("getYoilMVC4")
	public String main(MyDate date,
			Model model) throws IOException{	
		
		//1.유효성 검사
		if(!isValid(date))
			return "yoilError";
		
		//2.요일계산
		char yoil = getYoil(date);

		//3.계산한 결과를 Model에 저장
		model.addAttribute("myDate",date);
		model.addAttribute("yoil",yoil);
        
        return "yoil"; //WEB-INF/views/yoil.jsp
	}

private boolean isValid(MyDate date) {
	return true;
}

private char getYoil(MyDate date) {
	return getYoil(date.getYear(), date.getMonth(), date.getDay());
}

private char getYoil(int year, int month, int day) {
	Calendar cal = Calendar.getInstance();
	cal.set(year, month - 1, day);

	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	return "일월화수목금토".charAt(dayOfWeek);
}
}
