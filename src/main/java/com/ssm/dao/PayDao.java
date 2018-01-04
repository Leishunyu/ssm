/**
 * PayDao.java
 */
package com.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.ssm.entity.Yfb;

/**
 * @author wywl Jan 4, 2018 4:54:46 PM modified by :
 */
public interface PayDao {

	/**
	 * @param name
	 * @param orderNo
	 * @param status
	 * @param pageBounds
	 * @return
	 */
	List<Yfb> page(@Param("username") String username, @Param("orderNo") String orderNo, @Param("status") Long status,
			PageBounds pageBounds);

	/**
	 * @param yfb
	 */
	void insert(Yfb yfb);

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
