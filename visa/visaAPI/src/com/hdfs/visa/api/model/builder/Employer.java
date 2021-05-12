package com.hdfs.visa.api.model.builder;

public class Employer {
  private String status;
  private String name;
  private String jobTitle;
  private EmploymentDuration duration ;
  private String businessPhone;
  private EmploymentAddress address;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public EmploymentDuration getDuration() {
    return duration;
  }

  public void setDuration(EmploymentDuration duration) {
    this.duration = duration;
  }

  public String getBusinessPhone() {
    return businessPhone;
  }

  public void setBusinessPhone(String businessPhone) {
    this.businessPhone = businessPhone;
  }

  public EmploymentAddress getAddress() {
    return address;
  }

  public void setAddress(EmploymentAddress address) {
    this.address = address;
  }
}
