package hu.unideb.smartcampus.service.api.calendar.parser;

import net.fortuna.ical4j.model.component.VEvent;

import hu.unideb.smartcampus.service.api.calendar.domain.subject.AppointmentDetails;

/**
 * Calendar appointment details parser.
 */
public interface CalendarAppointmentDetailsParser {

  AppointmentDetails parseAppointmentDetails(VEvent vEvent);
}
