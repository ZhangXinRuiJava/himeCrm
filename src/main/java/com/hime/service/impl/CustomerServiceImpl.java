package com.hime.service.impl;

import java.util.List;

import com.hime.mapper.CustomerMapper;
import com.hime.pojo.Customer;
import com.hime.service.CustomerService;
import com.hime.utils.Page;
import com.hime.utils.QueryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Resource(name = "customerMapper")
	private CustomerMapper customerMapper;

	public CustomerMapper getCustomerMapper() {
		return customerMapper;
	}

	public void setCustomerMapper(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}

	@Override
	public Page queryAllCustomer(QueryVo vo) {
		vo.setStart((vo.getPage()-1)*vo.getSize());
		System.out.println("service:"+vo);
		List<Customer> list = customerMapper.queryAllCustomer(vo);
		System.out.println("查询数量："+list.size());//第一次141
		Integer total = customerMapper.queryCountByQueryVo(vo);
		Page<Customer> page = new Page<Customer>(total, vo.getPage(), vo.getSize(), list);
		return page;
	}

	@Override
	public Customer queryCustomerById(String id) {
		Customer customer = customerMapper.queryCustomerById(id);
		return customer;
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerMapper.updateCustomer(customer);
	}

	@Override
	public Integer deleteCustomer(String id) {
		Integer integer = customerMapper.deleteCustomer(id);
		return integer;
	}

}
