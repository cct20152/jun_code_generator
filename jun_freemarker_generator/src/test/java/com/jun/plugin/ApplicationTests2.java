package com.jun.plugin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jun.plugin.base.mapper.UserMapper;
import com.jun.plugin.base.model.User;
//import com.jun.plugin.biz.dao.User1Mapper;
//import com.jun.plugin.biz.pojo.User1;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests2 {

//	@Autowired
//	private User1Mapper userMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() {
//        List<User1> userList = userMapper.selectAll();
//        System.err.println(userList);
    }

}
