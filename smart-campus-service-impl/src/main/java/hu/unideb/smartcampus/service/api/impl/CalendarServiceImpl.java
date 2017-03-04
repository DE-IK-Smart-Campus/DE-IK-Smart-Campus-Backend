package hu.unideb.smartcampus.service.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import hu.unideb.smartcampus.service.api.CalendarService;
import hu.unideb.smartcampus.service.api.exception.InputParseException;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

public class CalendarServiceImpl implements CalendarService {

	@Override
	public void downloadCalendar(String url) throws MalformedURLException, InputParseException {
		// ide jön majd a lekérés
		InputStream stream = new InputStream() {

			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};

		CalendarBuilder calendarBuilder = new CalendarBuilder();
		try {
			Calendar calendar = calendarBuilder.build(stream);
			// for(Property prop :calendar.getComponents())
			// ComponentList does note implements Iterable
		} catch (IOException | ParserException e) {
			throw new InputParseException(e);
		}

	}

}
