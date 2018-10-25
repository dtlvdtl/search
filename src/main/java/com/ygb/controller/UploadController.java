package com.ygb.controller;

import com.ygb.jpa.ActivityJPA;
import com.ygb.jpa.StudentJPA;
import com.ygb.controller.StudentEntity;
import jxl.Sheet;
import jxl.Workbook;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

@Controller
public class UploadController {
    @Autowired
    private ActivityJPA activityJPA;
    @Autowired
    private StudentJPA studentJPA;
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(HttpServletRequest request, MultipartFile file){ // 使用@ResponseBody，返回值不会跳转路径
        int i=0, j=0;
        //上传目录地址
        String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String filename = uploadDir +  UUID.randomUUID() + ".xls";

        try{

            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if(!dir.exists()){
                dir.mkdir();
            }
            // 上传文件名
//            String filename = file.getOriginalFilename();
//            String filename = UUID.randomUUID();
            // 服务器端保存的文件对象
            File serverFile = new File(filename);
            file.transferTo(serverFile);
            Workbook book = Workbook.getWorkbook(new File(filename));
            Sheet sheet = book.getSheet(0);

//            行
            int rows = sheet.getRows();
//            列
            int cols = sheet.getColumns();

            //循环读取数据
            for(j=0;j<rows;j++){
                    if (!studentJPA.existsById(Integer.parseInt(sheet.getCell(2, j).getContents()))) {
                        StudentEntity studentEntity = new StudentEntity();
                        studentEntity.setId(Integer.parseInt(sheet.getCell(2, j).getContents()));
                        studentEntity.setName(sheet.getCell(0, j).getContents());
                        studentEntity.setFaculty(sheet.getCell(3, j).getContents());
                        studentEntity.setSumYgs(0);
                        studentJPA.save(studentEntity);
                    }
                    ActivityEntity activityEntity = new ActivityEntity();
                    activityEntity.setAcid(Integer.parseInt(sheet.getCell(2, j).getContents()));
                    activityEntity.setAcName(sheet.getCell(5, j).getContents());
                    activityEntity.setTime(sheet.getCell(6, j).getContents());
                    activityEntity.setYgs(Float.parseFloat(sheet.getCell(7, j).getContents()));
                    activityJPA.save(activityEntity);
//                System.out.println("第"+j+"行："+sheet.getCell(2, j).getContents());


            }
            new File(filename).delete();
        }catch (Exception e){
            // 打印错误信息
            e.printStackTrace();
            new File(filename).delete();
            String str = j+1+"行出现错误,此行以后上传失败";
            str = "第"+str;
            return str;
        }
        return  "上传成功";
    }
}
