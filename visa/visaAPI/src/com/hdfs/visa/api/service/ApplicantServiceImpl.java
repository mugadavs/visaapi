package com.hdfs.visa.api.service;

import com.hdfs.visa.api.dao.ApplicantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicantServiceImpl implements ApplicantService{
  private final ApplicantDAO applicantDAO;

  @Autowired
  public ApplicantServiceImpl(ApplicantDAO applicantDAO) {
    this.applicantDAO = applicantDAO;
  }

}
