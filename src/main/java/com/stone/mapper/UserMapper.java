package com.stone.mapper;

import java.util.Map;

import com.stone.model.User;

public interface UserMapper {
	public User selectUserByUsername(String username);
	public Integer insertUser(User user);
	public User selectUserByUsernameOrEmail(Map<String, String> parameters);
	public User selectUserByEmail(String email);
	public void update(User user);
}
