package com.blueshit.joke.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * 文件上传工具类
 */
@Controller
public class FileUploadController {

    @RequestMapping(value = {"/fileUpload"}, method = RequestMethod.GET)
    @ResponseBody
    public String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value = {"/fileUpload"}, method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request) {
        // 获取网络路径
        ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();
        String realPath = context.getRealPath("/");
        String name = UUID.randomUUID().toString();
        String sessionId = request.getParameter("sessionId");
        // .APK png等4位后缀 截取
        String suffix = file.getOriginalFilename();
        suffix = suffix.substring(suffix.length() - 4, suffix.length());

        // 文件夹确定
        File dir = new File(realPath + "/upload/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 存储相对路径确定
        String uri = "/upload/" + name + suffix;
        if (!file.isEmpty()) {
            try {
                // 写（上传）文件
                byte[] bytes = file.getBytes();
                
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(realPath + uri)));
                stream.write(bytes);
                stream.close();
                session.setAttribute(sessionId, uri);
                
                /* 构造文件路径 */
                String webRoot = realPath + uri;
                if(sessionId.equals("jokePictrue")){
                    //  图标解析
                    BufferedImage bufferedImage = ImageIO.read(new File(webRoot));
                    int width = bufferedImage.getWidth();
                    int height = bufferedImage.getHeight();
                    //  像素限制
                    if(width >720 || height > 1280){
                        session.setAttribute(sessionId, "sizeError");
                    }
                }
                return request.getContextPath()+uri;
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
