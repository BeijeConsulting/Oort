package it.beije.oort.file.sala.second;

public class Clazz {

	public static void main(String[] args) {
		Clazz c = new Clazz();
		StringBuilder a = new StringBuilder("aaaamareaaaaaaaa");
		StringBuilder b = new StringBuilder("mare");
		StringBuilder x = new StringBuilder("aaa");
		StringBuilder y = new StringBuilder("pepe");
		StringBuilder z = new StringBuilder("		  		cc c c c c	 	 	");
		//myContains
		System.out.println(c.myContains("aaaaaaaaamareaaaaaaaa", "mare"));
		System.out.println(c.myContains(a, b));
		//myStartsWith
		System.out.println(c.myStartsWith("mareaaaaaaaa", "mare"));
		System.out.println(c.myStartsWith(a, b));
		//myEndsWith
		System.out.println(c.myEndsWith("pepe", "pepe"));
		System.out.println(c.myEndsWith(x, y));
		//myTrim
		System.out.println("		     aaaa  			 ");
		System.out.println(c.myTrim("		     aaaa  			 "));
		System.out.println(c.myTrim(z));
		//myEquals
		System.out.println(c.myEquals("AAAAAAA", "A"));
		System.out.println(c.myEquals(y, y));
		//myReplace
		System.out.println("Replacing As in -bananananananana- with Es:");
		System.out.println(c.myReplace("bananananananana", 'a', 'e'));
		System.out.println(c.myReplace(a, 'a', 'e'));
		//mySubstring
		System.out.println(c.mySubstring("abcdefghijklmnopqrstuvwxyz",10, 20));

		
		System.out.println("Testing out some methods:::");
		System.out.println(a.toString().substring(3, 8).toUpperCase());
		System.out.println("PrOvA123CiAoCIaO".toLowerCase().equalsIgnoreCase("prova123ciaociao"));
		String test = "abab123cdcd";
		System.out.println(test.startsWith("abab")&&test.contains("123")&&test.endsWith("cdcd"));
		System.out.println("aaaaaaciaoaaaaaaa".replace('a', ' ').trim());
		StringBuilder test2 = new StringBuilder("9876431");
		System.out.println(test2.reverse().insert(4, "5").insert(1, "2").append("abcd eccetera..."));
		

	}
	
	public boolean myContains(String source, String pattern) {
		int skip = 0;
		boolean found = false;
		while(skip<(source.length()-pattern.length())) {
			for(int i=0;i<pattern.length();i++) {
				if(source.charAt(skip+i)==pattern.charAt(i)) {
					found = true;
				} else {
					skip++;
					found = false;
					break;
				}
			}
			if(found) break;
		}
		return found;
	}
	
	public boolean myContains(StringBuilder source, StringBuilder pattern) {

		int skip = 0;
		boolean found = false;
		while(skip<(source.length()-pattern.length())) {
			for(int i=0;i<pattern.length();i++) {
				if(source.charAt(skip+i)==pattern.charAt(i)) {
					found = true;
				} else {
					skip++;
					found = false;
					break;
				}
			}
			if(found) break;
		}
		return found;
	}

	public boolean myStartsWith(String source, String pattern) {
		boolean start = false;
		int pointer = 0;
		while(pointer<pattern.length()) {
			if(source.charAt(pointer)==pattern.charAt(pointer)) {
				start = true;
				pointer++;
			} else {
				start = false;
				break;
			}
		}
		return start;
	}
	
	public boolean myStartsWith(StringBuilder source, StringBuilder pattern) {

		boolean inizia = false;
		int pointer = 0;
		while(pointer<pattern.length()) {
			if(source.charAt(pointer)==pattern.charAt(pointer)) {
				inizia = true;
				pointer++;
			} else {
				inizia = false;
				break;
			}
		}
		return inizia;
	}

	public boolean myEndsWith(String source, String pattern) {
		int offset = source.length()-pattern.length();
		int pointer=0;
		boolean ends = false;
		if(source.length()<pattern.length()) ends = false;
		else {
			while(pointer<pattern.length()) {
				if(source.charAt(pointer+offset)==pattern.charAt(pointer)) {
					ends=true;
					pointer++;
				} else {
					ends = false;
					break;
				}
			}
		}
		return ends;
	}

	public boolean myEndsWith(StringBuilder source, StringBuilder pattern) {
		int offset = source.length()-pattern.length();
		int pointer=0;
		boolean ends = false;
		if(source.length()<pattern.length()) ends = false;
		else {
			while(pointer<pattern.length()) {
				if(source.charAt(pointer+offset)==pattern.charAt(pointer)) {
					ends=true;
					pointer++;
				} else {
					ends = false;
					break;
				}
			}
		}
		return ends;
	}

	public String myTrim(String s) {
		StringBuilder temp = new StringBuilder(s);
		int pointerLeft=0;
		int pointerRight=(s.length()-1);
		while(temp.charAt(pointerLeft)=='\n' ||
				temp.charAt(pointerLeft)=='\t'||
				temp.charAt(pointerLeft)==' ') pointerLeft++;
		while(temp.charAt(pointerRight)=='\n' ||
				temp.charAt(pointerRight)=='\t' ||
				temp.charAt(pointerRight)==' ' ) pointerRight--;
		return temp.delete(pointerRight+1,temp.length()).delete(0, pointerLeft).toString();
	}
	
	public StringBuilder myTrim(StringBuilder s) {
		int pointerLeft=0;
		int pointerRight=(s.length()-1);
		while(s.charAt(pointerLeft)=='\n' ||
				s.charAt(pointerLeft)=='\t'||
				s.charAt(pointerLeft)==' ') pointerLeft++;
		while(s.charAt(pointerRight)=='\n' ||
				s.charAt(pointerRight)=='\t' ||
				s.charAt(pointerRight)==' ' ) pointerRight--;
		return s.delete(pointerRight+1,s.length()).delete(0, pointerLeft);
	}

	public boolean myEquals(String s, String str) {
		boolean equals = false;
		if(s.length()==str.length()) {
			for(int i=0;i<s.length();i++) {
				if(s.charAt(i)==str.charAt(i)) equals = true;
				else {
					equals = false;
					break;
				}
			}
		}
		return equals;
	}
	
	public boolean myEquals(StringBuilder sb, StringBuilder sbr) {
		boolean equals = false;
		if(sb.length()==sbr.length()) {
			for(int i=0;i<sb.length();i++) {
				if(sb.charAt(i)==sbr.charAt(i)) equals = true;
				else {
					equals = false;
					break;
				}
			}
		}
		return equals;
	}

	public String myReplace(String s, char oldChar, char newChar) {
		StringBuilder temp = new StringBuilder(s);
		String oldStr = Character.toString(oldChar);
		String newStr = Character.toString(newChar);
		while(temp.indexOf(oldStr)!=-1) {
			int i = temp.indexOf(oldStr);
			temp.deleteCharAt(i);
			temp.insert(i, newStr);
		}
		return temp.toString();
	}
	
	public StringBuilder myReplace(StringBuilder s, char oldChar, char newChar) {
		String oldStr = Character.toString(oldChar);
		String newStr = Character.toString(newChar);
		while(s.indexOf(oldStr)!=-1) {
			int i = s.indexOf(oldStr);
			s.deleteCharAt(i);
			s.insert(i, newStr);
		}
		return s;
	}

	public String mySubstring(String s, int start, int end) {
		String returner = "";
		for(int i = start; i < end; i++) {
			returner += s.charAt(i);
		}
		return returner;
	}
}
