package com.eagle.WeChatRobot.utils;

public class SystemUtil {
	/**
	 * 判断字符是汉字还是字母
	 * @param string
	 * @return
	 */
    public static boolean isLetter(String string){
   	 if(string.length()==string.getBytes().length){
   		 return true;
   	 }else{
   		 return false;
   	 }
    }
}
