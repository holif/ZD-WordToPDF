package com.opopto.print.wordtopdf.util;

import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @auther: jone
 * @date: Created in 2018/10/3 15:25
 * @description:
 */
@CommonsLog
public class DownloadUtil {

    /* 文件下载 */
    public static String download(String httpUrl, String originalName){

        String fileName = Constant.dir + originalName;
        try {

            URL url = new URL(httpUrl);

            FileUtils.copyURLToFile(url, new File(fileName));

        }catch (MalformedURLException e){
            log.error("url error: " + httpUrl);
            log.error(e.fillInStackTrace());
        } catch (IOException ioe){
            log.error("save file fail!!!");
            log.error(ioe.fillInStackTrace());
        }


        return fileName;
    }
}
