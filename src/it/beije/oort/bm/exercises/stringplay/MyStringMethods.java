package it.beije.oort.bm.exercises.stringplay;

public class MyStringMethods {

	public static void main(String[] args) throws Exception {
		System.out.println("Checking myContains:");
		System.out.println();
		String s1 = "i nani";
		String s2 = "na";
		String s3 = "w";
		System.out.println("\"" + s1 + "\" contiene \"" + s2 + "\"?");
		System.out.println(myContains(s1, s2));
		System.out.println("\"" + s1 + "\" contiene \"" + s3 + "\"?");
		System.out.println(myContains(s1, s3));
		System.out.println();
		System.out.println("Checking myContains (StringBuilder):");
		System.out.println();
		StringBuilder sb1 = new StringBuilder("i nani");
		System.out.println("\"" + sb1 + "\" contiene \"" + s2 + "\"?");
		System.out.println(myContains(sb1, s2));
		System.out.println("\"" + sb1 + "\" contiene \"" + s3 + "\"?");
		System.out.println(myContains(sb1, s3));
		System.out.println();
		System.out.println("Checking myStartsWith:");
		System.out.println();
		s2 = "i na";
		System.out.println("\"" + s1 + "\" comincia con \"" + s2 + "\"?");
		System.out.println(myStartsWith(s1, s2));
		System.out.println("\"" + s1 + "\" comincia con \"" + s3 + "\"?");
		System.out.println(myStartsWith(s1, s3));
		System.out.println();
		System.out.println("Checking myStartsWith (StringBuilder):");
		System.out.println();
		System.out.println("\"" + sb1 + "\" comincia con \"" + s2 + "\"?");
		System.out.println(myStartsWith(sb1, s2));
		System.out.println("\"" + sb1 + "\" comincia con \"" + s3 + "\"?");
		System.out.println(myStartsWith(sb1, s3));
		System.out.println();
		System.out.println("Checking myEndsWith:");
		System.out.println();
		s2 = "ni";
		System.out.println("\"" + s1 + "\" finisce con \"" + s2 + "\"?");
		System.out.println(myEndsWith(s1, s2));
		System.out.println("\"" + s1 + "\" finisce con \"" + s3 + "\"?");
		System.out.println(myEndsWith(s1, s3));
		System.out.println();
		System.out.println("Checking myEndsWith (StringBuilder):");
		System.out.println();
		System.out.println("\"" + sb1 + "\" finisce con \"" + s2 + "\"?");
		System.out.println(myEndsWith(sb1, s2));
		System.out.println("\"" + sb1 + "\" finisce con \"" + s3 + "\"?");
		System.out.println(myEndsWith(sb1, s3));
		System.out.println();
		System.out.println("Checking my trim:");
		System.out.println();
		s1 = "   i nani  ";
		s2 = "  \t   INANI \n \r  ";
		System.out.println("\"" + s1 + "\" trimmed to \"" + myTrim(s1) + "\"");
		System.out.println("\"" + s2 + "\" trimmed to \"" + myTrim(s2) + "\"");
		System.out.println();
		System.out.println("Checking my trim (StringBuilder):");
		System.out.println();
		sb1 = new StringBuilder(s1);
		System.out.println("\"" + sb1 + "\" trimmed to \"" + myTrim(s1) + "\"");
		sb1 = new StringBuilder(s2);
		System.out.println("\"" + sb1 + "\" trimmed to \"" + myTrim(s2) + "\"");
		System.out.println();
		System.out.println("Checking myEquals:");
		System.out.println();
		s1 = new String("i nani");
		s2 = new String(s1);
		s3 = "coboldi";
		System.out.println("\"" + s1 + "\" è uguale a \"" + s2 + "\"?");
		System.out.println(myEquals(s1, s2));
		System.out.println("\"" + s1 + "\" è uguale a \"" + s3 + "\"?");
		System.out.println(myEquals(s1, s3));
		System.out.println();
		System.out.println("Checking myEquals (StringBuilder):");
		System.out.println();
		sb1 = new StringBuilder(s1);
		StringBuilder sb2, sb3;
		sb2 = new StringBuilder(s2);
		sb3 = new StringBuilder(s3);
		System.out.println("\"" + sb1 + "\" è uguale a \"" + s2 + "\"?");
		System.out.println(myEquals(sb1, sb2));
		System.out.println("\"" + sb1 + "\" è uguale a \"" + s3 + "\"?");
		System.out.println(myEquals(sb1, sb3));
		System.out.println();
		System.out.println("Checking myReplace:");
		System.out.println();
		System.out.println("Rimpiazzo n con c in \"" + s1 + "\" ottenendo \"" + myReplace(s1, 'n', 'c') + "\"");
		System.out.println();
		System.out.println("Rimpiazzo w con f in \"" + s1 + "\" ottenendo \"" + myReplace(s1, 'w', 'f') + "\"(no change)");
		System.out.println();
		System.out.println("Checking myReplace (StringBuilder):");
		System.out.println();
		System.out.println("Rimpiazzo n con c in \"" + sb1 + "\" ottenendo \"" + myReplace(sb1, 'n', 'c') + "\"");
		myReplace(sb1, 'c', 'n');
		System.out.println();
		System.out.println("Rimpiazzo w con f in \"" + sb1 + "\" ottenendo \"" + myReplace(sb1, 'w', 'f') + "\"(no change)");
		System.out.println();
		System.out.println("Checking mySubstring:");
		System.out.println();
		System.out.println("Taglio \"" + s1 + "\" in 2,4 ottenendo \"" + mySubstring(s1, 2, 4) + "\"");
		System.out.println();
		System.out.println("Checking mySubstring (StringBuilder):");
		System.out.println();
		System.out.println("Taglio \"" + sb1 + "\" in 2,4 ottenendo \"" + mySubstring(sb1, 2, 4) + "\"");
		System.out.println();
	}
	
	static boolean myContains(String s, String str) {
		return s.indexOf(str) != -1;
	}
	
	static boolean myContains(StringBuilder s, String str) {
		return s.indexOf(str) != -1; 
	}

	static boolean myStartsWith(String s, String str) throws Exception {
		if(str.length()>s.length()) {
			return false;
		}else {
			return myEquals(str, mySubstring(s, 0, str.length()));
		}
	}
	
	static boolean myStartsWith(StringBuilder s, String str) throws Exception {
		return myStartsWith(s.toString(),str);
	}

	static boolean myEndsWith(String s, String str) throws Exception {
		if(str.length()>s.length()) {
			return false;
		}else {
			return myEquals(str, mySubstring(s, s.length()-str.length(), s.length()));
		}
	}
	
	static boolean myEndsWith(StringBuilder s, String str) throws Exception {
		return myEndsWith(s.toString(),str); 
	}
	
	static String myTrim(String s) {
		StringBuilder sb = new StringBuilder(s);
		myTrim(sb);
		return sb.toString();
	}

	static StringBuilder myTrim(StringBuilder s) {
		while(s.charAt(0) == ' ' || s.charAt(0) == '\n' || s.charAt(0) == '\t' || s.charAt(0) == '\r') {
			s.deleteCharAt(0);
		}
		while(s.charAt(s.length() - 1) == ' ' || s.charAt(s.length() - 1) == '\n' || s.charAt(s.length() - 1) == '\t' || s.charAt(s.length() - 1) == '\r') {
			s.deleteCharAt(s.length() - 1);
		}
		return s;
	}
	
	static boolean myEquals(String s1, String s2) {
		boolean ret = true;
		if(s1.length()==s2.length()) {
			for(int i = 0; i<s1.length();i++) {
				if(s1.charAt(i)!=s2.charAt(i)) {
					ret = false;
					break;
				}
			}
		}else {
			ret = false;
		}
		return ret;
	}
	
	static boolean myEquals(StringBuilder sb1, StringBuilder sb2) {
		 return myEquals(sb1.toString(), sb2.toString());
	}
	
	static String myReplace(String s, char oldChar, char newChar) {
		StringBuilder builder = new StringBuilder(s);
		myReplace(builder, oldChar, newChar);
		return builder.toString();
	}
	
	static StringBuilder myReplace(StringBuilder s, char oldChar, char newChar) {
		for(int i = 0; i<s.length();i++) {
			if(s.charAt(i) == oldChar) {
				s.deleteCharAt(i);
				s.insert(i, newChar);
			}
		}
		return s;
	}
	
	static String mySubstring(String s, int start, int end) throws Exception {
		String ret = "";
		if(end < start || start >= s.length() || end > s.length()) throw new Exception();
		for(int i = 0; i<end-start; i++) {
			ret += s.charAt(i+start);
		}
		return ret;
	}
	
	static String mySubstring(StringBuilder s, int start, int end) throws Exception {
		return mySubstring(s.toString(), start, end);
	}

}
