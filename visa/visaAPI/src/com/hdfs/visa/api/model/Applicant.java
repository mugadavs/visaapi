package com.hdfs.visa.api.model;

public class Applicant {

  private ApplicantName name;
  private Address address;
  private Address mailingAddress;
  private String primaryPhone;
  private String emailAddress;
  private Employment employmentInformation;
  private Housing housingInformation;
  private FinancialInfo financialInformation;

  public ApplicantName getName() {
    return name;
  }

  public void setName(ApplicantName name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Address getMailingAddress() {
    return mailingAddress;
  }

  public void setMailingAddress(Address mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  public String getPrimaryPhone() {
    return primaryPhone;
  }

  public void setPrimaryPhone(String primaryPhone) {
    this.primaryPhone = primaryPhone;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public Employment getEmploymentInformation() {
    return employmentInformation;
  }

  public void setEmploymentInformation(Employment employmentInformation) {
    this.employmentInformation = employmentInformation;
  }

  public Housing getHousingInformation() {
    return housingInformation;
  }

  public void setHousingInformation(Housing housingInformation) {
    this.housingInformation = housingInformation;
  }

  public FinancialInfo getFinancialInformation() {
    return financialInformation;
  }

  public void setFinancialInformation(FinancialInfo financialInformation) {
    this.financialInformation = financialInformation;
  }
}
