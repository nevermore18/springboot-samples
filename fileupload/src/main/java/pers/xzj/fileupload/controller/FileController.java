package pers.xzj.fileupload.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description file
 * @Author xzj (x378042733@gmail.com)
 * @Date 2021-01-18 11:40
 * @Version 1.0.0
 */
@RestController
public class FileController {

    @GetMapping("/url")
    public void file(HttpServletRequest request) {
        // request.getContextPath() = /file
        System.out.println("request.getContextPath() = " + request.getContextPath());
        // request.getServletPath() = /test
        System.out.println("request.getServletPath() = " + request.getServletPath());
        // request.getRequestURI() = /file/test
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        // request.getRequestURL() = http://localhost:9000/file/test
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // request.getServletContext().getRealPath("/") = C:\Users\Administrator\AppData\Local\Temp\tomcat-docbase.9000.1060277654734337500\
        System.out.println("request.getServletContext().getRealPath(\"/\") = " + request.getServletContext().getRealPath("/"));
    }

    @GetMapping("/date")
    public void date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        // sdf.format(date) = 2021-01-18 12:23:38
        System.out.println("sdf.format(date) = " + sdf.format(date));
    }

    private SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @PostMapping("/upload")
    public Map<String, Object> fileupload(MultipartFile file, HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        String format = sdf.format(new Date());
        String realPath = req.getServletContext().getRealPath("/") + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        // 0c55aa5a-b534-4eae-9a4f-f0acbc472196.txt
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        try {
            file.transferTo(new File(folder, newName));
            // http://127.0.0.1:9000/file/2021/01/18/0c55aa5a-b534-4eae-9a4f-f0acbc472196.txt
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + format + newName;
            result.put("status", "ok");
            result.put("name", oldName);
            result.put("url", url);
        } catch (IOException e) {
            result.put("status", "error");
            result.put("msg", e.getMessage());
        }
        return result;
    }
}
