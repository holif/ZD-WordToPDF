package com.opopto.print.wordtopdf.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.apachecommons.CommonsLog;

import java.io.File;

/**
 * @auther: jone
 * @date: Created in 2018/10/11 00:59
 * @description:
 */
@CommonsLog
public class QiNiuUtil {

	/* 七牛云信息 */
    private static final String QINIU_HOST = "***********";
    private static final String ACCESS_KEY = "***********";
    private static final String SECRET_KEY = "***********";
    private static final String BUCKET = "**********";

    /* 上传file到七牛云，在七牛上的存储名称为fileName */
    public static String upload(File file, String fileName){

        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        try {
            Response response = uploadManager.put(file, fileName, upToken);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            log.info("upload file " + putRet.hash);
            return QINIU_HOST + fileName;
        } catch (QiniuException ex) {
            Response r = ex.response;
            log.error(r.toString());
            try {
                log.error(r.bodyString());
            } catch (QiniuException ex2) {
            }
        }
        return "null";
    }

}
