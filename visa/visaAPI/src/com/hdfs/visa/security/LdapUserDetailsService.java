package com.hdfs.visa.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LdapUserDetailsService implements UserDetailsService {
  private final LdapTemplate ldapTemplate;

  public LdapUserDetailsService(LdapTemplate ldapTemplate) {
    this.ldapTemplate = ldapTemplate;
  }

  @Override
  public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    List<String> attributes = search(s);
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String attribute : attributes)
    {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(StringUtils.split(attribute, ",")[0].trim());
      authorities.add(authority);
    }

    HDFSUserDetails user = new HDFSUserDetails();
    user.setUserName(s);
    user.setAuthorities(authorities);
    return user;
  }

  private List<String> search(String userName){
     List<List<?>> attributes = getLdapTemplate()
        .search(
            "OU=Users,OU=Client Management",
            "mailNickname=" + userName,
            (AttributesMapper<List<?>>) attrs -> Collections.list(attrs.get("memberOf").getAll())
        );

     if (attributes != null && !attributes.isEmpty())
     {
       return (List<String>)attributes.get(0);
     }
     return Collections.emptyList();
  }

  private LdapTemplate getLdapTemplate() {
    return ldapTemplate;
  }
}
