package hu.unideb.smartcampus.service.api;

import java.util.Map;

public interface UrlParameterSubstitutor {

  String substitute(String url, Map<String,String> content);
  
}
