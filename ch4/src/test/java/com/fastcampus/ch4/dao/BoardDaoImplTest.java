package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
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
    public void searchSelectPageTest() throws Exception{
        boardDao.deleteAll();
        for(int i=1;i<=20;i++){
            boardDao.insert(new BoardDto("title"+i, i+"번째 내용", "asdf"+i));
        }
        SearchCondition sc = new SearchCondition(1,10,"title2","T");
        List<BoardDto> list = boardDao.searchSelectPage(sc);
        System.out.println("list = " + list);
        assertTrue(list.size()==2);

        sc = new SearchCondition(1,10,"asdf2","W");
        list = boardDao.searchSelectPage(sc);
        System.out.println("list = " + list);

        assertTrue(list.size()==2);
    }

    @Test
    public void searchResultCnt() throws Exception{
        boardDao.deleteAll();
        for(int i=1;i<=20;i++){
            boardDao.insert(new BoardDto("title"+i, i+"번째 내용", "asdf"+i));
        }
        SearchCondition sc = new SearchCondition(1,10,"title2","T");
        int cnt = boardDao.searchResultCnt(sc);
        System.out.println("cnt = " + cnt);
        assertTrue(cnt==2);

        sc = new SearchCondition(1,10,"asdf2","W");
        cnt = boardDao.searchResultCnt(sc);
        System.out.println("cnt = " + cnt);
        assertTrue(cnt==2);
    }

    @Test
    public void insertTestData() throws Exception {
        boardDao.deleteAll();
        for(int i=1;i<220;i++){
            BoardDto dto = new BoardDto("title"+1, "no content", "asdf");
            boardDao.insert(dto);
        }
    }

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