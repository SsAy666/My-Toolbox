package com.lzs.service;

import com.lzs.common.ErrorStatus;
import com.lzs.common.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

/**
 * @author linzishan
 * @desc 电脑文件操作业务逻辑
 * @date 2022/06/05 17:08
 */
@Service
public class FileService {

    private final static Logger logger = LogManager.getLogger(FileService.class);

    public void renameFiles(Long initNum, String path) {
        File file = new File(path);
        File[] tempList = file.listFiles();

        if (Objects.isNull(tempList)) {
            throw new ServiceException(ErrorStatus.FILES_IS_EMPTY, "该文件夹不存在或文件夹内文件为空");
        }

        for (File oldFile : tempList) {
            if (oldFile.isFile()) {
                //原名称
                String oldFileName = oldFile.getName();
                //原后缀名
                String suffix = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
                if ("DS_Store".equals(suffix)) {
                    continue;
                }
                //新名称
                String newFileName = initNum + "." + suffix;
                //重命名 路径+新名称
                boolean isSuccess = oldFile.renameTo(new File(path + "/" + newFileName));
                initNum++;
                if (!isSuccess) {
                    logger.error("文件【{}】重命名失败", oldFileName);
                }
            }
        }
    }
}
