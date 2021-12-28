package com.chsengni.leaf.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.chsengni.leaf.interfaces.IDGen;
import com.chsengni.leaf.common.Result;
import com.chsengni.leaf.exception.InitException;
import com.chsengni.leaf.segment.SegmentIDGenImpl;
import com.chsengni.leaf.segment.dao.IDAllocDao;
import com.chsengni.leaf.segment.dao.impl.IDAllocDaoImpl;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class SegmentService {
    private Logger logger = LoggerFactory.getLogger(SegmentService.class);

    private IDGen idGen;
    private DruidDataSource dataSource;

    public SegmentService(String url, String username, String pwd) throws SQLException, InitException {
        Preconditions.checkNotNull(url,"database url can not be null");
        Preconditions.checkNotNull(username,"username can not be null");
        Preconditions.checkNotNull(pwd,"password can not be null");
        // Config dataSource
        dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(pwd);
        dataSource.init();
        // Config Dao
        IDAllocDao dao = new IDAllocDaoImpl(dataSource);
        // Config ID Gen
        idGen = new SegmentIDGenImpl();
        ((SegmentIDGenImpl) idGen).setDao(dao);
        if (idGen.init()) {
            logger.info("Segment Service Init Successfully");
        } else {
            throw new InitException("Segment Service Init Fail");
        }
    }

    public Result getId(String key) {
        return idGen.get(key);
    }

    public SegmentIDGenImpl getIdGen() {
        if (idGen instanceof SegmentIDGenImpl) {
            return (SegmentIDGenImpl) idGen;
        }
        return null;
    }
}
