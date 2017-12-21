package com.hd.userprofile.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hd.userprofile.dto.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserProfileContollerIntegrationTest {

	@Autowired
	UserProfileContoller userProfileContoller;
	
	@Test
	public void createUserProfile(){
		User user = new User();
		user.setFirstName("AAA");
		user.setLastName("BBB");
		
		ResponseEntity<?> respEntity = userProfileContoller.createUserProfile(user);
		
		assertNotNull(respEntity);
		assertEquals(200, respEntity.getStatusCodeValue());
	}
	
}
