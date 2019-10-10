package com.jumeng.auditcar.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {
	
	/**
     * 
     * 将String转换成unicode编码格式
     * @param str
     * @return String
     * @throws
     */
    public static String unicodeEncoding(String str) {
    	char[] utfBytes = str.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }
	
	/**
     * 将unicode转中文
     * @param dataStr
     * @return
     */
    public static String decodeUnicode(String dataStr) {     
    	Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(dataStr);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            dataStr = dataStr.replace(matcher.group(1), ch + "");

        }

        return dataStr;
     } 
//    public static String decodeUnicode(String dataStr) {     
//    	int start = 0;
//    	int end = 0;
//    	final StringBuffer buffer = new StringBuffer();
//    	while (start > -1) {
//    		end = dataStr.indexOf("\\u", start + 2);
//    		String charStr = "";
//    		if (end == -1) {
//    			charStr = dataStr.substring(start + 2, dataStr.length());
//    		} else {
//    			charStr = dataStr.substring(start + 2, end);
//    		}
//    		char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
//    		buffer.append(new Character(letter).toString());
//    		start = end;
//    	}
//    	return buffer.toString();
//    } 
}
