package it.beije.oort.franceschi.strings;

public class BasicStringBuilderMethods {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder("Ciao");
        StringBuilder s2 = new StringBuilder("Una string più lunga");


//		// length
//		System.out.printf("Lunghezza normale: %d.\tLunghezza brutta: %d. Lunghezza corretta: %d.\n", 
//				myLength(s2), 
//				myUglyLength(s2), 
//				s2.length());
//
//		// charAt
//		System.out.printf("CharAt: %s.\tCharAt corretto: %s\n", 
//				myCharAt(s2, 5), 
//				s2.charAt(5));
//		
//		// indexOf
//		System.out.printf("IndexOf: %d.\tIndexOf corretto: %d.\n", 
//				myIndexOf(s2, 'g'), 
//				s2.toString().indexOf('g'));
//		System.out.printf("IndexOf: %d.\tIndexOf corretto: %d.\n", 
//				myIndexOf(s2, new StringBuilder("ung")), 
//				s2.indexOf("ung"));
//		
//		// toLowerCase
//		System.out.printf("toLowerCase: %s.\t toLowerCase corretto: %s.\n", 
//				myToLowerCase(s2), 
//				s2.toString().toLowerCase());
//		
//		// toUpperCase
//		System.out.printf("toUpperCase: %s.\t toUpperCase corretto: %s.\n", 
//				myToUpperCase(s2), 
//				s2.toString().toUpperCase());
//		
//		// equals
//		System.out.printf("equals: %s.\t equals corretto: %s.\n", 
//				myEquals(s2, s1), 
//				s2.equals(s1));
//		
//		//append()
//		System.out.printf("append: %s.\t append corretto: %s.\n", 
//				myAppend(s2, s1), 
//				s2.append(s1));
//		
//		//insert()
//		System.out.printf("insert: %s.\t insert corretto: %s.\n", 
//				myInsert(s2, s1, 5), 
//				s2.insert(5, s1));
//		
//		// delete()
//		System.out.printf("delete: %s.\t delete corretto: %s.\n", 
//				myDelete(s2, 4, 10), 
//				s2.delete(4, 10));
//		
//		// deleteCharAt();
//		System.out.printf("deleteCharAt: %s.\t deleteCharAt corretto: %s.\n", 
//				myDeleteCharAt(s2, 4), 
//				s2.deleteCharAt(4));
//		
//		// reverse()
//		System.out.printf("reverse: %s.\t reverse corretto: %s.\n", 
//				myReverse(s2), 
//				s2.reverse());
    }

    public static StringBuilder myDeleteCharAt(StringBuilder sb, int index) {
        String returnThis = AdvStringMethods.mySubstring(sb, 0, index - 1).toString();
        returnThis += AdvStringMethods.mySubstring(sb, index + 1, myLength(sb) - 1).toString();
        return new StringBuilder(returnThis);
    }

    public static StringBuilder myReverse(StringBuilder s) {
        StringBuilder str = new StringBuilder();
        for (int i = myLength(s) - 1; i >= 0; i--) {
            str.append(s.charAt(i));
        }
        return new StringBuilder(str.toString());
    }

    public static StringBuilder myDelete(StringBuilder s, int start, int end) {
        String returnThis = AdvStringMethods.mySubstring(s, 0, start - 1).toString();
        returnThis += AdvStringMethods.mySubstring(s, end, myLength(s) - 1).toString();
        return new StringBuilder(returnThis);
    }

    public static StringBuilder myInsert(StringBuilder s1, StringBuilder s2, int pos) {
        String s = AdvStringMethods.mySubstring(s1, 0, pos - 1).toString();
        s += s2.toString();
        s += AdvStringMethods.mySubstring(s1, pos, myLength(s1) - 1);
        return new StringBuilder(s);
    }

    public static StringBuilder myAppend(StringBuilder s1, StringBuilder s2) {
        String s = s1.toString();
        s += s2.toString();
        return new StringBuilder(s);
    }

    // length
    public static int myUglyLength(StringBuilder s) {
        int i = 0;
        try {
            while (s.charAt(i) != '\u0000') {
                i++;
            }
        } catch (Exception e) {
            return i;
        }
        return i;
    }

    @SuppressWarnings("unused")
    public static int myLength(StringBuilder s) {
        int i = 0;
        for (char c : s.toString().toCharArray()) {
            i++;
        }
        return i;
    }
    // end length

    // charAt
    public static char myCharAt(StringBuilder s, int i) {
        return s.toString().toCharArray()[i];
    }
    // end charAt

    // indexOf
    public static int myIndexOf(StringBuilder s, char c) {
        for (int i = 0; i < myLength(s); i++) {
            if (c == s.toString().toCharArray()[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int myIndexOf(StringBuilder s, StringBuilder str) {
        int x = s.toString().indexOf(str.toString());
        if (x != -1 && (s.length() - x) - str.length() >= 0) {
            for (int i = x, k = 0; k < str.length(); k++, i++) {
                if (s.charAt(i) != str.charAt(k)) {
                    return -1;
                }
            }
            return x;
        } else {
            return -1;
        }
    }
    // end indexOf

    // toLowerCase()
    public static StringBuilder myToLowerCase(StringBuilder s) {
        StringBuilder low = new StringBuilder();
        for (int i = 0; i < myLength(s); i++) {
            if (myCharAt(s, i) == 32) {
                low.append(" ");
            } else if (97 <= myCharAt(s, i) && myCharAt(s, i) <= 122) {
                low.append(myCharAt(s, i));
            } else {
                low.append((char) (myCharAt(s, i) + 32));
            }
        }
        return low;
    }
    //end toLowerCase

    // toUpperCase()
    public static StringBuilder myToUpperCase(StringBuilder s) {
        StringBuilder up = new StringBuilder();
        for (int i = 0; i < myLength(s); i++) {
            if (myCharAt(s, i) == 32) {
                up.append(" ");
            } else if (97 <= myCharAt(s, i) && myCharAt(s, i) <= 122) {
                up.append((char) (myCharAt(s, i) - 32));
            } else {
                up.append(myCharAt(s, i));
            }
        }
        return up;
    }
    // end toUpperCase

    // equals()
    public static boolean myEquals(StringBuilder s, StringBuilder s2) {
        if (myLength(s) != myLength(s2)) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.toString().toCharArray()[i] != s2.toString().toCharArray()[i]) return false;
        }
        return true;
    }

    // equalsIgnoreCase()
    public static boolean myEqualsIgnoreCase(StringBuilder string1, StringBuilder string2) {
        StringBuilder s = new StringBuilder(string1.toString().toLowerCase()); //uso questo perchè il mio è rotto con le lettere accentate
        StringBuilder s2 = new StringBuilder(string2.toString().toLowerCase());
        if (myLength(s) != myLength(s2)) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.toString().toCharArray()[i] != s2.toString().toCharArray()[i]) return false;
        }
        return true;
    }
}
