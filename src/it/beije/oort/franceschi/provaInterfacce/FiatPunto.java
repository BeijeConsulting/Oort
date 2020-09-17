package it.beije.oort.franceschi.provaInterfacce;

public class FiatPunto extends Automobile{
	
	@Override
	public void avviaMotore() {
		System.out.println("Brrooooom.");
	}

	@Override
	public void fermaMotore() {
		System.out.println("Suono di motore che si ferma.");
	}

	@Override
	void frena() {
		System.out.println("Skrrrr.");
	}

	@Override
	String getVeicolo() {
		return "Automobile";
	}

	@Override
	String getModel() {
		return "Fiat Punto";
	}
}
