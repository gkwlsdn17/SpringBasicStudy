package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.User;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    BoardDto select(int bno) throws Exception;
    List<BoardDto> selectAll() throws Exception;
    int count() throws Exception;
    List<BoardDto> selectPage(Map map) throws Exception;
    int insert(BoardDto dto) throws Exception;
    int update(BoardDto dto) throws Exception;
    int delete(Integer bno, String writer) throws Exception;
    void deleteAll() throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;

}
