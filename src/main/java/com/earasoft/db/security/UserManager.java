package com.earasoft.db.security;

import java.util.List;

public interface UserManager {
	public void addUser(User user);
	public void deleteUser(User user);
	public List<User> getUsers();
	public User getUserById(Integer userId);
}
