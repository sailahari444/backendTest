package com.myWebApplication.GlowStudio.services.auth;

import com.myWebApplication.GlowStudio.dto.SignupRqst;
import com.myWebApplication.GlowStudio.dto.Userdto;

public interface AuthService {

	Userdto createUser(SignupRqst signupRqst);
}
