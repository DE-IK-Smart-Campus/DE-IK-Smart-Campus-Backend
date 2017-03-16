package hu.unideb.smartcampus.service.api.converter.toentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.service.api.domain.ConsultingDate;

@Component
public class ConsultingDateToConsultingDateEntityConverter
    implements Converter<ConsultingDate, ConsultingDateEntity> {

  @Autowired
  private FromToDateToFromToDateEntityConverter fromToDateConverter;

  @Override
  public ConsultingDateEntity convert(ConsultingDate source) {
    return source == null ? null
        : ConsultingDateEntity.builder().date(source.getDateInString())
            .fromToDate(fromToDateConverter.convert(source.getFromToDate())).sum(source.getSum())
            .build();
  }


}
