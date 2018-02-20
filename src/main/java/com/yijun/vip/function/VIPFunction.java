package com.yijun.vip.function;

import org.springframework.transaction.annotation.Transactional;

import com.yijun.vip.entity.VIPEntity;
import com.yijun.vip.web.handler.ao.VIPAo;

public interface VIPFunction {
	/**
	 * vip客户查询
	 * @param code
	 * @return
	 * @throws Exception
	 */
	VIPEntity queryVip(String code)throws Exception;
	/**
	 * 录入VIP
	 * @param ao
	 * @return
	 * @throws Exception
	 */
	@Transactional
	VIPEntity addVip(VIPAo ao)throws Exception;
	
}
