/*package it.beije.oort.sb.checklist;

import java.util.*;

public class CheckList {
	
	
	public static boolean checkList(List list1, List list2) { 
		boolean equals=false;
		List temp1 = new ArrayList(list1); 
		List temp2 = new ArrayList(list2);
		if(list1==null||list2==null||list1.size()!=list2.size()) equals=false;
		else if(list1.isEmpty() && list2.isEmpty()) equals=true;
		else {
		for(int i=0; i< list1.size();i++) {
			//if(temp1.get(i)!=temp2.get(i)) { //sarebbe un altro metodo per farlo funzionare
			if(temp1.remove(0)!=temp2.remove(0)) {
				equals=false;
				break;	
			} else equals = true; 
		}
}		return equals;

} 

	public static void main(String[] args) {
		List<String> a = new ArrayList<>();
		List<String> b = new ArrayList<>();
		List<String> c = new ArrayList<>();
		List<String> d = new ArrayList<>();
		a.add("prova");
		b.add("prova");
		a.add("uguale");
		b.add("uguale");
		System.out.println(checkList(a,b));
		System.out.println(a.equals(b));
		System.out.println(checkList(c,d));
		System.out.println(c.equals(d));

	}

}*/
