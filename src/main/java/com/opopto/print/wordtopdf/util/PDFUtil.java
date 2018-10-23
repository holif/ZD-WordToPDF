package com.opopto.print.wordtopdf.util;

import com.itextpdf.text.pdf.PdfReader;

import java.io.IOException;

/**
 * @auther: jone
 * @date: Created in 2018/10/3 15:17
 * @description:
 */
public class PDFUtil {

    /* 计算PDF文档页数 */
    public static int getPdfPage(String filepath){
        int pagecount = 0;
        PdfReader reader;
        try {
            reader = new PdfReader(filepath);
            pagecount= reader.getNumberOfPages();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pagecount;
    }

}
