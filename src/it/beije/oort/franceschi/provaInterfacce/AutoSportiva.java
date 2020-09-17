package it.beije.oort.franceschi.provaInterfacce;

public class AutoSportiva extends Automobile implements ICabrio{

	@Override
	public void avviaMotore() {
		System.out.println("BROOM");
	}

	@Override
	public void fermaMotore() {
		System.out.println("Il motore si ferma con un rumore da auto sportiva");
	}

	@Override
	String getModel() {
		return "Un modello generico di auto sportiva";
	}

	@Override
	void frena() {
		System.out.println("L'auto frena in modo sportivo");
	}

	@Override
	String getVeicolo() {
		return "Auto Sportiva";
	}
}
