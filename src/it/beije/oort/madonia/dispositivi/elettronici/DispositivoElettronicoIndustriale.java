package it.beije.oort.madonia.dispositivi.elettronici;

public abstract class DispositivoElettronicoIndustriale extends DispositiviElettronici {
	private double peso = 0;
	private double altezza = 0;
	
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltezza() {
		return altezza;
	}
	public void setAltezza(double altezza) {
		this.altezza = altezza;
	}

	public final String getTipologiaDispositivoElettronico() {
		return "Industriale";
	}
	
	public abstract boolean haBisognoManutenzione();
	public abstract String getTipologiaDispositivoElettronicoIndustriale();
}
