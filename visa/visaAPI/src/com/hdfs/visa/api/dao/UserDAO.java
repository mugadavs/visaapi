package com.hdfs.visa.api.dao;

import com.hdfs.visa.api.model.User;

public interface UserDAO {
  User getUser(String userName);
}
