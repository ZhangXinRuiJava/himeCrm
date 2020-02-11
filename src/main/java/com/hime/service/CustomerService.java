package com.hime.service;

import com.hime.pojo.Customer;
import com.hime.utils.Page;
import com.hime.utils.QueryVo;

public interface CustomerService {
	
	/**
	 * 根据条件查询所有Customer
	 * @param vo
	 * @return
	 */
	public Page queryAllCustomer(QueryVo vo);
	
	/**
	 * 根据id查询Customer
	 * @param id
	 * @return
	 */
	public Customer queryCustomerById(String id);
	
	/**
	 * 更新客户信息
	 * @param customer
	 */
	public void updateCustomer(Customer customer);
	
	/**
	 * 根据id删除客户信息
	 * @param id
	 * @return
	 */
	public Integer deleteCustomer(String id);
}
