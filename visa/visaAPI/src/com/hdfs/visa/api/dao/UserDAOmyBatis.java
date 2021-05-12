package com.hdfs.visa.api.dao;

import com.hdfs.visa.api.model.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAOmyBatis extends SqlSessionDaoSupport implements UserDAO {

  @Override
  public User getUser(String userName) {
    return getSqlSessionTemplate().selectOne("com.hdfs.visa.api.mapper.UserMapper.getUserByUserName", userName);
  }

  @Autowired
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    super.setSqlSessionFactory(sqlSessionFactory);
  }
}
