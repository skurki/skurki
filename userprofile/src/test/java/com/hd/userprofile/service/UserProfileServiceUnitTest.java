package com.hd.userprofile.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.hd.userprofile.dto.User;
import com.hd.userprofile.repository.UserProfileRepositoryService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class UserProfileServiceUnitTest {
	
	@InjectMocks
	UserProfileService userProfileService = new UserProfileServiceImpl();
	
	@Mock
	UserProfileRepositoryService userProfileRepositoryService;

	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateUserProfile(){
		
		User aMockUser = new User();
		aMockUser.setFirstName("John");
		aMockUser.setLastName("Smith");
		aMockUser.setId(100);
		
		Mockito.when(userProfileRepositoryService.saveProfile(Mockito.any(User.class)))
			.thenReturn(aMockUser);
		
		User createdUser = userProfileService.createUserProfile(null);
		
		assertEquals("John", createdUser.getFirstName());
		
	}
	
	
}
