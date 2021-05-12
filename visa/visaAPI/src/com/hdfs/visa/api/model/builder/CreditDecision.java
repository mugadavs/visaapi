package com.hdfs.visa.api.model.builder;

public class CreditDecision {
  private String creditTier;
  private int pricingTier;
  private double wholesaleRate;
  private double minDownPaymentPercent;
  private double maxApprovedAmount;
  private double maxLTV;
  private int maxTerm;
  private String ftb;
  private String isBaseline;

  public String getIsBaseline() {
    return isBaseline;
  }

  public void setIsBaseline(String isBaseline) {
    this.isBaseline = isBaseline;
  }

  public String getCreditTier() {
    return creditTier;
  }

  public void setCreditTier(String creditTier) {
    this.creditTier = creditTier;
  }

  public int getPricingTier() {
    return pricingTier;
  }

  public void setPricingTier(int pricingTier) {
    this.pricingTier = pricingTier;
  }

  public double getWholesaleRate() {
    return wholesaleRate;
  }

  public void setWholesaleRate(double wholesaleRate) {
    this.wholesaleRate = wholesaleRate;
  }

  public double getMinDownPaymentPercent() {
    return minDownPaymentPercent;
  }

  public void setMinDownPaymentPercent(double minDownPaymentPercent) {
    this.minDownPaymentPercent = minDownPaymentPercent;
  }

  public double getMaxApprovedAmount() {
    return maxApprovedAmount;
  }

  public void setMaxApprovedAmount(double maxApprovedAmount) {
    this.maxApprovedAmount = maxApprovedAmount;
  }

  public int getMaxTerm() {
    return maxTerm;
  }

  public void setMaxTerm(int maxTerm) {
    this.maxTerm = maxTerm;
  }

  public double getMaxLTV() {
    return maxLTV;
  }

  public void setMaxLTV(double maxLTV) {
    this.maxLTV = maxLTV;
  }

  public String getFtb() {
    return ftb;
  }

  public void setFtb(String ftb) {
    this.ftb = ftb;
  }
}
