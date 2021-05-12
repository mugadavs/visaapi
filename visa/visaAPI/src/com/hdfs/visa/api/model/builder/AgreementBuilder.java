package com.hdfs.visa.api.model.builder;

import com.hdfs.visa.api.model.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AgreementBuilder {

  private static Map<String, String> nameSuffixMap;
  static {
    nameSuffixMap = new HashMap<>();
    nameSuffixMap.put("II", "2");
    nameSuffixMap.put("III", "3");
    nameSuffixMap.put("IV", "4");
    nameSuffixMap.put("V", "5");
    nameSuffixMap.put("JR", "jr");
    nameSuffixMap.put("SR", "sr");
  }

  private long requestId;
  private long agreementId;
  private String applicationType;
  private String appStatus;
  private String dealerId;
  private String dealerName;
  private String originationSource;
  private String applicationSource;
  private String typeCode;
  private String busUnit;
  private double monthlyIncome;
  private long retainedBy;
  private Date createdDate;

  private CreditDecision bcd;
  private CreditDecision ccd;
  private int numMotorcycles;
  private AssetInfo assetInfo;
  private Applicant primApp;
  private Applicant secApp;

  private String primDealerPrincipal;
  private String primDealerEmployee;

  private String secDealerPrincipal;
  private String secDealerEmployee;

  public com.hdfs.visa.api.model.Applicant toApplicant(boolean isPrimary)
  {
    com.hdfs.visa.api.model.Applicant applicant = new com.hdfs.visa.api.model.Applicant();
    Applicant builderApplicant = primApp;
    if (!isPrimary)
    {
      builderApplicant = secApp;
    }
    ApplicantName applicantName = new ApplicantName();
    applicantName.setFirst(builderApplicant.getFirstName());
    applicantName.setMiddle(builderApplicant.getMiddleName());
    applicantName.setLast(builderApplicant.getLastName());
    applicantName.setSuffix(getMappedSuffixValues(builderApplicant.getSuffixName()));
    stripInvalidCharacters(applicantName);

    applicant.setName(applicantName);

    Address address = new Address();
    StringBuilder streetAddress = new StringBuilder();
    if (builderApplicant.getPhysicalAddress().getStreetNumber() != null)
    {
      streetAddress.append(builderApplicant.getPhysicalAddress().getStreetNumber());
      streetAddress.append(" ");
    }
    if (builderApplicant.getPhysicalAddress().getStreetName() != null)
    {
      streetAddress.append(builderApplicant.getPhysicalAddress().getStreetName());
      streetAddress.append(" ");
    }
    if (builderApplicant.getPhysicalAddress().getStreetType() != null)
    {
      streetAddress.append(builderApplicant.getPhysicalAddress().getStreetType());
    }

    address.setStreetAddress1(stripInvalidAddressCharacters(streetAddress.toString()));
    address.setStreetAddress1(StringUtils.truncate(address.getStreetAddress1(), 40));
    if (builderApplicant.getPhysicalAddress().getSuiteNumber() != null)
    {
      address.setStreetAddress2(StringUtils.truncate(stripInvalidSuiteCharacters(builderApplicant.getPhysicalAddress().getSuiteNumber()), 5));
    }

    address.setCity(StringUtils.truncate(stripInvalidCityCharacters(builderApplicant.getPhysicalAddress().getCity()), 20));
    address.setState(builderApplicant.getPhysicalAddress().getState());
    address.setZipCode(builderApplicant.getPhysicalAddress().getZipCode());

    applicant.setAddress(address);

    if (!builderApplicant.getPhysicalAddress().equals(builderApplicant.getMailingAddress()))
    {
      Address mailingAddress = new Address();
      streetAddress = new StringBuilder();
      if (builderApplicant.getMailingAddress().getStreetNumber() != null)
      {
        streetAddress.append(builderApplicant.getMailingAddress().getStreetNumber());
        streetAddress.append(" ");
      }
      if (builderApplicant.getMailingAddress().getStreetName() != null)
      {
        streetAddress.append(builderApplicant.getMailingAddress().getStreetName());
        streetAddress.append(" ");
      }
      if (builderApplicant.getMailingAddress().getStreetType() != null)
      {
        streetAddress.append(builderApplicant.getMailingAddress().getStreetType());
      }

      mailingAddress.setStreetAddress1(stripInvalidAddressCharacters(streetAddress.toString()));
      mailingAddress.setStreetAddress1(StringUtils.truncate(mailingAddress.getStreetAddress1(), 40));
      if (builderApplicant.getMailingAddress().getSuiteNumber() != null)
      {
        mailingAddress.setStreetAddress2(StringUtils.truncate(stripInvalidSuiteCharacters(builderApplicant.getMailingAddress().getSuiteNumber()), 5));
      }

      mailingAddress.setCity(StringUtils.truncate(stripInvalidCityCharacters(builderApplicant.getMailingAddress().getCity()), 20));
      mailingAddress.setState(builderApplicant.getMailingAddress().getState());
      mailingAddress.setZipCode(builderApplicant.getMailingAddress().getZipCode());

      applicant.setMailingAddress(mailingAddress);
    }

    if (builderApplicant.getHomePhoneNumber() != null)
    {
      applicant.setPrimaryPhone(builderApplicant.getHomePhoneNumber());
    }
    else {
      applicant.setPrimaryPhone(builderApplicant.getCellPhoneNumber());
    }

    applicant.setEmailAddress(StringUtils.truncate(builderApplicant.getEmailAddress(), 40));

    Employment employment = new Employment();
    if (StringUtils.equalsIgnoreCase(builderApplicant.getEmployer().getStatus(), "R"))
    {
      employment.setStatus("RTRD");
    }
    else if (StringUtils.equalsIgnoreCase(builderApplicant.getEmployer().getStatus(), "E"))
    {
      employment.setStatus("FTEMP");
    }
    else if (StringUtils.equalsIgnoreCase(builderApplicant.getEmployer().getStatus(), "S"))
    {
      employment.setStatus("SELF");
    }
    else if (StringUtils.equalsIgnoreCase(builderApplicant.getEmployer().getStatus(), "U"))
    {
      employment.setStatus("UNEM");
    }

    applicant.setEmploymentInformation(employment);

    FinancialInfo financialInfo = new FinancialInfo();
    financialInfo.setAnnualIncome(StringUtils.truncate(String.valueOf((int)(builderApplicant.getGrossIncome() * builderApplicant.getGrossIncomeFrequency())), 10));

    applicant.setFinancialInformation(financialInfo);

    Housing housing = new Housing();
    if (builderApplicant.getResidence() != null)
    {
      housing.setMonthlyPayment(String.valueOf((int)builderApplicant.getResidence().getMonthlyPayment()));

      if (StringUtils.equalsIgnoreCase(builderApplicant.getResidence().getType(), "O"))
      {
        housing.setStatus("OWN");
      }
      else{
        housing.setStatus("OTHER");
      }
      applicant.setHousingInformation(housing);
    }


    return applicant;
  }

  private String getMappedSuffixValues(String suffix)
  {
    if (StringUtils.isNotBlank(suffix))
    {
      return nameSuffixMap.get(suffix.toUpperCase());
    }
    return null;
  }

  private void stripInvalidCharacters (ApplicantName applicantName)
  {
    if (applicantName.getFirst() != null)
    {
      applicantName.setFirst(applicantName.getFirst().replaceAll("[^a-zA-Z0-9, \\-']",""));
    }
    if (applicantName.getMiddle() != null)
    {
      applicantName.setMiddle(applicantName.getMiddle().replaceAll("[^a-zA-Z0-9, \\-']",""));
    }
    if (applicantName.getLast() != null)
    {
      applicantName.setLast(applicantName.getLast().replaceAll("[^a-zA-Z0-9, \\-']",""));
    }
  }

  private String stripInvalidAddressCharacters(String address)
  {
    if (address != null)
    {
      return address.replaceAll("[^a-zA-Z0-9 /\\-,.]","");
    }
    return null;
  }

  private String stripInvalidSuiteCharacters(String suite)
  {
    if (suite != null)
    {
      return suite.replaceAll("[^a-zA-Z0-9 #]","");
    }
    return null;
  }

  private String stripInvalidCityCharacters(String suite)
  {
    if (suite != null)
    {
      return suite.replaceAll("[^a-zA-Z0-9 ]","");
    }
    return null;
  }

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public String getPrimDealerPrincipal() {
    return primDealerPrincipal;
  }

  public void setPrimDealerPrincipal(String primDealerPrincipal) {
    this.primDealerPrincipal = primDealerPrincipal;
  }

  public String getPrimDealerEmployee() {
    return primDealerEmployee;
  }

  public void setPrimDealerEmployee(String primDealerEmployee) {
    this.primDealerEmployee = primDealerEmployee;
  }

  public String getSecDealerPrincipal() {
    return secDealerPrincipal;
  }

  public void setSecDealerPrincipal(String secDealerPrincipal) {
    this.secDealerPrincipal = secDealerPrincipal;
  }

  public String getSecDealerEmployee() {
    return secDealerEmployee;
  }

  public void setSecDealerEmployee(String secDealerEmployee) {
    this.secDealerEmployee = secDealerEmployee;
  }

  public String getOriginationSource() {
    return originationSource;
  }

  public void setOriginationSource(String originationSource) {
    this.originationSource = originationSource;
  }

  public String getApplicationSource() {
    return applicationSource;
  }

  public void setApplicationSource(String applicationSource) {
    this.applicationSource = applicationSource;
  }

  public long getRequestId() {
    return requestId;
  }

  public void setRequestId(long requestId) {
    this.requestId = requestId;
  }

  public long getAgreementId() {
    return agreementId;
  }

  public void setAgreementId(long agreementId) {
    this.agreementId = agreementId;
  }

  public String getApplicationType() {
    return applicationType;
  }

  public void setApplicationType(String applicationType) {
    this.applicationType = applicationType;
  }

  public String getAppStatus() {
    return appStatus;
  }

  public void setAppStatus(String appStatus) {
    this.appStatus = appStatus;
  }

  public String getDealerId() {
    return dealerId;
  }

  public void setDealerId(String dealerId) {
    this.dealerId = dealerId;
  }

  public String getDealerName() {
    return dealerName;
  }

  public void setDealerName(String dealerName) {
    this.dealerName = dealerName;
  }

  public String getBusUnit() {
    return busUnit;
  }

  public void setBusUnit(String busUnit) {
    this.busUnit = busUnit;
  }

  public double getMonthlyIncome() {
    return monthlyIncome;
  }

  public void setMonthlyIncome(double monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }

  public CreditDecision getBcd() {
    return bcd;
  }

  public void setBcd(CreditDecision bcd) {
    this.bcd = bcd;
  }

  public CreditDecision getCcd() {
    return ccd;
  }

  public void setCcd(CreditDecision ccd) {
    this.ccd = ccd;
  }

  public AssetInfo getAssetInfo() {
    return assetInfo;
  }

  public void setAssetInfo(AssetInfo assetInfo) {
    this.assetInfo = assetInfo;
  }

  public Applicant getPrimApp() {
    return primApp;
  }

  public void setPrimApp(Applicant primApp) {
    this.primApp = primApp;
  }

  public Applicant getSecApp() {
    return secApp;
  }

  public void setSecApp(Applicant secApp) {
    this.secApp = secApp;
  }

  public int getNumMotorcycles() {
    return numMotorcycles;
  }

  public void setNumMotorcycles(int numMotorcycles) {
    this.numMotorcycles = numMotorcycles;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}
