package com.hdfs.visa.api.model;

public class ApplicationData {
  private Applicant primaryApplicant;
  private String partnerUserDefinedField1;
  private String recourseEligibilityFlag;

  public String getPartnerUserDefinedField1() {
    return partnerUserDefinedField1;
  }

  public void setPartnerUserDefinedField1(String partnerUserDefinedField1) {
    this.partnerUserDefinedField1 = partnerUserDefinedField1;
  }

  public Applicant getPrimaryApplicant() {
    return primaryApplicant;
  }

  public void setPrimaryApplicant(Applicant primaryApplicant) {
    this.primaryApplicant = primaryApplicant;
  }

  public String getRecourseEligibilityFlag() {
    return recourseEligibilityFlag;
  }

  public void setRecourseEligibilityFlag(String recourseEligibilityFlag) {
    this.recourseEligibilityFlag = recourseEligibilityFlag;
  }
}
