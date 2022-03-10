package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class B1Dao {
    @Autowired
    DataSource ds;

    public int insert(int key, int value) throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
//            conn = ds.getConnection();
            conn = DataSourceUtils.getConnection(ds);
            pstmt = conn.prepareStatement("insert into b1 values(?,?)");
            pstmt.setInt(1, key);
            pstmt.setInt(2, value);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(pstmt);
            DataSourceUtils.releaseConnection(conn, ds);
        }

    }

    private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }

    public void deleteAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            conn = ds.getConnection(); // deleteAll은 Tx랑 별개로 동작할거라서 utils말고 그냥 ds에서 커넥션 받음
            pstmt = conn.prepareStatement("delete from a1");
            pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(pstmt, conn);
        }


    }
}
