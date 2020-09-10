package it.beije.oort.franceschi.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateAndTime {

	public static void main(String[] args) {
		LocalDateTime localDT;
		LocalDate localD;
		LocalTime localT;
		LocalDateTime now;
		Period period;
		
		now = LocalDateTime.now();
		
		localD = LocalDate.of(2020, 9, 01);
		localT = LocalTime.of(14, 30);
		localDT = LocalDateTime.of(localD, localT);
		
		period = Period.of(0,1,0);
		
		
		System.out.println("Ora: " + now);
		System.out.println("Local Date Time: " + localDT);
		System.out.println("Un mese dopo: " + localDT.plusMonths(1));
		System.out.println("Torniamo indietro: " + localDT.minusMonths(1));
		System.out.println("Un mese dopo, ma con Period: " + localDT.plus(period));
		System.out.println("Un mese dietro, ma con Period: " + localDT.minus(period));
		System.out.println();
		System.out.println("Data formattata: " + localDT.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		
		DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("dd-MM-YYYY, HH:mm");
		System.out.println("Data formattata in modo decente: " + localDT.format(myFormatter));
	}

}
