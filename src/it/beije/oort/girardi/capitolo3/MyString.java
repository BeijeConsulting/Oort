/* CONSEGNA:
 * boolean myContains(String* s, String str)  //String & StringBuilder (edited) 
 * boolean myStartsWith(String* s, String str) //String & StringBuilder (edited) 
 * boolean myEndsWith(String* s, String str) //String & StringBuilder (edited)
 * String myTrim(String s) //trim tradizionale almeno sullo spazio //String (edited)
 * StringBuilder myTrim(StringBuilder s) //trim che modifica il contenuto (almeno sullo spazio) 
 * boolean myEquals(String s, String str) //String & StringBuilder (edited) 
 * boolean myEquals(StringBuilder s, StringBuilder str) //String & StringBuilder (edited) 
 * String myReplace(String s, char oldChar, char newChar) //String (edited) 
 * String mySubstring(String* s, int start, int end) //String & StringBuilder
*/

package it.beije.oort.girardi.capitolo3;

public class MyString {

// ----------- String methods ---------------
	public static boolean myContains (String str, String s) {
		int num = str.indexOf(s);
		return (num == -1)? false : true;  // operatore ternario
	}
	
	public static boolean myStartsWith (String str, String s) {
		boolean result = false;
		for (int i=0; i < s.length(); i++) {
			char ch = str.charAt(i);
			char sh = s.charAt(i);
			if (ch == sh)
				result = true;
			else {
				result = false;
				break;
			}
		}
		return result ; 
	}
	
	
	public static boolean myEndsWith (String str, String s) {
		boolean result = false;
		for (int i = str.length(), j = s.length(); j > 0; i--, j--) {
			char ch = str.charAt(i-1);
			char sh = s.charAt(j-1);
			if (ch == sh)
				result = true;
			else {
				result = false;
				break;
			}
		}
		return result ; 
	}
	
	public static String myTrim(String str) {
		String result = ""; // stringa vuota
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			int charVal = Character.getNumericValue(ch);
			if (charVal != -1) // al -1 corrispondono ' ', '\n', '\t' (e credo anche altri)
				result += ch;
		}
		return result;
	}
	
	public static boolean myEquals(String str1, String str2) {
		if (str1.length() != str2.length())
			return false;
		else {
			for (int i = 0; i < str1.length(); i++) {
				if (str1.charAt(i) != str2.charAt(i))
					return false;
			}
			return true;
		}
	}
	
	public static String myReplace(String str, char oldChar, char newChar) {
		int lung = str.length();
		String result = "";
		for (int i = 0; i < lung; i++) {
			if (str.charAt(i) == oldChar) 
				result += newChar;
			else 
				result += str.charAt(i);
		}
		return result;
	}
	
	public static String mySubstring(String str, int start, int end) {
		// il carattere alla posizione "start" è compreso, "end" è escluso.
		String result = "";
		for (int i = start; i < end; i++) 
			result += str.charAt(i);
		return result;
	}
	
	
// --------- StringBuilder methods ----------

	 public static boolean myContains(StringBuilder strb, String sb) {
		 int num = strb.indexOf(sb);
			return (num == -1)? false : true;  // operatore ternario
	 }
	
	 public static boolean myStartsWith(StringBuilder strb, String sb) {
		 boolean result = false;
			for (int i=0; i < sb.length(); i++) {
				char ch = strb.charAt(i);
				char sh = sb.charAt(i);
				if (ch == sh)
					result = true;
				else {
					result = false;
					break;
				}
			}
			return result ; 
		}
	 

	public static boolean myEndsWith(StringBuilder strb, String sb) {
		boolean result = false;
		for (int i = strb.length(), j = sb.length(); j > 0; i--, j--) {
			char ch = strb.charAt(i-1);
			char sh = sb.charAt(j-1);
			if (ch == sh)
				result = true;
			else {
				result = false;
				break;
			}
		}
		return result ; 
	}
	
	public static StringBuilder myTrim(StringBuilder strb) {
		StringBuilder result = new StringBuilder(); // stringa vuota
		for (int i = 0; i < strb.length(); i++) {
			char ch = strb.charAt(i);
			int charVal = Character.getNumericValue(ch);
			if (charVal != -1) // al -1 corrispondono ' ', '\n', '\t' (e credo anche altri)
				result.append(ch);
		}
		return result;
		
	}
	public static boolean myEquals(StringBuilder sb1, StringBuilder sb2) {
		if (sb1.length() != sb2.length())
			return false;
		else {
			for (int i = 0; i < sb1.length(); i++) {
				if (sb1.charAt(i) != sb2.charAt(i))
					return false;
			}
			return true;
		}
	}

	public static StringBuilder mySubstring(StringBuilder sb, int start, int end) {
		// il carattere alla posizione "start" è compreso, "end" è escluso.
		StringBuilder result = new StringBuilder();
		for (int i = start; i < end; i++) 
			result.append(sb.charAt(i));
		return result;
	}
	
	
// --------------- main ---------------------
	public static void main (String[] args) {
		//   "\n" è lunga 1 e non due come si potrebbe pensare
//		String str = "   \n   A\tnim als";
//		String s = "s";
//		String str1 =new String("Carmela");
//		String str2 = "Carmela";
//		char ch1 = 'a';
//		char ch2 = 'u';
//		System.out.println(myContains(str,s));
	//	System.out.println(myStartsWith(str,s));
	//	System.out.println(myEndsWith(str,s));
	//	System.out.println(myTrim(str));
	//	System.out.println(myEquals(str1, str2));
	//	System.out.println(myReplace(str1, ch1, ch2));
	//	System.out.println(mySubstring(str1,5,7));
		
		StringBuilder strb = new StringBuilder("Carla & Luca");
		StringBuilder strb1 = new StringBuilder("   \n   A\tnim als");
		String sb = new String("la");
//		System.out.println(myContains(strb,sb));
//		System.out.println(myStartsWith(strb,sb));
//		System.out.println(myEndsWith(strb,sb));
		System.out.println(myTrim(strb1));
		System.out.println(myEquals(strb, strb));
		System.out.println(mySubstring(strb,1,4));
		
	
	}
	
}
