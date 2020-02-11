package com.hime.mapper;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hime.pojo.Customer;
import com.hime.utils.QueryVo;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerMapperTest {

	@Resource(name="customerMapper")
	private CustomerMapper customerMapper;
	
	@Test
	public void testQueryAllCustomer() {
		QueryVo vo = new QueryVo();
		vo.setStart((vo.getPage()-1)*vo.getSize());
		System.out.println("service:"+vo);
		List<Customer> list = customerMapper.queryAllCustomer(vo);
		System.out.println("查询数量："+list.size());//第一次141
	}

}
