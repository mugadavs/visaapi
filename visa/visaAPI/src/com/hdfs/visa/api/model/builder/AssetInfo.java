package com.hdfs.visa.api.model.builder;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.HashMap;
import java.util.Map;

public class AssetInfo {
  private static final Map<String, String> equipmentCodeMap = new HashMap<>();
  static {
    equipmentCodeMap.put("SPBK", "ALL OTHER MOTORCYCLES");
    equipmentCodeMap.put("EMOR", "ENGINE, MOTORCYCLE LONGBLOCK");
    equipmentCodeMap.put("EMCN", "ENGINE, MOTORCYCLE NEW");
    equipmentCodeMap.put("MCYC", "MOTORCYCLE");
    equipmentCodeMap.put("SDCR", "SIDECAR");
    equipmentCodeMap.put("TRLR", "TRAILER");
    equipmentCodeMap.put("TRKT", "TRIKE KIT");
  }

  private String make;
  private String newUsedInd;
  private int modelYear;
  private String model;
  private double value;
  private double sellingPrice;
  private String vin;
  private String type;

  public double getSellingPrice() {
    return sellingPrice;
  }

  public void setSellingPrice(double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getNewUsedInd() {
    return newUsedInd;
  }

  public void setNewUsedInd(String newUsedInd) {
    this.newUsedInd = newUsedInd;
  }

  public int getModelYear() {
    return modelYear;
  }

  public void setModelYear(int modelYear) {
    this.modelYear = modelYear;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @JsonGetter ("type")
  public String getMappedType()
  {
    return equipmentCodeMap.get(type);
  }
}
