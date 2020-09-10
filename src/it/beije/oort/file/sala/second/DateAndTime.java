package it.beije.oort.file.sala.second;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateAndTime {

	public static void main(String[] args) {
		LocalDateTime datetime = LocalDateTime.now();
		LocalDate date = LocalDate.of(1997, Month.MAY, 25);
		LocalTime time = LocalTime.now();
		LocalDateTime datetime2 = LocalDateTime.of(date, time);
		System.out.println(date);
		System.out.println(time);
		System.out.println(datetime2);
		System.out.println(datetime2.plusDays(15));
		System.out.println(datetime2.plusDays(-15));
		Period p = Period.ofDays(15);
		System.out.println(datetime.plus(p));
		System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		System.out.println(formatter.format(datetime));
	}

}
