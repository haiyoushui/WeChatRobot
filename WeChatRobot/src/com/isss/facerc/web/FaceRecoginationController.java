package com.isss.facerc.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.isss.facerc.service.FacePepleInfoService;
import com.isss.facerc.util.FileUtils;
import com.isss.liuh.vo.FacePepleInfo;
import com.isss.weixin.pojo.ControllerResult;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/FaceRC")
public class FaceRecoginationController {
	@Autowired FacePepleInfoService facePepleInfoService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());	

	@RequestMapping(value = "/addFaceFile", method = RequestMethod.POST)
	@ResponseBody
	public ControllerResult<?> photo(@RequestParam(value = "multipartFile") MultipartFile multipartFile, HttpServletRequest request) {
		logger.info("移动端发送图片："+multipartFile.getOriginalFilename());
	
		try {
		String filePath = FileUtils.generateFilePath();
		String fileName = multipartFile.getOriginalFilename();
		boolean saveFile = FileUtils.saveFile(multipartFile, filePath, fileName);
		String uid = fileName.split("_")[0];
		System.out.println(uid+"###########"+fileName);
 		 boolean isSaveSQL = facePepleInfoService.updataPicPath(uid, filePath+fileName);
		if (!saveFile || !isSaveSQL) {
			return new ControllerResult(false,"保存失败");
		}else {
			return new ControllerResult(true,"保存成功");
		}
		}catch(Exception e) {
			e.printStackTrace();
			return new ControllerResult(false,"保存失败");
		}
		
	}
	
	
	
	@RequestMapping(value = "/addFaceInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public ControllerResult addFaceInfo(@RequestParam("pepleinfo") String  params ) {
    		logger.info("移动端添加人脸发送的数据："+params);
			FacePepleInfo facePepleInfo = new FacePepleInfo();
	          JSONObject jsonResult = JSONObject.fromObject(params);
	          facePepleInfo.setUid(jsonResult.getString("uid"));
	          facePepleInfo.setGroupid(jsonResult.getString("groupid"));
	          facePepleInfo.setUname(jsonResult.getString("uname"));
	          facePepleInfo.setGender(jsonResult.getString("gender"));
	          facePepleInfo.setBirthday(jsonResult.getString("birthday"));
	          facePepleInfo.setUinfo(jsonResult.getString("uinfo"));
	          facePepleInfo.setAddress(jsonResult.getString("address"));
	          facePepleInfo.setIdcardaddress(jsonResult.getString("idcardaddress"));
	          facePepleInfo.setIdcardid(jsonResult.getString("idcardid"));
	          facePepleInfo.setEthnic(jsonResult.getString("ethnic"));
	          facePepleInfo.setTime(jsonResult.getString("time"));
	          facePepleInfo.setIMEI(jsonResult.getString("IMEI"));
	          facePepleInfoService.addFaceInfo(facePepleInfo);
		ControllerResult result = new ControllerResult(true,"添加成功");//new ControllerResult<String>(false,"修改成功");
		
		 logger.info("人脸添加返回"+result.toString());
		return result;
	}
	
}
