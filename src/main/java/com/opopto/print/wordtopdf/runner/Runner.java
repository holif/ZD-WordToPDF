package com.opopto.print.wordtopdf.runner;

import com.opopto.print.wordtopdf.util.Constant;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @auther: jone
 * @date: Created in 2018/10/11 00:45
 * @description:
 */
@Component
@CommonsLog
public class Runner implements ApplicationRunner {
	/* 项目启动成功后检测临时文件目录时候存在，不存在则创建 */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        File dirFile = new File(Constant.dir);
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
    }
}
