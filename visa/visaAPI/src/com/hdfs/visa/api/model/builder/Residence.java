package com.hdfs.visa.api.model.builder;

public class Residence {
  private String type;
  private double monthlyPayment;
  private TimeAtResidence timeAtResidence;

  public TimeAtResidence getTimeAtResidence() {
    return timeAtResidence;
  }

  public void setTimeAtResidence(TimeAtResidence timeAtResidence) {
    this.timeAtResidence = timeAtResidence;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getMonthlyPayment() {
    return monthlyPayment;
  }

  public void setMonthlyPayment(double monthlyPayment) {
    this.monthlyPayment = monthlyPayment;
  }
}
