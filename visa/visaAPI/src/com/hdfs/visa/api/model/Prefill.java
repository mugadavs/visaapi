package com.hdfs.visa.api.model;

public class Prefill {
  private Partner partnerData;
  private ApplicationData applicationData;

  public Partner getPartnerData() {
    return partnerData;
  }

  public void setPartnerData(Partner partnerData) {
    this.partnerData = partnerData;
  }

  public ApplicationData getApplicationData() {
    return applicationData;
  }

  public void setApplicationData(ApplicationData applicationData) {
    this.applicationData = applicationData;
  }
}
