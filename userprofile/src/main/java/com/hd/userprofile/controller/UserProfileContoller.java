package com.hd.userprofile.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hd.userprofile.dto.User;
import com.hd.userprofile.exception.UserMessage;
import com.hd.userprofile.service.UserProfileService;


@RestController
@RequestMapping("/api/v1/userprofile")
public class UserProfileContoller {

	@Autowired
	UserProfileService userProfileService;
	
	public static final Logger logger = LoggerFactory.getLogger(UserProfileContoller.class);
	
	@RequestMapping(value="/user", method=RequestMethod.GET, 
			consumes="application/json", produces="application/json")
	public ResponseEntity<?> getAllUserProfiles(){
		logger.info("Get all profiles");
		List<User> profileList = userProfileService.findAllUserProfiles();
		if(profileList == null || profileList.isEmpty()){
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity(profileList, HttpStatus.OK);
	}
	

	@RequestMapping(value="/user/{id}", method=RequestMethod.GET,
			consumes="application/json", produces="application/json")
	public ResponseEntity<?> getUserProfile(@PathVariable("id") long userId){
		logger.info("Get profile by id");
		User user = userProfileService.findUserProfile(userId);
		if(user == null){
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST,
			consumes="application/json", produces="application/json")
	public ResponseEntity<?> createUserProfile(@RequestBody User user){
		logger.info("Create a profile");
		User createdUser = userProfileService.createUserProfile(user);
		return new ResponseEntity(createdUser, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT,
			consumes="application/json", produces="application/json")
	public ResponseEntity<?> updateUserProfile(@PathVariable("id") long id , @RequestBody User user){
		logger.info("Update an existing profile");
		User existingUser = userProfileService.findUserProfile(id);
		if(existingUser == null){
			UserMessage message = new UserMessage("Unable to find existing profile with user id: " + id);
			
			return new ResponseEntity(message, HttpStatus.NOT_FOUND);
		}
		userProfileService.updateUserProfile(id, user);
		return new ResponseEntity(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE,
			consumes="application/json", produces="application/json")
	public ResponseEntity<?> deleteUserProfile(@PathVariable("id") long id){
		logger.info("Delete a profile by id");
		User existingUser = userProfileService.findUserProfile(id);
		if(existingUser == null){
			UserMessage message = new UserMessage("Unable to find existing profile with user id: " + id);
			return new ResponseEntity(message, HttpStatus.NOT_FOUND);
		}
		userProfileService.deleteUserProfile(id);
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/user", method=RequestMethod.DELETE,
			consumes="application/json", produces="application/json")
	public ResponseEntity<?> deleteAllUserProfiles(){
		logger.info("Delete all profiles");
		userProfileService.deleteAllUserProfiles();
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}