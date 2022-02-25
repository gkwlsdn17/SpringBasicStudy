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
		//1. ModelAndView�� �����ϰ� �⺻ �並 ����
		ModelAndView mv = new ModelAndView();
		mv.setViewName("yoilError"); //���� �̸��� ����
		
		//2.��ȿ�� �˻�
		if(!isValid(year, month, day)) {
			return mv;
		}
			
		//3.���ϰ��
		char yoil = getYoil(year, month, day);

		//4.����� ����� ModelAndView�� ����
		mv.addObject("year",year);
		mv.addObject("month",month);
		mv.addObject("day",day);
		mv.addObject("yoil",yoil);
		
		//5.����� ������ view�� ����
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
	return "�Ͽ�ȭ�������".charAt(dayOfWeek);
}
}
