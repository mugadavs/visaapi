package com.hdfs.visa.api.model.builder;

import org.apache.commons.lang3.StringUtils;

public class ApplicantAddress {
  private String streetNumber;
  private String streetName;
  private String streetType;
  private String suiteNumber;
  private String city;
  private String state;
  private String zipCode;
  private String country;

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getStreetType() {
    return streetType;
  }

  public void setStreetType(String streetType) {
    this.streetType = streetType;
  }

  public String getSuiteNumber() {
    return suiteNumber;
  }

  public void setSuiteNumber(String suiteNumber) {
    this.suiteNumber = suiteNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ApplicantAddress)
    {
      return (StringUtils.equals(streetNumber, ((ApplicantAddress) obj).streetNumber)
        && StringUtils.equals(streetName, ((ApplicantAddress) obj).streetName)
        && StringUtils.equals(streetType, ((ApplicantAddress) obj).streetType)
        && StringUtils.equals(suiteNumber, ((ApplicantAddress) obj).suiteNumber)
        && StringUtils.equals(city, ((ApplicantAddress) obj).city)
        && StringUtils.equals(state, ((ApplicantAddress) obj).state)
        && StringUtils.equals(zipCode, ((ApplicantAddress) obj).zipCode)
        && StringUtils.equals(country, ((ApplicantAddress) obj).country)
        );
    }

    return false;
  }
}
