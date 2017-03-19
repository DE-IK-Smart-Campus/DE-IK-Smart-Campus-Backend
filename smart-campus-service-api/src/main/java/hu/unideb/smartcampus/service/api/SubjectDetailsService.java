package hu.unideb.smartcampus.service.api;

import java.util.List;
import hu.unideb.smartcampus.service.api.calendar.domain.subject.SubjectDetails;

public interface SubjectDetailsService {

  void save(SubjectDetails subjectDetails);

  void save(List<SubjectDetails> subjectDetailsList);
}
