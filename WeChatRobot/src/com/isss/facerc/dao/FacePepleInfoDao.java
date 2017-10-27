package com.isss.facerc.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.isss.liuh.vo.FacePepleInfo;


public interface FacePepleInfoDao {
	public int addFaceInfo(FacePepleInfo faceInfo);
	public int updataFaceInfo(FacePepleInfo faceInfo);
	public FacePepleInfo searchFacePepleInfoById(@Param("uid") String uid);
	public List<FacePepleInfo> searchFacePepleInfoAll(@Param("line") int line,@Param("number") int number);
	public int updataPicPath(@Param("uid") String uid,@Param("picPath") String picPath);
	public String searchPicPath(@Param("uid") String uid);
}
