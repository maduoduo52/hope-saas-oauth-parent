package com.hope.saas.wms.api.controller;

import com.hope.saas.api.util.util.FileUtil;
import com.hope.saas.wms.api.config.annotation.IgnoreLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Maduo
 * @date 2020/4/22 16:53
 */
@Controller
@RequestMapping("download")
public class DownloadController {

    /**
     * 下载开放文档
     *
     * @param request
     * @param response
     */
    @GetMapping("downloadDocFile")
    @ResponseBody
    @IgnoreLogin
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "运荔枝（WMS）开放平台API文档.docx";
        FileUtil.downloadFile(response, "doc/", fileName);
    }

    /**
     * 下载工具类
     *
     * @param request
     * @param response
     */
    @GetMapping("downloadUtilFile")
    @ResponseBody
    @IgnoreLogin
    public void downloadUtilFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "api文档+工具类.zip";
        FileUtil.downloadFile(response, "util/", fileName);
    }
}
