package it.beije.oort.madonia.dispositivi.elettronici;

public abstract class DispositiviElettronici {
	
	private String nome = "";
	private double consumo = 0;
	private double costo = 0;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getConsumo() {
		return consumo;
	}
	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	public abstract String getTipologiaDispositivoElettronico();
}
