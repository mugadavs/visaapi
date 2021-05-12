package com.hdfs.visa.api.resulthandler;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.HashMap;
import java.util.Map;

public class XrefResultHandler implements ResultHandler {
  private Map<String, String> xrefMap = new HashMap<String, String>();

  public Map<String, String> getXrefMap() {
    return xrefMap;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void handleResult(ResultContext resultContext) {
    Map<String,Object> resultMap = (Map<String,Object>)resultContext.getResultObject();
    xrefMap.put((String)getFromMap(resultMap, "type_code"),
        (String)getFromMap(resultMap, "value"));
  }

  private Object getFromMap(Map<String, Object> map, String key) {
    if (map.containsKey(key.toLowerCase())) {
      return map.get(key.toLowerCase());
    } else {
      return map.get(key.toUpperCase());
    }
  }
}
