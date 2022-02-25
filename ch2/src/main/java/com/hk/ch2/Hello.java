package com.hk.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//1.���� ȣ�Ⱑ���� ���α׷����� ���
@Controller
public class Hello {
	int iv = 10; //�ν��Ͻ� ����
	static int cv = 20; //static ����
	
	// 2. URL�� �޼��带 ����
	@RequestMapping("/hello")
	public void main() { //�ν��Ͻ� �޼���
		System.out.println("Hello");
		
		System.out.println(cv); //OK
		System.out.println(iv); //OK
	}
	
	@RequestMapping("/staticHello")
	public static void main2() { //static �޼���
		System.out.println(cv); //OK
		//System.out.println(iv); //����
	}
	
	@RequestMapping("/prv")
	private void main3() {
		System.out.println("private hello");
	}
}
