package com.hime.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hime.pojo.BaseDict;
import com.hime.pojo.Customer;
import com.hime.service.BaseDictService;
import com.hime.service.CustomerService;
import com.hime.utils.Page;
import com.hime.utils.QueryVo;

@Controller
public class CustomerController {
	
	@Resource(name="customerService")
	private CustomerService customerService;
	@Resource(name="baseDictService")
	private BaseDictService baseDictService;
	
	@Value("${source}")
	private String SOURCE;
	@Value("${industry}")
	private String INDUSTRY;
	@Value("${level}")
	private String LEVEL;
	
	@RequestMapping("/index.action")
	public String index(Model model,QueryVo vo){
		System.out.println("进入CustomerController2333");
		System.out.println("vo:"+vo);
		//1,初始化查询条件
		List<BaseDict> sourceType = baseDictService.queryNameByTypeCode(this.SOURCE);
		List<BaseDict> industryType = baseDictService.queryNameByTypeCode(this.INDUSTRY);
		List<BaseDict> levelType = baseDictService.queryNameByTypeCode(this.LEVEL);
		model.addAttribute("sourceType", sourceType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		//2,初始化列表数据
		Page page = customerService.queryAllCustomer(vo);
		model.addAttribute("page",page);
		return "customer";
	}
	
	@RequestMapping("/customer/edit.action")
	public @ResponseBody Customer edit(String id){
		Customer customer = customerService.queryCustomerById(id);
		return customer;
	}
	
	@RequestMapping("/customer/update.action")
	public @ResponseBody String update(Customer customer){
		customerService.updateCustomer(customer);
		return "success";
	}
	
	@RequestMapping("/customer/delete.action")
	public @ResponseBody String delete(String id){
		Integer integer = customerService.deleteCustomer(id);
		if(integer>0){
			return "success";
		}
		return "fail";
	}
	
	@RequestMapping("/test.action")
	public void test(Model model,String username,int age) throws UnsupportedEncodingException{
		//服务器解析请求体默认编码格式为ISO-8859-1
		//post乱码在web.xml中配置即可，get：1，手动、2，修改tomcat的默认URIEncoding格式，使其从ISO-8859-1变为UTF-8
		/*username = new String(username.getBytes("ISO-8859-1"),"utf-8");*/
		System.out.println("username:"+username);
		System.out.println("age:"+age);
	}
	
	@RequestMapping("/testMyTag.action")
	public String testMyTag(){
		return "testMyTag";
	}
	
}
