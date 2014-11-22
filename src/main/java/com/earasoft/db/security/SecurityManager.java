package com.earasoft.db.security;

public interface SecurityManager {
	public AuthenticationResultsE authenticateUser(String username, String password);
}


//http://www.sitefinity.com/documentation/documentationarticles/developers-guide/deep-dive/security/users/managing-users/querying-users