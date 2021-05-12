package com.hdfs.visa.api.model;

import com.hdfs.visa.api.model.builder.AgreementBuilder;

public class AgreementVisaPrefillData {
  private String versionNumber;
  private String correlationId;
  private Prefill prefill;

  public String getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(String versionNumber) {
    this.versionNumber = versionNumber;
  }

  public String getCorrelationId() {
    return correlationId;
  }

  public void setCorrelationId(String correlationId) {
    this.correlationId = correlationId;
  }

  public Prefill getPrefill() {
    return prefill;
  }

  public void setPrefill(Prefill prefill) {
    this.prefill = prefill;
  }
}
