package com.hdfs.visa.api.model;

import java.util.Date;

public class Agreement {
  private int agreementId;
  private Date updateDate;

  public int getAgreementId() {
    return agreementId;
  }

  public void setAgreementId(int agreementId) {
    this.agreementId = agreementId;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
}
