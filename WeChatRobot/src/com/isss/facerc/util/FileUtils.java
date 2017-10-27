package com.isss.facerc.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;



public class FileUtils {
	/**
	 * 生成文件路径
	 * @return 文件路径（完整）
	 */
	public static String generateFilePath() {
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String filePath = "H:\\ISSS\\FaceAdd\\"+simpleDateFormat.format(new Date())+"\\";
	        File folder = new File(filePath);
	        if(folder.exists() && folder.isDirectory()) {
	        	return filePath;
	        }else {
	        	folder.mkdirs();
	        	return filePath;
	        }
	}

	/**
	 * 保存文件MultipartFile<br>
	 * @param multipartFile
	 * @param filePath
	 * @param fileName
	 */
	private final static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	public static boolean saveFile(MultipartFile multipartFile, String filePath, String fileName){
        File targetFile = new File(filePath+fileName);
        if(!targetFile.exists())
            targetFile.mkdirs();
        //保存
        try {
        	multipartFile.transferTo(targetFile);
        	return true;
        } catch (Exception e) {
        	logger.error("保存文件：\"" + filePath + fileName + "\"失败" + e.toString());
            return false;
        }
	}
}
