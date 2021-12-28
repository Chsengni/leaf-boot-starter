package com.chsengni.leaf.service;

import com.chsengni.leaf.interfaces.IDGen;
import com.chsengni.leaf.common.Result;
import com.chsengni.leaf.exception.InitException;
import com.chsengni.leaf.snowflake.SnowflakeIDGenImpl;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnowflakeService {
    private Logger logger = LoggerFactory.getLogger(SnowflakeService.class);

    private IDGen idGen;
    public SnowflakeService(String zkpath,int port) throws InitException {
            Preconditions.checkNotNull(zkpath,"zookeeper path can not be null");
            Preconditions.checkNotNull(port,"zookeeper port  can not be null");
            idGen = new SnowflakeIDGenImpl(zkpath, port);
            if(idGen.init()) {
                logger.info("Snowflake Service Init Successfully");
            } else {
                throw new InitException("Snowflake Service Init Fail");
            }
    }

    public Result getId(String key) {
        return idGen.get(key);
    }
}
