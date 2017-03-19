package hu.unideb.smartcampus.service.api;

import java.util.List;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;


public interface SubjectEventService {

  void save(SubjectEvent subjectEvent);

  void save(List<SubjectEvent> subjectEvents);
}
