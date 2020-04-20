package com.example.boot.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.common.page.Page;
import com.example.boot.test.entity.TestEntity;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author panzhi
 * @since 2019-06-21
 */
public interface TestMapper extends BaseMapper<TestEntity> {

	@SuppressWarnings("rawtypes")
	Page<TestEntity> findPageList(Page page);
}
