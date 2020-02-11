package com.hime.mapper;

import java.util.List;

import com.hime.pojo.Customer;
import com.hime.utils.QueryVo;

public interface CustomerMapper {
	
	/**
	 * 查询所有客户
	 * @return
	 */
	public List<Customer> queryAllCustomer(QueryVo vo);
	
	/**
	 * 根据条件查询所有条数
	 * @param vo
	 * @return
	 */
	public Integer queryCountByQueryVo(QueryVo vo);
	
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
