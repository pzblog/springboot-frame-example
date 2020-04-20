package com.example.upload.controller;


import com.example.upload.entity.FileUploadBean;
import com.example.upload.util.CacheUtil;
import com.example.upload.util.FileUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@RestController
public class CustomerController {

    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

//    private static String finalDirPath = "/Users/panzhi/Documents/pz/upload/";

    private static String finalDirPath = "/xuhao/upload/";


    private static Integer MAX_COUNT = 1000000;


    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

    @CrossOrigin
    @RequestMapping("/upload")
    public void uploadFile(HttpServletRequest request) throws IOException, InterruptedException {
        boolean multipartContent = ServletFileUpload.isMultipartContent(request);
        // 是文件上传请求
        if(multipartContent){
            // 获取请求长度
            int length = request.getIntHeader("Content-Length");
            logger.info("用户请求的长度为:{}", length);

            // 创建工厂（这里用的是工厂模式）
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //获取汽车零件清单与组装说明书（从ServletContext中得到上传来的数据）
            ServletContext servletContext = request.getSession().getServletContext();
            // 临时文件目录
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            //工厂把将要组装的汽车的参数录入工厂自己的系统，因为要根据这些参数开设一条生产线（上传来的文件的各种属性）
            factory.setRepository(repository);

            //此时工厂中已经有了汽车的组装工艺、颜色等属性参数（上传来的文件的大小、文件名等）
            //执行下面的这一行代码意味着根据组装工艺等开设了一条组装生产线
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                FileUploadBean param = new FileUploadBean(fileItems, logger);
//                logger.info("文件信息为:{}", param.toString());

                String uploadDirPath = finalDirPath + param.getMd5();
                Path tmpDir = Paths.get(uploadDirPath);
                //判断文件夹是否存在
                if (!Files.exists(tmpDir)) {
                    //创建一个文件夹
                    synchronized (CustomerController.class) {
                        if (!Files.exists(tmpDir)) {
                            Files.createDirectory(tmpDir);
                        }
                    }
                }else{
                    //判断目标文件是否存在，如果存在，判断MD5是否相同
                    Path localPath = Paths.get(uploadDirPath, param.getName());
                    if (Files.exists(localPath)) {
                        //表示已经上传过相同的文件
                        if(CacheUtil.containsKey(param.getMd5())){
                            // 比较相等,那么直接返回成功
                            logger.info("已检测到重复文件{},并且比较md5相等,已直接返回", param.getName());
                            return;
                        }
                    }
                }

                //上传临时分片文件
                String tempFileName = param.getName() + "_" + param.getChunk() + "_tmp";
                Path path = Paths.get(uploadDirPath, tempFileName);
                if (!Files.exists(path)) {
                    byte[] fileData = FileUtils.read(param.getFile(), 2048);
                    try {
                        FileUtils.authorizationAll(path);
                        Files.write(path, fileData, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
                    } catch (IOException e) {
                        // 删除上传的文件
                        Files.deleteIfExists(path);
                        logger.error("删除临时文件失败！",e);
                    }
                }

                //合并文件，分片从0开始
                if((param.getChunk() + 1) == param.getChunks()){
                    Path realFile = Paths.get(uploadDirPath, param.getName());
                    if(!Files.exists(realFile)){
                        synchronized (CustomerController.class){
                            if(!Files.exists(realFile)){
                                System.out.println("合并文件");
                                realFile = Files.createFile(realFile);
                                // 设置权限
                                FileUtils.authorizationAll(realFile);
                                for (int i = 0; i < param.getChunks(); i++) {
                                    // 获取每个分片
                                    String tempChuckFileName = param.getName() + "_" + i + "_tmp";
                                    Path itemChuckPath = Paths.get(uploadDirPath, tempChuckFileName);
                                    //循环检查是否临时文件是否生成
                                    int count = 0;
                                    while (count < MAX_COUNT){
                                        if(Files.exists(itemChuckPath)){
                                            break;
                                        }
                                        count++;
                                    }
                                    byte[] bytes = Files.readAllBytes(itemChuckPath);
                                    Files.write(realFile, bytes, StandardOpenOption.APPEND);
                                    //写完后删除掉临时文件
                                    Files.delete(itemChuckPath);
                                }
                                CacheUtil.add(param.getMd5());
                                logger.info("合并文件{}成功", param.getName());
                            }
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }
}
