package it.beije.oort.gregori.string;

/**
 * length(), indexOf(), charAt()
 * 
 * boolean myContains(String s, String r)
 * boolean myStartsWith(String s, String r)
 * boolean myEndsWith(String s, String r)
 * boolean myEquals(String s, String r)
 * String myTrim() // trim tradizionale almeno sullo spazio
 * String myReplace(String s, char oldChar, char newChar) 
 * String mySubstring(String s, int start, int end)
 * 
 * @author Luca Gregori
 *
 */
public class TestStringhe {
	
	/**
	 * Controlla se una String s contiene una String r
	 * 
	 * @param s
	 * @param r
	 * @return true se r è contenuto in s, false altrimenti
	 */
	public boolean myContains(String s, String r) {
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
	 * Controlla se una String s inizia con la String r
	 * @param s
	 * @param r
	 * @return true se s inizia con r, false altrimenti
	 */
	public boolean myStartsWith(String s, String r) {
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
	 * Controlla se una String s termina con una String r
	 * @param s
	 * @param r
	 * @return true se s termina con r, false altrimenti
	 */
	public boolean myEndsWith(String s, String r) {
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
	 * Controlla se due String "s" e "r" sono equivalenti
	 * @param s
	 * @param r
	 * @return true se "s" e "r" sono equivalenti, false altrimenti
	 */
	public boolean myEquals(String s, String r) {
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
	 * Rimuove gli spazi all'inizio e alla fine della String s
	 * @param s
	 * @return una String senza spazi all'inizio e alla fine
	 */
	public String myTrim(String s) {
		String returnValue = "";
		
		if(s.charAt(0) == ' ') {
			for(int i = 1; i < s.length(); i++) {
				if(i == (s.length() - 1) && s.charAt(i) == ' ') {
					// do nothing
				}
				else {
					returnValue += s.charAt(i);
				}
			}
		}
		
		return returnValue;
	}
	
	/**
	 * Sostituisce in una String "s", il char "oldChar" con un char "newChar"
	 * @param s
	 * @param oldChar
	 * @param newChar
	 * @return una String con "oldChar" e "newChar" sostituiti.
	 */
	public String myReplace(String s, char oldChar, char newChar) {
		String returnValue = "";
		int charPos = s.indexOf(oldChar);
		
		if (charPos != -1) {
			for(int i = 0; i < s.length(); i++) {
				if(i == charPos) {
					returnValue += newChar;
				}
				else {
					returnValue += s.charAt(i);
				}
			}
		}
		else {
			returnValue = s;
		}
		
		return returnValue;
	}
	
	/**
	 * Restituisce una String che parte dall'indice "start" fino all'indice "end" escluso.
	 * @param s
	 * @param start
	 * @param end
	 * @return una String che parte dall'indice "start" fino all'indice "end" escluso.
	 */
	public String mySubstring(String s, int start, int end) {
		String returnValue = "";
		
		if(start > end) {
			returnValue = s;
		}
		else {
			for(int i = start; i < end; i++) {
				returnValue += s.charAt(i);
			}
		}
		
		return returnValue;
	}

	
	public static void main(String[] args) {

		TestStringhe ts = new TestStringhe();
		String s = "def";
		String r = "def";
		System.out.println(ts.myContains(s, r));
		System.out.println(ts.myEndsWith(s, r));
		System.out.println(ts.myEquals(s, r));
		String str = " ciao ";
		System.out.println(ts.myTrim(str));
		System.out.println(ts.myReplace(s, 'e', 'k'));
		System.out.println(ts.mySubstring(s, 1, 2));
	}

}
