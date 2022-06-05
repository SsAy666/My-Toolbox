package com.lzs.service;

import com.lzs.common.utils.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author linzishan
 * @desc 日期操作业务逻辑
 * @date 2022/06/05 18:45
 */
@Service
public class DateService {

    private final static Logger logger = LogManager.getLogger(DateService.class);

    public Integer calculateDays(String startDate, String endDate) {
        // String转Date
        Date start = DateUtil.parse(startDate, "yyyy-MM-dd");
        Date end = DateUtil.parse(endDate, "yyyy-MM-dd");
        // 开始计算
        return DateUtil.getDayDiffer(start, end);
    }
}
