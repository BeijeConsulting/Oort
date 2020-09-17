package it.beije.oort.sba.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneNumberGenerator {
	private List<String> generated = new ArrayList<>();
	private ListRandomSelector selector;
	
	public PhoneNumberGenerator(List<String> prefixes) {
		selector = new ListRandomSelector(prefixes);
	}
	
	public String nextNumber() {
		Random r = new Random();
		StringBuilder b = new StringBuilder();
		int rand = r.nextInt(8)+1;
		if(rand < 2) {
			return "";
		}else if(rand < 3) {
			return selectRandomFromGenerated();
		}else if(rand < 5){
			b.append("+39");
		}
		b.append(selector.getNext());
		for(int i = 0; i<7; i++) {
			b.append("" + r.nextInt(10));
		}
		String ret = b.toString();
		generated.add(ret);
		return ret;
	}

	private String selectRandomFromGenerated() {
		if(generated.isEmpty()) return "";
		else {
			ListRandomSelector lrs = new ListRandomSelector(generated);
			return lrs.getNext();
		}
	}
}
