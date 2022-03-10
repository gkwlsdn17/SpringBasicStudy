package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@Service
public class TxService {
    @Autowired A1Dao a1Dao;
    @Autowired B1Dao b1Dao;
    @Autowired DataSource ds;

//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertA1WithTx() throws Exception{
        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
        DefaultTransactionDefinition txd = new DefaultTransactionDefinition();
        txd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);;
        TransactionStatus status = tm.getTransaction(txd);

        try {
            a1Dao.insert(1, 100);
            insertB1WithTx();
            a1Dao.insert(2, 100);
            tm.commit(status);
        }catch (Exception e){
            e.printStackTrace();
            tm.rollback(status);
        } finally {

        }

    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void insertB1WithTx() throws Exception{
        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
        DefaultTransactionDefinition txd = new DefaultTransactionDefinition();
        txd.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);;
        TransactionStatus status = tm.getTransaction(txd);

        try{
            b1Dao.insert(1, 100);
            b1Dao.insert(1, 200);
            tm.commit(status);
        }catch (Exception e){
            e.printStackTrace();
            tm.rollback(status);
        }

    }

    public void insertA1WithoutTx() throws Exception{
        a1Dao.insert(1, 100);
        a1Dao.insert(1, 200);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertA1WithTxFail() throws Exception{
        a1Dao.insert(1, 100);
        a1Dao.insert(1, 200);
    }

    @Transactional
    public void insertA1WithTxSuccess() throws Exception{
        a1Dao.insert(1, 100);
        a1Dao.insert(2, 200);
    }
}
