package com.lzs.controller;

import com.lzs.common.Result;
import com.lzs.service.DateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linzishan
 * @desc 日期计算工具
 * @date 2022/06/05 18:45
 */
@RestController
@RequestMapping("/toolbox/date")
public class DateController extends BaseController {

    @Resource
    private DateService dateService;

    /**
     * 根据起始日期计算间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/calculate/days")
    public Result<Integer> calculateDays(@RequestParam String startDate, @RequestParam String endDate) {
        return wrapper(dateService.calculateDays(startDate, endDate));
    }

}
