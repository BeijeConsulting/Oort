package it.beije.oort.franceschi.provaInterfacce;

public abstract class Automobile extends Veicolo implements IMotore, IQuattroRuote, IBenzina{
	public static int getNumeroRuote() {return IQuattroRuote.getNumeroRuote();}
	//public boolean isStabile() {return IQuattroRuote.isStabile();}
	public String getTipoCarburante() {return IBenzina.tipoCarburante;}
	abstract String getModel();
}
