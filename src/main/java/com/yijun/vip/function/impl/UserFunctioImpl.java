package com.yijun.vip.function.impl;

import com.github.pagehelper.PageHelper;
import com.yijun.common.ThisSystemException;
import com.yijun.common.ThisSystemUtil;

import static com.yijun.common.AssertThrowUtil.*;

import java.util.List;

import com.yijun.vip.dao.UserDao;
import com.yijun.vip.dao.VIPDao;
import com.yijun.vip.entity.UserEntity;
import com.yijun.vip.entity.VIPEntity;
import com.yijun.vip.function.UserFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
@Service
public class UserFunctioImpl implements UserFunction{
    @Autowired
    UserDao udao;
    
    @Override
    public UserEntity login(String account, String password) throws Exception {
    	account=$("账户不能为空!",account);
        password=$("密码不能为空!",password);

        UserEntity u=udao.select("account",account);
        if(u==null){
            throw new ThisSystemException("账号不存在!");
        }
        password=ThisSystemUtil.md5(password);
        if(!u.getPassword().equals(password)){
            throw new ThisSystemException("密码错误");
        }


        return u;
    }
    
	public UserEntity updatePassword(String id,String oldPassword, String newPassword, String newPasswordConfirm)
			throws Exception {
		//1验证参数
		id=$("id不能为空",id);
		oldPassword=$("旧密码必须填写!",oldPassword);
		newPassword=$("新密码必须填写!",newPassword);
		newPasswordConfirm=$("新密码确认必须填写!",newPasswordConfirm);
		System.out.println(newPassword+"-"+newPasswordConfirm);
		assertEquals("两次密码不一致", newPassword, newPasswordConfirm);
		assertNotEquals("新密码和旧密码不能一样", oldPassword,newPassword);
		//2找到用户
		UserEntity u=udao.select("id", id);
		/*
		if(u==null){
			throw new ThisSystemException("无法找到用户");
		}
		*/
		assertNotNull("无法找到用户", u);
		oldPassword=ThisSystemUtil.md5(oldPassword);
		//3验证旧密码
		assertEquals("旧密码不正确", u.getPassword(),oldPassword);
		//4更新密码
		newPassword=ThisSystemUtil.md5(newPassword);
		u.setPassword(newPassword);
		
		udao.update(u);
		
		return u;
	}
}
