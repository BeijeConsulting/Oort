package it.beije.oort.sba.rubrica.datarec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.beije.oort.sba.rubrica.Contatto;

public class ContactSet implements Set<Contatto> {

	private List<Contatto> elements;
	
	public ContactSet() {
		elements = new ArrayList<>();
	}
	
	public ContactSet(int capacity) {
		elements = new ArrayList<>(capacity);
	}
	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return elements.contains(o); //dovrebbe essere affidabile
	}

	@Override
	public Iterator<Contatto> iterator() {
		return elements.iterator();
	}

	@Override
	public Object[] toArray() {
		return elements.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return elements.toArray(a);
	}

	@Override
	public boolean add(Contatto e) {
		if(!this.contains(e)) {
			elements.add(e);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return elements.remove(o); //dovrebbe essere affidabile
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return elements.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Contatto> c) {
		return elements.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return elements.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return elements.removeAll(c);
	}

	@Override
	public void clear() {
		elements.clear();
		
	}

}
