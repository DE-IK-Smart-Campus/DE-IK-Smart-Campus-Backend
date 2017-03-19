package hu.unideb.smartcampus.service.api.converter.toentity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.service.api.domain.ConsultingDate;

@Component
public class ConsultingDateToConsultingDateEntityConverter implements Converter<ConsultingDate, ConsultingDateEntity> {

  @Override
  public ConsultingDateEntity convert(final ConsultingDate consultingDate) {
    return consultingDate == null ? null : convertConsultingDateToConsultingDateEntity(consultingDate);
  }

  private ConsultingDateEntity convertConsultingDateToConsultingDateEntity(final ConsultingDate consultingDate) {
    return ConsultingDateEntity.builder()
        .date(consultingDate.getDate())
        .build();
  }
}
