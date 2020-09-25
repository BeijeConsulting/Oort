package it.beije.oort.franceschi.strings;

public class AdvStringMethods {

    public static void main(String[] args) {
        String firstString = "Ciao", secondString = "No", longString = "    Ciao questa è una stringa lunga     ";

        StringBuilder firstSb = new StringBuilder("Ciao");
        new StringBuilder("No");
        StringBuilder thirdSb = new StringBuilder("Ciao"), longSb = new StringBuilder("    Ciao questa è una stringa lunga     ");

        boolean showAllOutput = true;

        if (showAllOutput) {
            System.out.println("Contains:");
            System.out.println(myContains(longString, "una"));
            System.out.println(myContains(longSb, new StringBuilder("Paolo")));
            System.out.println(myShortContains(longString, "una"));

            System.out.println();

            System.out.println("StartWith:");
            System.out.println(myStartWith(longString, "Ciao"));
            System.out.println(myStartWith(longSb, new StringBuilder("una")));

            System.out.println();

            System.out.println("MyEndsWith:");
            System.out.println(myEndsWith(longString, "lunga"));
            System.out.println(myEndsWith(longSb, new StringBuilder("lungo")));

            System.out.println();

            System.out.println("Trim:");
            System.out.println(myTrimAll(longString));
            System.out.println(myTrim(longString));
            System.out.println(myTrim(longSb));

            System.out.println();

            System.out.println("Equals:");
            System.out.println(myEqualsString(firstString, secondString));
            System.out.println(myEqualsStringBuilder(firstSb, thirdSb));

            System.out.println();

            System.out.println("Replace:");
            System.out.println(myReplace(longString, 'a', 'B'));
            System.out.println(myTrim(myReplace(longSb, 'a', 'B')));

            System.out.println();

            System.out.println("Substring:");
            System.out.println(mySubstring(longString, 10, 20));
            System.out.println(mySubstring(longSb, 10, 20));
        }
    }

    // short MyContains
    public static boolean myShortContains(String s, String str) {
        return s.indexOf(str) != -1;
    }

    // boolean myContains(String* s, String str)
    public static boolean myContains(String s, String str) {
        int x = s.indexOf(str);
        if (x != -1 && (s.length() - x) - str.length() >= 0) {
            for (int i = x, k = 0; k < str.length(); k++, i++) {
                if (s.charAt(i) != str.charAt(k)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // boolean myContains(StringBuilder s, StringBuilder str)
    public static boolean myContains(StringBuilder s, StringBuilder str) {
        int x = s.indexOf(str.toString());
        if (x != -1 && (s.length() - x) - str.length() >= 0) {
            for (int i = x, k = 0; k < str.length(); k++, i++) {
                if (s.charAt(i) != str.charAt(k)) {
                    System.out.println(s.charAt(i) + str.charAt(k));
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    // boolean myStartsWith(String s, String str)
    public static boolean myStartWith(String s, String str) {
        if (s.indexOf(str) != 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (s.charAt(i) != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // boolean myStartsWith(StringBuilder s, StringBuilder str)
    public static boolean myStartWith(StringBuilder s, StringBuilder str) {
        if (s.indexOf(str.toString()) != 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (s.charAt(i) != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // boolean myEndsWith(String* s, String str)
    public static boolean myEndsWith(String s, String str) {
        if (s.charAt(s.length() - str.length()) == str.charAt(0)) {
            for (int i = (s.length() - str.length()), k = 0; i < s.length(); i++, k++) {
                if (s.charAt(i) != str.charAt(k)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean myEndsWith(StringBuilder s, StringBuilder str) {
        if (s.charAt(s.length() - str.length()) == str.charAt(0)) {
            for (int i = (s.length() - str.length()), k = 0; i < s.length(); i++, k++) {
                if (s.charAt(i) != str.charAt(k)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static String myTrimAll(String s) {
        StringBuilder newStr = new StringBuilder();
        for (int x = 0; x < s.length(); x++) {
            if (s.charAt(x) != ' ') {
                newStr.append(s.charAt(x));
            }
        }

        return newStr.toString();
    }

    public static String myTrim(String s) {
        StringBuilder newStr = new StringBuilder();
        int init = 0, end = s.length() - 1;
        while (s.charAt(init) == ' ') {
            init++;
        }
        while (s.charAt(end) == ' ') {
            end--;
        }
        for (int x = init; x <= end; x++) {
            newStr.append(s.charAt(x));
        }
        return newStr.toString();
    }

    public static StringBuilder myTrim(StringBuilder s) {
        StringBuilder newStr = new StringBuilder();
        int init = 0, end = s.length() - 1;
        while (s.charAt(init) == ' ') {
            init++;
        }
        while (s.charAt(end) == ' ') {
            end--;
        }
        for (int x = init; x <= end; x++) {
            newStr.append(s.charAt(x));
        }
        return newStr;
    }

    // boolean myEquals(String s, String str)
    public static boolean myEqualsString(String s, String equal) {
        if (s.length() != equal.length()) {
            return false;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != equal.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    // boolean myEquals(StringBuilder s, StringBuilder str)
    public static boolean myEqualsStringBuilder(StringBuilder s, StringBuilder equal) {
        if (s.length() != equal.length()) {
            return false;
        } else {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != equal.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String myReplace(String s, char oldChar, char newChar) {
        StringBuilder newStr = new StringBuilder();
        for (int x = 0; x < s.length(); x++) {
            if (s.charAt(x) == oldChar) {
                newStr.append(newChar);
            } else {
                newStr.append(s.charAt(x));
            }
        }
        return newStr.toString();
    }

    public static StringBuilder myReplace(StringBuilder s, char oldChar, char newChar) {
        StringBuilder newStr = new StringBuilder();
        for (int x = 0; x < s.length(); x++) {
            if (s.charAt(x) == oldChar) {
                newStr.append(newChar);
            } else {
                newStr.append(s.charAt(x));
            }
        }
        return newStr;
    }

    public static String mySubstring(String s, int start, int end) {
        StringBuilder newStr = new StringBuilder();
        while (start <= end) {
            newStr.append(s.charAt(start++));
        }
        return newStr.toString();
    }

    public static StringBuilder mySubstring(StringBuilder s, int start, int end) {
        StringBuilder newStr = new StringBuilder();
        while (start <= end) {
            newStr.append(s.charAt(start++));
        }
        return newStr;
    }
}
