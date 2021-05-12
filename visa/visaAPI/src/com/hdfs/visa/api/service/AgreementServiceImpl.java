package com.hdfs.visa.api.service;


import com.hdfs.visa.api.dao.AgreementDAO;
import com.hdfs.visa.api.model.*;
import com.hdfs.visa.api.model.builder.AgreementBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AgreementServiceImpl implements AgreementService {

  private static Set<String> excludedStatuses;
  static {
    excludedStatuses = new HashSet<>();
    excludedStatuses.add("AWRES");
    excludedStatuses.add("CNCL");
    excludedStatuses.add("CONT");
    excludedStatuses.add("CRDEC");
    excludedStatuses.add("CRREV");
    excludedStatuses.add("CRERR");
    excludedStatuses.add("CRFSO");
    excludedStatuses.add("INCAPP");
    excludedStatuses.add("INFOREQ");
    excludedStatuses.add("WITHDRW");
    excludedStatuses.add("NEW");
  }

  private final AgreementDAO agreementDAO;

  @Autowired
  public AgreementServiceImpl(AgreementDAO agreementDAO) {
    this.agreementDAO = agreementDAO;
  }

  @Override
  public AgreementVisaPrefillData getAgreementVisaPrefill(String cellPhoneNum, String correlationId) {

    AgreementVisaPrefillData agreementVisaPrefill = new AgreementVisaPrefillData();
    agreementVisaPrefill.setVersionNumber("1.0");
    agreementVisaPrefill.setCorrelationId(correlationId);
    List<Agreement> agreements = agreementDAO.getAgreementsByCellPhone(cellPhoneNum);
    if (agreements.size() > 0)
    {
      AgreementBuilder eligibleAgreementBuilder = null;
      Agreement eligibleAgreement = null;
      boolean multipleEligibleAgreements = false;
      for (Agreement agreement : agreements)
      {
        AgreementBuilder agreementBuilder = agreementDAO.getAgreementBuilder(agreement.getAgreementId());
        if (!StringUtils.equalsIgnoreCase("HDUSMST", agreementBuilder.getBusUnit()) || !StringUtils.equalsIgnoreCase("LOAN", agreementBuilder.getTypeCode()) || excludedStatuses.contains(agreementBuilder.getAppStatus()) || !StringUtils.equalsIgnoreCase(agreementBuilder.getPrimApp().getCellPhoneNumber(), cellPhoneNum))
        {
          continue;
        }

        if (eligibleAgreementBuilder == null)
        {
          eligibleAgreementBuilder = agreementBuilder;
          eligibleAgreement = agreement;
        }
        else
        {
          multipleEligibleAgreements = true;
          break;
        }
      }

      if (eligibleAgreement != null && eligibleAgreementBuilder != null)
        {
          Prefill prefill = new Prefill();
          if (showApplicantData(eligibleAgreement))
          {
            ApplicationData applicationData = new ApplicationData();
            applicationData.setPrimaryApplicant(eligibleAgreementBuilder.toApplicant(true));
            applicationData.setPartnerUserDefinedField1(String.valueOf(eligibleAgreementBuilder.getAgreementId()));
            applicationData.setRecourseEligibilityFlag(agreementDAO.getIsVisaEligible(eligibleAgreementBuilder.getAgreementId()));
            prefill.setApplicationData(applicationData);
          }
          else
          {
            ApplicationData applicationData = new ApplicationData();
            applicationData.setPartnerUserDefinedField1(String.valueOf(eligibleAgreementBuilder.getAgreementId()));
            applicationData.setRecourseEligibilityFlag(agreementDAO.getIsVisaEligible(eligibleAgreementBuilder.getAgreementId()));
            prefill.setApplicationData(applicationData);
          }

          Partner partner = new Partner();
          if (!multipleEligibleAgreements)
          {
            partner.setPartnerLocationID(eligibleAgreementBuilder.getDealerId());
          }
          prefill.setPartnerData(partner);
          agreementVisaPrefill.setPrefill(prefill);
          agreementDAO.setVisaProcessComplete(eligibleAgreementBuilder.getAgreementId());
      }
    }

    return agreementVisaPrefill;
  }

  private boolean showApplicantData(Agreement agreement)
  {
    LocalDate updateDate = agreement.getUpdateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate now = LocalDate.now();

    long applicantDataDayLimit = Long.parseLong(agreementDAO.getConfigValue("string.visaprefill.fulldate.limit"));
    long daysBetween = ChronoUnit.DAYS.between(updateDate, now);

    return (daysBetween < applicantDataDayLimit);
  }
}
