package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;
import hu.unideb.smartcampus.domain.ConsultingDate;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;

public class ConsultingDateListConverter implements Converter<List<ConsultingDateIqElement>, List<ConsultingDate>> {

  private ConsultingDateConverter consultingDateConverter;

  public ConsultingDateListConverter() {
    this.consultingDateConverter = new ConsultingDateConverter();
  }

  @Override
  public List<ConsultingDate> convert(List<ConsultingDateIqElement> consultingDateIqElements) {
    return consultingDateIqElements.stream().map(
        consultingDateIqElement -> consultingDateConverter.convert(consultingDateIqElement)
    ).collect(Collectors.toList());
  }
}
