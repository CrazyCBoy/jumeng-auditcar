package com.jumeng.auditcar.common;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import javax.servlet.http.HttpServlet;
public class QuerySendDetails extends HttpServlet {
  /*  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mobile=request.getParameter("mobile");
        String verifyCode=getPhonemsg(request.getParameter("mobile"));
        HttpSession session = request.getSession();
        JSONObject json = new JSONObject();
        json.put("mobile", mobile);
        json.put("verifyCode", verifyCode);
        json.put("createTime", System.currentTimeMillis());
        request.getSession().setAttribute("verifyCode", json);
    }
*/
    public static String getPhonemsg(String mobile) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4Ffxzr9diRAjbdeswk7Z", "aaDMeHc4EXHJQemBBVvgwI3QvacnQP");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", "Smsboy001");
        request.putQueryParameter("TemplateCode", "SMS_174991971");
        request.putQueryParameter("TemplateParam", "{ \"code\":\""+vcode()+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String vcode(){
        String vcode = "";
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }
}

