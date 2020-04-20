package com.example.boot.test.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.common.page.Page;
import com.example.boot.test.dao.TestMapper;
import com.example.boot.test.entity.TestEntity;
import com.example.boot.test.service.TestService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author panzhi
 * @since 2019-06-21
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements TestService {

	@Override
	public Page<TestEntity> queryAll(Integer current, Integer size) {
		Page<TestEntity> page = new Page<>(current,size);
		return baseMapper.findPageList(page);
	}

	@Override
	public void deleteAll() {
		baseMapper.delete(new QueryWrapper<TestEntity>());
	}

}
