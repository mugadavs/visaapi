package com.hdfs.visa.api.dao;

import com.hdfs.visa.api.model.builder.AgreementBuilder;
import com.hdfs.visa.api.model.Agreement;

import java.util.List;

public interface AgreementDAO {

  List<Agreement> getAgreementsByCellPhone(String cellPhone);
  AgreementBuilder getAgreementBuilder(long agreementId);
  String getIsVisaEligible(long agreementId);
  void setVisaProcessComplete(long agreementId);
  String getConfigValue(String configName);
}
