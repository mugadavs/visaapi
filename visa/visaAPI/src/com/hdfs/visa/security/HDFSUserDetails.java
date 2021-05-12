package com.hdfs.visa.security;

import com.hdfs.visa.api.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class HDFSUserDetails implements org.springframework.security.core.userdetails.UserDetails {
  private Collection<? extends GrantedAuthority> authorities;
  private int id;
  private String userName;
  private User dlinkUser;

  public String getUserName() {
    return userName;
  }

  public User getDlinkUser() {
    return dlinkUser;
  }

  public void setDlinkUser(User dlinkUser) {
    this.dlinkUser = dlinkUser;
  }

  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
