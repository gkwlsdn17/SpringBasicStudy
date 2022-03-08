package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test{

    @Autowired
    DataSource ds;

    @Test
    public void insertUserTest() throws Exception {
        User user = new User("ownf12","efffs3","kitty","snlef@naver.com",new Date(), "fb", new Date());
        deleteAll();
        int rowCnt = insertUser(user);
        assertTrue(rowCnt==1);
    }

    @Test
    public void selectUserTest() throws Exception{
        User user = new User("ownf12","efffs3","kitty","snlef@naver.com",new Date(), "fb", new Date());
        deleteAll();
        int rowCnt = insertUser(user);
        User user2 = selectUser("ownf12");
        assertTrue(user2.getId().equals("ownf12"));
    }

    @Test
    public void deleteUserTest() throws Exception{
        deleteAll();
        assertTrue(deleteUser("ownf12") == 0);

        User user = new User("ownf12","efffs3","kitty","snlef@naver.com",new Date(), "fb", new Date());
        assertTrue(insertUser(user)==1);
        assertTrue(deleteUser(user.getId())==1);
        assertTrue(selectUser(user.getId())==null);

    }

    @Test
    public void updateUserTest() throws Exception{
        User user = new User("ownf12","11223344","kitty","skef@abcd.com",new Date(), "twitter", new Date());
        assertTrue(updateUser(user)==1);
    }

    private int updateUser(User user) throws Exception{
        Connection conn = ds.getConnection();
        String sql = "update user_info set pwd = ?, name = ?, email = ?, birth = ?, sns = ? where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,user.getPwd());
        pstmt.setString(2,user.getName());
        pstmt.setString(3,user.getEmail());
        pstmt.setDate(4,new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(5,user.getSns());
        pstmt.setString(6,user.getId());
        return pstmt.executeUpdate();
    }

    private User selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();
        String sql = "select * from user_info where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery(); // select
        if(rs.next()){
            User user = new User();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setReg_date(new Date(rs.getDate(7).getTime()));

            return user;
        }
        return null;
    }

    private void deleteAll() throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "delete from user_info";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }

    private int deleteUser(String id) throws Exception{
        Connection conn = ds.getConnection();
        String sql = "delete from user_info where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        int rowCnt = pstmt.executeUpdate();
        return rowCnt;
    }
    //사용자 정보를 user_info테이블에 저장하는 메서드
    public int insertUser(User user) throws Exception{
        Connection conn = ds.getConnection();

        String sql =
                //"insert into user_info values ('ownf12','efffs3','kitty','snlef@naver.com','1980-05-07','facebook',now())";
                "insert into user_info values (?,?,?,?,?,?,now())";

        PreparedStatement pstmt = conn.prepareStatement(sql); //sql Injection 공격, 성능향상
        pstmt.setString(1,user.getId());
        pstmt.setString(2,user.getPwd());
        pstmt.setString(3,user.getName());
        pstmt.setString(4,user.getEmail());
        pstmt.setDate(5,new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6,user.getSns());

        int rowCnt = pstmt.executeUpdate(); //insert, delete, update

        return rowCnt;
    }

    @Test
    public void transactionTest() throws Exception{
        int rowCnt = 0; //insert, delete, update
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            deleteAll();
            conn = ds.getConnection();
            conn.setAutoCommit(false); // default는 true상태

            String sql =
                    //"insert into user_info values ('ownf12','efffs3','kitty','snlef@naver.com','1980-05-07','facebook',now())";
                    "insert into user_info values (?,?,?,?,?,?,now())";

            pstmt = conn.prepareStatement(sql); //sql Injection 공격, 성능향상
            pstmt.setString(1,"asdf");
            pstmt.setString(2,"1234");
            pstmt.setString(3,"abc");
            pstmt.setString(4,"aaa@aaa.com");
            pstmt.setDate(5,new java.sql.Date(new Date().getTime()));
            pstmt.setString(6,"fb");

            rowCnt = pstmt.executeUpdate();
            pstmt.setString(1,"asdf");
            rowCnt = pstmt.executeUpdate();
            conn.commit();

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if(pstmt!=null) pstmt.close();
            if(conn!=null) conn.close();
        }

    }

    @Test
    public void main() throws Exception{
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn!=null); //괄호안의 조건식이 true면 테스트 성공, 아니면 실패
    }
}