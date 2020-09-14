package it.beije.oort.file.sala.third;

import java.util.*;

public class CheckList {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<>();
		ArrayList<String> b = new ArrayList<>();
		ArrayList<String> c = new ArrayList<>();
		ArrayList<String> d = new ArrayList<>();
		c.add("StringPoolTest");
		c.add("StringPoolTest2");
		d.add("StringPoolTest");
		d.add("StringPoolTest2");
		System.out.println(checkList(a, b));
		System.out.println(a.equals(b));
		System.out.println(checkList(a, c));
		System.out.println(a.equals(c));
		System.out.println(checkList(c, d));
		System.out.println(c.equals(d));
	}
	
	public static boolean checkList(List list1, List list2) {
		boolean equals = false;
		if(list1==null || list2==null || list1.size()!=list2.size()) equals = false;
		else if(list1.isEmpty() && list2.isEmpty()) equals = true;
		else {
			for(int i=0; i<list1.size();i++) {
				if(list1.get(i)==list2.get(i)) equals=true;
				else {
					equals=false;
					break;
				}
			}
		}
		return equals;
	}

}
