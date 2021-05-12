package com.hdfs.visa.api.service;

import com.hdfs.visa.api.dao.UserDAO;
import com.hdfs.visa.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserDAO userDAO;

  @Autowired
  public UserServiceImpl(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public User getUser(String userName) {
    return userDAO.getUser(userName);
  }
}
