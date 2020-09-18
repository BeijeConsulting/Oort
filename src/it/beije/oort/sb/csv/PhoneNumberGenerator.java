package it.beije.oort.sb.csv;

import java.util.List;
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


	public void numeroTel(List<String> numeri, List<String> numeroTele){
		PhoneNumberGenerator sette = new PhoneNumberGenerator();
		ListRandomSelector numero = new ListRandomSelector(numeri);
		numeroTele.add(numero.getNext() + sette.nextNumber());
	}
	
	public void numeroTel39(List<String> numeri, List<String> numeroTele){
		PhoneNumberGenerator sette = new PhoneNumberGenerator();
		ListRandomSelector numero = new ListRandomSelector(numeri);
		numeroTele.add("+39" + numero.getNext() + sette.nextNumber());
	}
}	
	