package com.hdfs.visa.config;

import com.hdfs.visa.api.dao.UserDAO;
import com.hdfs.visa.security.DLinkUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter  {

  @Autowired
  private UserDAO userDAO;

  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
    auth.authenticationProvider(preAuthenticatedAuthenticationProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilter(requestHeaderAuthenticationFilter())
        .authenticationProvider(preAuthenticatedAuthenticationProvider())
        .csrf().disable()
        .authorizeRequests().antMatchers("/**").hasAuthority("READ/WRITE")
        .anyRequest().authenticated();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  protected RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() throws Exception {
    RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
    filter.setPrincipalRequestHeader("iv-user");
    filter.setAuthenticationManager(authenticationManager());
    filter.setExceptionIfHeaderMissing(false);
    return filter;
  }

  @Bean
  public UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper() {
    UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper =
        new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>();
    wrapper.setUserDetailsService(userDetailsService());
    return wrapper;
  }

  @Bean
  protected PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
    PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
    authenticationProvider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());

    return authenticationProvider;
  }

//  @Bean
//  protected UserDetailsService userDetailsService() {
//    return new LdapUserDetailsService(ldapTemplate());
//  }

  @Bean
  protected UserDetailsService userDetailsService() {
    return new DLinkUserDetailsService(userDAO);
  }

//  @Bean
//  public LdapContextSource contextSource() {
//    LdapContextSource contextSource = new LdapContextSource();
//
////    contextSource.setUrl("ldap://10.240.24.122:389");
////    contextSource.setBase(
////        "DC=hdmc,DC=harley-davidson,DC=com");
////    contextSource.setUserDn(
////        "hdmc\\efficientst");
////    contextSource.setPassword(
////        "stnx!23");
//
////    contextSource.setUrl(env.getProperty("ldap.url"));
////    contextSource.setBase(
////        env.getProperty("ldap.base"));
////    contextSource.setUserDn(
////        env.getProperty("ldap.userName"));
////    contextSource.setPassword(
////        env.getProperty("ldap.password"));
//
//    return contextSource;
//  }

//  @Bean
//  public LdapTemplate ldapTemplate() {
//    return new LdapTemplate(contextSource());
//  }

}
