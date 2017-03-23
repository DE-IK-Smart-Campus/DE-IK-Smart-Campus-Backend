package hu.unideb.smartcampus.service.api.converter.toentity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.service.api.domain.ConsultingDate;

/**
 * Converter.
 */
@Component
public class ConsultingDateToConsultingDateEntityConverter
    implements Converter<ConsultingDate, ConsultingDateEntity> {

  @Autowired
  private FromToDateToFromToDateEntityConverter fromToDateConverter;

  @Override
  public ConsultingDateEntity convert(final ConsultingDate consultingDate) {
    return consultingDate == null ? null
        : convertConsultingDateToConsultingDateEntity(consultingDate);
  }

  private ConsultingDateEntity convertConsultingDateToConsultingDateEntity(
      final ConsultingDate consultingDate) {
    return ConsultingDateEntity.builder()
        .fromToDate(fromToDateConverter.convert(consultingDate.getFromToDate()))
        .sum(consultingDate.getSum()).build();
  }
}
