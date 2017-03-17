package hu.unideb.smartcampus.service.api.calendar.parser;

import net.fortuna.ical4j.model.component.VEvent;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentTime;

/**
 * Calendar appointment details parser.
 */
public interface CalendarAppointmentDetailsParser {

  AppointmentTime parseAppointmentDetails(VEvent vEvent);
}
