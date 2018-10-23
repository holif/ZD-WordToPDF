package com.opopto.print.wordtopdf.controller;

import com.alibaba.fastjson.JSONObject;
import com.opopto.print.wordtopdf.util.Constant;
import com.opopto.print.wordtopdf.util.Word2PdfUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: jone
 * @date: Created in 2018/10/3 15:14
 * @description:
 */
@Controller
@CommonsLog
@ResponseBody
public class Word2PDFController {


    /* 获取网络word文件的页数及转成pdf后的url */
    @PostMapping("/word2pdf")
    public Object word2pdf(@RequestParam String fileUrl){

        Map<String, Object> map = Word2PdfUtil.word2pdf(fileUrl);
        JSONObject result = (JSONObject) JSONObject.toJSON(map);
        return result;
    }

    @PostMapping("/uploadWord")
    public Object uploadWord(@RequestParam("file") MultipartFile file){
        String originalName = file.getOriginalFilename().trim();
        String suffix = originalName.substring(originalName.lastIndexOf("."));
        Map<String,Object> map = new HashMap<>();
        try{
            String newFileName = Constant.dir + System.currentTimeMillis() + suffix;
            File destFile = new File(newFileName);
            file.transferTo(destFile);
            map = Word2PdfUtil.word2pdfLocalFile(destFile.getPath(),originalName);
        } catch (Exception e){
            log.error(e.fillInStackTrace());
        }

        JSONObject result = (JSONObject) JSONObject.toJSON(map);
        return result;

    }

    @GetMapping("/keepalived")
    public String keepalived(){
        return "ok";
    }

}
