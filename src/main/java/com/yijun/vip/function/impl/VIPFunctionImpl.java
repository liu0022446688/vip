package com.yijun.vip.function.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.yijun.common.AssertThrowUtil.*;

import com.yijun.common.ThisSystemException;
import com.yijun.common.ThisSystemUtil;
import com.yijun.vip.dao.VIPDao;
import com.yijun.vip.entity.UserEntity;
import com.yijun.vip.entity.VIPEntity;
import com.yijun.vip.function.VIPFunction;
import com.yijun.vip.web.handler.ao.VIPAo;

@Service
public class VIPFunctionImpl implements VIPFunction{
	@Autowired
	VIPDao vdao;
	@Override
	public VIPEntity queryVip(String code) throws Exception {
		//1验证参数
		code=$("vip账号必须填写!",code);
		//验证其他
		
		//2业务处理
		VIPEntity v=vdao.select("code", code);
		
		if(v==null){
			throw new ThisSystemException("未找到此VIP客户["+code+"]");
		}
		//3封装业务结果
		return v;
	}
	@Override
	public VIPEntity addVip(VIPAo ao) throws Exception {
		//1 参数验证
		if(ao==null){
			throw new IllegalArgumentException("ao不能为空");
		}
		//1.1 验证是否必须输入字段
		String phone=$("手机号码必须填写!",ao.getPhone());
		String name=$("手机号码必须填写!",ao.getName());
		//1.2验证手机号是否合法
		assertPatternMatch("手机号码不合法","\\d{11}", phone);
		//1.3 验证手机号码是否已存在
		assertFalse("手机号已存在",vdao.exsits("code",phone));
		//1.4验证名字是否合法
		assertPatternMatch("姓名不合法!","[\u4e00-\u9fa5]{2,}", name);
		//1.5获取性别
		boolean male="1".equals(ao.getSex());
		//1.6获取年龄
		int age=ThisSystemUtil.parseInt(ao.getAge(),0);
		
		//2 业务处理
		VIPEntity v=new VIPEntity();
		
		v.setId(ThisSystemUtil.uuid());
		v.setAddress(ao.getAddress());
		v.setAge(age);
		v.setAmount(0);
		v.setCode(phone);
		v.setEmail(ao.getEmail());
		v.setMale(male);
		v.setName(name);
		v.setQq(ao.getQq());
		v.setRank(0);
		v.setRemark(ao.getRemark());
		v.setZip(ao.getZip());
		
		vdao.insert(v);
		
		//3 封装业务结果
		return v;
	}
	
}
