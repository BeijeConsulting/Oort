package it.beije.oort.bm.exercises.rubrica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtilities {
	@SafeVarargs
	public static <T extends Comparable<? super T>> List<T> mergeLists(List<T>... lists){
		List<T> ret = new ArrayList<T>();
		for(List<T> l : lists) {
			ret.addAll(l);
		}
		Collections.sort(ret);
		return ret;
	}
}
