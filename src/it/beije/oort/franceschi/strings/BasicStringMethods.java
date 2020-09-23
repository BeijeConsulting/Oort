package it.beije.oort.franceschi.strings;

public class BasicStringMethods {

    public static void main(String[] args) {
        String s1 = "Ciao";
        String s2 = "Una string più lunga";

        // length
        System.out.printf("Lunghezza normale: %d.\tLunghezza brutta: %d. Lunghezza corretta: %d.\n",
                myLength(s2),
                myUglyLength(s2),
                s2.length());

        // charAt
        System.out.printf("CharAt: %s.\tCharAt corretto: %s\n",
                myCharAt(s2, 5),
                s2.charAt(5));

        // indexOf
        System.out.printf("IndexOf: %d.\tIndexOf corretto: %d.\n",
                myIndexOf(s2, 'g'),
                s2.indexOf('g'));
        System.out.printf("IndexOf: %d.\tIndexOf corretto: %d.\n",
                myIndexOf(s2, "ung"),
                s2.indexOf("ung"));

        // toLowerCase
        System.out.printf("toLowerCase: %s.\t toLowerCase corretto: %s.\n",
                myToLowerCase(s2),
                s2.toLowerCase());

        // toUpperCase
        System.out.printf("toUpperCase: %s.\t toUpperCase corretto: %s.\n",
                myToUpperCase(s2),
                s2.toUpperCase());

        // equals
        System.out.printf("equals: %s.\t equals corretto: %s.\n",
                myEquals(s2, s1),
                s2.equals(s1));
    }

    // length
    public static int myUglyLength(String s) {
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

    public static int myLength(String s) {
        int i = 0;
        for (@SuppressWarnings("unused") char c : s.toCharArray()) {
            i++;
        }
        return i;
    }
    // end length

    // charAt
    public static char myCharAt(String s, int i) {
        return s.toCharArray()[i];
    }
    // end charAt

    // indexOf
    public static int myIndexOf(String s, char c) {
        for (int i = 0; i < myLength(s); i++) {
            if (c == s.toCharArray()[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int myIndexOf(String s, String str) {
        int x = s.indexOf(str);
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
    public static String myToLowerCase(String s) {
        String low = "";
        for (int i = 0; i < myLength(s); i++) {
            if (myCharAt(s, i) == 32) {
                low += " ";
            } else if (97 <= myCharAt(s, i) && myCharAt(s, i) <= 122) {
                low += myCharAt(s, i);
            } else {
                low += (char) (myCharAt(s, i) + 32);
            }
        }
        return low;
    }
    //end toLowerCase

    // toUpperCase()
    public static String myToUpperCase(String s) {
        String up = "";
        for (int i = 0; i < myLength(s); i++) {
            if (myCharAt(s, i) == 32) {
                up += " ";
            } else if (97 <= myCharAt(s, i) && myCharAt(s, i) <= 122) {
                up += (char) (myCharAt(s, i) - 32);
            } else {
                up += myCharAt(s, i);
            }
        }
        return up;
    }
    // end toUpperCase

    // equals()
    public static boolean myEquals(String s, String s2) {
        if (myLength(s) != myLength(s2)) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.toCharArray()[i] != s2.toCharArray()[i]) return false;
        }
        return true;
    }

    // equalsIgnoreCase()
    public static boolean myEqualsIgnoreCase(String string1, String string2) {
        String s = string1.toLowerCase(); //uso questo perchè il mio è rotto con le lettere accentate
        String s2 = string2.toLowerCase();
        if (myLength(s) != myLength(s2)) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.toCharArray()[i] != s2.toCharArray()[i]) return false;
        }
        return true;
    }
}
