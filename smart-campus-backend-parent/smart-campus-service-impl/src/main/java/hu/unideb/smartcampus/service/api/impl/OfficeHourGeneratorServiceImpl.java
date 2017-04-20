package hu.unideb.smartcampus.service.api.impl;

import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hu.unideb.smartcampus.service.api.OfficeHourGeneratorService;
import hu.unideb.smartcampus.service.api.domain.ConsultingDate;
import hu.unideb.smartcampus.service.api.domain.FromToDate;
import hu.unideb.smartcampus.shared.officehour.OfficeHour;
import hu.unideb.smartcampus.shared.officehour.OfficeHourIntervall;

/**
 * Office hour generator service impl.
 *
 */
@Service
public class OfficeHourGeneratorServiceImpl implements OfficeHourGeneratorService {

  private static final Integer HUNGARIAN_PLUS_HOURS = 2;

  private static final char WHITESPACE = ' ';

  private static final char DATE_SEPARATOR = '-';

  private static final String TIME_SEPARATOR = ":";

  private static final Logger LOGGER =
      LoggerFactory.getLogger(OfficeHourGeneratorServiceImpl.class);

  /**
   * {@inheritDoc}.
   */
  @Override
  public List<ConsultingDate> generateOfficeHoursInIntervall(OfficeHourIntervall intervall,
      List<OfficeHour> officeHours) {
    LOGGER.debug("Generator service creating new consulting dates in intervall {} - {}",
        intervall.getFromDate(), intervall.getToDate());
    List<ConsultingDate> consultingDates = new ArrayList<>();
    LocalDate fromDate = intervall.getFromDate().toLocalDateTime().toLocalDate();
    LocalDate toDate = intervall.getToDate().toLocalDateTime().toLocalDate();
    while (toDate.isAfter(fromDate)) {
      consultingDates.addAll(generateNextOfficeHours(officeHours, fromDate));
      fromDate = fromDate.plusWeeks(1);
    }
    LOGGER.debug("{} consulting date(s) generated.", consultingDates.size());
    return consultingDates;
  }

  private List<ConsultingDate> generateNextOfficeHours(List<OfficeHour> officeHours,
      LocalDate fromDate) {
    List<ConsultingDate> consultingDates = new ArrayList<>();
    for (OfficeHour officeHour : officeHours) {
      FromToDate fromToDate = createFromToDateEntity(fromDate, officeHour);
      ConsultingDate entity = ConsultingDate.builder().sum(0).fromToDate(fromToDate)
          .date(buildDateString(officeHour)).build();
      consultingDates.add(entity);
    }
    return consultingDates;
  }

  private String buildDateString(OfficeHour officeHour) {
    StringBuilder builder = new StringBuilder();
    builder.append(officeHour.getDay().getDisplayName(TextStyle.FULL, Locale.US)).append(WHITESPACE)
        .append(officeHour.getFrom()).append(DATE_SEPARATOR).append(officeHour.getTo());
    return builder.toString();
  }

  private FromToDate createFromToDateEntity(LocalDate fromDate, OfficeHour officeHour) {
    LocalDateTime from = createLocalDate(fromDate, officeHour.getDay(), officeHour.getFrom());
    LocalDateTime to = createLocalDate(fromDate, officeHour.getDay(), officeHour.getTo());
    return FromToDate.builder().fromDate(from).toDate(to).build();
  }

  private LocalDateTime createLocalDate(LocalDate fromDate, DayOfWeek day, String timeInString) {
    LocalDate upcomingDay = fromDate.with(TemporalAdjusters.next(day));
    return LocalDateTime.of(fromDate.getYear(), fromDate.getMonth(), upcomingDay.getDayOfMonth(),
        getHour(timeInString), getMinutes(timeInString));
  }

  private int getHour(String from) {
    return Integer.valueOf(from.split(TIME_SEPARATOR)[0]);
  }

  private int getMinutes(String from) {
    return Integer.valueOf(from.split(TIME_SEPARATOR)[1]);
  }

  private Timestamp getTimestamp(LocalDateTime time) {
    return Timestamp.valueOf(time);
  }
}
