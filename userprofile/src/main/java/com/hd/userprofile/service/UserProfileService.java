package com.hd.userprofile.service;

import java.util.List;

import com.hd.userprofile.dto.User;

public interface UserProfileService {

	public List<User> findAllUserProfiles();
	
	public User findUserProfile(long id);
	
	public User createUserProfile(User user);
	
	public User updateUserProfile(long id, User user);
	
	public void deleteUserProfile(long id);
	
	public void deleteAllUserProfiles();
	
}
