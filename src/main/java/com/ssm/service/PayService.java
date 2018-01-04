/**
 * PayService.java
 */
package com.ssm.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.ssm.entity.Yfb;

/**
 * @author wywl Jan 4, 2018 4:53:12 PM modified by :
 */
public interface PayService {

	/**
	 * @param lkl
	 * @param pageBounds
	 * @return
	 */
	List<Yfb> page(Yfb lkl, PageBounds pageBounds);

	/**
	 * @param yfb
	 */
	void save(Yfb yfb);

	/**
	 * @param id
	 * @return
	 */
	Yfb getById(Long id);

	/**
	 * @param yfb
	 */
	void update(Yfb yfb);

}
