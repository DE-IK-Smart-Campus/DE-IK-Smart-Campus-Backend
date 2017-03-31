package hu.unideb.smartcampus.service.api.converter.todomain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.FromToDateEmbeddedEntity;
import hu.unideb.smartcampus.service.api.domain.ConsultingDate;
import hu.unideb.smartcampus.service.api.domain.FromToDate;

@Component
public class ConsultingDateEntityToConsultingDateConverter implements Converter<ConsultingDateEntity, ConsultingDate> {

  private final Converter<FromToDateEmbeddedEntity, FromToDate> fromToDateConverter;

  @Autowired
  public ConsultingDateEntityToConsultingDateConverter(final Converter<FromToDateEmbeddedEntity, FromToDate> fromToDateConverter) {
    this.fromToDateConverter = fromToDateConverter;
  }

  @Override
  public ConsultingDate convert(final ConsultingDateEntity consultingDateEntity) {
    return consultingDateEntity == null ? null
        : convertConsultingDateEntityToConsultingDate(consultingDateEntity);
  }

  private ConsultingDate convertConsultingDateEntityToConsultingDate(final ConsultingDateEntity consultingDateEntity) {
    return ConsultingDate.builder()
        .fromToDate(fromToDateConverter.convert(consultingDateEntity.getFromToDate()))
        .sum(consultingDateEntity.getSum())
        .build();
  }
}
