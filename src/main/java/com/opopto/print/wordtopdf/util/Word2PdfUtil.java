package com.opopto.print.wordtopdf.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import lombok.extern.apachecommons.CommonsLog;

/**
 * @auther: jone
 * @date: Created in 2018/10/3 14:48
 * @description:
 */


@CommonsLog
public class Word2PdfUtil {

    static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。
    static final int wdFormatPDF = 17;// word转PDF 格式

    /**
     *  根据doc的url获取页数
     *  首先下载该文件到本地，将其转换成pdf，再获取pdf的页数
     * @param fileUrl
     * @return
     */
    public static Map<String,Object> word2pdf(String fileUrl) {

        String originalName = fileUrl.substring(fileUrl.lastIndexOf("/")+1);

        //下载文件
        String source = DownloadUtil.download(fileUrl, originalName);

        return word2pdfLocalFile(source,originalName);
    }

    /**
     * 根据word文件路径（本地）和文件名，获取文档的页数和转换成pdf后的七牛链接
     */
    public static Map<String,Object> word2pdfLocalFile(String filePath, String originalName) {

        String pdfName = originalName.substring(0,originalName.lastIndexOf("."))+".pdf";

        String tpPath = Constant.dir+ pdfName;

        log.info("Word转PDF开始启动...");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", false);
            Dispatch docs = app.getProperty("Documents").toDispatch();

            log.info("打开文档：" + filePath);

            Dispatch doc = Dispatch.call(docs, "Open", filePath, false, true).toDispatch();

            log.info("转换文档到PDF：" + tpPath);

            File tofile = new File(tpPath);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(doc, "SaveAs", tpPath, wdFormatPDF);
            Dispatch.call(doc, "Close", false);
            long end = System.currentTimeMillis();

            log.info("转换完成，用时：" + (end - start) + "ms");

            int pages = PDFUtil.getPdfPage(tpPath);

            String qiNiuUrl = QiNiuUtil.upload(tofile, pdfName);

            Map<String,Object> map = new HashMap<>();
            map.put("pages", pages);
            map.put("url",qiNiuUrl);

            return map;
        } catch (Exception e) {
            log.info("Word转PDF出错：" + e.getMessage());
            return new HashMap<>();
        } finally {
            if (app != null) {
                app.invoke("Quit", wdDoNotSaveChanges);
            }
        }
    }

}
