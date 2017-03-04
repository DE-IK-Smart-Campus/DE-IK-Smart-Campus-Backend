package hu.unideb.smartcampus.service.api;

import java.net.MalformedURLException;

import hu.unideb.smartcampus.service.api.exception.InputParseException;

public interface CalendarService {

  void downloadCalendar(String url) throws MalformedURLException, InputParseException;

}
