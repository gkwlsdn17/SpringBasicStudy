package com.hk.ch2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
//	@RequestMapping(value="/register/add", method={RequestMethod.GET, RequestMethod.POST}) //GET, POST 둘 다 허용
	@RequestMapping("/register/add") //신규가입화면
//	@GetMapping("/register/add")
	public String register() {
		return "registerForm"; //WEB-INF/views/registerForm.jsp
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)
	@PostMapping("/register/save") //spring 버전 4.3부터
	public String save(User user, Model m) throws UnsupportedEncodingException {
		// 1.유효성 검사
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.","utf-8");
			
			m.addAttribute("msg", msg);
//			return "redirect:/register/add";
			return "forward:/register/add";
//			return "redirect:/register/add?msg="+msg; //URL재작성(rewriting)
		}
		// 2.DB에 신규회원 정보를 저장
		return "registerInfo";
	}
	
	private boolean isValid(User user) {
		return false;
	}

}
