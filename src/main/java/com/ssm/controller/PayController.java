/**
 * PayController.java
 */
package com.ssm.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.ssm.dto.CardInfoDto;
import com.ssm.dto.PaymentOrderDto;
import com.ssm.entity.Yfb;
import com.ssm.service.PayService;
import com.ssm.utils.CryptoUtil;
import com.ssm.utils.Digest;
import com.ssm.utils.HttpClientUtil;
import com.ssm.utils.JSONUtil;
import com.ssm.utils.MoneyUtils;
import com.ssm.utils.PaymentOrderMain;

/**
 * @author wywl Jan 2, 2018 9:43:23 PM modified by :
 */
@RequestMapping(value = "/pay")
@Controller("payController")
public class PayController {
	/**
	 * 签名私钥
	 */
	private static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL2lPh5ao0MOW82zj4HtfnCHTT/dWAFmiVSkLKjsmMGjerb1ia0llMHtOPdot6lQGWUtKp6bQhJwcp47gY+PsGdnk3XhbNsBFmRvUgatXzrRNgNR4RA60q+tR0tyPjE04WXsDGBReDg4behvFIStie9nginDCJ0pZLpuT3zfnDpLAgMBAAECgYBojAz3rN3uHJ9Nghyt8uBOZriWaY6XjxBMQgmAwpXfwiDndesFMf+U/RL3iZoeU9L8LO8e6tZ81dLKGmHtY7hKBNakqAt2DSrCj1oVvCIYDw88UDEHNq0V33HpujRHxCBUsYxFQRBOKx/OE/W3BsjTXOC+bkW1ifoH+KDPJMdUuQJBAPRyMUjaNsBYQBiy9osFiPhRzI/7DYcsJ0giixnDi5iDxRWSIF1diYBEvXD7e82W9SxifQLZamphij8I4TG+dsUCQQDGm/X1+6iMquYpDjrznwqS02WXS/UABK8X8jb7I4rYXB7LX90u7QjeMnhVunYuw8AGG4x5o1AyVeoUuigV7H3PAkBP6bkE+RmnHfKuYGtLHZ8elxdKBqfwhdW9tlVFMfFEBef7Wk8sVrTp8w7/jad2maBDR1tZABzS9FpOqyd7rKmFAkBCM3g/3vumr8x6QOELlaa7nDVPwiTPNcB7VeRXv9gnGEW3eqPKYX5EOkR8JuP/4IXgQ0yGuxYfQR3+fLKh5CtnAkEAypP897fXTALsaRrbZEY2YqLuDvvji9yhdSzKG02Uyu64BEEbYK//muK1jWehoFv9X9o0cRZuP/218avO9PAx6w==";
	/**
	 * 访问地址 SIT
	 */
	static String url = "https://ebanksandbox.suning.com/epps-ebpg/singleWithhold/paymentOrder.do";

	@Resource
	private PayService payService;

	@RequestMapping(value = "/list")
	public ModelAndView findAll(@RequestParam(required = false) String name,
			@RequestParam(required = false) String orderNo, @RequestParam(required = false) Long status,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "30000") int limit, HttpSession session) {
		System.out.println(session.getAttribute("user"));
		if (session.getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		String sortString = "id.desc";// 如果你想排序的话逗号分隔可以排序多列
		Yfb yfb = new Yfb();
		yfb.setStatus(status);
		if (!StringUtils.isEmpty(orderNo)) {
			yfb.setOrderNo(orderNo);
		}
		if (!StringUtils.isEmpty(name)) {
			yfb.setName(name);
		}
		List<Yfb> list = payService.page(yfb, new PageBounds(page, limit, Order.formString(sortString)));
		PageList pagelist = (PageList) list;
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("lists", list);
		model.put("pages", pagelist.getPaginator());
		model.put("yfb", yfb);
		return new ModelAndView("list", model);
	}

	@RequestMapping(value = "saveOrder")
	public Object saveOrder(HttpServletRequest request, HttpServletResponse response, CardInfoDto entity, Double amount,
			String bankCode) throws Exception {
		Yfb yfb = new Yfb();
		yfb.setAmount(String.valueOf(MoneyUtils.yuanToFen(amount)));
		yfb.setBankCode(bankCode);
		String jsonStr = JSONUtil.toJSONString(entity);
		String cryptoStr = CryptoUtil.encryptJson(jsonStr);
		yfb.setCardInfo(cryptoStr);
		yfb.setName(entity.getCardHolderName());
		yfb.setCardNo(entity.getCardNo());
		yfb.setMobile(entity.getMobileNo());
		System.out.println("加密字符串" + jsonStr);
		System.out.println("加密结果" + cryptoStr);
		payService.save(yfb);
		return "redirect:/list.htm";
	}

	@RequestMapping(value = "payOrder")
	public Object payOrder(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
		Yfb yfb = payService.getById(id);
		if (yfb == null) {
			throw new Exception("系统异常");
		}
		PaymentOrderDto dto = new PaymentOrderDto();
		dto.setBankCode(yfb.getBankCode());
		dto.setCardInfo(yfb.getCardInfo());
		dto.setOrderAmount(yfb.getAmount());
		dto.setOutOrderNo(PaymentOrderMain.getNum() + id);
		Map<String, String> map = objectToMap(dto);
		// 将这些数据按字典顺序（从a到z，首字母相同则看第二个字母）排序并拼接 字符串
		String digestStr = Digest.digest(map, "signature", "signAlgorithm");
		System.out.println("签名前:" + digestStr);
		String signature = CryptoUtil.sign(digestStr, CryptoUtil.getPrivateKey(PRIVATE_KEY));
		dto.setSignature(signature);
		System.out.println("请求环境 参数" + map);
		map = objectToMap(dto);
		String responseStr = HttpClientUtil.post(url, map, false);
		System.out.println(responseStr);
		yfb.setStatus(1L);
		yfb.setOrderNo(dto.getOutOrderNo());
		yfb.setOrderTime(PaymentOrderMain.getNum());
		payService.update(yfb);
		return "redirect:/list.htm";
	}

	/**
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private Map<String, String> objectToMap(Object obj) throws IllegalArgumentException, IllegalAccessException {
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
