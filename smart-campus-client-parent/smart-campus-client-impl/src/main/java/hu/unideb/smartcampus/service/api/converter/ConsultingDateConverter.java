package hu.unideb.smartcampus.service.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import hu.unideb.smartcampus.domain.ConsultingDate;
import hu.unideb.smartcampus.shared.iq.request.element.ConsultingDateIqElement;
import hu.unideb.smartcampus.shared.iq.request.element.FromToDateIqElement;

public class ConsultingDateConverter implements Converter<ConsultingDateIqElement, ConsultingDate> {

  private Converter<FromToDateIqElement, Timestamp[]> fromToDateConverter;

  public ConsultingDateConverter() {
    this.fromToDateConverter = new FromToDateConverter();
  }

  @Override
  public ConsultingDate convert(ConsultingDateIqElement consultingDateIqElement) {
    Timestamp[] timestamps = fromToDateConverter.convert(consultingDateIqElement.getFromToDates());
    return new ConsultingDate(consultingDateIqElement.getConsultingDateId(), timestamps[0], timestamps[1], consultingDateIqElement.getReservedSum());
  }
}
