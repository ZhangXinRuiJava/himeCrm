package com.hime.service.impl;

import java.util.List;

import com.hime.mapper.BaseDictMapper;
import com.hime.pojo.BaseDict;
import com.hime.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {

	private BaseDictMapper baseDictMapper;

	public BaseDictMapper getBaseDictMapper() {
		return baseDictMapper;
	}

	public void setBaseDictMapper(BaseDictMapper baseDictMapper) {
		this.baseDictMapper = baseDictMapper;
	}

	@Override
	public List<BaseDict> queryNameByTypeCode(String code) {
		List<BaseDict> list = baseDictMapper.queryNameByTypeCode(code);
		return list;
	}

}
