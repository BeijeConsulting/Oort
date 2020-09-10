package kirolosmater;
import java.util.*;

public class myCheckList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] nomi1 = {"kirolos mater", "simone maisto", "daniel bassanelli"};
		String[] nomi2 = {"kirolos mater", "simone maisto", "daniel bassanelli"};
		List<String> list1 = Arrays.asList(nomi1);
		List<String> list2 = Arrays.asList(nomi2);
		System.out.println(checkList(list1, list2));
	}

	public static boolean checkList(List list1, List list2) {
		// deve tornare esattamente il risultato di list1.equals(list2)
		boolean risultato = false;
		if(list1.size() != list2.size()) {
			risultato = false;
		}
		else {		
			for(int i = 0; i < list1.size(); i++) {
				if(list1.get(i) == list2.get(i)) {
					risultato = true;
				}
				else {
					risultato = false;
					break;
				}
			}
		}
		return risultato;
	}
}
