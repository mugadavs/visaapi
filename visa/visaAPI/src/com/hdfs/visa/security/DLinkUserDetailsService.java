package com.hdfs.visa.security;

import com.hdfs.visa.api.dao.UserDAO;
import com.hdfs.visa.api.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class DLinkUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  private final UserDAO userDAO;

  public DLinkUserDetailsService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    User user = userDAO.getUser(s);

    List<GrantedAuthority> authorities = new ArrayList<>();
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("READ/WRITE");

    if (user == null)
    {
      throw new UsernameNotFoundException("User not found in DealLink Users: " + s);
    }
    authorities.add(authority);
    HDFSUserDetails userDetails = new HDFSUserDetails();
    userDetails.setUserName(s);
    userDetails.setAuthorities(authorities);
    userDetails.setId(user.getId());
    userDetails.setDlinkUser(user);
    return userDetails;
  }
}
