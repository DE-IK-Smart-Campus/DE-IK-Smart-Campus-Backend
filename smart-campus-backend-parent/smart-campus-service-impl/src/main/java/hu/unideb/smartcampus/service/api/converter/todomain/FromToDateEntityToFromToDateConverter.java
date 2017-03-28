package hu.unideb.smartcampus.service.api.converter.todomain;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.FromToDateEmbeddedEntity;
import hu.unideb.smartcampus.service.api.domain.FromToDate;

@Component
public class FromToDateEntityToFromToDateConverter implements Converter<FromToDateEmbeddedEntity, FromToDate> {

  @Override
  public FromToDate convert(final FromToDateEmbeddedEntity fromToDateEmbeddedEntity) {
    return fromToDateEmbeddedEntity == null ? null
        : FromToDate.builder()
            .fromDate(fromToDateEmbeddedEntity.getFromDate())
            .toDate(fromToDateEmbeddedEntity.getToDate())
            .build();
  }
}
