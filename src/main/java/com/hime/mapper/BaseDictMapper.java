package com.hime.mapper;

import java.util.List;

import com.hime.pojo.BaseDict;

public interface BaseDictMapper {
	
	/**
	 * 根据字典类别代码查询相应名称
	 * @param code
	 * @return
	 */
	public List<BaseDict> queryNameByTypeCode(String code);
	
}
