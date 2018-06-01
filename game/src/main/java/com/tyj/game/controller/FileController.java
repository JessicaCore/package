package com.tyj.game.controller;

import com.tyj.game.bean.Label;
import com.tyj.game.dao.LabelDao;
import com.tyj.game.util.HttpClientUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
public class FileController {
    @Autowired
    private LabelDao labelDao;

    /**
     * 上传图片
     * @param fileData
     * @return
     */
    @RequestMapping(value ="/upload",method = RequestMethod.POST)
    public String upload(@RequestParam(name = "fileData", required = true) MultipartFile[] fileData){
        try {
            String str = HttpClientUpload.httpClientUploadFile(fileData, "image");
            String[] images = str.split(",");
            for (String st:images){
                st = "http://47.94.255.16/"+st;
                Label label = new Label();
                label.setImage(st);
                labelDao.add(label);
            }
            return "添加成功";
        }catch (Exception e){
            return "添加失败";
        }
    }
}
