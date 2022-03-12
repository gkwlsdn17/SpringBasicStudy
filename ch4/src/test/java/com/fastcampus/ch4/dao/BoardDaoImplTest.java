package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest {

    @Autowired
    BoardDao boardDao;

    @Test
    public void select() throws Exception {
        assertTrue(boardDao!=null);
        System.out.println("boardDao = " + boardDao);
        BoardDto dto =  boardDao.select(1);
        System.out.println("dto = " + dto);
    }

    @Test
    public void selectAll() throws Exception {
        List<BoardDto> list = boardDao.selectAll();
        assertTrue(list!=null);
        for(BoardDto dto : list){
            System.out.println(dto);
        }
    }

    @Test
    public void count() throws Exception {
        int cnt = boardDao.count();
        assertTrue(cnt>-1);
        System.out.println("cnt = " + cnt);
    }

    @Test
    public void selectPage() throws Exception {
        Map map = new HashMap();
        map.put("from",0);
        map.put("size",2);
        List<BoardDto> list = boardDao.selectPage(map);
        assertTrue(list!=null);
        for(BoardDto dto : list){
            System.out.println(dto);
        }
    }

    @Test
    public void insert() throws Exception{
        BoardDto dto = new BoardDto("title1","content1","wri1");
        assertTrue(boardDao.insert(dto)>0);

    }

    @Test
    public void update() throws Exception{
        BoardDto dto = new BoardDto("title_up_1","content_up_1","wri1");
        dto.setBno(4);
        assertTrue(boardDao.update(dto)>0);

    }

    @Test
    public void delete() throws Exception{
        Integer bno = 4;
        String writer = "wri1";
        assertTrue(boardDao.delete(bno, writer)>0);
        selectAll();
    }

    @Test
    public void deleteAll() throws Exception{
        boardDao.deleteAll();
        selectAll();
    }

    @Test
    public void increaseViewCnt() throws Exception{
        assertTrue(boardDao.increaseViewCnt(5)>0);
    }
}