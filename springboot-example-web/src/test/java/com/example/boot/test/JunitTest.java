package com.example.boot.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.boot.Application;
import com.example.boot.test.entity.TestEntity;
import com.example.boot.test.service.TestService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JunitTest {
	
	@Autowired
	private TestService testService;
	
	@Test
	public void testInsert(){
		testService.deleteAll();
		List<TestEntity> list = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			list.add(new TestEntity(String.valueOf(i), "name-"+i, LocalDateTime.now().plusSeconds(i), LocalDate.now().plusDays(i)));
		}
		testService.saveBatch(list, list.size());
	}

}
