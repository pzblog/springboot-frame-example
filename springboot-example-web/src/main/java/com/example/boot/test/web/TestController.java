package com.example.boot.test.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.common.page.Page;
import com.example.boot.test.entity.TestEntity;
import com.example.boot.test.service.TestService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author panzhi
 * @since 2019-06-21
 */
@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping("queryAll")
	public Page<TestEntity> queryAll(Integer current, Integer size){
		if(current == null) {
			current = 0;
		}
		if(size == null) {
			size = 15;
		}
		return testService.queryAll(current, size);
	}

}
