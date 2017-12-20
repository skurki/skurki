package com.hd.userprofile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.userprofile.dto.User;
import com.hd.userprofile.repository.UserProfileRepositoryService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileRepositoryService userProfileRepositoryService;
	
	@Override
	public List<User> findAllUserProfiles() {
		return userProfileRepositoryService.findAll();
	}

	@Override
	public User findUserProfile(long id) {
		return userProfileRepositoryService.findByProfileId(id);
	}

	@Override
	public User createUserProfile(User user) {
		return userProfileRepositoryService.saveProfile(user);
	}

	@Override
	public User updateUserProfile(long id, User user) {
		user.setId(id);
		return userProfileRepositoryService.updateProfile(user);
	}

	@Override
	public void deleteUserProfile(long id) {
		 userProfileRepositoryService.deleteProfile(id);
	}

	@Override
	public void deleteAllUserProfiles() {
		userProfileRepositoryService.deleteAllProfiles();
	}

}
