package com.fastcampus.ch4.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;

//@Controller
//@ResponseBody
@RestController //RestController에 Controller랑 ResponseBody 다 정의되어있음
public class CommentController {

	@Autowired
	CommentService service;
	
//	{
//		"pcno":0,
//		"comment":"content다"
//	}
	
	@PatchMapping("/comments/{cno}")
	public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto, HttpSession session){
		String commenter = (String)session.getAttribute("id");
		dto.setCno(cno);
		dto.setCommenter(commenter);
		System.out.println("dto="+dto);
		try {
			if(service.modify(dto)!=1)
				throw new Exception("Modify failed.");
			
			return new ResponseEntity<String>("MOD_OK",HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("MOD_ERR",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/comments")
	public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session){
		String commenter = (String)session.getAttribute("id");
		dto.setCommenter(commenter);
		dto.setBno(bno);
		System.out.println("dto="+dto);
		try {
			if(service.write(dto)!=1)
				throw new Exception("Write failed.");
			
			return new ResponseEntity<String>("WRT_OK",HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("WRT_ERR",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("/comments/{cno}")   //  /comments/1?bno=1085  <-- 삭제할 댓글 번호
	public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session) {
		String commenter = (String)session.getAttribute("id");
		try {
			int rowCnt = service.remove(cno, bno, commenter);
			if(rowCnt!=1)
				throw new Exception("Delete Failed");
			
			return new ResponseEntity<String>("DEL_OK",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("DEL_ERR",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDto>> list(Integer bno){
		List<CommentDto> list = null;
		try {
			list = service.getList(bno);
			return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK); //200
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST); //400
		}
		
	}
	
	public List<CommentDto> getCommentList(Integer bno){
		List<CommentDto> list = null;
		try {
			list = service.getList(bno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
