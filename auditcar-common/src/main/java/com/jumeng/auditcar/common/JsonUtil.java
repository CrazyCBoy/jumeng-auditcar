package com.jumeng.auditcar.common;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class JsonUtil {
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	// 将对象转换为JSON字符串
	public static String toJson(Object object) {
		try {
			return MAPPER.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 将JSON字符串转换为对象
	public static <T> T toObject(String json, Class<T> clazz) {
		try {
			return MAPPER.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 将JSON字符串转换为MAP
	@SuppressWarnings("unchecked")
	public static Map<String, String> toMap(String json) {
		try {
			return MAPPER.readValue(json, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 将JSON字符串转换为MAP
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toObjectMap(String json) {
		try {
			return MAPPER.readValue(json, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 将对象转换为JSON字符串
	public static String toJson(Object object,String dateFormat) {
		MAPPER.setDateFormat(new SimpleDateFormat(dateFormat));
		try {
			return MAPPER.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 将JSON字符串转换为对象
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String json, TypeReference<?> typeReference) throws JsonParseException, JsonMappingException, IOException {
		MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		return (T)MAPPER.readValue(json, typeReference);
	}

}