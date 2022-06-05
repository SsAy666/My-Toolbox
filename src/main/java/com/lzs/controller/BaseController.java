package com.lzs.controller;


import com.alibaba.fastjson.JSONObject;
import com.lzs.common.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseController {

    private final static Logger logger = LogManager.getLogger(BaseController.class);

    public <T> Result<T> wrapper(T object) {
        Result<T> result = new Result<>(0, "success", object);
        logger.info("response data: {}", JSONObject.toJSONString(result));
        return result;
    }
}
