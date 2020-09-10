package it.beije.oort.bm.exercises.lists;

import java.util.List;

public class ListChecker {
	public static boolean checkList(List<? extends Object> list1, List<? extends Object> list2) {
		boolean ret = true;
		if(list1 == null || list2 == null || list1.size() != list2.size()) {
			ret = false;
		} else {
			for(int i = 0; i<list1.size(); i++) {
				if(!list1.get(i).equals(list2.get(i))) {
					ret = false;
					break;
				}
			}
		}
		return ret;
	}
	
	public static void main(String[] args) { //testing purpose only
		List<String> list1 = new java.util.ArrayList<String>();
		List<Integer> list2 = new java.util.ArrayList<Integer>();
		String a = "a", b = "b", c = "c";
		list1.add(a);
		list1.add(b);
		list1.add(c);
		list2.add(1);
		list2.add(2);
		list2.add(3);
		
		System.out.println(checkList(list1, list2));
	}

}
