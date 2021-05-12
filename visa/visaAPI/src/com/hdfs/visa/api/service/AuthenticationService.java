package com.hdfs.visa.api.service;

import com.hdfs.visa.security.HDFSUserDetails;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
  String PEGA_USER_NAME_DEV = "hd-pega-dev";
  String PEGA_USER_NAME_PROD = "hd-pega-prod";

  Authentication getAuthentication();
  HDFSUserDetails getUserDetails();
}
