package com.hdfs.visa.api.model.builder;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Applicant {
  private long customerId;
  private String firstName;
  private String middleName;
  private String lastName;
  private String suffixName;
  private String ssn;
  private Date dob;
  private String driversLicense;
  private boolean isDealerEmployee;
  private boolean isDealerPrincipal;
  private String homePhoneNumber;
  private String cellPhoneNumber;
  private String emailAddress;
  private double grossIncome;
  private int grossIncomeFrequency;
  private double otherIncome;
  private int otherIncomeFrequency;
  private boolean isMLA;
  private ApplicantAddress physicalAddress;
  private ApplicantAddress mailingAddress;
  private Employer employer;
  private Residence residence;

  public Applicant() {
  }

  public String getSuffixName() {
    return suffixName;
  }

  public void setSuffixName(String suffixName) {
    this.suffixName = suffixName;
  }

  public int getGrossIncomeFrequency() {
    return grossIncomeFrequency;
  }

  public void setGrossIncomeFrequency(int grossIncomeFrequency) {
    this.grossIncomeFrequency = grossIncomeFrequency;
  }

  public int getOtherIncomeFrequency() {
    return otherIncomeFrequency;
  }

  public void setOtherIncomeFrequency(int otherIncomeFrequency) {
    this.otherIncomeFrequency = otherIncomeFrequency;
  }

  public String getSsn() {
    return ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  @JsonGetter("dob")
  public String getFormattedDob()
  {
    if (this.dob == null)
    {
      return null;
    }
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(this.dob);
  }

  public String getDriversLicense() {
    return driversLicense;
  }

  public void setDriversLicense(String driversLicense) {
    this.driversLicense = driversLicense;
  }

  public boolean isDealerEmployee() {
    return isDealerEmployee;
  }

  public void setDealerEmployee(boolean dealerEmployee) {
    isDealerEmployee = dealerEmployee;
  }

  public boolean isDealerPrincipal() {
    return isDealerPrincipal;
  }

  public void setDealerPrincipal(boolean dealerPrincipal) {
    isDealerPrincipal = dealerPrincipal;
  }

  public String getHomePhoneNumber() {
    return homePhoneNumber;
  }

  public void setHomePhoneNumber(String homePhoneNumber) {
    this.homePhoneNumber = homePhoneNumber;
  }

  public String getCellPhoneNumber() {
    return cellPhoneNumber;
  }

  public void setCellPhoneNumber(String cellPhoneNumber) {
    this.cellPhoneNumber = cellPhoneNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public double getGrossIncome() {
    return grossIncome;
  }

  public void setGrossIncome(double grossIncome) {
    this.grossIncome = grossIncome;
  }

  public double getOtherIncome() {
    return otherIncome;
  }

  public void setOtherIncome(double otherIncome) {
    this.otherIncome = otherIncome;
  }

  public boolean isMLA() {
    return isMLA;
  }

  public void setMLA(boolean MLA) {
    isMLA = MLA;
  }

  public ApplicantAddress getPhysicalAddress() {
    return physicalAddress;
  }

  public void setPhysicalAddress(ApplicantAddress physicalAddress) {
    this.physicalAddress = physicalAddress;
  }

  public ApplicantAddress getMailingAddress() {
    return mailingAddress;
  }

  public void setMailingAddress(ApplicantAddress mailingAddress) {
    this.mailingAddress = mailingAddress;
  }

  public long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(long customerId) {
    this.customerId = customerId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Residence getResidence() {
    return residence;
  }

  public void setResidence(Residence residence) {
    this.residence = residence;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }
}
