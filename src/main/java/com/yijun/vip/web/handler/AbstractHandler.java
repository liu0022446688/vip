package com.yijun.vip.web.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yijun.vip.entity.VIPRankEntity;

public abstract class AbstractHandler {

	public VIPRankEntity getRank(HttpServletRequest req,int rank){
		Map<Integer, VIPRankEntity> rankMap=(Map<Integer, VIPRankEntity>)req.getServletContext().getAttribute("RANKS-MAP");
		return rankMap.get(rank);
	}
	
}
