package com.example.boot.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.common.page.Page;
import com.example.boot.test.entity.TestEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author panzhi
 * @since 2019-06-21
 */
public interface TestService extends IService<TestEntity> {
	
	Page<TestEntity> queryAll(Integer current, Integer size);
	
	void deleteAll();
}
