package com.lzs.controller;

import com.lzs.common.Result;
import com.lzs.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linzishan
 * @desc 电脑文件操作工具
 * @date 2022/05/29 18:52
 */
@RestController
@RequestMapping("/toolbox/file")
public class FileController extends BaseController {

    @Resource
    private FileService fileService;

    /**
     * 重命名文件（保证文件夹内的文件不重复）
     *
     * @param initNum
     * @param path
     * @return
     */
    @GetMapping("/no/repeat/rename")
    public Result<Void> renameFiles(@RequestParam Long initNum, @RequestParam String path) {
        fileService.renameFiles(initNum, path);
        return wrapper(null);
    }

}
