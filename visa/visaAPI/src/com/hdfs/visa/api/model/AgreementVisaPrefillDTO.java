package com.hdfs.visa.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AgreementVisaPrefillDTO {

  @NotNull (message = "Phone invalid")
  @NotBlank (message = "Phone invalid")
  private String mobilePhone;

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }
}
