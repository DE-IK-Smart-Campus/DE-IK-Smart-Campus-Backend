package hu.unideb.smartcampus.domain;

import hu.unideb.smartcampus.domain.calendar.CalendarSubject;

public class SubjectModel {
  private CalendarSubject subject;

  public CalendarSubject getSubject() {
    return subject;
  }

  public void setSubject(CalendarSubject subject) {
    this.subject = subject;
  }
}
