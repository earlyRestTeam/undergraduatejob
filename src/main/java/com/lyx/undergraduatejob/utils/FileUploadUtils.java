package com.lyx.undergraduatejob.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class FileUploadUtils {

    @Value("${fileupload.path}")
    public static String UPLOAD_PATH;

    public static String upload(MultipartFile file, HttpServletRequest request){


        String realpath = request.getServletContext().getRealPath("/");

        String uploadSavePath = realpath + UPLOAD_PATH;
        File uploadDir = new File(uploadSavePath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }


        String newFileName = UUIDUtils.getTimerCode(file.getOriginalFilename());

        File uploadFile = new File(uploadDir,newFileName);

        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return UPLOAD_PATH+"/"+newFileName;
    }



}
