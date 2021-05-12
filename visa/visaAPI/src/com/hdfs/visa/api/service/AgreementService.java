package com.hdfs.visa.api.service;

import com.hdfs.visa.api.model.AgreementVisaPrefillData;

public interface AgreementService {
  AgreementVisaPrefillData getAgreementVisaPrefill(String cellPhoneNum, String correlationId);
}
