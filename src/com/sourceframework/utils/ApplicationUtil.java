package com.sourceframework.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sourceframework.domain.SortCollection;

public abstract class ApplicationUtil
{
  public static Map<String, String> getSort(String sort)
  {
    Gson gson = new Gson();
    Map<String, String> map = new HashMap<String, String>();
    if ((sort == null) || ("".equals(sort))) {
      map.put("direction", "");
      map.put("property", "");
    } else {
      List<?> sortData = (List<?>)gson.fromJson(sort, new TypeToken<Object>() {} .getType());
      map.put("direction", ((SortCollection)sortData.get(0)).getDirection());
      map.put("property", ((SortCollection)sortData.get(0)).getProperty());
    }
    return map;
  }
}