package com.hdfs.visa.api.dao;

import com.hdfs.visa.api.model.builder.AgreementBuilder;
import com.hdfs.visa.api.model.Agreement;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AgreementDAOmyBatis extends SqlSessionDaoSupport implements AgreementDAO{

  @Autowired
  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    super.setSqlSessionFactory(sqlSessionFactory);
  }

  @Override
  public List<Agreement> getAgreementsByCellPhone(String cellPhone) {
    int maxDays = Integer.parseInt(getConfigValue("string.visaprefill.date.limit"));

    Map<String, Object> map = new HashMap<>();
    map.put("cellPhoneNum", cellPhone);
    map.put("maxDays", maxDays);

    List<Agreement> agreements = getSqlSessionTemplate().selectList("com.hdfs.visa.api.mapper.AgreementMapper.getAgreementsByCellPhone", map);

    if (agreements != null)
    {
      return agreements;
    }

    return Collections.emptyList();
  }

  public String getConfigValue(String configName)
  {
    return getSqlSessionTemplate().selectOne("com.hdfs.visa.api.mapper.AgreementMapper.getConfigValue", configName);
  }

  public AgreementBuilder getAgreementBuilder(long agreementId) {
    AgreementBuilder agreementBuilder = getSqlSessionTemplate().selectOne("com.hdfs.visa.api.mapper.AgreementMapper.getAgreement", agreementId);

    return agreementBuilder;
  }

  public String getIsVisaEligible(long agreementId)
  {
    if (StringUtils.equalsIgnoreCase("YES", getSqlSessionTemplate().selectOne("com.hdfs.visa.api.mapper.AgreementMapper.getIsVisaEligible", agreementId)))
    {
      return "Y";
    }
    return "N";
  }

  public void setVisaProcessComplete(long agreementId)
  {
    getSqlSessionTemplate().update("com.hdfs.visa.api.mapper.AgreementMapper.setVisaProcessComplete", agreementId);
  }
}
