package com.isss.facerc.service.Impl;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isss.facerc.dao.FacePepleInfoDao;
import com.isss.facerc.service.FacePepleInfoService;
import com.isss.liuh.vo.FacePepleInfo;


@Service
public class FacePepleInfoServiceImpl implements FacePepleInfoService {

	@Autowired
	private FacePepleInfoDao facePepleInfoDao;
	private Logger logger = Logger.getLogger(FacePepleInfoServiceImpl.class);

	@Override
	public boolean addFaceInfo(FacePepleInfo facePepleInfo) {
		int state = 0;
		String id = null;
		if(facePepleInfo.getUid() != null && !"null".equals(facePepleInfo.getUid()) && !"".equals(facePepleInfo.getUid())){
			id = facePepleInfo.getUid();
		}else {
			id = facePepleInfo.getIdcardid();
		}
		if(SearchFaceInfoById(id) == null){
			state = facePepleInfoDao.addFaceInfo(facePepleInfo);
		}else {
			state = updataFaceInfo(facePepleInfo);
		}
		if (state > 0) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public int updataFaceInfo(FacePepleInfo facePepleInfo) {
	return  facePepleInfoDao.updataFaceInfo(facePepleInfo);
	}
	@Override
	public FacePepleInfo SearchFaceInfoById(String uid) {
		return facePepleInfoDao.searchFacePepleInfoById(uid);
	}

	@Override
	public List<FacePepleInfo> SearchFaceInfoByAll(int line, int number) {
		return facePepleInfoDao.searchFacePepleInfoAll(line, number);
	}
	@Override
	public boolean updataPicPath(String uid,String picPath) {
		int state = 0;
		String path = searchPicPath(uid);
		if(path ==null || "".equals(path )) {
			 state = facePepleInfoDao.updataPicPath(uid,picPath);
		}else {
			 state = facePepleInfoDao.updataPicPath(uid,path+","+picPath);
		}
		if(state>0) {
			return true;
		}else {
		return false;
		}
	}
	@Override
	public String searchPicPath(String uid) {
		return facePepleInfoDao.searchPicPath(uid);
	}

}
