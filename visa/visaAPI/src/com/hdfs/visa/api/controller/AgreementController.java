package com.hdfs.visa.api.controller;

import com.hdfs.visa.api.model.AgreementVisaPrefillDTO;
import com.hdfs.visa.api.model.AgreementVisaPrefillData;
import com.hdfs.visa.api.model.VisaPrefill;
import com.hdfs.visa.api.service.AgreementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/v1/visaApplication")
@Validated
public class AgreementController
{
  private static final Logger logger = LogManager.getLogger(AgreementController.class);
  private final AgreementService agreementService;

  @Autowired
  public AgreementController(AgreementService agreementService) {
    this.agreementService = agreementService;
  }

  @RequestMapping(method = POST, value = "/prefillData")
  @ResponseStatus(HttpStatus.OK)
  public AgreementVisaPrefillData getPrefillData(@Valid @RequestBody AgreementVisaPrefillDTO agreementVisaPrefillDTO, @RequestHeader("correlationID") String correlationId)
  {
    AgreementVisaPrefillData visaPrefillData = agreementService.getAgreementVisaPrefill(agreementVisaPrefillDTO.getMobilePhone(), correlationId);
    return visaPrefillData;
  }



}
