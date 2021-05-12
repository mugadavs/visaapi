package com.hdfs.visa.api.service;

import com.hdfs.visa.security.HDFSUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserService userService;

  @Autowired
  public AuthenticationServiceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @Override
  public HDFSUserDetails getUserDetails() {
    return (HDFSUserDetails)getAuthentication().getPrincipal();
  }


//  public User getUser() {
//    User user = userService.getUser(getAuthentication().getName());
//    if (user == null)
//    {
//      user = userService.getUser(PEGA_USER_NAME_DEV);
//      if (user == null)
//      {
//        user = userService.getUser(PEGA_USER_NAME_PROD);
//      }
//    }
//    return user;
//  }
}
