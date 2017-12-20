package com.hd.userprofile.repository;

import java.util.List;

import com.hd.userprofile.dto.User;

public interface UserProfileRepositoryService {
	
	public List<User> findAll();
	
	public User findByProfileId(long id);
	
	public User saveProfile(User user);

	public User updateProfile(User user);
	
	public User deleteProfile(long id);
	
	public void deleteAllProfiles();
	
}
