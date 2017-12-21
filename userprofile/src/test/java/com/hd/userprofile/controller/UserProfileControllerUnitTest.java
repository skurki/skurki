package com.hd.userprofile.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;

import com.hd.userprofile.dto.User;
import com.hd.userprofile.service.UserProfileServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserProfileContoller.class)
public class UserProfileControllerUnitTest {

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private final String USER_REQUEST = "{\"fistName\" : \"John\", \"lastName\" : \"Smith\"}";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserProfileServiceImpl userProfileService; // = new UserProfileServiceImpl();
	
	@InjectMocks
	private UserProfileContoller userProfileController;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateUserProfile() throws Exception{
		User aMockedUser = new User();
		aMockedUser.setFirstName("John");
		aMockedUser.setLastName("Smith");
		aMockedUser.setId(100);
		
		Mockito.when(userProfileService.createUserProfile(Mockito.any(User.class)))
			.thenReturn(aMockedUser);
		
//		User uiUser = new User();
//		uiUser.setFirstName("John");
//		uiUser.setLastName("Smith");
		
		//mockMvc.perform(post("/api/v1/userprofile/user", uiUser))
		mockMvc.perform(post("/api/v1/userprofile/user")
				.content(USER_REQUEST)
				.contentType(APPLICATION_JSON_UTF8))
				.andExpect(status().is(200));
	}
	
	@Test
	public void testCreateUserProfileNotFound() throws Exception{
		User aMockedUser = new User();
		aMockedUser.setFirstName("John");
		aMockedUser.setLastName("Smith");
		aMockedUser.setId(100);
		
		Mockito.when(userProfileService.createUserProfile(Mockito.any(User.class)))
			.thenReturn(aMockedUser);
		
		
		//mockMvc.perform(post("/api/v1/userprofile/user", uiUser))
		mockMvc.perform(post("/api/v1/userprofile/users")
				.content(USER_REQUEST)
				.contentType(APPLICATION_JSON_UTF8))
				//.andExpect(status().is(200));
				.andExpect(status().is(404));
	}
	
}
