package hu.unideb.smartcampus.shared.iq.request.element;

import java.util.Collections;
import java.util.List;

import lombok.val;

public class BaseIqElement {

  protected String openTag(String tagName) {
    return "<" + tagName + ">";
  }

  protected String closeTag(String tagName) {
    return "</" + tagName + ">";
  }

  protected String tag(String tagName, Object value) {
    return openTag(tagName) + value + closeTag(tagName);
  }

  protected String tagIfNotNull(String tagName, Object value){
    if (value!=null){
      return tag(tagName,value);
    }
    return "";
  }
  
  protected Object emptyIfNull(Object o) {
    if (o == null) {
      return "";
    }
    return o;
  }

  protected <T> List<T> emptyIfNull(List<T> list) {
    if (list == null) {
      return Collections.<T>emptyList();
    }
    return list;
  }

}
