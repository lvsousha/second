package com.stone.mapper;

import com.stone.model.User;

public interface UserMapper {
	public User selectUserByUsername(String username);
	public Integer insertUser(User user);
}
