package it.beije.oort.sba.rubrica;

import java.util.Random;

public class PhoneNumberGenerator {
	
	public String nextNumber() {
		Random r = new Random();
		StringBuilder b = new StringBuilder();
		for(int i = 0; i<7; i++) {
			b.append("" + (Math.abs(r.nextInt()))%10);
		}
		return b.toString();
	}
}
