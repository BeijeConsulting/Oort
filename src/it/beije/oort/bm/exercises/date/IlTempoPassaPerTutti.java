package it.beije.oort.bm.exercises.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class IlTempoPassaPerTutti {

	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		System.out.println(date);
		LocalTime time = LocalTime.now();
		System.out.println(time);
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd MMM yyyy");
		System.out.println("Today is " + f.format(date));
		System.out.println("Yesterday was " + f.format(date.minusDays(1)));
		System.out.println("Tomorrow will be " + f.format(date.plusDays(1)));
		f = DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy");
		System.out.println("One-hundred hours ago, it was " + f.format(dt.minusHours(100)));
		String data = "3/8/2020";
		f = DateTimeFormatter.ofPattern("d/M/yyyy");
		date = LocalDate.parse(data, f);
		System.out.println(f.format(date));

	}

}
