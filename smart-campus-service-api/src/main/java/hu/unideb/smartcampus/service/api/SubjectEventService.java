package hu.unideb.smartcampus.service.api;

import java.util.List;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectEvent;


public interface SubjectEventService {

  List<SubjectEvent> getAllSubjectEventByUserId(Long userId);

  List<SubjectEvent> getAllSubjectEventBySubjectDetails(List<SubjectDetails> subjectDetailsList);

  void save(SubjectEvent subjectEvent);

  void save(List<SubjectEvent> subjectEvents);
}
