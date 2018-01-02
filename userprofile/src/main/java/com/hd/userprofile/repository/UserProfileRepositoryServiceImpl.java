package com.hd.userprofile.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.hd.userprofile.dto.User;

@Repository
public class UserProfileRepositoryServiceImpl implements UserProfileRepositoryService {

	private static List<User> userProfileList = new ArrayList<>();
	
	private static final AtomicLong counter = new AtomicLong(100);
	
	static {
		User user = new User();
		user.setFirstName("John");
		user.setLastName("Smith");
		user.setEmailId("john.smith@mailme.com");
		user.setId(counter.get());
		userProfileList.add(user);
		
		user = new User();
		user.setFirstName("Jack");
		user.setLastName("Smith");
		user.setEmailId("jack.smith@mailme.com");
		user.setId(counter.incrementAndGet());
		userProfileList.add(user);	
	}
	@Override
	public List<User> findAll() {
		return userProfileList;
	}

	@Override
	public User findByProfileId(long id) {
		for(User user : userProfileList){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	@Override
	public User saveProfile(User user) {
		long id = counter.incrementAndGet();
		user.setId(id);
		userProfileList.add(user);
		return user;
	}

	@Override
	public User updateProfile(User user) {
		int listIndex = 0;
		for(User aUser: userProfileList){
			if(aUser.getId() == user.getId()){
				userProfileList.set(listIndex, user);
				break;
			}
			listIndex++;
		}
		
		return user;
	}

	@Override
	public User deleteProfile(long id) {
		int index = 0;
		for( User user: userProfileList){
			if(id == user.getId()){
				userProfileList.remove(index);
				break;
			}
			index++;
		}
		return null;
	}

	@Override
	public void deleteAllProfiles() {
		userProfileList.clear();
	}

}
