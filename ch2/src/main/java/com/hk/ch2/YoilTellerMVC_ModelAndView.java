package com.hk.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class YoilTellerMVC_ModelAndView { // http://localhost:8080/ch2/getYoilMVC?year=2022&month=2&day=4
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException{
	@RequestMapping("getYoilMVC_ModelAndView")
	public ModelAndView main(int year, int month, int day) throws IOException{	
		//1. ModelAndView를 생성하고 기본 뷰를 설정
		ModelAndView mv = new ModelAndView();
		mv.setViewName("yoilError"); //뷰의 이름을 지정
		
		//2.유효성 검사
		if(!isValid(year, month, day)) {
			return mv;
		}
			
		//3.요일계산
		char yoil = getYoil(year, month, day);

		//4.계산한 결과를 ModelAndView에 저장
		mv.addObject("year",year);
		mv.addObject("month",month);
		mv.addObject("day",day);
		mv.addObject("yoil",yoil);
		
		//5.결과를 보여줄 view를 지정
        mv.setViewName("yoil");
        
        return mv;

	}

private boolean isValid(int year, int month, int day) {
	return false;
}

private char getYoil(int year, int month, int day) {
	Calendar cal = Calendar.getInstance();
	cal.set(year, month - 1, day);

	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	return "일월화수목금토".charAt(dayOfWeek);
}
}
