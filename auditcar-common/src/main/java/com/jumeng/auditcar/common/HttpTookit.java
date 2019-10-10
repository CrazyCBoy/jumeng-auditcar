package com.jumeng.auditcar.common;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP工具类
 * @author zhangmeng
 */

public final class HttpTookit { 
    private static Log log = LogFactory.getLog(HttpTookit.class); 
    
    public static String postJson(String url, String jsonBody) throws Exception { 
    	String response = null; 
    	HttpClient client = new HttpClient(); 
    	PostMethod post = new PostMethod(url);
    	post.setRequestHeader("Content-Type","application/json"); 
    	try {
    		if(StringUtils.isNotEmpty(jsonBody)){
    			RequestEntity entity = new StringRequestEntity(jsonBody ,"application/json" ,"UTF-8");
    			post.setRequestEntity(entity);
    		}
        	client.executeMethod(post);
 			response = new String(post.getResponseBodyAsString());
 		} catch (Exception e) {
 			log.error(url + "时，发生异常！", e); 
 		}finally{
 			post.releaseConnection(); 
 		}
        return response;
    }
    
    public static String post(String url, Map<String, Object> params) throws Exception { 
    	String response = null; 
    	HttpClient client = new HttpClient(); 
    	PostMethod post = new PostMethod(url);
    	post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8"); 
    	try {
    		if(params != null){
    			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    			for (String key : params.keySet()) {
    				if(params.get(key) == null){
    					continue;
    				}
    				nameValuePairs.add(new NameValuePair(key, params.get(key).toString()));
    			}
    			post.setRequestBody(nameValuePairs.toArray(new NameValuePair[nameValuePairs.size()]));
    		}
        	client.executeMethod(post);
        	
        	BufferedReader reader = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));  
            StringBuffer stringBuffer = new StringBuffer();  
            String str = "";  
            while((str = reader.readLine())!=null){  
                stringBuffer.append(str);  
            }  
            response = stringBuffer.toString();
        	
// 			response = new String(post.getResponseBodyAsString());
 		} catch (Exception e) {
 			log.error(url + "时，发生异常！", e); 
 		}finally{
 			post.releaseConnection(); 
 		}
        return response;
    }

    public static String get(String url, Map<String, Object> params) throws Exception { 
    	String response = null; 
    	HttpClient client = new HttpClient(); 
    	GetMethod get = new GetMethod(url);
    	try {
			client.executeMethod(get);
			response = new String(get.getResponseBody(), "utf-8");
    	} catch (Exception e) {
    		log.error(url + "时，发生异常！", e); 
    	}finally{
    		get.releaseConnection(); 
    	}
    	return response;
    }
    
  
    
	public static void main(String[] args) throws Exception {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("pager.orderBy", "salesVolume");
//		params.put("pager.order", "desc");
//		params.put("pager.pageNumber", 1);
//		params.put("pager.pageSize", 10);
//		params.put("ownerId", "40288171533825e001533c4550c30014");
//		params.put("token", "6m3ou41429594424828");
//		String json = post("http://192.168.10.111:9080/ecbitfe/client/goods!GetAllGoodsByHot.action", params);
    }
    
}