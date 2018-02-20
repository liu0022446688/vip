package com.yijun.vip.dao;

import java.util.List;

import com.yijun.vip.entity.VIPRankEntity;

public interface VIPRankDao {
	List<VIPRankEntity> selectAll()throws Exception;
}
