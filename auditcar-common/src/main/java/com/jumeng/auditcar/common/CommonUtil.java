package com.jumeng.auditcar.common;

import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类 - 公用
 */

public class CommonUtil {

	public static final String PATH_PREPARED_STATEMENT_UUID = "\\{uuid\\}";// UUID路径占位符
	public static final String PATH_PREPARED_STATEMENT_DATE = "\\{date(\\(\\w+\\))?\\}";// 日期路径占位符
	
	/**
	 * 实现两个实体类属性之间的复制
	 * @param source
	 * @param dest
	 * @throws Exception
	 */
	public static void Copy(Object source, Object dest) throws Exception {
		// 获取属性
		BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), Object.class);
		PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();
		BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), Object.class);
		PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();
		try {
			for (int i = 0; i < sourceProperty.length; i++) {
				for (int j = 0; j < destProperty.length; j++) {
					if (sourceProperty[i].getName().equals(
							destProperty[j].getName())) {
						// 调用source的getter方法和dest的setter方法
						destProperty[j].getWriteMethod().invoke(dest,sourceProperty[i].getReadMethod().invoke(source));
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("属性复制失败:" + e.getMessage());
		}
	}

	/**
	 * 随机获取UUID字符串(无中划线)
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
	
	/**
	 * 获取实际路径
	 * 
	 * @param path
	 *            路径
	 */
	public static String getPreparedStatementPath(String path) {
		if (StringUtils.isEmpty(path)) {
			return null;
		}
		StringBuffer uuidStringBuffer = new StringBuffer();
		Matcher uuidMatcher = Pattern.compile(PATH_PREPARED_STATEMENT_UUID).matcher(path);
		while(uuidMatcher.find()) {
			uuidMatcher.appendReplacement(uuidStringBuffer, CommonUtil.getUUID());
		}
		uuidMatcher.appendTail(uuidStringBuffer);
		
		StringBuffer dateStringBuffer = new StringBuffer();
		Matcher dateMatcher = Pattern.compile(PATH_PREPARED_STATEMENT_DATE).matcher(uuidStringBuffer.toString());
		while(dateMatcher.find()) {
			String dateFormate = "yyyyMM";
			Matcher dateFormatMatcher = Pattern.compile("\\(\\w+\\)").matcher(dateMatcher.group());
			if (dateFormatMatcher.find()) {
				String dateFormatMatcherGroup = dateFormatMatcher.group();
				dateFormate = dateFormatMatcherGroup.substring(1, dateFormatMatcherGroup.length() - 1);
			}
			dateMatcher.appendReplacement(dateStringBuffer, new SimpleDateFormat(dateFormate).format(new Date()));
		}
		dateMatcher.appendTail(dateStringBuffer);
		
		return dateStringBuffer.toString();
	}

	/**
    * 判断当前时间是否满足传入的时间段
    * @param beginDate
    * @param endTime
    * @return
    */
	public static Boolean compareDate4NowDate(Date beginDate, Date endTime){
	   	Boolean key = false;
	   	Date nowDate=new Date();
	   	if(beginDate != null && endTime != null){
				Calendar date = Calendar.getInstance();
				date.setTime(endTime);
				date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
				if(nowDate.compareTo(beginDate) >= 0 && nowDate.compareTo(date.getTime()) <= 0){
					key = true;
				}
			}else{
				if(beginDate != null && nowDate.compareTo(beginDate) >= 0){
					key = true;
				}
				if(endTime != null){
					Calendar date = Calendar.getInstance();
					date.setTime(endTime);
					date.set(Calendar.DATE, date.get(Calendar.DATE) + 1);
					if(nowDate.compareTo(date.getTime()) <= 0){
						key = true;
					}
				}
			}
	   	return key;
	}
}