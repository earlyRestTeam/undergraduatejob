package com.lyx.undergraduatejob.controlles;



import com.lyx.undergraduatejob.utils.APIResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.UUID;


@Controller
public class ImageUploadController {

    @PostMapping("upload")
    @ResponseBody
    public APIResult upload(HttpServletRequest request, MultipartFile file) throws UnsupportedEncodingException {
        String file1 = URLDecoder.decode(this.getClass().getClassLoader().getResource("static").getFile(),"utf8");
        String realPath = file1+File.separator+"upload"+File.separator;

        System.out.println("realPath = " + realPath);
        String name = UUID.randomUUID().toString().replaceAll("-", "");
        String originalFilename = file.getOriginalFilename();
        //originalFilename = originalFilename.substring(originalFilename.lastIndexOf("."));
        name += originalFilename;
        System.out.println("originalFilename = " + originalFilename);
        File f = new File(realPath+name);
        File parentFile = f.getParentFile();
        if( !parentFile.exists())
            parentFile.mkdirs();
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return APIResult.genSuccessApiResponse("/upload/"+name);
    }

}
