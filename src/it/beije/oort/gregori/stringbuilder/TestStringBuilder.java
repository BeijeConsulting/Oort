package it.beije.oort.gregori.stringbuilder;

/**
 * 
 * boolean myContains(StringBuilder s, String r)
 * boolean myStartsWith(StringBuilder s, String r)
 * boolean myEndsWith(StringBuilder s, String r)
 * boolean myEquals(StringBuilder s, StringBuilder r)
 * StringBuilder myTrim() //trim che modifica il contenuto (almeno sullo spazio)
 * StringBuilder myReplace(StringBuilder s, char oldChar, char newChar) ù
 * StringBuilder mySubstring(StringBuilder s, int start, int end)
 * 
 * @author Luca Gregori
 *
 */
public class TestStringBuilder {

	/**
	 * Controlla se uno StringBuilder s contiene una String r
	 * 
	 * @param s
	 * @param r
	 * @return true se r è contenuto in s, false altrimenti
	 */
	public boolean myContains(StringBuilder s, String r) {
		boolean returnValue;
		
		if(s.indexOf(r) != -1) {
			returnValue = true;
		}
		else {
			returnValue = false;
		}
		
		return returnValue;
	}
	
	
	/**
	 * Controlla se uno StringBuilder s inizia con la String r
	 * @param s
	 * @param r
	 * @return true se s inizia con r, false altrimenti
	 */
	public boolean myStartsWith(StringBuilder s, String r) {
		boolean returnValue;
		
		if(s.indexOf(r) == 0) {
			returnValue = true;
		}
		else {
			returnValue = false;
		}
		
		return returnValue;
	}
	
	
	/**
	 * Controlla se uno StringBuilder s termina con una String r
	 * @param s
	 * @param r
	 * @return true se s termina con r, false altrimenti
	 */
	public boolean myEndsWith(StringBuilder s, String r) {
		boolean returnValue = false;
		
		if(myContains(s, r)) {
			if(r.length() > 1) {
				for(int i = 0; i < r.length(); i++) {
					if(s.charAt(s.indexOf(r) + i) == r.charAt(i)) {
						returnValue = true;
					}
					else {
						return false;
					}
				}
			}
			else {
				if(s.charAt(s.length() - 1) == r.charAt(0)) {
					returnValue = true;
				}
			}
		}
		
		return returnValue;
	}
	
	
	/**
	 * Controlla se due StringBuilder "s" e "r" sono equivalenti
	 * @param s
	 * @param r
	 * @return true se "s" e "r" sono equivalenti, false altrimenti
	 */
	public boolean myEquals(StringBuilder s, StringBuilder r) {
		boolean returnValue = false;
		
		if(r.length() == s.length()) {
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == r.charAt(i)) {
					returnValue = true;
				}
				else {
					return false;
				}
			}
		}
		
		return returnValue;
	}
	
	/**
	 * Rimuove gli spazi all'inizio e alla fine dello StringBuilder s
	 * @param s
	 * @return uno StringBuilder senza spazi all'inizio e alla fine
	 */
	public StringBuilder myTrim(StringBuilder s) {
		StringBuilder returnValue = new StringBuilder();
		
		if(s.charAt(0) == ' ') {
			for(int i = 1; i < s.length(); i++) {
				if(i == (s.length() - 1) && s.charAt(i) == ' ') {
					// do nothing
				}
				else {
					returnValue.append(s.charAt(i));
				}
			}
		}
		
		return returnValue;
	}
	
	/**
	 * Sostituisce in uno StringBuilder "s", il char "oldChar" con un char "newChar"
	 * @param s
	 * @param oldChar
	 * @param newChar
	 * @return uno StringBuilder con "oldChar" e "newChar" sostituiti.
	 */
	public StringBuilder myReplace(StringBuilder s, char oldChar, char newChar) {
		StringBuilder returnValue = new StringBuilder();
		String str = "";
		str += oldChar;
		int charPos = s.indexOf(str);
		
		if (charPos != -1) {
			for(int i = 0; i < s.length(); i++) {
				if(i == charPos) {
					returnValue.append(newChar);
				}
				else {
					returnValue.append(s.charAt(i));
				}
			}
		}
		else {
			returnValue = s;
		}
		
		return s;
	}
	
	/**
	 * Restituisce uno StringBuilder che parte dall'indice "start" fino all'indice "end" escluso.
	 * @param s
	 * @param start
	 * @param end
	 * @return uno StringBuilder che parte dall'indice "start" fino all'indice "end" escluso.
	 */
	public StringBuilder mySubstring(StringBuilder s, int start, int end) {
		StringBuilder returnValue = new StringBuilder();
		
		if(start > end) {
			returnValue = s;
		}
		else {
			for(int i = start; i < end; i++) {
				returnValue.append(s.charAt(i));
			}
		}
		
		return returnValue;
	}

	
	public static void main(String[] args) {

		TestStringBuilder ts = new TestStringBuilder();
		StringBuilder s = new StringBuilder("def");
		String r = "def";
		System.out.println(ts.myContains(s, r));
		System.out.println(ts.myEndsWith(s, r));
		StringBuilder s1 = new StringBuilder("def");
		System.out.println(ts.myEquals(s, s1));
		StringBuilder str = new StringBuilder(" ciao ");
		System.out.println(ts.myTrim(str));
		System.out.println(ts.myReplace(s, 'e', 'k'));
		System.out.println(s);
		System.out.println(ts.mySubstring(s, 1, 2));
	}
}