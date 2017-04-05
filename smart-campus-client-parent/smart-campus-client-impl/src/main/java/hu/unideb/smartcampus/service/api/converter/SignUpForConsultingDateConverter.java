package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import hu.unideb.smartcampus.shared.iq.request.SignUpForConsultingDateIqRequest;

public class SignUpForConsultingDateConverter implements Converter<SignUpForConsultingDateIqRequest, String> {
  @Override
  public String convert(SignUpForConsultingDateIqRequest signUpForConsultingDateIqRequest) {
    return signUpForConsultingDateIqRequest.getResponseMessage();
  }
}
