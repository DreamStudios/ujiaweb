package com.blueshit.joke.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 文件上传工具类
 */
@Controller
public class FileUploadController {

    /**
     * 图片上传
     * @param file
     * @param session
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {"/fileUpload"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request, Model model) {
        ServletContext context = RequestContextUtils.getWebApplicationContext(request).getServletContext();
        String realPath = context.getRealPath("/");
        String name = UUID.randomUUID().toString();
        String sessionId = request.getParameter("sessionId");
        
        String suffix = file.getOriginalFilename();
		suffix = suffix.substring(suffix.length()-4,suffix.length());

        //文件存放位置
        File dir = new File(realPath + "/upload/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String uri = "/upload/"+ name + suffix;
        if(sessionId.equals("photo")){//上传头像
            File dirPhoto = new File(realPath + "/upload/photo/");
            if (!dirPhoto.exists()) {
                dirPhoto.mkdirs();
            }
            uri = "/upload/photo/"+ name + suffix;
        }
        //文件全路径
        String fullName = realPath + uri;
        if (!file.isEmpty()) {
            try {
                long fileSize = file.getSize(); //图片字节数
                if(fileSize <= 1024 * 1024){ //图片小于1M
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullName)));
                    stream.write(bytes);
                    stream.close();

                    session.setAttribute(sessionId, uri);
                    //图片大小判定
                    BufferedImage bufferedImage = ImageIO.read(new File(fullName));
                    int width = bufferedImage.getWidth();
                    int height = bufferedImage.getHeight();
                    //  像素限制
                    if(width >800 || height > 4200){
                        session.setAttribute(sessionId, "sizeError");
                    }
                }else{
                    session.setAttribute(sessionId, "sizeError");
                }
                return request.getContextPath()+uri;
            } catch (Exception e) {
                return "false";
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
