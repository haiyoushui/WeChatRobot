package com.isss.facerc.service;

import java.util.List;

import com.isss.liuh.vo.FacePepleInfo;
public interface FacePepleInfoService {
	/**
	 * 添加新的人脸信息
	 * @param wxMeg
	 * @return
	 */
	public boolean addFaceInfo(FacePepleInfo facePepleInfo);
	public int updataFaceInfo(FacePepleInfo facePepleInfo);
	public boolean updataPicPath(String uid,String path);
	public String searchPicPath(String uid);
	public FacePepleInfo SearchFaceInfoById(String uid);
	public List<FacePepleInfo> SearchFaceInfoByAll(int line, int number);
}
	