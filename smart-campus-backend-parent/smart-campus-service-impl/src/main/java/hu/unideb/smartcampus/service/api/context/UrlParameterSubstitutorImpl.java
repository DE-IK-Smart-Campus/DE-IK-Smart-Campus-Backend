package hu.unideb.smartcampus.service.api.context;

import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.UrlParameterSubstitutor;

@Service
public class UrlParameterSubstitutorImpl implements UrlParameterSubstitutor{

  @Override
  public String substitute(String url, Map<String, String> content) {
    StrSubstitutor substitutor = new StrSubstitutor(content,"{","}");
    return substitutor.replace(url);
  }
  
  
}
