package hu.unideb.smartcampus.service.api.converter.toentity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.FromToDateEmbeddedEntity;
import hu.unideb.smartcampus.service.api.domain.FromToDate;

/**
 * From to date converter.
 *
 */
@Component
public class FromToDateToFromToDateEntityConverter
    implements Converter<FromToDate, FromToDateEmbeddedEntity> {

  @Override
  public FromToDateEmbeddedEntity convert(FromToDate source) {
    return source == null ? null
        : FromToDateEmbeddedEntity.builder().fromDate(source.getFromDate())
            .toDate(source.getToDate()).build();
  }


}
