package zy.com.cn.sicily.web.service;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
    /**
     * 上传文件
     * @param file
     * @param fileType
     * @return
     */
    String upload(MultipartFile file, String fileType);
}
