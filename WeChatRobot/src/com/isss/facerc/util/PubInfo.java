package com.isss.facerc.util;

public class PubInfo {
    public static final String BaiduFace_group_id = "ISSS_2017_T";//�ٶ��������������
	private static final String AccessToken = "24.2b6af126706e21fb723762f3ef9ac631.2592000.1510901621.282335-10173383";
	/**
	 * ����ɾ��
	 */
	public static final String faceDeletUrl = "https://aip.baidubce.com/rest/2.0/face/v2/faceset/user/delete?access_token="+AccessToken;
	/**
	 * ������Ϣ
	 */
	public static final String faceGet = "https://aip.baidubce.com/rest/2.0/face/v2/faceset/user/get?access_token="+AccessToken;

	/**
	 * ����Ϣ
	 */
	public static final String faceGroup = "https://aip.baidubce.com/rest/2.0/face/v2/faceset/group/getlist?access_token="+AccessToken;

	/**
	 * ������Ϣ
	 */
	public static final String faceGroupGet = "https://aip.baidubce.com/rest/2.0/face/v2/faceset/group/getusers?access_token="+AccessToken;

}
