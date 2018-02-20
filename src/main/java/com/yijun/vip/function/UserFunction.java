package com.yijun.vip.function;

import org.springframework.transaction.annotation.Transactional;

import com.yijun.vip.entity.UserEntity;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public interface UserFunction {
	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
    public UserEntity login(String account,String password)throws Exception;
    @Transactional
    UserEntity updatePassword(String id,String oldPassword,String newPasswrod,String newPasswordConfirm)throws Exception;
    
    
    
}
