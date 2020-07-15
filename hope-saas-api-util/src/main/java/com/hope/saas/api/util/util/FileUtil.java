package com.hope.saas.api.util.util;

import cn.hutool.http.Header;
import com.hope.saas.api.util.exception.WmsApiException;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Maduo
 * @date 2020/4/22 17:00
 */
public class FileUtil {

    /**
     * 下载项目根目录下doc下的文件
     *
     * @param response response
     * @param fileName 文件名
     */
    public static void downloadFile(HttpServletResponse response, String path, String fileName) {
        InputStream stream = FileUtil.class.getClassLoader().getResourceAsStream(path + fileName);
        response.setHeader(Header.CONTENT_TYPE.toString(), "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            String name = java.net.URLEncoder.encode(fileName, "UTF-8");
            response.setHeader(Header.CONTENT_DISPOSITION.toString(), "attachment;filename=" + java.net.URLDecoder.decode(name, "ISO-8859-1"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(stream);
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (FileNotFoundException e1) {
            throw new WmsApiException("系统找不到指定的文件");
        } catch (IOException e) {
            e.printStackTrace();
            throw new WmsApiException("文件下载异常");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
