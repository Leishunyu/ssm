package com.ssm.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ssm.dto.CardInfoDto;
import com.ssm.dto.PaymentOrderDto;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author 16060823
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class PaymentOrderMain {
	/**
	 * 签名私钥
	 */
	private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL2lPh5ao0MOW82zj4HtfnCHTT/dWAFmiVSkLKjsmMGjerb1ia0llMHtOPdot6lQGWUtKp6bQhJwcp47gY+PsGdnk3XhbNsBFmRvUgatXzrRNgNR4RA60q+tR0tyPjE04WXsDGBReDg4behvFIStie9nginDCJ0pZLpuT3zfnDpLAgMBAAECgYBojAz3rN3uHJ9Nghyt8uBOZriWaY6XjxBMQgmAwpXfwiDndesFMf+U/RL3iZoeU9L8LO8e6tZ81dLKGmHtY7hKBNakqAt2DSrCj1oVvCIYDw88UDEHNq0V33HpujRHxCBUsYxFQRBOKx/OE/W3BsjTXOC+bkW1ifoH+KDPJMdUuQJBAPRyMUjaNsBYQBiy9osFiPhRzI/7DYcsJ0giixnDi5iDxRWSIF1diYBEvXD7e82W9SxifQLZamphij8I4TG+dsUCQQDGm/X1+6iMquYpDjrznwqS02WXS/UABK8X8jb7I4rYXB7LX90u7QjeMnhVunYuw8AGG4x5o1AyVeoUuigV7H3PAkBP6bkE+RmnHfKuYGtLHZ8elxdKBqfwhdW9tlVFMfFEBef7Wk8sVrTp8w7/jad2maBDR1tZABzS9FpOqyd7rKmFAkBCM3g/3vumr8x6QOELlaa7nDVPwiTPNcB7VeRXv9gnGEW3eqPKYX5EOkR8JuP/4IXgQ0yGuxYfQR3+fLKh5CtnAkEAypP897fXTALsaRrbZEY2YqLuDvvji9yhdSzKG02Uyu64BEEbYK//muK1jWehoFv9X9o0cRZuP/218avO9PAx6w==";
	/**
	 * 访问地址 SIT
	 */
	static String url = "https://ebanksandbox.suning.com/epps-ebpg/singleWithhold/paymentOrder.do";
	// 正式秘钥
	// static String
	// url="https://payment.suning.com/epps-pag/apiGateway/merchantOrder/queryMerchantOrder.do";

	public static void main(String[] args) {

		try {
			System.out.println("1.构造参数开始....");
			;
			// 1.构造参数
			PaymentOrderDto dto = buildPaymentOrderDto();

			CardInfoDto buildCardInfo = buildCardInfo();
			buildCardInfo.setCardHolderName("梁晓明");
			buildCardInfo.setCardNo("‭6228480608750192270‬");
			buildCardInfo.setCertNo("445381199006304016");
			buildCardInfo.setMobileNo("13751231682");
			// 2.cardInfo 加密
			String jsonStr = JSONUtil.toJSONString(buildCardInfo);
			String cryptoStr = CryptoUtil.encryptJson(jsonStr);
			dto.setCardInfo(cryptoStr);
			System.out.println("加密字符串" + jsonStr);

			System.out.println("加密结果" + cryptoStr);
			// 3.签名
			Map<String, String> map = objectToMap(dto);
			// 将这些数据按字典顺序（从a到z，首字母相同则看第二个字母）排序并拼接 字符串
			String digestStr = Digest.digest(map, "signature", "signAlgorithm");
			System.out.println("签名前:" + digestStr);

			String signature = CryptoUtil.sign(digestStr, CryptoUtil.getPrivateKey(PRIVATE_KEY));

			dto.setSignature(signature);
			System.out.println("请求环境 参数" + map);
			map = objectToMap(dto);
			String responseStr = HttpClientUtil.post(url, map, false);
			System.out.println("结果响应" + responseStr);

		} catch (Exception e) {
			System.out.println("错误内容" + e);
		}

	}

	private static CardInfoDto buildCardInfo() {
		// 取默认的信息
		return new CardInfoDto();
	}

	private static PaymentOrderDto buildPaymentOrderDto() {
		System.out.println("支付订单构建开始....");
		PaymentOrderDto dto = new PaymentOrderDto();
		dto.setMerchantNo("70182424");
		dto.setPublicKeyIndex("0001");
		dto.setVersion("2.0");
		dto.setSignature("");
		dto.setSignAlgorithm("RSA");
		dto.setInputCharset("UTF-8");
		dto.setSubmitTime(getNum());
		dto.setBankCode("ABC");
		dto.setCardType("1");
		// dto.setCardInfo("加密");
		dto.setOutOrderNo(getNum() + "001"); //
		dto.setOrderType("01");
		dto.setOrderAmount("100");
		dto.setCurrency("CNY");
		dto.setOrderTime(getNum());
		dto.setSalerMerchantNo("");
		dto.setGoodsType("521405");
		dto.setGoodsName("6LSd5bCU6YeR5oqk6IWV5byPIA==");
		dto.setPayTimeout("7d");
		dto.setRoyaltyParameters("NzAwNTY1NzVeMTBe5YiG5L2g55qEfDcwMDU2NTc1XjIwXuS9oOS5n+aciQ=="); // 70056575^10^分你的|70056575^20^你也有
		dto.setTunnelData("eyJ0dW5uZWxEYXRhIjoidGVzdERhdGEifQ==");// {"tunnelData":"testData"}
		dto.setRemark("6YCP5Lyg57uZ5ZWG5oi355qE5YaF5a65");// 透传给商户的内容
		System.out.println("支付订单构建结束...");
		return dto;
	}

	public static String getNum() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	public static Map<String, String> objectToMap(Object obj) throws Exception {
		if (obj == null) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();

		Field[] declaredFields = obj.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			field.setAccessible(true);
			Object fieldObj = field.get(obj);
			String fieldStr = "";
			if (null != fieldObj) {
				fieldStr = fieldObj.toString();
			}
			map.put(field.getName(), fieldStr);
		}

		return map;
	}

}
