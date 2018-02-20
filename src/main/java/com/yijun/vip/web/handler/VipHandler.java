package com.yijun.vip.web.handler;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yijun.common.ThisSystemException;
import com.yijun.vip.entity.VIPEntity;
import com.yijun.vip.entity.VIPRankEntity;
import com.yijun.vip.function.VIPFunction;
import com.yijun.vip.web.handler.ao.VIPAo;
import com.yijun.vip.web.handler.vo.VIPVo;
@Controller
public class VipHandler extends AbstractHandler {
	@Autowired
	VIPFunction fun;

	@RequestMapping(path="/vip/qry.do",method=RequestMethod.GET)
	public String qryView()throws Exception{
		return "vip/qry";
	}
	
	@RequestMapping(path="/vip/qry.do",method=RequestMethod.POST)
	public String qry(String code,HttpServletRequest  req)throws Exception{
		try{
			VIPEntity v=fun.queryVip(code);
			
			VIPVo vo=new VIPVo();
			vo.setAddress(v.getAddress());
			vo.setAge(String.valueOf(v.getAge()));
			vo.setAmount(String.valueOf(v.getAmount()/100.0));
			vo.setCode(v.getCode());
			vo.setEmail(v.getEmail());
			vo.setName(v.getName());
			vo.setQq(v.getQq());
			VIPRankEntity rank=this.getRank(req,v.getRank());
			//TODO:查询vip级别
			vo.setRank(rank.getName());
			vo.setRemark(v.getRemark());
			vo.setSex(v.isMale()?"男":"女");
			vo.setZip(v.getZip());
			req.setAttribute("v", vo);
		}catch(ThisSystemException e){
			req.setAttribute("message", e.getMessage());
		}
		return "vip/qry";
	}
	@RequestMapping(path="/vip/add.do",method=RequestMethod.GET)
	public String addView(){
		return "vip/add";
	}
	@RequestMapping(path="/vip/add.do",method=RequestMethod.POST)
	public String addVip(VIPAo ao,HttpServletRequest req)throws Exception{
		try{
			//1 业务方法执行
			VIPEntity v=fun.addVip(ao);
			//2业务跳转
			//TODO:后期跳转到消费录入界面
			req.setAttribute("message", "录入成功!");
			return "vip/add";
		}catch(ThisSystemException e){
			req.setAttribute("message", e.getMessage());
		}
		
		return "vip/add";
	}
	
	
	
	
}
