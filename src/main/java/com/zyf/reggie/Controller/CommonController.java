package com.zyf.reggie.Controller;

import com.zyf.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {
    @Value("${reggie.basePath}")
    private String basePath;
    @PostMapping("/upload")
    public R<String> upload(@RequestPart("file") MultipartFile multipartFile){
        String originalFilename = multipartFile.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String string = UUID.randomUUID().toString();
        String lastFileName=string+substring;
        File file =new File(basePath+lastFileName);
        if (file.exists()){
            file.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(lastFileName);
    }
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        log.info(name);
        try {
            InputStream is = new FileInputStream(new File(basePath+name));
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
