package com.jumeng.auditcar.common;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 工具类 - 编号生成
 */

public class SerialNumberUtil {
	
	private static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
	
	public static final String MEMBER_SN_PREFIX = "8";// 会员卡号前缀
	public static final String MEMBER_SN_EXCEPTION_PREFIX = "9";// 会员卡号前缀(异常情况)
	public static final long MEMBER_SN_FIRST = 8000000L;// 会员卡号起始数
	public static final long MEMBER_SN_STEP = 1L;// 会员卡号步长
	public static final String PAYMENT_SN_PREFIX = "1";// 支付编号前缀
	public static final String CARD_SN_PREFIX = "5";// 礼品卡编号前缀
	public static final String REFUND_PAYMENT_SN_PREFIX = "4";// 退款支付编号前缀
	public static final String REFUND_ORDER_SN_PREFIX = "22";// 订单编号前缀
	public static final String VERIFY_CODE_PREFIX = "31";// 核销码前缀
	
	public static Long lastMemberSnNumber;
	
	/**
	 * 生成token
	 * 
	 * @return token
	 */
	public synchronized static String buildAppId() {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";  
	    Random random = new Random();  
	    StringBuffer sb = new StringBuffer();  
	    for (int i = 0; i < 6; i++) {  
	        int number = random.nextInt(base.length());  
	        sb.append(base.charAt(number));  
	    } 
	    String baseString = sb.toString();
	    return baseString+System.currentTimeMillis(); 
	}
	
	/**
	 * 生成token
	 * 
	 * @return token
	 */
	public synchronized static String buildToken(Integer length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    Random random = new Random();  
	    StringBuffer sb = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        int number = random.nextInt(base.length());  
	        sb.append(base.charAt(number));  
	    } 
//		Random rnd = new Random();
//	    byte[] tokenData = new byte[8];
//	    rnd.nextBytes(tokenData);
//	    String baseString = Base64.encodeBase64URLSafeString(tokenData);
	    String baseString = sb.toString();
	    return baseString; 
//	    return baseString+System.currentTimeMillis(); 
	}
	
	public synchronized static Set<String> buildTokenSet(Integer num) {
		Set<String> codeSet = new HashSet<>();
		if(num == null) {
			num = 100;
		}
		for (int i = 0; i < num; i++) {
			Boolean isOld = codeSet.add(buildToken(6));
			while(!isOld) {
				isOld = codeSet.add(buildToken(6));
			}
		}
	    return codeSet; 
	}
	
	/**
	 * 生成paymentSn
	 * 
	 * @return paymentSn
	 */
	public synchronized static String buildPaymentSn() {
		return PAYMENT_SN_PREFIX + idWorker.nextId() + "";
	}

	/**
	 * 生成refundPaymentSn
	 * 
	 * @return refundPaymentSn
	 */
	public synchronized static String buildRefundPaymentSn() {
		return REFUND_PAYMENT_SN_PREFIX + idWorker.nextId() + "";
	}
	
	/**
	 * 生成cardSn
	 * 
	 * @return cardSn
	 */
	public synchronized static String buildCardSn() {
		return CARD_SN_PREFIX + idWorker.nextId() + "";
	}

	/**
	 * 生成OrderSn
	 * 
	 * @return OrderSn
	 */
	public synchronized static String buildOrderSn() {
		return REFUND_ORDER_SN_PREFIX + idWorker.nextId();
	}
	
	/**
	 * 生成核销码
	 * 
	 * @return skuId
	 */
	public synchronized static String buildVerifyCode() {
		return VERIFY_CODE_PREFIX + idWorker.nextId() + "";
	}
}