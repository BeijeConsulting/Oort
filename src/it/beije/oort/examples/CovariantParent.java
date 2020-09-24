package it.beije.oort.examples;

public class CovariantParent {
	
	public int getInt() {
		return 5;
	}

	public long getLong() {
		return getInt();
	}

	public Integer getInteger() {
		return 5;//new Integer(5);
	}

	public Long getLongObj() {
		return 5L; //new Long(5);
	}

	public Number getNumber() {
		return 5L;
	}

	public Object getObject() {
		return 5L;
	}

}
