package hu.unideb.smartcampus.service.api.requestprocess;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.unideb.smartcampus.persistence.entity.ConsultingDateEntity;
import hu.unideb.smartcampus.persistence.entity.FromToDateEmbeddedEntity;
import hu.unideb.smartcampus.persistence.repository.InstructorRepository;
import hu.unideb.smartcampus.service.api.MessageProcessingClass;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.ConsultingHourWrapper;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.FromToDate;
import hu.unideb.smartcampus.service.api.domain.response.wrapper.InstructorConsultingHoursWrapper;
import hu.unideb.smartcampus.shared.requestmessages.BaseRequestType;
import hu.unideb.smartcampus.shared.requestmessages.RetrieveInstructorConsultingHours;
import hu.unideb.smartcampus.shared.requestmessages.constants.RequestMessagesConstants;

/**
 * Retrieve instructor consulting dates service.
 */
@Component
public class RetrieveInsturctorConsultingHoursServiceImpl
    implements MessageProcessingClass<InstructorConsultingHoursWrapper> {

  @Autowired
  private InstructorRepository instructorRepository;

  /**
   * {@inheritDoc}.
   */
  @Override
  public InstructorConsultingHoursWrapper getResponse(Object object) {
    RetrieveInstructorConsultingHours msg = (RetrieveInstructorConsultingHours) object;
    Long instructorId = msg.getInstructorId();

    Calendar from = Calendar.getInstance();
    Calendar to = Calendar.getInstance();
    to.add(Calendar.WEEK_OF_YEAR, 1);

    Set<ConsultingDateEntity> consultingDates = instructorRepository
        .getInstructorConsultingDatesByIdAndGivenDate(instructorId, from.getTime(), to.getTime());

    List<ConsultingHourWrapper> consultingHours = extractConsultingHour(consultingDates);

    InstructorConsultingHoursWrapper result =
        InstructorConsultingHoursWrapper.builder().consultingHours(consultingHours)
            .messageType(RequestMessagesConstants.RETRIEVE_CONSULTING_HOURS_RESPONSE).build();

    return result;
  }


  private List<ConsultingHourWrapper> extractConsultingHour(
      Set<ConsultingDateEntity> consultingDates) {
    return consultingDates.stream().map(this::consultingDateToWrapper).collect(Collectors.toList());
  }

  private ConsultingHourWrapper consultingDateToWrapper(ConsultingDateEntity entity) {
    return ConsultingHourWrapper.builder().consultingHourId(entity.getId())
        .fromToDates(convertToWrapperFromToDate(entity.getFromToDate()))
        .reservedSum(entity.getSum()).build();
  }

  private FromToDate convertToWrapperFromToDate(FromToDateEmbeddedEntity fromToDate) {
    return FromToDate.builder().from(fromToDate.getFromDate().getTime())
        .to(fromToDate.getToDate().getTime()).build();
  }


  /**
   * {@inheritDoc}.
   */
  @Override
  public Class<? extends BaseRequestType> getSupportedClass() {
    return RetrieveInstructorConsultingHours.class;
  }

}
