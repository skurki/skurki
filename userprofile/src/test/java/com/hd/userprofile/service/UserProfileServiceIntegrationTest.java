package com.hd.userprofile.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.hd.userprofile.dto.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class UserProfileServiceIntegrationTest {

	@Autowired
	UserProfileService userProfileService;
	
	@Test
	public void testCreateUserProfile(){
		User userProfile = new User();
		userProfile.setFirstName("Adam");
		userProfile.setLastName("Johnson");
		User createdUser = userProfileService.createUserProfile(userProfile);
		
		assertNotNull(createdUser);
		assertEquals("Adam", createdUser.getFirstName());
		assertTrue(createdUser.getId() > 0);
		
	}
	
}
