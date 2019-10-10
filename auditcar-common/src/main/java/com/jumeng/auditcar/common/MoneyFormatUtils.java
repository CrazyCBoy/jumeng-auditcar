package com.jumeng.auditcar.common;

		import java.math.BigDecimal;

public class MoneyFormatUtils {

	public static BigDecimal moneyFormat(BigDecimal money) {
		if(money != null) {
			money = money.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return money;
	}

}
