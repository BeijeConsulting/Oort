package it.beije.oort.franceschi.checkthelist;

import java.util.List;

public class CheckList {
	public static boolean checkList(List<?> list1, List<?> list2) {
		int lSize = (list1.size() == list2.size()) ? list1.size() : -1;
		if (lSize < 0) return false;
		
		for (int i = 0; i < lSize; i++) {
			if (!list1.get(i).equals(list2.get(i))) return false;
		}
		return true;
	}

}
