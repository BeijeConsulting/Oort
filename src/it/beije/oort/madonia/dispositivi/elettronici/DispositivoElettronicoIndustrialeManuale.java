package it.beije.oort.madonia.dispositivi.elettronici;

public abstract class DispositivoElettronicoIndustrialeManuale extends DispositivoElettronicoIndustriale {
	private int numeroLavoratori = 0;
	
	public int getNumeroLavoratori() {
		return numeroLavoratori;
	}
	public void setNumeroLavoratori(int numeroLavoratori) {
		this.numeroLavoratori = numeroLavoratori;
	}
	
	public double getPeso() {
		return super.getPeso() + numeroLavoratori * 75.0;
	}

	public String getTipologiaDispositivoElettronicoIndustriale() {
		return "Manuale";
	}
}
