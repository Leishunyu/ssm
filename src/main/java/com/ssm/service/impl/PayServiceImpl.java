/**
 * PayService.java
 */
package com.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.ssm.dao.PayDao;
import com.ssm.entity.Yfb;
import com.ssm.service.PayService;

/**
 * @author wywl Jan 4, 2018 4:53:32 PM modified by :
 */
@Service("payService")
public class PayServiceImpl implements PayService {

	@Resource
	private PayDao payDao;

	@Override
	public List<Yfb> page(Yfb entity, PageBounds pageBounds) {
		return payDao.page(entity.getName(), entity.getOrderNo(), entity.getStatus(), pageBounds);
	}

	@Override
	public void save(Yfb yfb) {
		payDao.insert(yfb);
	}

	@Override
	public Yfb getById(Long id) {
		return payDao.getById(id);
	}

	@Override
	public void update(Yfb yfb) {
		payDao.update(yfb);
	}

}
