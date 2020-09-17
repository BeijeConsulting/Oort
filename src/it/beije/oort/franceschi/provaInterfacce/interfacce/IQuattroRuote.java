package it.beije.oort.franceschi.provaInterfacce.interfacce;

public interface IQuattroRuote {
	static int getNumeroRuote() {
		return 4;
	}

	default boolean isStabile() {
		return true;
	}
}
