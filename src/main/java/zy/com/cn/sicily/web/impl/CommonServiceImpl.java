package zy.com.cn.sicily.web.impl;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zy.com.cn.sicily.web.service.CommonService;
import zy.com.cn.sicily.web.utils.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @title: CommonServiceImpl
 * @description: 上传文件服务
 * @author: zhangyan
 * @date: 2020-08-20 17:38
 * @version: 1.0
 **/
@Service
public class CommonServiceImpl implements CommonService {

    private Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
    /**
     * 上传文件
     *
     * @param file
     * @param fileType
     * @return
     */
    @Override
    public String upload(MultipartFile file, String fileType) {
        try {
            String extension = getExtension(file.getOriginalFilename());
            boolean isAllowFormat = false;
            for(String f:Constants.UPLOAD_IMAGE_FORMAT){
                if(f.equalsIgnoreCase("*") || f.equalsIgnoreCase(extension)){
                    isAllowFormat = true;
                    break;
                }
            }
            if(!isAllowFormat){
                logger.error("请上传{}格式的文件", Constants.UPLOAD_IMAGE_FORMAT.toString());
                return MessageFormat.format("请上传{0}格式的文件", Constants.UPLOAD_IMAGE_FORMAT.toString());
            }
            Long maxUploadSize = Constants.UPLOAD_IMAGE_SIZE;
            Long fileSize = (long) Math.ceil(file.getSize() / FileUtils.ONE_MB);
            if (maxUploadSize != null && fileSize.longValue() > maxUploadSize.longValue()) {
                return MessageFormat.format("文件超出大小限制({0})KB", maxUploadSize);
            }
            String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()).concat(UUID.randomUUID().toString().replace("-", ""));
            String savePath = Constants.UPLOAD_IMAGE_DIR + File.separator;
            String url = Constants.UPLOAD_IMAGE_URL + name + "." + extension ;
            uploadFile(file.getBytes(), savePath, name + "." + extension);
            return url;
        } catch (Exception e) {
            logger.error("服务器繁忙，上传文件失败", e);
            return "文件上传失败";
        }
    }

    public void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + File.separator + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    private String getExtension(String originalFilename) {
        int i = originalFilename.lastIndexOf(".");
        return originalFilename.substring(i + 1).toLowerCase();
    }
}
