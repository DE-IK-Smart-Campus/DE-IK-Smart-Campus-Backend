package hu.unideb.smartcampus.service.api;

import java.util.List;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;

public interface SubjectDetailsService {

  List<SubjectDetails> getAllSubjectDetailsByUserId(Long userId);

  void save(SubjectDetails subjectDetails);

  void save(List<SubjectDetails> subjectDetailsList);
}
